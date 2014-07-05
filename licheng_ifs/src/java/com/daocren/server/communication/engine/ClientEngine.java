package com.daocren.server.communication.engine;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 客户连接管理器，用于初始化到不同主机的客户连接，并负责断开重连
 *
 * @author Daocren
 */
public class ClientEngine implements ConnectorListener {

    private Logger logger = Logger.getLogger(ClientEngine.class);

    private List connectors = new ArrayList();

    private List conFactoryList;

    private boolean running = true;

    /**
     * 链接关闭
     * @param connector 连接器
     */
    public void connectorTerminated(ClientConnector connector) {
        if (running) {
            logger.debug("Connector terminated ,restart");
            startConnector(connector);
        }
    }

    private void startConnector(ClientConnector connector) {
        connector.setListener(this);
        Thread thread = new Thread(connector);
        thread.setName("client - " + connector);
        thread.start();
    }

    /**
     * 遍历所有连接工厂，分别创建指定数量的客户连接
     */
    public void start() {
        for (Iterator iter = conFactoryList.iterator(); iter.hasNext();) {
            ConnectorFactory cf = (ConnectorFactory) iter.next();
            for (int i = 0; i < cf.getCount(); i++) {
                connectors.add(cf.buildConnector());
            }
        }
        for (int i = 0; i < connectors.size(); i++) {
            ClientConnector con = (ClientConnector) connectors.get(i);
            startConnector(con);
        }
    }

    /**
     * 停止链接
     */
    public void stop() {
        running = false;
        for (int i = 0; i < connectors.size(); i++) {
            ClientConnector con = (ClientConnector) connectors.get(i);
            con.stop();
        }
    }

    /**
     * 获取链接工厂列表
     * @return 链接工厂列表
     */
    public List getConFactoryList() {
        return conFactoryList;
    }

    /**
     * 设置链接工厂列表
     * @param conFactoryList 列表信息
     */
    public void setConFactoryList(List conFactoryList) {
        this.conFactoryList = conFactoryList;
    }
}
