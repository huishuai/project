package com.daocren.server.communication.codec;

import com.daocren.server.communication.message.AbstractMsg;
import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * ������Ϣ������
 *
 * @author Daocren
 */
public abstract class AbstractDecoder implements MessageDecoder {

    private int cmdType;

    private boolean readHeader = false;

    private long length;

    /**
     * �ж��Ƿ���ԶԻ�����н���
     * @param session �Ի�
     * @param in ���뻺��
     * @return ����ṹ
     */
    public MessageDecoderResult decodable(IoSession session, ByteBuffer in) {
        // Return NEED_DATA if the package size is not read yet.
        if (in.remaining() < 4) {
            return MessageDecoderResult.NEED_DATA;
        }

        length = in.getUnsignedInt();

        if (in.remaining() < length - 4) {
            return MessageDecoderResult.NEED_DATA;
        }

        cmdType = in.getUnsignedShort();

        if (cmdMatch(cmdType)) {
            return MessageDecoderResult.OK;
        }

        // Return NOT_OK if not matches.
        return MessageDecoderResult.NOT_OK;
    }

    /**
     * ���󷽷����ж����������Ƿ�ƥ��
     * @param cmdType ����
     * @return  �Ƿ�ƥ������
     */
    public abstract boolean cmdMatch(long cmdType);

    /**
     * ��д����������Ҫʵ��
     * @param session �Ի�
     * @param out ���
     * @throws Exception
     */
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
    }

    /**
     * �������巽��
     * @param session �Ի�
     * @param in ����
     * @param out ���
     * @return ������
     */
    public MessageDecoderResult decode(IoSession session, ByteBuffer in,
                                       ProtocolDecoderOutput out) {

        // logger.debug("���� " + length);

        // Try to skip header if not read.
        if (!readHeader) {
            in.getInt(); // Skip 'package length'.
            in.getUnsignedShort(); // Skip 'cmd remoteQname'.
            // logger.debug("��ͷ�ѽ����������� ");
            readHeader = true;
        }

        // Try to decode body
        AbstractMsg m = decodeBody(in, length - 6);

        // Return NEED_DATA if the body is not fully read.
        if (m == null) {
            return MessageDecoderResult.NEED_DATA;
        } else {
            readHeader = false; // reset readHeader for the next decode
            m.setCmdType(cmdType);
            m.setLength(length);
            out.write(m);
            return MessageDecoderResult.OK;
        }

    }

    /**
     * ����̳�ʵ�ֽ��뷽����ֻ��������
     * @param in ����
     * @param length �ɽ�������
     * @return ������Ϣ
     */
    protected abstract AbstractMsg decodeBody(ByteBuffer in, long length);

}
