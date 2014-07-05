package com.daocren.server.communication.codec;

import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.message.BindReq;
import org.apache.mina.common.ByteBuffer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 绑定请求消息编码器
 * @author Daocren
 */
public class BindReqEncoder extends AbstractEncoder {

    public BindReqEncoder() {
        Set types = new HashSet();
        types.add(BindReq.class);
        type = Collections.unmodifiableSet(types);
    }

    protected void encodeBody(ByteBuffer buf, AbstractMsg msg) {
        BindReq m = (BindReq) msg;

        buf.putInt((int) m.getSeqNo());
        buf.put(m.getVersion());
        buf.putShort((short) m.getReqQname());
        buf.put(m.getRandCode());
        buf.put(m.getRandAuth());
    }

}
