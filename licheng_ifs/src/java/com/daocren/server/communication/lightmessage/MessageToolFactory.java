package com.daocren.server.communication.lightmessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * message tool ������<br/>
 * ���д����� ��tool ���ᱻ���뻺�棬�Ա�����
 *
 * @author Daocren
 */
public class MessageToolFactory {
    private static Logger logger = Logger.getLogger(MessageToolFactory.class);

    private Map tools = new HashMap();
    private boolean isTopic = false;

    private ConnectionFactory cf;

    /**
     * ����Ϣ��ʱ
     */
    private long receiveTimeout;

    /**
     * ��ȡ���ָ�����е�message tool �����Ŀǰ��û�У������ϴ���һ��
     *
     * @param key
     * @return  ��Ϣ����
     */
    synchronized public MessageTool getTool(Object key) {
        if (key == null)
            throw new IllegalArgumentException("����������Ϊ��");
        MessageTool mt = (MessageTool) tools.get(key);
        if (mt == null) {
            mt = new MessageTool();
            JmsTemplate jt = new JmsTemplate();
            jt.setConnectionFactory(cf);
            if (isTopic) {
                jt.setPubSubDomain(true);
                jt.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                jt.setDeliveryPersistent(false);
            }
            jt.setDefaultDestinationName((String) key);
            jt.setReceiveTimeout(receiveTimeout);
            mt.setJmsTemplate(jt);
            tools.put(key, mt);
        }
        return mt;
    }

    /**
     * ����Ϣ�ַ�ʱ��ȡ���߷���
     * @param key ��������
     * @param ip ��ȡ��IP��Ϣ
     * @return ��Ϣ����
     */
    synchronized public MessageTool getTool(Object key, String ip) {
        if (key == null)
            throw new IllegalArgumentException("����������Ϊ��");
        MessageTool mt = (MessageTool) tools.get(key + "," + ip);
        if (mt == null) {
            mt = new MessageTool();
            JmsTemplate jt = new JmsTemplate();
            jt.setConnectionFactory(cf);
            if (isTopic) {
                jt.setPubSubDomain(true);
                jt.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                jt.setDeliveryPersistent(false);
            }
            jt.setDefaultDestinationName((String) key);
            jt.setReceiveTimeout(receiveTimeout);
            jt.setTimeToLive(20);
            mt.setJmsTemplate(jt);
            tools.put(key + "," + ip, mt);
        }
        return mt;
    }

    /**
     * �Ƴ����ر�ָ�����е�message tool
     *
     * @param key ��������
     */
    synchronized public void kick(Object key) {
        if (key == null)
            throw new IllegalArgumentException("����������Ϊ��");
        MessageTool mt = (MessageTool) tools.remove(key);
        if (mt != null) {
            mt.close();
        }
    }

    /**
     * �ر�����message tool
     */
    synchronized public void close() {
        logger.info("�ر���Ϣ�ͻ���...");
        for (Object o : tools.values()) {
            try {
                ((MessageTool) o).close();
            } catch (Exception e) {
                logger.warn("close error", e);
            }

        }
        logger.info("...�ѹر���Ϣ�ͻ���");
    }

    /**
     * ��ȡ���ӹ���
     * @return ���ӹ���
     */
    public ConnectionFactory getCf() {
        return cf;
    }

    /**
     * �������ӹ���
     * @param cf
     */
    public void setCf(ConnectionFactory cf) {
        this.cf = cf;
    }

    /**
     * ���û�ȡ��ʱʱ��
     * @return ��ʱʱ��
     */
    public long getReceiveTimeout() {
        return receiveTimeout;
    }

    /**
     * ���û�ȡ��ʱʱ��
     * @param receiveTimeout ��ʱʱ��
     */
    public void setReceiveTimeout(long receiveTimeout) {
        this.receiveTimeout = receiveTimeout;
    }

    /**
     * �ж��Ƿ�Ϊ����ģʽ
     * @return  �Ƿ�Ϊ����ģʽ
     */
    public boolean isTopic() {
        return isTopic;
    }

    /**
     * ��������ģʽ
     * @param topic
     */
    public void setTopic(boolean topic) {
        isTopic = topic;
    }
}
