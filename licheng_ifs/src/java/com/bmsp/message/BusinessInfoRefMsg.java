package com.bmsp.message;

import com.bmsp.BsmpConstant;
import com.daocren.server.communication.message.SeqMsgAdapter;

/**
 * �ײ�ҵ����Ϣ��ϵ��Ϣ
 */
public class BusinessInfoRefMsg extends SeqMsgAdapter {

    private static final long serialVersionUID = -1569988402453092660L;
    private byte[] body = null;
    private String businessInfoRefString;

    /**
     * BusinessInfoRefMsg���캯������ʼ����Ϣ����
     */
    public BusinessInfoRefMsg() {
        this.cmdType = BsmpConstant.BUSINESSINFOREF_MSG_REQ;
    }

    /**
     * ��ȡ��Ϣ����
     *
     * @return length
     */
    public long getLength() {
        if (length < 10)
            length = 10 + this.getBody().length;
        return length;
    }

    /**
     * ��ȡ��Ϣ��
     *
     * @return body
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * ������Ϣ��
     *
     * @param bytes ��Ϣ��
     */
    public void setBody(byte[] bytes) {
        this.body = bytes;
        this.businessInfoRefString = new String(bytes);
    }

    /**
     * ��ȡ�ײ�ҵ����Ϣ��ϵ�ַ���
     *
     * @return businessInfoRefString
     */
    public String getBusinessInfoRefString() {
        return businessInfoRefString;
    }

    /**
     * �����ײ�ҵ����Ϣ��ϵ�ַ���
     *
     * @param businessInfoRefString �ײ�ҵ����Ϣ��ϵ�ַ���
     */
    public void setBusinessInfoRefString(String businessInfoRefString) {
        this.businessInfoRefString = businessInfoRefString;
    }
}
