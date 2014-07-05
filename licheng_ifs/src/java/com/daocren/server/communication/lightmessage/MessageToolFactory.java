package com.daocren.server.communication.lightmessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * message tool 工厂类<br/>
 * 所有创建过 的tool 都会被加入缓存，以备后用
 *
 * @author Daocren
 */
public class MessageToolFactory {
    private static Logger logger = Logger.getLogger(MessageToolFactory.class);

    private Map tools = new HashMap();
    private boolean isTopic = false;

    private ConnectionFactory cf;

    /**
     * 收消息超时
     */
    private long receiveTimeout;

    /**
     * 获取针对指定队列的message tool ，如果目前还没有，则马上创建一个
     *
     * @param key
     * @return  消息工具
     */
    synchronized public MessageTool getTool(Object key) {
        if (key == null)
            throw new IllegalArgumentException("队列名不能为空");
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
     * 做消息分发时获取工具方法
     * @param key 队列名称
     * @param ip 获取方IP信息
     * @return 消息工具
     */
    synchronized public MessageTool getTool(Object key, String ip) {
        if (key == null)
            throw new IllegalArgumentException("队列名不能为空");
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
     * 移除并关闭指定队列的message tool
     *
     * @param key 队列名称
     */
    synchronized public void kick(Object key) {
        if (key == null)
            throw new IllegalArgumentException("队列名不能为空");
        MessageTool mt = (MessageTool) tools.remove(key);
        if (mt != null) {
            mt.close();
        }
    }

    /**
     * 关闭所有message tool
     */
    synchronized public void close() {
        logger.info("关闭消息客户端...");
        for (Object o : tools.values()) {
            try {
                ((MessageTool) o).close();
            } catch (Exception e) {
                logger.warn("close error", e);
            }

        }
        logger.info("...已关闭消息客户端");
    }

    /**
     * 获取链接工厂
     * @return 链接工厂
     */
    public ConnectionFactory getCf() {
        return cf;
    }

    /**
     * 设置链接工厂
     * @param cf
     */
    public void setCf(ConnectionFactory cf) {
        this.cf = cf;
    }

    /**
     * 设置获取超时时间
     * @return 超时时间
     */
    public long getReceiveTimeout() {
        return receiveTimeout;
    }

    /**
     * 设置获取超时时间
     * @param receiveTimeout 超时时间
     */
    public void setReceiveTimeout(long receiveTimeout) {
        this.receiveTimeout = receiveTimeout;
    }

    /**
     * 判断是否为主题模式
     * @return  是否为主题模式
     */
    public boolean isTopic() {
        return isTopic;
    }

    /**
     * 设置主题模式
     * @param topic
     */
    public void setTopic(boolean topic) {
        isTopic = topic;
    }
}
