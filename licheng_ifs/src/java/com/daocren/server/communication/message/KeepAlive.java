package com.daocren.server.communication.message;

/**
 * 用于保持链接的消息
 *
 * @author Daocren
 */
public class KeepAlive extends AbstractMsg {
    private static final long serialVersionUID = -6080240357340930282L;

    public KeepAlive() {
        length = 10;
    }

    private long reserved;

    /**
     * 获取保留时间
     * @return 保留时间
     */
    public long getReserved() {
        return reserved;
    }

    /**
     * 设置保留时间
     * @param reserved
     */
    public void setReserved(long reserved) {
        this.reserved = reserved;
    }

}
