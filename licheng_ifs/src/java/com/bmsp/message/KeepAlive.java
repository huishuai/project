package com.bmsp.message;

import com.bmsp.util.ConstantUtil;
import com.bmsp.util.SessionIdCreator;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 15:52:50
 * 心跳信息体
 */
public class KeepAlive extends BaseMessage {
    /**
     * 实例化心跳信息，默认参数值
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
     * 实例化心跳信息，所有参数均不设置
     */
    public KeepAlive(int init) {
    }
}
