package com.dvn.miniboss.jc.ftp

import com.miniboss.acct.bean.BsmpFtpFileBean
import com.miniboss.acct.utils.FtpClient
import com.miniboss.acct.utils.Md5Util

abstract class BaseFtpProcessService {
    boolean transactional = false
    def String type
    def String code
    def FtpClient ftpClient;
    /*
    *ͨ���ļ����ͳ��Ȼ�ȡmd5��
    */

    protected String getMD5(String fileName, long length) {
        String validateValue = fileName + length + Md5Util.SPARATOR_N;
        return Md5Util.byte2hex(Md5Util.encryptMD5(validateValue.getBytes()));
    }

    /**
     *  ����ftp�ļ�����ʱ�ļ��ĸ��ֲ���
     * @param type �ļ�����
     * @param code �ļ��ӿڱ���
     * @param remotePath Զ��ftp��Ŀ¼
     * @return
     */
    private BsmpFtpFileBean buildFileBean(String type, String code, String remotePath) {
        BsmpFtpFileBean bean = new BsmpFtpFileBean(type, code, remotePath)
        bean.load()
        return bean
    }

    /**
     *  ����ftp�ļ�����ʱ�ļ��ĸ��ֲ���
     * @param type �ļ�����
     * @param code �ļ��ӿڱ���
     * @param remotePath Զ��ftp��Ŀ¼
     * @param localPath ���ظ�Ŀ¼
     * @return
     */
    protected BsmpFtpFileBean buildFileBean(String type, String code, String remotePath, String localPath) {
        BsmpFtpFileBean bean = new BsmpFtpFileBean(type, code, remotePath, localPath)
        bean.load()
        return bean
    }

    public void run() {
        BsmpFtpFileBean bean = buildFileBean(this.type, this.code, ftpClient.remoteFilePath, ftpClient.localFilePath);
        execute(bean);
    }
    /**
     * �����壬��ѯ���ݲ��ϴ�
     * @param bean
     * @return
     */
    abstract void execute(BsmpFtpFileBean bean)

    //todo �ṩһ���������ļ�ֱ���ϴ���ftp��������,Ȼ����ɾ�������ļ���ͨ�õķ���
}
