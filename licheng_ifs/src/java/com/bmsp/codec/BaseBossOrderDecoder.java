package com.bmsp.codec;

import com.bmsp.message.BaseMessage;
import com.bmsp.util.ConstantUtil;
import org.apache.log4j.Logger;
import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-13
 * Time: 17:49:22
 * �������
 */
public abstract class BaseBossOrderDecoder implements MessageDecoder {
    /**
     * ����logger��Ϣ
     */
    protected final Logger logger = Logger.getLogger(getClass());

    private int mark;

    private boolean readHeader = false;

    private int length;

    public int commandId;

    /**
     * �ж��Ƿ���ԶԻ�����н���
     *
     * @param session �Ի�
     * @param in      ���뻺��
     * @return ����ṹ
     */
    public MessageDecoderResult decodable(IoSession session, ByteBuffer in) {
        // Return NEED_DATA if the package size is not read yet.
        if (in.remaining() < 12) {
            return MessageDecoderResult.NEED_DATA;
        }
        mark = in.getInt();
        length = (int) in.getUnsignedInt();
        commandId = (int) in.getInt();
        if(in.remaining()<length-12) {
            return MessageDecoderResult.NEED_DATA;
        }
        if (commandIdMatch(commandId) && mark == ConstantUtil.BOSS_ORDER_MARK) {
            return MessageDecoderResult.OK;
        }

        // Return NOT_OK if not matches.
        return MessageDecoderResult.NOT_OK;
    }

    /**
     * ���󷽷����ж����������Ƿ�ƥ��
     *
     * @param commandId ����
     * @return �Ƿ�ƥ������
     */
    public abstract boolean commandIdMatch(int commandId);

    /**
     * ��д����������Ҫʵ��
     *
     * @param session �Ի�
     * @param out     ���
     * @throws Exception
     */
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
    }

    /**
     * �������巽��
     *
     * @param session �Ի�
     * @param in      ����
     * @param out     ���
     * @return ������
     */
    public MessageDecoderResult decode(IoSession session, ByteBuffer in,
                                       ProtocolDecoderOutput out) {

        // Try to decode All
        if (!readHeader) {
            in.getInt();
            in.getInt();
            in.getInt();
            readHeader = true;
        }
        BaseMessage m = decodeBody(in, length - 12);

        // Return NEED_DATA if the body is not fully read.
        if (m == null) {
            return MessageDecoderResult.NEED_DATA;
        } else {
            readHeader = false; // reset readHeader for the next decode
            m.setCommandId(commandId);
            m.setLength(length);
            m.setMark(mark);
            out.write(m);
            return MessageDecoderResult.OK;
        }

    }

    /**
     * ����̳�ʵ�ֽ��뷽����ֻ��������
     *
     * @param in     ����
     * @param length �ɽ�������
     * @return ������Ϣ
     */
    protected abstract BaseMessage decodeBody(ByteBuffer in, int length);
}