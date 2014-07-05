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
public class KeepOrderAlive extends KeepAlive {
    /**
     * ʵ����������Ϣ��Ĭ�ϲ���ֵ
     */
    public KeepOrderAlive() {
        this.setMark(0x00E60001);
        this.setCommandId(ConstantUtil.BOSS_ORDER_MONTH_KEEPLIVE_REQ);
        this.setSessionId(SessionIdCreator.createSessionId());
        BodyMessage bodyMessage = new BodyMessage();
        bodyMessage.setSessionId(this.getSessionId());
        this.setBody(bodyMessage.getKeepLiveXml().getBytes());
    }

    /**
     * ʵ����������Ϣ�����в�����������
     */
    public KeepOrderAlive(int init) {
    }
}