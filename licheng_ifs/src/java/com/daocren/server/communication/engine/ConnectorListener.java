package com.daocren.server.communication.engine;

/**
 * �����������ӿ�
 * @author Daocren
 */
public interface ConnectorListener {
    /**
     * ���ӹرջص�����
     * @param connector ������
     */
    void connectorTerminated(ClientConnector connector);

}
