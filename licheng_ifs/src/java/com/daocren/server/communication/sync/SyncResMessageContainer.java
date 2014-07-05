package com.daocren.server.communication.sync;

import java.util.Hashtable;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-14
 * Time: 14:59:45
 * 返回消息容器，将Socket返回的消息放在容器中，以便在线程激活以后能够获取到返回的消息
 */
public class SyncResMessageContainer {
    private Hashtable<String, SyncMessage> responseMap = new Hashtable<String, SyncMessage>();

    /**
     * 实例化创建容器
     */
    public SyncResMessageContainer() {
        responseMap = new Hashtable<String, SyncMessage>();
    }

    /**
     * 查询是否存在某条消息
     *
     * @param key 消息识别关键字
     * @return boolean 是否存在
     */
    synchronized public boolean containsKey(String key) {
        return responseMap.containsKey(key);
    }

    /**
     * 获取消息
     *
     * @param key 消息识别关键字
     * @return SyncMessage 消息
     */
    synchronized public SyncMessage getResponseMessage(String key) {
        return responseMap.get(key);
    }

    /**
     * 添加消息
     *
     * @param key             消息识别关键字
     * @param responseMessage 消息
     */
    synchronized public void addResponseMessage(String key, SyncMessage responseMessage) {
        responseMap.put(key, responseMessage);
    }

    /**
     * 删除消息
     *
     * @param key 消息识别关键字
     */
    synchronized public void deleteResponseMessage(String key) {
        responseMap.remove(key);
    }

    /**
     * 消息个数
     *
     * @return int 个数
     */
    synchronized public int size() {
        return responseMap.size();
    }
}
