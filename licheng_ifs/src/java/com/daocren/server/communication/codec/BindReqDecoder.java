package com.daocren.server.communication.codec;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.message.BindReq;
import org.apache.mina.common.ByteBuffer;

/**
 * 绑定请求消息解码器
 * @author Daocren
 */
public class BindReqDecoder extends AbstractDecoder {
    public boolean cmdMatch(long cmdType) {
        return (cmdType == Constant.BIND_REQ);
    }

    protected AbstractMsg decodeBody(ByteBuffer in, long length) {
        BindReq msg = new BindReq();
        msg.setSeqNo(in.getUnsignedInt());
        msg.setVersion(in.get());
        msg.setReqQname(in.getUnsignedShort());
        in.get(msg.getRandCode());
        in.get(msg.getRandAuth());
        return msg;
    }

}
