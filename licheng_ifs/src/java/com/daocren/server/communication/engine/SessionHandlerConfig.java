package com.daocren.server.communication.engine;

import org.apache.mina.handler.demux.MessageHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


/**
 * Created by IntelliJ IDEA.
 * User: Daocren
 * Date: 2008-8-4
 * Time: 14:19:42
 * �Ի�����������<br/>
 * ����ͬʱ���ñ�������������Ӧ�Ĵ�����
 */
public class SessionHandlerConfig {
    private List<Class> coders = new ArrayList<Class>();
    private Map<Class, MessageHandler> clazzHandlerMap = new LinkedHashMap<Class, MessageHandler>();
    private Class keepAliveClass;

    /**
     * ��ȡ������б�
     *
     * @return ������б�
     */
    public List<Class> getCoders() {
        return coders;
    }

    /**
     * ���ñ�����б�
     *
     * @param coders ������б�
     */
    public void setCoders(List<Class> coders) {
        this.coders = coders;
    }


    /**
     * ��ȡ���Ӧ�Ĵ�����
     *
     * @return ���Ӧ�Ĵ�����
     */
    public Map<Class, MessageHandler> getClazzHandlerMap() {
        return clazzHandlerMap;
    }

    /**
     * �������Ӧ�Ĵ�����
     *
     * @param clazzHandlerMap ���Ӧ�Ĵ�����
     */
    public void setClazzHandlerMap(Map<Class, MessageHandler> clazzHandlerMap) {
        this.clazzHandlerMap = clazzHandlerMap;
    }

    /**
     * ���ñ������ӵ���Ϣ����
     *
     * @param keepAliveClass �������ӵ���Ϣ����
     */
    public void setKeepAliveClass(Class keepAliveClass) {
        this.keepAliveClass = keepAliveClass;
    }

    /**
     * ��ȡ�������ӵ���Ϣ����
     *
     * @return keepAliveClass �������ӵ���Ϣ����
     */
    public Class getKeepAliveClass() {
        return keepAliveClass;
    }
}
