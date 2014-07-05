package com.daocren.server.communication.codec;

import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.message.CommonMsg;
import org.apache.mina.common.ByteBuffer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * »ù´¡ÏûÏ¢±àÂëÆ÷
 *
 * @author Daocren
 */
public class CommonEncoder extends AbstractEncoder {

    public CommonEncoder() {
        Set types = new HashSet();
        types.add(CommonMsg.class);
        type = Collections.unmodifiableSet(types);
    }

    protected void encodeBody(ByteBuffer buf, AbstractMsg msg) {
        CommonMsg m = (CommonMsg) msg;
        buf.putInt((int) m.getSeqNo());
        buf.put(m.getResult());
    }
}
