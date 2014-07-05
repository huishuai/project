package com.daocren.server.communication.codec;

import com.daocren.server.communication.message.AbstractMsg;
import org.apache.log4j.Logger;
import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import java.util.Set;

/**
 * 抽象消息编码类
 *
 * @author Daocren
 */
public abstract class AbstractEncoder implements MessageEncoder {
    protected static final Logger logger = Logger.getLogger(AbstractEncoder.class);

    protected Set type;

    /**
     * 获取支持的消息类型集合
     * @return 集合信息
     */
    public Set getMessageTypes() {
        return type;
    }

    /**
     * 抽象方法，处理具体消息把信息写入流
     *
     * @param buf 字节缓冲
     * @param msg 抽象消息
     */
    abstract protected void encodeBody(ByteBuffer buf, AbstractMsg msg);

    /**
     * 计算消息的长度
     *
     * @param msg 抽象消息
     * @return 消息长度
     */
    protected long calcLength(AbstractMsg msg) {
        return msg.getLength();
    }

    /**
     * 编码方法
     * @param session 对话
     * @param message 消息
     * @param out 输出
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
