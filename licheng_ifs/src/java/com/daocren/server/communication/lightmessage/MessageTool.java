package com.daocren.server.communication.lightmessage;

import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

import javax.jms.*;
import java.io.Serializable;

/**
 * activemq消息工具封装,用于发送和接收消息
 *
 * @author Daocren
 */
public class MessageTool extends JmsGatewaySupport {
    private Connection con;

    private Session session;

    private final Object sendLock = new Object();

    private MessageConsumer consumer;

    private Thread receiveWorker;

    private boolean running = true;

    private MsgListener ltn = null;

    /**
     * 发送消息方法
     * @param msg 需发送消息
     */
    public void send(final Serializable msg) {
        if (!running) {
            logger.warn("已停止，无法发送");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //
            }
            return;
        }
        try {

            synchronized (sendLock) {
                long start = System.currentTimeMillis();
                getJmsTemplate().setExplicitQosEnabled(false);
                getJmsTemplate().send(new MessageCreator() {
                    public Message createMessage(Session session)
                            throws JMSException {
                        if (msg instanceof String) {
                            return session.createTextMessage((String) msg);
                        } else {
                            return session.createObjectMessage(msg);
                        }
                    }
                });
                if (logger.isDebugEnabled()) {
                    logger.debug("队列 "
                            + getJmsTemplate().getDefaultDestinationName()
                            + " 发送消息 耗时: "
                            + (System.currentTimeMillis() - start));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按照优先级发送消息
     * @param msg 消息
     * @param priority 优先级
     */
    public void send(final Serializable msg, int priority) {
        if (!running) {
            logger.warn("已停止，无法发送");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //
            }
            return;
        }

        try {
            synchronized (sendLock) {

                long start = System.currentTimeMillis();
                getJmsTemplate().setExplicitQosEnabled(true);
                getJmsTemplate().setPriority(priority);
                getJmsTemplate().send(new MessageCreator() {
                    public Message createMessage(Session session)
                            throws JMSException {
                        if (msg instanceof String) {
                            return session.createTextMessage((String) msg);
                        } else {
                            return session.createObjectMessage(msg);
                        }
                    }
                }

                );
                if (logger.isDebugEnabled()) {
                    logger.debug("队列 "
                            + getJmsTemplate().getDefaultDestinationName()
                            + " 发送消息 耗时: "
                            + (System.currentTimeMillis() - start));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化方法
     * @throws Exception
     */
    protected void init() throws Exception {
        con = getConnectionFactory().createConnection();
        con.start();
        session = con.createSession(getJmsTemplate().isSessionTransacted(),
                getJmsTemplate().getSessionAcknowledgeMode());
        Destination dest = getJmsTemplate().getDestinationResolver()
                .resolveDestinationName(session,
                        getJmsTemplate().getDefaultDestinationName(),
                        getJmsTemplate().isPubSubDomain());

        if (getJmsTemplate().isPubSubNoLocal()) {
            consumer = session.createConsumer(dest, null, true);
        } else {
            consumer = session.createConsumer(dest);
        }

        // 如果是异步方式
        if (ltn != null) {

            receiveWorker = new Thread(new Runnable() {
                public void run() {
                    while (running) {
                        // 对于异步接收的方式，不能堵在那儿一直等待，因为这样就可能永远没机会跳出循环了
                        if (getJmsTemplate().getReceiveTimeout() <= 0) {
                            getJmsTemplate().setReceiveTimeout(10000);
                        }
                        ltn.onMessage(receive());
                    }

                }
            });

        }
    }

    /**
     * 获取消息方法
     * @return 获取到的序列化消息
     */
    synchronized public Serializable receive() {
        if (!running) {
            logger.warn("已停止，无法接收");
            // 不用马上返回，反正也没消息，歇一秒先。
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //
            }
            return null;
        }
        try {

            if (consumer == null) {
                init();
            }
            Message msg;
            long start = System.currentTimeMillis();
            if (getJmsTemplate().getReceiveTimeout() > 0) {
                msg = consumer.receive(getJmsTemplate().getReceiveTimeout());
            } else {
                msg = consumer.receive();
            }

            if (logger.isDebugEnabled()) {
                logger.debug("队列 "
                        + getJmsTemplate().getDefaultDestinationName()
                        + " 接收消息 耗时: " + (System.currentTimeMillis() - start));
            }

            if (msg == null)
                return null;

            if (msg instanceof TextMessage) {
                return ((TextMessage) msg).getText();

            } else {
                return ((ObjectMessage) msg).getObject();
            }
        } catch (Exception e) {
            logger.info(e);
            throw new RuntimeException("获取消息内容出错", e);
        }
    }

    /**
     * 设置监听器
     * @param ltn 监听器
     */
    synchronized public void setListener(MsgListener ltn) {
        this.ltn = ltn;
        if (consumer == null) {
            try {
                init();
            } catch (Exception e) {
                logger.error("初始化失败!", e);
            }
        }
        receiveWorker.start();

    }

    /**
     * 管理消息工具方法
     */
    public void close() {
        running = false;

        try {
            if (consumer != null) {
                consumer.close();
                consumer = null;
            }
            if (session != null) {
                session.close();
                session = null;
            }
            if (con != null) {
                con.close();
                con = null;
            }

            receiveWorker = null;
        } catch (JMSException e) {
            logger.warn("消息接收者关闭连接出错");
        }

        ltn = null;

    }

}
