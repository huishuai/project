package com.bmsp.codec;

import com.bmsp.message.BossMessage;
import com.bmsp.message.BaseMessage;
import org.apache.mina.common.ByteBuffer;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 13:14:29
 * ���뷢��BOSS����Ϣ
 */
public class BossMsgEncoder extends BaseEncoder {
    /**
     * ����Ҫ�������Ϣʵ��
     */
    public BossMsgEncoder() {
        this.setMessageTypes(BossMessage.class);
    }

    /**
     * ������Ϣ��
     *
     * @param buf �ֽ����ַ�
     * @param msg ��Ϣ
     */
    protected void encodeBody(ByteBuffer buf, BaseMessage msg) {
        BossMessage m = (BossMessage) msg;
        buf.put(m.getBody());
    }
}
