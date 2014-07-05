package com.bmsp.message;

import com.bmsp.util.ConstantUtil;
import com.bmsp.util.SessionIdCreator;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 15:52:50
 * ������Ϣ��
 */
public class KeepAlive extends BaseMessage {
    /**
     * ʵ����������Ϣ��Ĭ�ϲ���ֵ
     */
    public KeepAlive() {
        this.setMark(0x00E7C6B5);
        this.setCommandId(ConstantUtil.BOSS_KEEP_LIFE_REQ);
        this.setSessionId(SessionIdCreator.createSessionId());
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setSessionId(this.getSessionId());
        this.setBody(bodyMessage.getKeepLiveXml().getBytes());
    }

    /**
     * ʵ����������Ϣ�����в�����������
     */
    public KeepAlive(int init) {
    }
}
