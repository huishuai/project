package com.daocren.server.communication.sync;

import org.apache.mina.common.IoSession;

import java.util.*;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-11
 * Time: 16:18:48
 * Session������Session��Ŵ���
 */
public class SessionsContainer {
    private Hashtable<String, Hashtable<String, IoSession>> sessionMap = new Hashtable<String, Hashtable<String, IoSession>>();
    private static Random random = new Random();

    public SessionsContainer() {
        sessionMap = new Hashtable<String, Hashtable<String, IoSession>>();
    }

    /**
     * ��ѯSession�Ƿ����
     *
     * @param key Session����
     * @return boolean �Ƿ����
     */
    public boolean containsKey(String key) {
        return sessionMap.containsKey(key);
    }

    /**
     * ���һ��Session
     *
     * @param key       Session����
     * @param ioSession Session
     */
    public void addSession(String key, IoSession ioSession) {
        Hashtable<String, IoSession> map = sessionMap.get(key);
        if (map == null) {
            map = new Hashtable<String, IoSession>();
            sessionMap.put(key, map);
        }
        map.put(String.valueOf(ioSession.hashCode()), ioSession);
    }

    /**
     * ��ȡSession�б�
     *
     * @param sessionName Session����
     * @return Hashtable<String, IoSession> Hashtable�б�
     */
    public Hashtable<String, IoSession> getSessions(String sessionName) {
        return sessionMap.get(sessionName);
    }

    /**
     * ��ȡһ��Session�����Hashtable�д��ڶ��Sessionʱ�������ȡһ��Session
     *
     * @param sessionName Session����
     * @return IoSession
     */
    public IoSession getSession(String sessionName) {
        Hashtable<String, IoSession> stringIoSessionHashtable = sessionMap.get(sessionName);
        if (stringIoSessionHashtable != null && stringIoSessionHashtable.size() > 0) {
            int size = stringIoSessionHashtable.size();
            if (size == 1) {
                return stringIoSessionHashtable.elements().nextElement();
            } else {
                return (IoSession) stringIoSessionHashtable.values().toArray()[random.nextInt(size)];
            }
        } else {
            return null;
        }
    }

    /**
     * ɾ��Session
     *
     * @param sessionName Session����
     */
    public void deleteSession(String sessionName) {
        sessionMap.remove(sessionName);
    }

    /**
     * ɾ��ĳ���ض���Session������Session���ֲ�ѯ��Session�б��ٸ���Session��HashCode����ɾ���ض���Session
     *
     * @param sessionName Session����
     * @param ioSession   Session
     */
    public void deleteSession(String sessionName, IoSession ioSession) {
        if (sessionMap.containsKey(sessionName)) {
            Hashtable<String, IoSession> map = sessionMap.get(sessionName);
            map.remove(String.valueOf(ioSession.hashCode()));
        }
    }

    /**
     * ��ȡ����Session
     *
     * @return List<IoSession> ���е�Session�б�
     */
    public List<IoSession> getIoSessions() {
        List<IoSession> list = new ArrayList<IoSession>();
        Collection<Hashtable<String, IoSession>> hashtableCollection = sessionMap.values();
        for (Hashtable<String, IoSession> stringIoSessionHashtable : hashtableCollection) {
            Collection<IoSession> sessionCollection = stringIoSessionHashtable.values();
            list.addAll(sessionCollection);
        }
        return list;
    }
}
