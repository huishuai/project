package com.daocren.server.communication.engine;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * �ͻ����ӹ����������ڳ�ʼ������ͬ�����Ŀͻ����ӣ�������Ͽ�����
 *
 * @author Daocren
 */
public class ClientEngine implements ConnectorListener {

    private Logger logger = Logger.getLogger(ClientEngine.class);

    private List connectors = new ArrayList();

    private List conFactoryList;

    private boolean running = true;

    /**
     * ���ӹر�
     * @param connector ������
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
     * �����������ӹ������ֱ𴴽�ָ�������Ŀͻ�����
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
     * ֹͣ����
     */
    public void stop() {
        running = false;
        for (int i = 0; i < connectors.size(); i++) {
            ClientConnector con = (ClientConnector) connectors.get(i);
            con.stop();
        }
    }

    /**
     * ��ȡ���ӹ����б�
     * @return ���ӹ����б�
     */
    public List getConFactoryList() {
        return conFactoryList;
    }

    /**
     * �������ӹ����б�
     * @param conFactoryList �б���Ϣ
     */
    public void setConFactoryList(List conFactoryList) {
        this.conFactoryList = conFactoryList;
    }
}
