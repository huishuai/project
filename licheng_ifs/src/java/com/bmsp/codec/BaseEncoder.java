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
 * 编码基类
 */
public abstract class BaseEncoder implements MessageEncoder {
    /**
     * 基本logger信息
     */
    protected final Logger logger = Logger.getLogger(getClass());

    protected Set type;

    /**
     * 获取支持的消息类型集合
     *
     * @return 集合信息
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
     * 抽象方法，处理具体消息把信息写入流
     *
     * @param buf 字节缓冲
     * @param msg 抽象消息
     */
    abstract protected void encodeBody(ByteBuffer buf, BaseMessage msg);

    /**
     * 计算消息的长度
     *
     * @param msg 抽象消息
     * @return 消息长度
     */
    protected int calcLength(BaseMessage msg) {
        return msg.getLength();
    }

    /**
     * 编码方法
     *
     * @param session 对话
     * @param message 消息
     * @param out     输出
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
