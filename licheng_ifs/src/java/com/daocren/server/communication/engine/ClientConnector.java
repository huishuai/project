package com.daocren.server.communication.engine;

import com.daocren.server.communication.message.MsgConstant;
import org.apache.log4j.Logger;
import org.apache.mina.common.*;
import org.apache.mina.integration.spring.ExecutorThreadModelFactoryBean;
import org.apache.mina.transport.socket.nio.SocketConnector;

import java.net.InetSocketAddress;

/**
 * @author Daocren
 */
public class ClientConnector implements Runnable {

    private static final String PROTOCOL_THREAD_POOL_NAME = "protocolThreadPool";

    private static final String IO_THREAD_POOL_NAME = "ioThreadPool";

    private static final String THREAD_POOL_NAME = "ThreadPool";

    private Logger logger = Logger.getLogger(ClientConnector.class);

    private final String host;

    private final int port;

    private boolean running = true;

    private static final int CONNECT_TIMEOUT = 1000 * 60 * 5;

    private final IoHandler handler;

    private ConnectorListener listener;

    private IoSession session;

    private IoConnector connector;

    private ExecutorThreadModelFactoryBean threadModel;

    private String name;

    /**
     * �������췽��
     *
     * @param host    ���ӷ�������IP
     * @param port    ���ӷ������˶˿�
     * @param handler ��Ӧ������
     */
    public ClientConnector(String name, String host, int port, IoHandler handler) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.handler = handler;
    }

    /**
     * �ͻ������ӷ���������������<br/>
     * �����̷߳�����������<br/>
     * ÿ��һ��ʱ��������ӣ�ֱ���ɹ����ӷ�������
     */
    public void run() {
        // Create I/O and Protocol thread pool filter.
        // I/O thread pool performs encoding and decoding of messages.
        // Protocol thread pool performs actual protocol flow.
        //ExecutorThreadModel etm = ExecutorThreadModel.getInstance(THREAD_POOL_NAME);

        connector = new SocketConnector();
        connector.getDefaultConfig().setThreadModel(ThreadModel.MANUAL);
        //connector.getFilterChain().addFirst(THREAD_POOL_NAME, threadModel);

        //connector.setFilterChainBuilder(etm);

//		connector.getFilterChain().addFirst(IO_THREAD_POOL_NAME,
//				new ThreadPoolFilter());
//		connector.getFilterChain().addLast(PROTOCOL_THREAD_POOL_NAME,
//				new ThreadPoolFilter());

        ((IoConnectorConfig) connector.getDefaultConfig())
                .setConnectTimeout(CONNECT_TIMEOUT);

        // ���޴ε�������ֱ���ɹ���������һ��ѭ��������ʱ��Ϊ5��
        while (running) {
            try {
                ConnectFuture future = connector.connect(new InetSocketAddress(host, port), handler);
                future.join();
                logger.info(" ���� : " + host + ", �˿�: " + port);
                session = future.getSession();
                session.setAttribute(MsgConstant.KEY_CONNECTOR_NAME, name);
                ((ClientSessionHandler)handler).getSessionsContainer().addSession(name,session);
                break;
            } catch (Exception e) {
                logger.error("�����ӳ���//" + host + ":" + port + "����!", e);
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {

                }
            }
        }

        // ֻҪ�����ţ��������εڸ�������һ������״̬������Ѷϣ��򴥷�listner���������½������ӣ�
        while (running) {

            if (session.isConnected()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            } else {
                logger.debug("���ӶϿ�!");
                clear();
                if (listener != null) {
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        logger.error(e);
                    }
                    listener.connectorTerminated(this);
                }
                break;
            }
        }

    }

    /**
     * ����connector
     */
    private void clear() {
        if (connector != null) {
//			connector.getFilterChain().remove(IO_THREAD_POOL_NAME);
//			connector.getFilterChain().remove(PROTOCOL_THREAD_POOL_NAME);
            //connector.getFilterChain().remove(THREAD_POOL_NAME);
            connector = null;
        }
    }

    /**
     * �ر�connector
     */
    public void stop() {
        logger.info("�ر�����");
        try {
            running = false;
            if (session != null)
                session.close().join();
            clear();
        } catch (Exception e) {
            logger.warn("�ر�����ʧ��", e);
        }
    }

    /**
     * ���ü�����
     *
     * @param listener ������
     */
    public void setListener(ConnectorListener listener) {
        this.listener = listener;
    }


}