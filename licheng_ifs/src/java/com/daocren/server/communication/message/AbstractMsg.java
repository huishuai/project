package com.daocren.server.communication.message;

import java.io.Serializable;

/**
 * �������紫����Ϣ�ĳ�����
 *
 * @author Daocren
 */
public abstract class AbstractMsg implements Serializable {

    private static final long serialVersionUID = 4349486942932247601L;
    /**
     * ��Ϣ����
     */
    protected long cmdType;
    /**
     * ��Ϣ����
     */
    protected long length;

    /**
     * ��ȡ��Ϣ����
     * @return ��Ϣ����
     */
    public long getCmdType() {
        return cmdType;
    }

    /**
     * ������Ϣ����
     * @param cmdType
     */
    public void setCmdType(long cmdType) {
        this.cmdType = cmdType;
    }

    /**
     * ��ȡ��Ϣ����
     * @return ��Ϣ����
     */
    public long getLength() {
        return length;
    }

    /**
     * ������Ϣ����
     * @param pkgLength
     */
    public void setLength(long pkgLength) {
        this.length = pkgLength;
    }

}
