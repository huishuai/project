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
    *通过文件名和长度获取md5码
    */

    protected String getMD5(String fileName, long length) {
        String validateValue = fileName + length + Md5Util.SPARATOR_N;
        return Md5Util.byte2hex(Md5Util.encryptMD5(validateValue.getBytes()));
    }

    /**
     *  创建ftp文件传输时文件的各种参数
     * @param type 文件类型
     * @param code 文件接口编码
     * @param remotePath 远程ftp根目录
     * @return
     */
    private BsmpFtpFileBean buildFileBean(String type, String code, String remotePath) {
        BsmpFtpFileBean bean = new BsmpFtpFileBean(type, code, remotePath)
        bean.load()
        return bean
    }

    /**
     *  创建ftp文件传输时文件的各种参数
     * @param type 文件类型
     * @param code 文件接口编码
     * @param remotePath 远程ftp根目录
     * @param localPath 本地根目录
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
     * 方法体，查询数据并上传
     * @param bean
     * @return
     */
    abstract void execute(BsmpFtpFileBean bean)

    //todo 提供一个将本地文件直接上传到ftp服务器上,然后再删除本地文件的通用的方法
}
