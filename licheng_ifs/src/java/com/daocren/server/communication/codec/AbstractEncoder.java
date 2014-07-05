package com.daocren.server.communication.codec;

import com.daocren.server.communication.message.AbstractMsg;
import org.apache.log4j.Logger;
import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import java.util.Set;

/**
 * ������Ϣ������
 *
 * @author Daocren
 */
public abstract class AbstractEncoder implements MessageEncoder {
    protected static final Logger logger = Logger.getLogger(AbstractEncoder.class);

    protected Set type;

    /**
     * ��ȡ֧�ֵ���Ϣ���ͼ���
     * @return ������Ϣ
     */
    public Set getMessageTypes() {
        return type;
    }

    /**
     * ���󷽷������������Ϣ����Ϣд����
     *
     * @param buf �ֽڻ���
     * @param msg ������Ϣ
     */
    abstract protected void encodeBody(ByteBuffer buf, AbstractMsg msg);

    /**
     * ������Ϣ�ĳ���
     *
     * @param msg ������Ϣ
     * @return ��Ϣ����
     */
    protected long calcLength(AbstractMsg msg) {
        return msg.getLength();
    }

    /**
     * ���뷽��
     * @param session �Ի�
     * @param message ��Ϣ
     * @param out ���
     */
    public void encode(IoSession session, Object message,
                       ProtocolEncoderOutput out) {
        AbstractMsg m = (AbstractMsg) message;
        long length = calcLength(m);
        ByteBuffer buf = ByteBuffer.allocate((int) length);

        buf.putInt((int) length);
        buf.putShort((short) m.getCmdType());

        encodeBody(buf, m);

        buf.flip();
        out.write(buf);
    }

}
