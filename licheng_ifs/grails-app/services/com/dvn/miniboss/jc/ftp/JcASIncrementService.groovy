package com.dvn.miniboss.jc.ftp

import com.dvn.miniboss.jc.process.AuthServiceProcessService
import com.miniboss.acct.bean.BsmpFtpFileBean
import com.miniboss.acct.utils.FtpOperate
import org.apache.log4j.Logger

class JcASIncrementService extends BaseFtpProcessService {

    boolean transactional = false
    def AuthServiceProcessService authServiceProcessService
    private final static Logger logger = Logger.getLogger(JcASIncrementService.class)

    public JcASIncrementService() {
        this.type = "A"
        this.code = "03001"
    }

    /**
     *   重写方法，文件一天之内不重复，序号自增[原方法不自增，需要手动调一下next()方法才会增加]
     */
    protected BsmpFtpFileBean buildFileBean(String type, String code, String remotePath, String localPath) {
        BsmpFtpFileBean bean = new BsmpFtpFileBean(type, code, remotePath, localPath)
        bean.setIncrement(true)
        bean.load()
        return bean
    }


    void execute(BsmpFtpFileBean bean) {
        //增量订购关系   BOSS-->BSMP
        //控制每次获取内容是否会导致内存溢出
        //控制文件大小为2G
        logger.info("Begin-----WriteJcASIncrementServiceFile--------------:"+new Date());
        authServiceProcessService.getAuthServiceIncrementSyncStr(bean.localFileDir, bean.fileName)
        InputStream inputStreamMD5 = null
        try {
            File localFile = new File(bean.localFilePath)
            if (localFile.exists() && localFile.isFile()) {
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
        logger.info("End-------WriteJcASIncrementServiceFile--------------:"+new Date());
    }

}
