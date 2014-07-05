package com.bmsp.message;

import com.bmsp.BsmpConstant;
import com.daocren.server.communication.message.SeqMsgAdapter;

/**
 * 套餐业务信息关系消息
 */
public class BusinessInfoRefMsg extends SeqMsgAdapter {

    private static final long serialVersionUID = -1569988402453092660L;
    private byte[] body = null;
    private String businessInfoRefString;

    /**
     * BusinessInfoRefMsg构造函数，初始化消息类型
     */
    public BusinessInfoRefMsg() {
        this.cmdType = BsmpConstant.BUSINESSINFOREF_MSG_REQ;
    }

    /**
     * 获取消息长度
     *
     * @return length
     */
    public long getLength() {
        if (length < 10)
            length = 10 + this.getBody().length;
        return length;
    }

    /**
     * 获取消息体
     *
     * @return body
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * 设置消息体
     *
     * @param bytes 消息体
     */
    public void setBody(byte[] bytes) {
        this.body = bytes;
        this.businessInfoRefString = new String(bytes);
    }

    /**
     * 获取套餐业务信息关系字符串
     *
     * @return businessInfoRefString
     */
    public String getBusinessInfoRefString() {
        return businessInfoRefString;
    }

    /**
     * 设置套餐业务信息关系字符串
     *
     * @param businessInfoRefString 套餐业务信息关系字符串
     */
    public void setBusinessInfoRefString(String businessInfoRefString) {
        this.businessInfoRefString = businessInfoRefString;
    }
}
