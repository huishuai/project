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

    //�趨��ҳ��ѯ����ʱÿҳ��ѯ������
    public final static int ONE_PAGE_DATA_SIZE = 1000

    public UserInfoForBMPTService() {
        //todo����Ҫ�޸ģ����������ļ����Ļ���
        this.type = ""
        this.code = "Iboss_Bmpt"
    }

    public void run() {
        BsmpFtpFileBean bean = buildFileBean(this.type, this.code, ftpClientBmpt.remoteFilePath, ftpClientBmpt.localFilePath);
        execute(bean);
    }

    /**
     *   ��д������û����ŵķ�ʽ���ļ���׺Ϊ".txt"
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
        //BMPT�û�ͬ��   BOSS-->BMPT
        //���÷�ҳ��ʽ��ȡ���ݣ�����ÿ�λ�ȡ���ݵ���������ֹ�ڴ����
        //��Ҫ�����ļ���СΪ2G
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
     * ȫ���û���Ϣͬ������ȡ�����û���Ϣ
     * @return
     * @throws Exception
     */
    public void createCmngUserAllSyncFile(String localPath, String fileName) throws Exception {

        //���ȴ����ļ�дͷ��Ϣ
        String IncrementStr = "CUSTOMNO|CUSTOMNM|SEX|OPENDATE|USERSTATUS|MAINTELE|ADDRESS|ROOMSUM|ROOMAREA|BUILDTYPE|BUILDNATURE|USENATURE|CARS|FAMILYMBERS|STBID|ICID|SMCODE|STBSTATUS\n";
        FileUtil fileUtil = new FileUtil()
        fileUtil.writeSyncDataToFileFoot(localPath, fileName, IncrementStr)

        //��ҳ���������ͬ������
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

        //���ȫ���û���Ϣ
        System.out.println("sql:" + sql.toString());
        Connection conn1 = dataSource.getConnection()
        int page = 1;
        int pageNum = ONE_PAGE_DATA_SIZE;
        BasePage basePage = new BasePage();
        basePage.setPageNo(page);
        basePage.setPageSize(pageNum);
        JdbcUtils.calcCount(basePage, sql.toString(), conn1)
        conn1.close()
        //��ҳ��ѯȫ���û���Ϣ���б����ļ�д��
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
     * �����û���Ϣ�Ͷ�����ϵ��Ϣƴװȫ���û�ͬ������
     * @param results
     * @return
     */
    public void createCmngUserSyncData(List<Map> userInfoList, String localPath, String fileName) {

        if (userInfoList == null || userInfoList.size() == 0)
            return
        StringBuffer IncrementStr = new StringBuffer()
        for (Map userInfoMap: userInfoList) {
            //��װ�û�ͬ����Ϣ
            IncrementStr.append(this.convertBeanToFtpLog(userInfoMap))
        }

        FileUtil fileUtil = new FileUtil()
        fileUtil.writeSyncDataToFileFoot(localPath, fileName, IncrementStr.toString())
        IncrementStr.delete(0, IncrementStr.length());

    }

    def String convertBeanToFtpLog(def userInfoMap) {
        //˳���Խӿ��ĵ�Ϊ����
        StringBuffer result = new StringBuffer()
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("CUSTOMNO"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("CUSTOMNM"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("SEX"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("OPENDATE"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("USERSTATUS"))).append(FtpBean.SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(userInfoMap.get("MAINTELE"))).append(FtpBean.SPLIT)
        //��ַ�ֶ��п��ܻ��лس�������Ҫ�����˵��������ڵ����ļ��л���ֲ������ķ���
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