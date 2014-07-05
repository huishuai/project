package com.daocren.server.communication.filter;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.MsgConstant;
import org.apache.log4j.Logger;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoSession;

/**
 * ��־������<br/>
 * �ṩ����Ϣ�������е���־���
 */
public class LoggingFilter extends org.apache.mina.filter.LoggingFilter {
    public static final Logger logger = Logger.getLogger(LoggingFilter.class);

    /**
     * �Ի������ص�����
     *
     * @param nextFilter ��һ��������
     * @param ioSession  �Ի�
     */
    public void sessionCreated(NextFilter nextFilter, IoSession ioSession) {
        nextFilter.sessionCreated(ioSession);
    }

    /**
     * �Ի��򿪻ص�����
     *
     * @param nextFilter ��һ��������
     * @param ioSession  �Ի�
     */
    public void sessionOpened(NextFilter nextFilter, IoSession ioSession) {
        nextFilter.sessionOpened(ioSession);
    }

    /**
     * �Ի��رջص�����
     *
     * @param nextFilter ��һ��������
     * @param ioSession  �Ի�
     */
    public void sessionClosed(NextFilter nextFilter, IoSession ioSession) {
        nextFilter.sessionClosed(ioSession);
    }

    /**
     * �Ի���ʱ�ص�����
     *
     * @param nextFilter ��һ��������
     * @param ioSession  �Ի�
     */
    public void sessionIdle(NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) {
        nextFilter.sessionIdle(ioSession, idleStatus);
    }

    /**
     * �쳣����ص�����
     *
     * @param nextFilter ��һ��������
     * @param ioSession  �Ի�
     * @param throwable  �쳣��Ϣ
     */
    public void exceptionCaught(NextFilter nextFilter, IoSession ioSession, Throwable throwable) {
        nextFilter.exceptionCaught(ioSession, throwable);
    }

    /**
     * ��Ϣ���ͻص�����
     *
     * @param nextFilter ��һ��������
     * @param ioSession  �Ի�
     * @param o          ������Ϣ
     */
    public void messageSent(NextFilter nextFilter, IoSession ioSession, Object o) {
        String sessionName = (String) ioSession.getAttribute(MsgConstant.KEY_CONNECTOR_NAME);
        String msg = o.toString();
        int flag = getFlag(msg);
        if (msg.contains("[pos=0 lim=0 cap=0: empty]") ||
                flag == Constant.KEEP_ALIVE_REQ ||
                flag == Constant.KEEP_ALIVE_RES) {
        } else if (flag == Constant.BIND_RES) {
            logger.debug(sessionName + ">[BIND_RES]" + msg);
        } else if (flag == Constant.BIND_REQ) {
            logger.debug(sessionName + ">[BIND_REQ]" + msg);
        } else {
            logger.debug(sessionName + ">[*********]" + msg);
        }
        nextFilter.messageSent(ioSession, o);
    }

    /**
     * ������д��ص�����
     *
     * @param nextFilter   ��һ��������
     * @param ioSession    �Ի�
     * @param writeRequest д������
     */
    public void filterWrite(NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) {
        nextFilter.filterWrite(ioSession, writeRequest);
    }


    /**
     * �������رջص�����
     *
     * @param nextFilter ��һ��������
     * @param ioSession  �Ի�
     */
    public void filterClose(NextFilter nextFilter, IoSession ioSession) throws Exception {
        nextFilter.filterClose(ioSession);
    }

    /**
     * ��Ϣ���ջص�����
     *
     * @param nextFilter ��һ��������
     * @param ioSession  �Ի�
     * @param o          ������Ϣ
     */
    public void messageReceived(NextFilter nextFilter, IoSession ioSession, Object o) {
        String sessionName = (String) ioSession.getAttribute(MsgConstant.KEY_CONNECTOR_NAME);
        String msg = o.toString();
        int flag = getFlag(msg);
        if (msg.contains("[pos=0 lim=0 cap=0: empty]") ||
                flag == Constant.KEEP_ALIVE_REQ ||
                flag == Constant.KEEP_ALIVE_RES) {
        } else if (flag == Constant.BIND_RES) {
            logger.debug(sessionName + "<[BIND_RES]" + msg);
        } else if (flag == Constant.BIND_REQ) {
            logger.debug(sessionName + "<[BIND_REQ]" + msg);
        } else {
            logger.debug(sessionName + "<[*********]" + msg);
        }
        nextFilter.messageReceived(ioSession, o);
    }

    private static int getFlag(String msg) {
        //DirectBuffer[pos=0 lim=323 cap=512: 00 00 01 43 00 04
        int commarIndex = msg.indexOf(":");
        if (commarIndex != -1 && commarIndex + 19 <= msg.length()) {
            String ret = msg.substring(commarIndex + 14, msg.indexOf(":") + 19);
            ret = ret.replaceAll(" ", "");
            return Integer.parseInt(ret, 16);
        }
        return 0;
    }
}
