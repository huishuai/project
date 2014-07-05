package com.daocren.server.communication.message;

import java.io.Serializable;

/**
 * 用于网络传输消息的抽象父类
 *
 * @author Daocren
 */
public abstract class AbstractMsg implements Serializable {

    private static final long serialVersionUID = 4349486942932247601L;
    /**
     * 消息类型
     */
    protected long cmdType;
    /**
     * 消息长度
     */
    protected long length;

    /**
     * 获取消息类型
     * @return 消息类型
     */
    public long getCmdType() {
        return cmdType;
    }

    /**
     * 设置消息类型
     * @param cmdType
     */
    public void setCmdType(long cmdType) {
        this.cmdType = cmdType;
    }

    /**
     * 获取消息长度
     * @return 消息长度
     */
    public long getLength() {
        return length;
    }

    /**
     * 设置消息长度
     * @param pkgLength
     */
    public void setLength(long pkgLength) {
        this.length = pkgLength;
    }

}
