package com.bmsp.codec;

import com.bmsp.message.BaseMessage;
import com.bmsp.message.KeepAlive;
import org.apache.mina.common.ByteBuffer;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 16:22:25
 * 编码心跳消息
 */
public class KeepAliveEncoder extends BaseEncoder {
    /**
     * 设置要编码的消息实体
     */
    public KeepAliveEncoder() {
        this.setMessageTypes(KeepAlive.class);
    }

    /**
     * 编码消息体
     *
     * @param buf 字节码字符
     * @param msg 消息
     */
    protected void encodeBody(ByteBuffer buf, BaseMessage msg) {
        KeepAlive m = (KeepAlive) msg;
        buf.put(m.getBody());
    }
}
