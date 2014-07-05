package com.daocren.server.communication.engine;

/**
 * 连接器监听接口
 * @author Daocren
 */
public interface ConnectorListener {
    /**
     * 连接关闭回调方法
     * @param connector 连接器
     */
    void connectorTerminated(ClientConnector connector);

}
