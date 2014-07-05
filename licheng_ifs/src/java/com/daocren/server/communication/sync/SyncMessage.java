package com.daocren.server.communication.sync;

/**
 * Created by IntelliJ IDEA.
 * User: Daocren
 * Date: 2008-8-15
 * Time: 16:21:20
 * 同步消息接口，在消息体中实现这个接口，在同布消息器中将使用这个ID
 */
public interface SyncMessage {
    public String getSyncMessageId();
}
