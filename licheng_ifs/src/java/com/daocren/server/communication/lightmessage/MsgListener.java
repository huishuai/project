package com.daocren.server.communication.lightmessage;

import java.io.Serializable;

/**
 * �����������ӿ�
 * @author Daocren
 */
public interface MsgListener {
    /**
     * ��Ϣ����ʱ������
     * @param msg �账����Ϣ
     */
    public void onMessage(Serializable msg);
}
