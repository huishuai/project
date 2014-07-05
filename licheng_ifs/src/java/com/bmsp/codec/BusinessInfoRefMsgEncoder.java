package com.bmsp.codec;

import com.bmsp.message.BusinessInfoRefMsg;
import com.daocren.server.communication.codec.AbstractEncoder;
import com.daocren.server.communication.message.AbstractMsg;
import org.apache.mina.common.ByteBuffer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户使用记录消息编码器
 */
public class BusinessInfoRefMsgEncoder extends AbstractEncoder {
    /**
     * BusinessInfoRefMsgEncoder构造函数，初始化消息类
     */
    public BusinessInfoRefMsgEncoder() {
        Set<Class> types = new HashSet<Class>();
        types.add(BusinessInfoRefMsg.class);
        type = Collections.unmodifiableSet(types);

    }

    /**
     * 编码消息体
     *
     * @param buf 消息流
     * @param msg 消息
     */
    protected void encodeBody(ByteBuffer buf, AbstractMsg msg) {
        BusinessInfoRefMsg m = (BusinessInfoRefMsg) msg;
        buf.putInt((int) m.getSeqNo());
        buf.put(m.getBody());
    }
}
