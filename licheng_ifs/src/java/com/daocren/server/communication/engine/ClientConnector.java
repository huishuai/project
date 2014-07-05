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
     * 基本构造方法
     *
     * @param host    连接服务器端IP
     * @param port    链接服务器端端口
     * @param handler 对应处理类
     */
    public ClientConnector(String name, String host, int port, IoHandler handler) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.handler = handler;
    }

    /**
     * 客户端链接服务器端启动方法<br/>
     * 按照线程方法进行启动<br/>
     * 每隔一段时间进行连接，直至成功连接服务器端
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

        // 无限次的重连，直到成功，跳入下一个循环，重试时间为5秒
        while (running) {
            try {
                ConnectFuture future = connector.connect(new InetSocketAddress(host, port), handler);
                future.join();
                logger.info(" 连接 : " + host + ", 端口: " + port);
                session = future.getSession();
                session.setAttribute(MsgConstant.KEY_CONNECTOR_NAME, name);
                ((ClientSessionHandler)handler).getSessionsContainer().addSession(name,session);
                break;
            } catch (Exception e) {
                logger.error("打开连接出错，//" + host + ":" + port + "重试!", e);
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {

                }
            }
        }

        // 只要还活着，就有责任第隔五秒检查一次连接状态，如果已断，则触发listner动作（重新建立连接）
        while (running) {

            if (session.isConnected()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            } else {
                logger.debug("连接断开!");
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
     * 清理connector
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
     * 关闭connector
     */
    public void stop() {
        logger.info("关闭连接");
        try {
            running = false;
            if (session != null)
                session.close().join();
            clear();
        } catch (Exception e) {
            logger.warn("关闭连接失败", e);
        }
    }

    /**
     * 配置监听器
     *
     * @param listener 监听器
     */
    public void setListener(ConnectorListener listener) {
        this.listener = listener;
    }


}