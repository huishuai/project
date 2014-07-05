package com.daocren.server.communication.sync;

import java.util.Hashtable;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-11
 * Time: 16:07:15
 * ͬ���߳�����
 */
public class SyncThreadContainer {
    private Hashtable<String, Thread> threadMap = new Hashtable<String, Thread>();

    /**
     * ʵ��������
     */
    public SyncThreadContainer() {
        threadMap = new Hashtable<String, Thread>();
    }

    /**
     * ��ѯ�Ƿ����ĳ���߳�
     *
     * @param key �߳�ʶ��ؼ���
     * @return boolean �Ƿ����
     */
    synchronized public boolean containsKey(String key) {
        return threadMap.containsKey(key);
    }

    /**
     * ��ȡһ���߳�
     *
     * @param key �߳�ʶ��ؼ���
     * @return Thread �߳�
     */
    synchronized public Thread getThread(String key) {
        return threadMap.get(key);
    }

    /**
     * ���һ���߳�
     *
     * @param key    �߳�ʶ��ؼ���
     * @param thread �߳�
     */
    synchronized public void addThread(String key, Thread thread) {
        threadMap.put(key, thread);
    }

    /**
     * ɾ��һ���߳�
     *
     * @param key �߳�ʶ��ؼ���
     */
    synchronized public void deleteThread(String key) {
        threadMap.remove(key);
    }

    /**
     * �̸߳���
     *
     * @return int ����
     */
    synchronized public int size() {
        return threadMap.size();
    }
}