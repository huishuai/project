package com.dvn.miniboss.jc.ftp

import com.dvn.miniboss.jc.process.AuthServiceProcessService
import com.dvn.miniboss.oldsms.AuthService
import com.miniboss.acct.bean.BsmpFtpFileBean
import com.miniboss.acct.utils.FtpOperate
import java.sql.Connection
import javax.sql.DataSource
import org.apache.log4j.Logger
import com.dvn.miniboss.oldsms.ChargeBillType
import com.miniboss.acct.utils.FileUtil
import com.dvn.miniboss.serv.Empowerment
import com.dvn.miniboss.product.Commodity
import com.dvn.miniboss.acct.CmngUser

class JcASAllService extends BaseFtpProcessService {

    boolean transactional = false
    def AuthServiceProcessService authServiceProcessService
    def DataSource dataSource
    public static final Logger logger = Logger.getLogger(JcASAllService.class)

    public JcASAllService() {
        this.type = "C"
        this.code = "03002"
    }

    void execute(BsmpFtpFileBean bean) {
        //全量订购关系   BOSS-->BSMP
        //需要分页获取内容是否会导致内存溢出

        Connection conn = dataSource.getConnection()
//        String sql = "select * from AUTH_SERVICE a where a.status = '" + AuthService.STATUS_RUNNING + "' and a.BILLTYPEID = '" + ChargeBillType.SERVICE_VOD_MONTH + "' ";
        String sql = "select * from Empowerment e where e.status= '" + Empowerment.STATUS_VALID + "' and e.CHARGE_BILL_TYPE='" + ChargeBillType.SERVICE_VOD_MONTH + "' and e.PACKAGE_TYPE = '" + Commodity.PACKAGETYPE_NORMAL + "' and e.GROUP_TYPE = '" + CmngUser.GROUPTYPE_PERSON + "' " + " and e.MIN_BEGIN_DATE is not null and e.MAX_END_DATE is not null";
        logger.info("Begin-----WriteJcASAllServiceFile--------------:"+new Date());
        logger.info("--SQL:"+sql);
        authServiceProcessService.batchWriteAuthServiceToLocalFile(sql, conn, bean.localFileDir, bean.fileName)
        InputStream inputStreamMD5 = null
        try {
            File localFile = new File(bean.localFilePath)
            if (localFile.exists() && localFile.isFile()) {
                logger.info("Local SyncFile Exist!Begin Upload!FileName:" + bean.localFilePath)
                FtpOperate client = this.ftpClient.createFtpClient()
                boolean uploadFlag = client.uploadFile(bean.remoteFileDir, bean.localFilePath)
                if (uploadFlag) {
                    long size = client.getFileSize(bean.remoteFileDir, bean.fileName)
                    String md5Str = getMD5(bean.name, size)
                    inputStreamMD5 = new ByteArrayInputStream(md5Str.getBytes())
                    client.uploadFile(bean.remoteFileDir, bean.getMd5Name(), inputStreamMD5)
                } else {
                    logger.error("Upload SyncFile Error!FileName:" + bean.localFilePath)
                }
            }else{
                logger.info("Local SyncFile NotFound!FileName:" + bean.localFilePath)
            }
        } catch (Exception ex) {
            ex.printStackTrace()
        } finally {
            try {
                if (inputStreamMD5 != null)
                    inputStreamMD5.close()
            } catch (Exception ex) {
                ex.printStackTrace()
            }
        }
        logger.info("End-------WriteJcASAllServiceFile--------------:"+new Date());
    }

}