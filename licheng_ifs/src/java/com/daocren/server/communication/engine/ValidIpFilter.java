package com.daocren.server.communication.engine;

import org.apache.log4j.Logger;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoFilterAdapter;
import org.apache.mina.common.IoSession;

import java.util.HashSet;
import java.util.Set;

/**
 * ip��ַ��������
 * @author Daocren
 */
public class ValidIpFilter extends IoFilterAdapter {
    private final static Logger logger = Logger.getLogger(ValidIpFilter.class);

    private Set<String> validIps = new HashSet<String>();

    /**
     * ��Ϣ���ջص�����
     * @param nextFilter ��һ��������
     * @param session �Ի�
     * @param message ��Ϣ
     */
    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        if (valid(session)) {
            super.messageReceived(nextFilter, session, message);
        } else {
            blockSession(session);
        }
    }

    /**
     * ��Ϣ���ͻص�����
     * @param nextFilter ��һ��������
     * @param session �Ի�
     * @param message ��Ϣ
     */
    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        if (valid(session)) {
            super.messageSent(nextFilter, session, message);
        } else {
            blockSession(session);
        }
    }

    /**
     * �Ի��رջص�����
     * @param nextFilter ��һ��������
     * @param session �Ի�
     */
    @Override
    public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {
        if (valid(session)) {
            super.sessionClosed(nextFilter, session);
        } else {
            blockSession(session);
        }
    }

    /**
     * �Ի������ص�����
     * @param nextFilter ��һ��������
     * @param session �Ի�
     */
    @Override
    public void sessionCreated(NextFilter nextFilter, IoSession session) throws Exception {
        if (valid(session)) {
            super.sessionCreated(nextFilter, session);
        } else {
            blockSession(session);
        }
    }

    /**
     * �Ի���ʱ�ص�����
     * @param nextFilter ��һ��������
     * @param session �Ի�
     * @throws status ״̬��Ϣ
     */
    @Override
    public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception {
        if (valid(session)) {
            super.sessionIdle(nextFilter, session, status);
        } else {
            blockSession(session);
        }
    }

    /**
     * �Ի��򿪻ص�����
     * @param nextFilter ��һ��������
     * @param session �Ի�
     */
    @Override
    public void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception {
        if (valid(session)) {
            super.sessionOpened(nextFilter, session);
        } else {
            blockSession(session);
        }
    }

    /**
     * ���úϷ�ip�б�
     *
     * @param validIps ��Чip����
     */
    public void setValidIps(Set<String> validIps) {
        this.validIps = validIps;
    }

    /**
     * ��֤���session��Զ��ip�Ƿ�Ϸ�
     *
     * @param session �Ի�
     * @return true��ʾ�Ϸ�
     */
    private boolean valid(IoSession session) {
        String ip = session.getRemoteAddress().toString();
        return validIps.contains(ip.substring(1, ip.indexOf(":")));
    }

    private void blockSession(IoSession session) {
        logger.warn("�Ƿ���ip���ӣ�" + session.getRemoteAddress());
        session.close();
    }
}
