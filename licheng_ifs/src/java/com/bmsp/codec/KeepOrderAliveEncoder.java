package com.bmsp.codec;

import com.bmsp.message.BaseMessage;
import com.bmsp.message.KeepOrderAlive;
import org.apache.mina.common.ByteBuffer;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 16:22:25
 * ����������Ϣ
 */
public class KeepOrderAliveEncoder extends BaseEncoder {
    /**
     * ����Ҫ�������Ϣʵ��
     */
    public KeepOrderAliveEncoder() {
        this.setMessageTypes(KeepOrderAlive.class);
    }

    /**
     * ������Ϣ��
     *
     * @param buf �ֽ����ַ�
     * @param msg ��Ϣ
     */
    protected void encodeBody(ByteBuffer buf, BaseMessage msg) {
        KeepOrderAlive m = (KeepOrderAlive) msg;
        buf.put(m.getBody());
    }
}