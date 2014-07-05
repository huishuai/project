package com.bmsp.message;

import com.bmsp.BsmpConstant;
import com.daocren.server.communication.message.SeqMsgAdapter;
import org.apache.log4j.Logger;

/**
 * 业务信息消息
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
     * 获取包体
     *
     * @return 包体
     */
    public byte[] getBody() {
        businessInfoString.getBytes();
        return body;
    }

    /**
     * 设置包体
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
     * 获取业务信息消息字符串
     *
     * @return 业务信息消息字符串
     */
    public String getBusinessInfoString() {
        return businessInfoString;
    }

    /**
     * 设置业务信息消息字符串
     *
     * @param businessInfoString
     */
    public void setBusinessInfoString(String businessInfoString) {
        this.businessInfoString = businessInfoString;
    }
}
