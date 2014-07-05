package com.daocren.server.communication.sync;

import org.apache.mina.common.IoSession;

import java.util.*;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-11
 * Time: 16:18:48
 * Session容器，Session存放处理
 */
public class SessionsContainer {
    private Hashtable<String, Hashtable<String, IoSession>> sessionMap = new Hashtable<String, Hashtable<String, IoSession>>();
    private static Random random = new Random();

    public SessionsContainer() {
        sessionMap = new Hashtable<String, Hashtable<String, IoSession>>();
    }

    /**
     * 查询Session是否存在
     *
     * @param key Session名字
     * @return boolean 是否存在
     */
    public boolean containsKey(String key) {
        return sessionMap.containsKey(key);
    }

    /**
     * 添加一个Session
     *
     * @param key       Session名字
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
     * 获取Session列表
     *
     * @param sessionName Session名字
     * @return Hashtable<String, IoSession> Hashtable列表
     */
    public Hashtable<String, IoSession> getSessions(String sessionName) {
        return sessionMap.get(sessionName);
    }

    /**
     * 获取一个Session，如果Hashtable中存在多个Session时，随机获取一个Session
     *
     * @param sessionName Session名字
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
     * 删除Session
     *
     * @param sessionName Session名字
     */
    public void deleteSession(String sessionName) {
        sessionMap.remove(sessionName);
    }

    /**
     * 删除某个特定的Session，根据Session名字查询出Session列表，再根据Session的HashCode码来删除特定的Session
     *
     * @param sessionName Session名字
     * @param ioSession   Session
     */
    public void deleteSession(String sessionName, IoSession ioSession) {
        if (sessionMap.containsKey(sessionName)) {
            Hashtable<String, IoSession> map = sessionMap.get(sessionName);
            map.remove(String.valueOf(ioSession.hashCode()));
        }
    }

    /**
     * 获取所有Session
     *
     * @return List<IoSession> 所有的Session列表
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
