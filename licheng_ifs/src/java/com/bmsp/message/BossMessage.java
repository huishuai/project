package com.bmsp.message;

import com.bmsp.util.SessionIdCreator;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 11:32:42
 * BOSS消息
 */
public class BossMessage extends BaseMessage {

    private static final long serialVersionUID = 7496993482838075141L;

    /**
     * 实例化BOSS消息，默认参数值
     */
    public BossMessage() {
        this.setMark(0x00E7C6B5);
        this.setSessionId(SessionIdCreator.createSessionId());
    }

    /**
     * 实例化BOSS消息，所有参数均不设置
     */
    public BossMessage(int init) {
    }

}
