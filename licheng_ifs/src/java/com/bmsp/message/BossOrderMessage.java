package com.bmsp.message;

import com.bmsp.util.SessionIdCreator;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 11:32:42
 * BOSS��Ϣ
 */
public class BossOrderMessage extends BossMessage {

    private static final long serialVersionUID = 7496993482838075141L;

    /**
     * ʵ����BOSS��Ϣ��Ĭ�ϲ���ֵ
     */
    public BossOrderMessage() {
        this.setMark(0x00E60001);
        this.setSessionId(SessionIdCreator.createSessionId());
    }

    /**
     * ʵ����BOSS��Ϣ�����в�����������
     */
    public BossOrderMessage(int init) {
    }

}