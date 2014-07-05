package com.daocren.server.communication.filter;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.MsgConstant;
import org.apache.log4j.Logger;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoSession;

/**
 * 日志过滤器<br/>
 * 提供对消息处理工程中的日志监控
 */
public class LoggingFilter extends org.apache.mina.filter.LoggingFilter {
    public static final Logger logger = Logger.getLogger(LoggingFilter.class);

    /**
     * 对话创建回调方法
     *
     * @param nextFilter 下一个过滤器
     * @param ioSession  对话
     */
    public void sessionCreated(NextFilter nextFilter, IoSession ioSession) {
        nextFilter.sessionCreated(ioSession);
    }

    /**
     * 对话打开回调方法
     *
     * @param nextFilter 下一个过滤器
     * @param ioSession  对话
     */
    public void sessionOpened(NextFilter nextFilter, IoSession ioSession) {
        nextFilter.sessionOpened(ioSession);
    }

    /**
     * 对话关闭回调方法
     *
     * @param nextFilter 下一个过滤器
     * @param ioSession  对话
     */
    public void sessionClosed(NextFilter nextFilter, IoSession ioSession) {
        nextFilter.sessionClosed(ioSession);
    }

    /**
     * 对话超时回调方法
     *
     * @param nextFilter 下一个过滤器
     * @param ioSession  对话
     */
    public void sessionIdle(NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) {
        nextFilter.sessionIdle(ioSession, idleStatus);
    }

    /**
     * 异常捕获回调方法
     *
     * @param nextFilter 下一个过滤器
     * @param ioSession  对话
     * @param throwable  异常信息
     */
    public void exceptionCaught(NextFilter nextFilter, IoSession ioSession, Throwable throwable) {
        nextFilter.exceptionCaught(ioSession, throwable);
    }

    /**
     * 消息发送回调方法
     *
     * @param nextFilter 下一个过滤器
     * @param ioSession  对话
     * @param o          处理消息
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
     * 过滤器写入回调方法
     *
     * @param nextFilter   下一个过滤器
     * @param ioSession    对话
     * @param writeRequest 写入请求
     */
    public void filterWrite(NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) {
        nextFilter.filterWrite(ioSession, writeRequest);
    }


    /**
     * 过滤器关闭回调方法
     *
     * @param nextFilter 下一个过滤器
     * @param ioSession  对话
     */
    public void filterClose(NextFilter nextFilter, IoSession ioSession) throws Exception {
        nextFilter.filterClose(ioSession);
    }

    /**
     * 消息接收回调方法
     *
     * @param nextFilter 下一个过滤器
     * @param ioSession  对话
     * @param o          处理消息
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
