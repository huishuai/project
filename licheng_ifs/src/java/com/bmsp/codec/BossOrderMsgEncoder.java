package com.bmsp.codec;

import com.bmsp.message.BaseMessage;
import com.bmsp.message.BossMessage;
import com.bmsp.message.BossOrderMessage;
import org.apache.mina.common.ByteBuffer;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 13:14:29
 * 编码发给BOSS的消息
 */
public class BossOrderMsgEncoder extends BaseEncoder {
    /**
     * 设置要编码的消息实体
     */
    public BossOrderMsgEncoder() {
        this.setMessageTypes(BossOrderMessage.class);
    }

    /**
     * 编码消息体
     *
     * @param buf 字节码字符
     * @param msg 消息
     */
    protected void encodeBody(ByteBuffer buf, BaseMessage msg) {
        BossOrderMessage m = (BossOrderMessage) msg;
        buf.put(m.getBody());
    }
}