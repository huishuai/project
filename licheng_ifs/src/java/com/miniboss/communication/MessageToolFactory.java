package com.miniboss.communication;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * Created by IntelliJ IDEA.
 * User: XIE Feng
 * Date: 2009-12-3
 * Time: 11:35:09
 * To change this template use File | Settings | File Templates.
 */
public class MessageToolFactory {
    private static Logger logger = Logger.getLogger(MessageToolFactory.class);

    private Map<Object, MessageTool> tools = new HashMap<Object, MessageTool>();
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
     * @return
     */
    synchronized public MessageTool getTool(Object key) {
        if (key == null)
            throw new IllegalArgumentException("����������Ϊ��");
        MessageTool mt = tools.get(key);
        if (mt == null) {
            mt = new MessageTool();
            JmsTemplate jt = new JmsTemplate();
            if (cf==null) System.out.println("cf is null");
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

    synchronized public MessageTool getTool(Object key, String ip) {
        if (key == null)
            throw new IllegalArgumentException("����������Ϊ��");
        MessageTool mt = tools.get(key + "," + ip);
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
     * @param key
     */
    synchronized public void kick(Object key) {
        if (key == null)
            throw new IllegalArgumentException("����������Ϊ��");
        MessageTool mt = tools.remove(key);
        if (mt != null) {
            mt.close();
        }
    }

    /**
     * �ر�����message tool
     *
     * @throws
     */
    synchronized public void close() {
        logger.info("�ر���Ϣ�ͻ���...");
        for (Iterator<MessageTool> iter = tools.values().iterator(); iter.hasNext();) {
            try {
                (iter.next()).close();
            } catch (Exception e) {
                logger.warn("close error", e);
            }
        }
        logger.info("...�ѹر���Ϣ�ͻ���");
    }

    public ConnectionFactory getCf() {
        return cf;
    }

    public void setCf(ConnectionFactory cf) {
        this.cf = cf;
    }

    public long getReceiveTimeout() {
        return receiveTimeout;
    }

    public void setReceiveTimeout(long receiveTimeout) {
        this.receiveTimeout = receiveTimeout;
    }

    public boolean isTopic() {
        return isTopic;
    }

    public void setTopic(boolean topic) {
        isTopic = topic;
    }
}

