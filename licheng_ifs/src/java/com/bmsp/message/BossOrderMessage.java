package com.bmsp.message;

import com.bmsp.util.SessionIdCreator;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 11:32:42
 * BOSS消息
 */
public class BossOrderMessage extends BossMessage {

    private static final long serialVersionUID = 7496993482838075141L;

    /**
     * 实例化BOSS消息，默认参数值
     */
    public BossOrderMessage() {
        this.setMark(0x00E60001);
        this.setSessionId(SessionIdCreator.createSessionId());
    }

    /**
     * 实例化BOSS消息，所有参数均不设置
     */
    public BossOrderMessage(int init) {
    }

}