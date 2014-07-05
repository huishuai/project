package com.daocren.server.communication.filter;

import com.daocren.server.communication.lightmessage.MessageToolFactory;
import com.daocren.server.communication.message.AbstractMsg;
import org.apache.log4j.Logger;

/**
 * jms入队处理器 将其配置于责任链中，即可实现消息jms排队
 *
 * @author Daocren
 */
public class EnterQueueFilter implements MsgFilter {
    private static final Logger logger = Logger.getLogger(EnterQueueFilter.class);

    private MessageToolFactory toolMgr;

    /**
     * 队列名称
     */
    private String qname = "-1";

    /**
     * 优先级
     */
    private int priority = 5;

    /**
     * 处理消息方法
     * @param msg 处理消息
     * @return 处理成功结果
     */
    public boolean dealMessage(AbstractMsg msg) {
        try {
            toolMgr.getTool(qname).send(msg, priority);
        } catch (Exception e) {
            logger.fatal("保存消息出错，无法继续服务", e);
            System.exit(0);
        }
        return false;
    }

    /**
     * 获取消息处理工厂
     * @return 消息处理工厂
     */
    public MessageToolFactory getToolMgr() {
        return toolMgr;
    }

    /**
     * 设置消息处理工厂
     * @param toolMgr 消息处理工厂
     */
    public void setToolMgr(MessageToolFactory toolMgr) {
        this.toolMgr = toolMgr;
    }

    /**
     * 设置注入队列名称
     * @return 注入队列名称
     */
    public String getQname() {
        return qname;
    }

    /**
     * 设置注入队列名称
     * @param name 注入队列名称
     */
    public void setQname(String name) {
        qname = name;
    }

    /**
     * 获取入队优先级
     * @return 入队优先级
     */
    public int getPriority() {
        return priority;
    }

    /**
     * 获取入队优先级
     * @param priority 入队优先级
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

}
