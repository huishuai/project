package com.daocren.server.communication.filter;

import com.daocren.server.communication.lightmessage.MessageToolFactory;
import com.daocren.server.communication.message.AbstractMsg;
import org.apache.log4j.Logger;

/**
 * jms��Ӵ����� �����������������У�����ʵ����Ϣjms�Ŷ�
 *
 * @author Daocren
 */
public class EnterQueueFilter implements MsgFilter {
    private static final Logger logger = Logger.getLogger(EnterQueueFilter.class);

    private MessageToolFactory toolMgr;

    /**
     * ��������
     */
    private String qname = "-1";

    /**
     * ���ȼ�
     */
    private int priority = 5;

    /**
     * ������Ϣ����
     * @param msg ������Ϣ
     * @return ����ɹ����
     */
    public boolean dealMessage(AbstractMsg msg) {
        try {
            toolMgr.getTool(qname).send(msg, priority);
        } catch (Exception e) {
            logger.fatal("������Ϣ�����޷���������", e);
            System.exit(0);
        }
        return false;
    }

    /**
     * ��ȡ��Ϣ������
     * @return ��Ϣ������
     */
    public MessageToolFactory getToolMgr() {
        return toolMgr;
    }

    /**
     * ������Ϣ������
     * @param toolMgr ��Ϣ������
     */
    public void setToolMgr(MessageToolFactory toolMgr) {
        this.toolMgr = toolMgr;
    }

    /**
     * ����ע���������
     * @return ע���������
     */
    public String getQname() {
        return qname;
    }

    /**
     * ����ע���������
     * @param name ע���������
     */
    public void setQname(String name) {
        qname = name;
    }

    /**
     * ��ȡ������ȼ�
     * @return ������ȼ�
     */
    public int getPriority() {
        return priority;
    }

    /**
     * ��ȡ������ȼ�
     * @param priority ������ȼ�
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

}
