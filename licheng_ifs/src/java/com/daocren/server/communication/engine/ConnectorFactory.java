package com.daocren.server.communication.engine;

import org.apache.mina.common.IoHandler;
import org.apache.mina.integration.spring.ExecutorThreadModelFactoryBean;

/**
 * 创建客户连接的工厂类，可以指定连接数
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
     * 建立客户端链接器
     * @return 客户端链接器
     */
    public ClientConnector buildConnector() {
        return new ClientConnector(name,getHost(), getPort(), getHandler());
    }

    /**
     * 获取连接数
     * @return 连接数
     */
    public int getCount() {
        return count;
    }

    /**
     * 设置连接数量
     * @param count 连接数量
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 获取处理类
     * @return 处理类
     */
    public IoHandler getHandler() {
        return handler;
    }

    /**
     * 设置处理类
     * @param handler 处理类
     */
    public void setHandler(IoHandler handler) {
        this.handler = handler;
    }

    /**
     * 获取链接服务器主机IP
     * @return 服务器主机IP
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置链接服务器主机IP
     * @param host 服务器主机IP
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 获取服务器端口
     * @return 服务器端口
     */
    public int getPort() {
        return port;
    }

    /**
     * 设置服务器端口
     * @param port 服务器端口
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 获取线程处理工厂bean
     * @return 线程处理工厂bean
     */
    public ExecutorThreadModelFactoryBean getThreadModel() {
        return threadModel;
    }

    /**
     * 设置线程处理工厂bean
     * @param threadModel 线程处理工厂bean
     */
    public void setThreadModel(ExecutorThreadModelFactoryBean threadModel) {
        this.threadModel = threadModel;
    }

    /**
     * 设置连接名称
     * @return 连接名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取连接名称
     * @param name 连接名称
     */
    public void setName(String name) {
        this.name = name;
    }
}
