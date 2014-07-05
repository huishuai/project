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
 * 解码基类
 */
public abstract class BaseBossOrderDecoder implements MessageDecoder {
    /**
     * 基本logger信息
     */
    protected final Logger logger = Logger.getLogger(getClass());

    private int mark;

    private boolean readHeader = false;

    private int length;

    public int commandId;

    /**
     * 判断是否可以对缓冲进行解码
     *
     * @param session 对话
     * @param in      输入缓冲
     * @return 解码结构
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
     * 抽象方法，判断命令类型是否匹配
     *
     * @param commandId 命令
     * @return 是否匹配命令
     */
    public abstract boolean commandIdMatch(int commandId);

    /**
     * 覆写方法，不需要实现
     *
     * @param session 对话
     * @param out     输出
     * @throws Exception
     */
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
    }

    /**
     * 解码主体方法
     *
     * @param session 对话
     * @param in      输入
     * @param out     输出
     * @return 解码结果
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
     * 子类继承实现解码方法，只解析包体
     *
     * @param in     输入
     * @param length 可解析长度
     * @return 抽象消息
     */
    protected abstract BaseMessage decodeBody(ByteBuffer in, int length);
}