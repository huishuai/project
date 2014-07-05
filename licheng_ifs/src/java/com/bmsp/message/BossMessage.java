package com.bmsp.message;

import com.bmsp.util.SessionIdCreator;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 11:32:42
 * BOSS��Ϣ
 */
public class BossMessage extends BaseMessage {

    private static final long serialVersionUID = 7496993482838075141L;

    /**
     * ʵ����BOSS��Ϣ��Ĭ�ϲ���ֵ
     */
    public BossMessage() {
        this.setMark(0x00E7C6B5);
        this.setSessionId(SessionIdCreator.createSessionId());
    }

    /**
     * ʵ����BOSS��Ϣ�����в�����������
     */
    public BossMessage(int init) {
    }

}
