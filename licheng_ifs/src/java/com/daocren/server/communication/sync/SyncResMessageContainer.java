package com.daocren.server.communication.sync;

import java.util.Hashtable;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-14
 * Time: 14:59:45
 * ������Ϣ��������Socket���ص���Ϣ���������У��Ա����̼߳����Ժ��ܹ���ȡ�����ص���Ϣ
 */
public class SyncResMessageContainer {
    private Hashtable<String, SyncMessage> responseMap = new Hashtable<String, SyncMessage>();

    /**
     * ʵ������������
     */
    public SyncResMessageContainer() {
        responseMap = new Hashtable<String, SyncMessage>();
    }

    /**
     * ��ѯ�Ƿ����ĳ����Ϣ
     *
     * @param key ��Ϣʶ��ؼ���
     * @return boolean �Ƿ����
     */
    synchronized public boolean containsKey(String key) {
        return responseMap.containsKey(key);
    }

    /**
     * ��ȡ��Ϣ
     *
     * @param key ��Ϣʶ��ؼ���
     * @return SyncMessage ��Ϣ
     */
    synchronized public SyncMessage getResponseMessage(String key) {
        return responseMap.get(key);
    }

    /**
     * �����Ϣ
     *
     * @param key             ��Ϣʶ��ؼ���
     * @param responseMessage ��Ϣ
     */
    synchronized public void addResponseMessage(String key, SyncMessage responseMessage) {
        responseMap.put(key, responseMessage);
    }

    /**
     * ɾ����Ϣ
     *
     * @param key ��Ϣʶ��ؼ���
     */
    synchronized public void deleteResponseMessage(String key) {
        responseMap.remove(key);
    }

    /**
     * ��Ϣ����
     *
     * @return int ����
     */
    synchronized public int size() {
        return responseMap.size();
    }
}
