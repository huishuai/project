package com.dvn.miniboss.jc.ftp

import com.dvn.miniboss.acct.CmngUser
import com.miniboss.acct.bean.BsmpFtpFileBean
import com.miniboss.acct.utils.FileUtil
import com.miniboss.acct.utils.FtpClient
import com.miniboss.acct.utils.FtpOperate
import com.miniboss.acct.utils.JdbcUtils
import com.miniboss.bean.BasePage
import com.miniboss.sync.ftp.FtpBean
import com.miniboss.sync.ftp.FtpBeanUtil
import java.sql.Connection
import javax.sql.DataSource
import org.apache.log4j.Logger

class UserInfoForBMPTService {

    boolean transactional = false
    def String type
    def String code
    def FtpClient ftpClientBmpt;
    def DataSource dataSource;
    private final static Logger logger = Logger.getLogger(UserInfoForBMPTService.class)

    //设定分页查询数据时每页查询数据量
    public final static int ONE_PAGE_DATA_SIZE = 1000

    public UserInfoForBMPTService() {
        //todo：需要修改，控制生成文件名的机制
        this.type = ""
        this.code = "Iboss_Bmpt"
    }

    public void run() {
        BsmpFtpFileBean bean = buildFileBean(this.type, this.code, ftpClientBmpt.remoteFilePath, ftpClientBmpt.localFilePath);
        execute(bean);
    }

    /**
     *   重写方法，没有序号的方式，文件后缀为".txt"
     */
    protected BsmpFtpFileBean buildFileBean(String type, String code, String remotePath, String localPath) {
        BsmpFtpFileBean bean = new BsmpFtpFileBean(type, code, remotePath, localPath)
        bean.setIncrement(true)
        bean.setHasIndex(false)
        bean.setUserDefineSuffix(".txt")
        bean.load()
        return bean
    }

    void execute(BsmpFtpFileBean bean) {
        //BMPT用户同步   BOSS-->BMPT
        //采用分页方式获取数据，控制每次获取内容的数量，防止内存溢出
        //需要控制文件大小为2G
        logger.info("Begin-----WriteUserInfoForBMPTFile--------------:" + new Date());
        this.createCmngUserAllSyncFile(bean.localFileDir, bean.fileName)
        try {
            File localFile = new File(bean.localFilePath)
            if (localFile.exists() && localFile.isFile()) {
                FtpOperate client = this.ftpClientBmpt.createFtpClient()
                boolean uploadFlag = client.uploadFile(bean.remoteRootPath, bean.localFilePath)
                if (!uploadFlag) {
                    logger.error("Upload UserInfoForBMPT SyncFile Error! FileName:" + bean.localFilePath)
                }
            } else {
                logger.info("Local SyncFile NotFound! FileName:" + bean.localFilePath)
            }
        } catch (Exception ex) {
            ex.printStackTrace()
            logger.error("-----------Sync Error!")
        }
        logger.info("End-------WriteUserInfoForBMPTFile--------------:" + new Date());
    }

    /**
     * 全量用户信息同步，获取所有用户信息
     * @return
     * @throws Exception
     */
    public void createCmngUserAllSyncFile(String localPath, String fileName) throws Exception {

        //优先创建文件写头信息
        String IncrementStr = "CUSTOMNO|CUSTOMNM|SEX|OPENDATE|USERSTATUS|MAINTELE|ADDRESS|ROOMSUM|ROOMAREA|BUILDTYPE|BUILDNATURE|USENATURE|CARS|FAMILYMBERS|STBID|ICID|SMCODE|STBSTATUS\n";
        FileUtil fileUtil = new FileUtil()
        fileUtil.writeSyncDataToFileFoot(localPath, fileName, IncrementStr)

        //翻页处理大批量同步数据
        StringBuffer sql = new StringBuffer("select a.customno,d.customnm,d.sex,to_char(a.opendate,'yyyy-MM-dd') opendate,");
        sql.append(" a.status userstatus,d.maintele,d.address,");
        sql.append(" b.roomsum,b.roomarea,b.buildtype,b.buildnature,b.usenature,b.car,");
        sql.append(" b.familymber,c.stbid,c.icid,c.smcode,c.status stbstatus ");
        sql.append(" from cmng_custom       a,");
        sql.append(" cmng_register     b,");
        sql.append(" cmng_stbcustomvdo c,");
        sql.append(" cmng_custominfo   d ");
        sql.append(" where a.customno = b.dvbcustomid(+) ");
        sql.append(" and a.dvbcustomid = c.dvbcustomid");
        sql.append(" and  a.customid = d.customid ");
        sql.append(" and a.customtype = '1'");
        sql.append(" and a.status in ('01','08')");

        //获得全量用户信息
        System.out.println("sql:" + sql.toString());
        Connection conn1 = dataSource.getConnection()
        int page = 1;
        int pageNum = ONE_PAGE_DATA_SIZE;
        BasePage basePage = new BasePage();
        basePage.setPageNo(page);
        basePage.setPageSize(pageNum);
        JdbcUtils.calcCount(basePage, sql.toString(), conn1)
        conn1.close()
        //分页查询全量用户信息进行本地文件写入
        int count = basePage.getPageCount()
        System.out.println("count:" + count);
        for (int i = 0; i < count; i++) {
            Connection conn = dataSource.getConnection()
            basePage.setPageNo(i + 1);
            basePage.setFetchTotal(false)
            List<Map> userInfoList = JdbcUtils.getResultList(sql.toString(), basePage, conn);
            createCmngUserSyncData(userInfoList, localPath, fileName)
        }
    }

    /**
     * 根据用户信息和订购关系信息拼装全量用户同步数据
     * @param results
     * @return
     */
    public void createCmngUserSyncData(List<Map> userInfoList, String localPath, String fileName) {

        if (userInfoList == null || userInfoList.size() == 0)
            return
        StringBuffer IncrementStr = new StringBuffer()
        for (Map userInfoMap: userInfoList) {
            //组装用户同步信息
            IncrementStr.append(this.convertBeanToFtpLog(userInfoMap))
        }

        FileUtil fileUtil = new FileUtil()
        fileUtil.writeSyncDataToFileFoot(localPath, fileName, IncrementStr.toString())
        IncrementStr.delete(0, IncrementStr.length());

    }

    def String convertBeanToFtpLog(def userInfoMap) {
        //顺序以接口文档为依据
        StringBuffer result = new StringBuffer()
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("CUSTOMNO"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("CUSTOMNM"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("SEX"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("OPENDATE"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("USERSTATUS"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("MAINTELE"))).append(FtpBean.SPLIT)
        //地址字段中可能会有回车符，需要过过滤掉，否则在导出文件中会出现不正常的分行
        String address = userInfoMap.get("ADDRESS");
        if(address!=null&&address.length()>0){
            address = address.replaceAll("\n","");
            address = address.replaceAll("\r","");
        }
        result.append(FtpBeanUtil.convertStringToLog(address)).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertBigDecimalToLog(userInfoMap.get("ROOMSUM"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertBigDecimalToLog(userInfoMap.get("ROOMAREA"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("BUILDTYPE"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("BUILDNATURE"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("USENATURE"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertBigDecimalToLog(userInfoMap.get("CAR"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertBigDecimalToLog(userInfoMap.get("FAMILYMBER"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("STBID"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("ICID"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("SMCODE"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("STBSTATUS")))
        result.append(FtpBean.LINE_END);
        return result.toString();
    }

}