package com.daocren.server.communication.codec;

import com.daocren.server.communication.message.AbstractMsg;
import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * 抽象消息解码类
 *
 * @author Daocren
 */
public abstract class AbstractDecoder implements MessageDecoder {

    private int cmdType;

    private boolean readHeader = false;

    private long length;

    /**
     * 判断是否可以对缓冲进行解码
     * @param session 对话
     * @param in 输入缓冲
     * @return 解码结构
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
     * 抽象方法，判断命令类型是否匹配
     * @param cmdType 命令
     * @return  是否匹配命令
     */
    public abstract boolean cmdMatch(long cmdType);

    /**
     * 覆写方法，不需要实现
     * @param session 对话
     * @param out 输出
     * @throws Exception
     */
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
    }

    /**
     * 解码主体方法
     * @param session 对话
     * @param in 输入
     * @param out 输出
     * @return 解码结果
     */
    public MessageDecoderResult decode(IoSession session, ByteBuffer in,
                                       ProtocolDecoderOutput out) {

        // logger.debug("包长 " + length);

        // Try to skip header if not read.
        if (!readHeader) {
            in.getInt(); // Skip 'package length'.
            in.getUnsignedShort(); // Skip 'cmd remoteQname'.
            // logger.debug("包头已解析过，忽略 ");
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
     * 子类继承实现解码方法，只解析包体
     * @param in 输入
     * @param length 可解析长度
     * @return 抽象消息
     */
    protected abstract AbstractMsg decodeBody(ByteBuffer in, long length);

}
