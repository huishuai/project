package com.daocren.server.communication.codec;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.message.KeepAlive;
import org.apache.mina.common.ByteBuffer;

/**
 * 保持mina连接消息类解码器
 * @author Daocren
 */
public class KeepAliveDecoder extends AbstractDecoder {

    protected AbstractMsg decodeBody(ByteBuffer in, long length) {
        KeepAlive msg = new KeepAlive();
        msg.setReserved(in.getUnsignedInt());
        return msg;
    }

    public boolean cmdMatch(long cmdType) {
        return (cmdType == Constant.KEEP_ALIVE_REQ || cmdType == Constant.KEEP_ALIVE_RES);
    }

}
