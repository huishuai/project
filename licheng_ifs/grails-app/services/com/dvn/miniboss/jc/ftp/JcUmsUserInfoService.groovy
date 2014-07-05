package com.dvn.miniboss.jc.ftp

import com.dvn.miniboss.jc.process.AuthServiceProcessService
import com.miniboss.acct.bean.BsmpFtpFileBean
import com.miniboss.acct.utils.FtpOperate
import org.apache.log4j.Logger
import com.miniboss.acct.utils.FtpClient

class JcUmsUserInfoService{

    boolean transactional = false
    def String type
    def String code
    def FtpClient ftpClientUms;

    private final static Logger logger = Logger.getLogger(JcUmsUserInfoService.class)
    def AuthServiceProcessService authServiceProcessService

    public JcUmsUserInfoService() {
        //todo����Ҫ�޸ģ����������ļ����Ļ���
        this.type = ""
        this.code = "sms"
    }

    public void run() {
        BsmpFtpFileBean bean = buildFileBean(this.type, this.code, ftpClientUms.remoteFilePath, ftpClientUms.localFilePath);
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
        //ums�û�ͬ��   BOSS-->UMS
        //���÷�ҳ��ʽ��ȡ���ݣ�����ÿ�λ�ȡ���ݵ���������ֹ�ڴ����
        //��Ҫ�����ļ���СΪ2G
        logger.info("Begin-----WriteJcUmsUserInfoFile--------------:"+new Date());
        authServiceProcessService.createCmngUserAllSyncFile(bean.localFileDir, bean.fileName)
        try {
            File localFile = new File(bean.localFilePath)
            if (localFile.exists() && localFile.isFile()) {
                FtpOperate client = this.ftpClientUms.createFtpClient()
                boolean uploadFlag = client.uploadFile(bean.remoteRootPath, bean.localFilePath)
                if (!uploadFlag) {
                    logger.error("Upload UserInfo SyncFile Error!FileName:" + bean.localFilePath)
                }
            }else{
                logger.info("Local SyncFile NotFound!FileName:" + bean.localFilePath)
            }
        } catch (Exception ex) {
            ex.printStackTrace()
            logger.error("-----------Sync Error!")
        }
        logger.info("End-------WriteJcUmsUserInfoFile--------------:"+new Date());
    }

}