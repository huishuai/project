package com.bmsp.codec;

import com.bmsp.message.BusinessInfoRefMsg;
import com.daocren.server.communication.codec.AbstractEncoder;
import com.daocren.server.communication.message.AbstractMsg;
import org.apache.mina.common.ByteBuffer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * �û�ʹ�ü�¼��Ϣ������
 */
public class BusinessInfoRefMsgEncoder extends AbstractEncoder {
    /**
     * BusinessInfoRefMsgEncoder���캯������ʼ����Ϣ��
     */
    public BusinessInfoRefMsgEncoder() {
        Set<Class> types = new HashSet<Class>();
        types.add(BusinessInfoRefMsg.class);
        type = Collections.unmodifiableSet(types);

    }

    /**
     * ������Ϣ��
     *
     * @param buf ��Ϣ��
     * @param msg ��Ϣ
     */
    protected void encodeBody(ByteBuffer buf, AbstractMsg msg) {
        BusinessInfoRefMsg m = (BusinessInfoRefMsg) msg;
        buf.putInt((int) m.getSeqNo());
        buf.put(m.getBody());
    }
}
