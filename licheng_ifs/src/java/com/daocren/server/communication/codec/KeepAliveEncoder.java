package com.daocren.server.communication.codec;

import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.message.KeepAlive;
import org.apache.mina.common.ByteBuffer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 保持mina连接消息类编码器
 * @author Daocren
 */
public class KeepAliveEncoder extends AbstractEncoder {

    public KeepAliveEncoder() {
        Set types = new HashSet();
        types.add(KeepAlive.class);
        type = Collections.unmodifiableSet(types);
    }

    protected void encodeBody(ByteBuffer buf, AbstractMsg msg) {
        KeepAlive m = (KeepAlive) msg;
        buf.putInt((int) m.getReserved());

    }

}
