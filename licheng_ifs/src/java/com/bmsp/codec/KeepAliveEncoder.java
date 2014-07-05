package com.bmsp.codec;

import com.bmsp.message.BaseMessage;
import com.bmsp.message.KeepAlive;
import org.apache.mina.common.ByteBuffer;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 16:22:25
 * ����������Ϣ
 */
public class KeepAliveEncoder extends BaseEncoder {
    /**
     * ����Ҫ�������Ϣʵ��
     */
    public KeepAliveEncoder() {
        this.setMessageTypes(KeepAlive.class);
    }

    /**
     * ������Ϣ��
     *
     * @param buf �ֽ����ַ�
     * @param msg ��Ϣ
     */
    protected void encodeBody(ByteBuffer buf, BaseMessage msg) {
        KeepAlive m = (KeepAlive) msg;
        buf.put(m.getBody());
    }
}
