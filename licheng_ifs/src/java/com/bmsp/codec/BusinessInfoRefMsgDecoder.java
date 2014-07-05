package com.bmsp.codec;

import com.bmsp.BsmpConstant;
import com.bmsp.message.BusinessInfoRefMsg;
import com.daocren.server.communication.codec.AbstractDecoder;
import com.daocren.server.communication.message.AbstractMsg;
import org.apache.mina.common.ByteBuffer;

/**
 * 用户使用记录消息解码器
 */
public class BusinessInfoRefMsgDecoder extends AbstractDecoder {
    /**
     * 验证消息类型
     *
     * @param cmdType 消息类型
     * @return boolean
     */
    public boolean cmdMatch(long cmdType) {
        return cmdType == BsmpConstant.BUSINESSINFOREF_MSG_REQ;
    }

    /**
     * 解码消息体
     *
     * @param in     消息流
     * @param length 消息长度
     * @return BusinessInfoRefMsg
     */
    protected AbstractMsg decodeBody(ByteBuffer in, long length) {
        BusinessInfoRefMsg message = new BusinessInfoRefMsg();
        message.setSeqNo(in.getUnsignedInt());
        byte[] bytes = new byte[in.limit() - 10];
        try {
            in.get(bytes);
            message.setBody(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}
