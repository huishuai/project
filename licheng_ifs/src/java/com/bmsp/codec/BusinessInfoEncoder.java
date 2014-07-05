package com.bmsp.codec;

import com.bmsp.message.BusinessInfoMessage;
import com.daocren.server.communication.codec.AbstractEncoder;
import com.daocren.server.communication.message.AbstractMsg;
import org.apache.mina.common.ByteBuffer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 业务信息编码器
 */
public class BusinessInfoEncoder extends AbstractEncoder {

    public BusinessInfoEncoder() {
        Set<Class> types = new HashSet<Class>();
        types.add(BusinessInfoMessage.class);
        type = Collections.unmodifiableSet(types);
    }

    @Override
    protected void encodeBody(ByteBuffer buf, AbstractMsg msg) {
        BusinessInfoMessage m = (BusinessInfoMessage) msg;
        buf.putInt((int) m.getSeqNo());
        buf.put(m.getBody());
    }

}
