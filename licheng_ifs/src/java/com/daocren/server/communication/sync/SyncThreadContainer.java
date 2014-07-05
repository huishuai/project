package com.daocren.server.communication.sync;

import java.util.Hashtable;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-11
 * Time: 16:07:15
 * 同步线程容器
 */
public class SyncThreadContainer {
    private Hashtable<String, Thread> threadMap = new Hashtable<String, Thread>();

    /**
     * 实例化容器
     */
    public SyncThreadContainer() {
        threadMap = new Hashtable<String, Thread>();
    }

    /**
     * 查询是否存在某个线程
     *
     * @param key 线程识别关键字
     * @return boolean 是否存在
     */
    synchronized public boolean containsKey(String key) {
        return threadMap.containsKey(key);
    }

    /**
     * 获取一个线程
     *
     * @param key 线程识别关键字
     * @return Thread 线程
     */
    synchronized public Thread getThread(String key) {
        return threadMap.get(key);
    }

    /**
     * 添加一个线程
     *
     * @param key    线程识别关键字
     * @param thread 线程
     */
    synchronized public void addThread(String key, Thread thread) {
        threadMap.put(key, thread);
    }

    /**
     * 删除一个线程
     *
     * @param key 线程识别关键字
     */
    synchronized public void deleteThread(String key) {
        threadMap.remove(key);
    }

    /**
     * 线程个数
     *
     * @return int 个数
     */
    synchronized public int size() {
        return threadMap.size();
    }
}