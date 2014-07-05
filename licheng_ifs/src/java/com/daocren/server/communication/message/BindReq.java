package com.daocren.server.communication.message;

import com.daocren.server.communication.Constant;


/**
 * ��֤�����ⷢ��Ϣ
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

    private byte version; // �ӿڰ汾�ţ���4λΪ���汾�ţ���4λΪ�ΰ汾�š�

    private int reqQname; // ���

    private byte[] randCode = new byte[8]; // ���8λ�ַ�

    private byte[] randAuth = new byte[8]; // ��֤8λ�ַ����������"txblxdyy"��򣩡�

    /**
     * ��ȡ�汾��Ϣ
     * @return �汾��Ϣ
     */
    public byte getVersion() {
        return version;
    }

    /**
     * ��ȡ��֤��Ϣ
     * @return ��֤��Ϣ
     */
    public byte[] getRandAuth() {
        return randAuth;
    }

    /**
     * ������֤��Ϣ
     * @param randAuth
     */
    public void setRandAuth(byte[] randAuth) {
        this.randAuth = randAuth;
    }

    /**
     * ��ȡ�����
     * @return �����
     */
    public byte[] getRandCode() {
        return randCode;
    }

    /**
     * ���������
     * @param randCode
     */
    public void setRandCode(byte[] randCode) {
        this.randCode = randCode;
    }

    /**
     * ���ð汾
     * @param version
     */
    public void setVersion(byte version) {
        this.version = version;
    }

    /**
     * ��ȡ�����͵ķ������˶�������
     * @return �����͵ķ������˶�������
     */
    public int getReqQname() {
        return reqQname;
    }

    /**
     * ���������͵ķ������˶������� 
     * @param reqQname
     */
    public void setReqQname(int reqQname) {
        this.reqQname = reqQname;
    }

}
