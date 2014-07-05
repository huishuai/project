package com.bmsp.codec;

import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.common.IoSession;
import org.apache.mina.common.ByteBuffer;
import org.apache.log4j.Logger;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

import com.bmsp.message.BaseMessage;

/**
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-13
 * Time: 16:37:27
 * �������
 */
public abstract class BaseEncoder implements MessageEncoder {
    /**
     * ����logger��Ϣ
     */
    protected final Logger logger = Logger.getLogger(getClass());

    protected Set type;

    /**
     * ��ȡ֧�ֵ���Ϣ���ͼ���
     *
     * @return ������Ϣ
     */
    public Set getMessageTypes() {
        return type;
    }

    public void setMessageTypes(Class iClass) {
        Set types = new HashSet();
        types.add(iClass);
        type = Collections.unmodifiableSet(types);
    }

    /**
     * ���󷽷������������Ϣ����Ϣд����
     *
     * @param buf �ֽڻ���
     * @param msg ������Ϣ
     */
    abstract protected void encodeBody(ByteBuffer buf, BaseMessage msg);

    /**
     * ������Ϣ�ĳ���
     *
     * @param msg ������Ϣ
     * @return ��Ϣ����
     */
    protected int calcLength(BaseMessage msg) {
        return msg.getLength();
    }

    /**
     * ���뷽��
     *
     * @param session �Ի�
     * @param message ��Ϣ
     * @param out     ���
     */
    public void encode(IoSession session, Object message,
                       ProtocolEncoderOutput out) {
        BaseMessage m = (BaseMessage) message;

        int length = calcLength(m);
        ByteBuffer buf = ByteBuffer.allocate(length);
        buf.putInt(m.getMark());
        buf.putInt(length);
        buf.putInt(m.getCommandId());
        encodeBody(buf, m);
        buf.flip();
        out.write(buf);
    }
}
