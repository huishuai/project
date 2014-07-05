package com.daocren.server.communication.message;

/**
 * ���ڱ������ӵ���Ϣ
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
     * ��ȡ����ʱ��
     * @return ����ʱ��
     */
    public long getReserved() {
        return reserved;
    }

    /**
     * ���ñ���ʱ��
     * @param reserved
     */
    public void setReserved(long reserved) {
        this.reserved = reserved;
    }

}
