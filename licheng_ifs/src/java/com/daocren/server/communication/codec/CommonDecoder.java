package com.daocren.server.communication.codec;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.message.CommonMsg;
import org.apache.mina.common.ByteBuffer;

/**
 * 基础消息解码器<br/>
 * 提供对基本的响应消息的编码
 * @author Daocren
 */
public class CommonDecoder extends AbstractDecoder {
    public boolean cmdMatch(long cmdType) {
        return (cmdType == Constant.BIND_RES);
    }

    protected AbstractMsg decodeBody(ByteBuffer in, long length) {
        if (in.remaining() < length)
            return null;

        CommonMsg res = new CommonMsg();
        res.setSeqNo(in.getUnsignedInt());
        res.setResult(in.get());
        return res;
    }

}
