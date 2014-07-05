package com.daocren.server.communication.message;

import com.daocren.server.communication.Constant;


/**
 * 认证请求，外发消息
 *
 * @author Daocren
 */
public class BindReq extends SeqMsgAdapter {
    private static final long serialVersionUID = -5525944386969927479L;

    public BindReq() {
        length = 29;
        cmdType = Constant.BIND_REQ;
        version = 0x20;
    }

    private byte version; // 接口版本号，高4位为主版本号，低4位为次版本号。

    private int reqQname; // 标号

    private byte[] randCode = new byte[8]; // 随机8位字符

    private byte[] randAuth = new byte[8]; // 认证8位字符（随机吗与"txblxdyy"异或）。

    /**
     * 获取版本信息
     * @return 版本信息
     */
    public byte getVersion() {
        return version;
    }

    /**
     * 获取验证信息
     * @return 验证信息
     */
    public byte[] getRandAuth() {
        return randAuth;
    }

    /**
     * 设置验证信息
     * @param randAuth
     */
    public void setRandAuth(byte[] randAuth) {
        this.randAuth = randAuth;
    }

    /**
     * 获取随机码
     * @return 随机码
     */
    public byte[] getRandCode() {
        return randCode;
    }

    /**
     * 设置随机码
     * @param randCode
     */
    public void setRandCode(byte[] randCode) {
        this.randCode = randCode;
    }

    /**
     * 设置版本
     * @param version
     */
    public void setVersion(byte version) {
        this.version = version;
    }

    /**
     * 获取请求发送的服务器端队列名称
     * @return 请求发送的服务器端队列名称
     */
    public int getReqQname() {
        return reqQname;
    }

    /**
     * 设置请求发送的服务器端队列名称 
     * @param reqQname
     */
    public void setReqQname(int reqQname) {
        this.reqQname = reqQname;
    }

}
