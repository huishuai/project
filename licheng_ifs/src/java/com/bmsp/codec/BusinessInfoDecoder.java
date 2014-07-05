package com.bmsp.codec;

import com.bmsp.BsmpConstant;
import com.bmsp.message.BusinessInfoMessage;
import com.daocren.server.communication.codec.AbstractDecoder;
import com.daocren.server.communication.message.AbstractMsg;
import org.apache.mina.common.ByteBuffer;

/**
 * 业务信息解码器
 */
public class BusinessInfoDecoder extends AbstractDecoder {

    @Override
    public boolean cmdMatch(long cmdType) {
        // TODO Auto-generated method stub
        return cmdType == BsmpConstant.BUSINESSINFO_MSG_REQ;
    }

    @Override
    protected AbstractMsg decodeBody(ByteBuffer in, long length) {
        // TODO Auto-generated method stub
        BusinessInfoMessage message = new BusinessInfoMessage();

        // in.get(bytes);
        message.setSeqNo(in.getUnsignedInt());
        byte[] bytes = new byte[in.limit() - 10];
        try {
            in.get(bytes);
            message.setBody(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // message.setBody(bytes);
        return message;
    }

}
