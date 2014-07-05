package com.daocren.server.communication.lightmessage;

import java.io.Serializable;

/**
 * 苗西监听器接口
 * @author Daocren
 */
public interface MsgListener {
    /**
     * 消息接收时处理方法
     * @param msg 需处理消息
     */
    public void onMessage(Serializable msg);
}
