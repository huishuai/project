package com.daocren.server.communication.engine;

import org.apache.mina.common.IoHandler;
import org.apache.mina.integration.spring.ExecutorThreadModelFactoryBean;

/**
 * �����ͻ����ӵĹ����࣬����ָ��������
 *
 * @author Daocren
 */
public class ConnectorFactory {
    private int count = 1;

    private String host;

    private int port;

    private IoHandler handler;

    private String name="default";

    private ExecutorThreadModelFactoryBean threadModel;

    /**
     * �����ͻ���������
     * @return �ͻ���������
     */
    public ClientConnector buildConnector() {
        return new ClientConnector(name,getHost(), getPort(), getHandler());
    }

    /**
     * ��ȡ������
     * @return ������
     */
    public int getCount() {
        return count;
    }

    /**
     * ������������
     * @param count ��������
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * ��ȡ������
     * @return ������
     */
    public IoHandler getHandler() {
        return handler;
    }

    /**
     * ���ô�����
     * @param handler ������
     */
    public void setHandler(IoHandler handler) {
        this.handler = handler;
    }

    /**
     * ��ȡ���ӷ���������IP
     * @return ����������IP
     */
    public String getHost() {
        return host;
    }

    /**
     * �������ӷ���������IP
     * @param host ����������IP
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * ��ȡ�������˿�
     * @return �������˿�
     */
    public int getPort() {
        return port;
    }

    /**
     * ���÷������˿�
     * @param port �������˿�
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * ��ȡ�̴߳�����bean
     * @return �̴߳�����bean
     */
    public ExecutorThreadModelFactoryBean getThreadModel() {
        return threadModel;
    }

    /**
     * �����̴߳�����bean
     * @param threadModel �̴߳�����bean
     */
    public void setThreadModel(ExecutorThreadModelFactoryBean threadModel) {
        this.threadModel = threadModel;
    }

    /**
     * ������������
     * @return ��������
     */
    public String getName() {
        return name;
    }

    /**
     * ��ȡ��������
     * @param name ��������
     */
    public void setName(String name) {
        this.name = name;
    }
}
