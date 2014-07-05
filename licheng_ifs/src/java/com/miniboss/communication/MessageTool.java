package com.miniboss.communication;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

import javax.jms.*;
import java.io.Serializable;
/**
 * Created by IntelliJ IDEA.
 * User: XIE Feng
 * Date: 2009-12-3
 * Time: 11:32:06
 * To change this template use File | Settings | File Templates.
 */
public class MessageTool extends JmsGatewaySupport {
    private Connection con;

    private Session session;

    private final Object sendLock = new Object();

    private MessageConsumer consumer;

    private Thread receiveWorker;

    private boolean running = true;

    private MsgListener ltn = null;

    public void send(final Serializable msg) {
        if (!running) {
            logger.warn("��ֹͣ���޷�����");
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
                    logger.debug("���� "
                            + getJmsTemplate().getDefaultDestinationName()
                            + " ������Ϣ ��ʱ: "
                            + (System.currentTimeMillis() - start));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
           // System.out.print(e.fillInStackTrace());
            throw new RuntimeException(e);
        }
    }

    public void send(final Serializable msg, int priority) {
        if (!running) {
            logger.warn("��ֹͣ���޷�����");
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
                    logger.debug("���� "
                            + getJmsTemplate().getDefaultDestinationName()
                            + " ������Ϣ ��ʱ: "
                            + (System.currentTimeMillis() - start));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

        // ������첽��ʽ
        if (ltn != null) {

            receiveWorker = new Thread(new Runnable() {
                public void run() {
                    while (running) {
                        // �����첽���յķ�ʽ�����ܶ����Ƕ�һֱ�ȴ�����Ϊ�����Ϳ�����Զû��������ѭ����
                        if (getJmsTemplate().getReceiveTimeout() <= 0) {
                            getJmsTemplate().setReceiveTimeout(10000);
                        }
                        ltn.onMessage(receive());
                    }

                }
            });

        }
    }

    synchronized public Serializable receive() {
        if (!running) {
            logger.warn("��ֹͣ���޷�����");
            // �������Ϸ��أ�����Ҳû��Ϣ��Ъһ���ȡ�
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
                logger.debug("���� "
                        + getJmsTemplate().getDefaultDestinationName()
                        + " ������Ϣ ��ʱ: " + (System.currentTimeMillis() - start));
            }

            if (msg == null)
                return null;

            if (msg instanceof TextMessage) {
                return ((TextMessage) msg).getText();

            } else {
                return ((ObjectMessage) msg).getObject();
            }
        } catch (Exception e) {
            throw new RuntimeException("��ȡ��Ϣ���ݳ���", e);
        }
    }

    // public Serializable receive() {
    // try {
    // Message msg = getJmsTemplate().receive();
    // if (msg == null)
    // return null;
    // if (msg instanceof TextMessage) {
    // return ((TextMessage) msg).getText();
    // } else {
    // return ((ObjectMessage) msg).getObject();
    // }
    // } catch (Exception e) {
    // throw new RuntimeException("��ȡ��Ϣ���ݳ���", e);
    // }
    // }

    synchronized public void setListener(MsgListener ltn) {
        this.ltn = ltn;
        if (consumer == null) {
            try {
                init();
            } catch (Exception e) {
                logger.error("��ʼ��ʧ��!", e);
            }
        }
        receiveWorker.start();

    }

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
            logger.warn("��Ϣ�����߹ر����ӳ���");
        }

        ltn = null;

    }

}

