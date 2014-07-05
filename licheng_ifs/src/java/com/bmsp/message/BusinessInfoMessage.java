package com.bmsp.message;

import com.bmsp.BsmpConstant;
import com.daocren.server.communication.message.SeqMsgAdapter;
import org.apache.log4j.Logger;

/**
 * ҵ����Ϣ��Ϣ
 */
public class BusinessInfoMessage extends SeqMsgAdapter {
    private static final long serialVersionUID = -956313399497707891L;

    private byte[] body = null;

    private String businessInfoString;
    private static Logger logger = Logger.getLogger(BusinessInfoMessage.class);

    public BusinessInfoMessage() {
        this.cmdType = BsmpConstant.BUSINESSINFO_MSG_REQ;
    }

    /**
     * ��ȡ����
     *
     * @return ����
     */
    public byte[] getBody() {
        businessInfoString.getBytes();
        return body;
    }

    /**
     * ���ð���
     *
     * @param bytes
     */
    public void setBody(byte[] bytes) {
        body = bytes;
        businessInfoString = new String(bytes);
    }

    @Override
    public long getLength() {
        if (length < 10)
            length = 10 + getBody().length;
        return length;
    }

    /**
     * ��ȡҵ����Ϣ��Ϣ�ַ���
     *
     * @return ҵ����Ϣ��Ϣ�ַ���
     */
    public String getBusinessInfoString() {
        return businessInfoString;
    }

    /**
     * ����ҵ����Ϣ��Ϣ�ַ���
     *
     * @param businessInfoString
     */
    public void setBusinessInfoString(String businessInfoString) {
        this.businessInfoString = businessInfoString;
    }
}
