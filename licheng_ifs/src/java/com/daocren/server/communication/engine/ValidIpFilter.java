package com.daocren.server.communication.engine;

import org.apache.log4j.Logger;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoFilterAdapter;
import org.apache.mina.common.IoSession;

import java.util.HashSet;
import java.util.Set;

/**
 * ip地址过过滤器
 * @author Daocren
 */
public class ValidIpFilter extends IoFilterAdapter {
    private final static Logger logger = Logger.getLogger(ValidIpFilter.class);

    private Set<String> validIps = new HashSet<String>();

    /**
     * 消息接收回调方法
     * @param nextFilter 下一个过滤器
     * @param session 对话
     * @param message 消息
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
     * 消息发送回调方法
     * @param nextFilter 下一个过滤器
     * @param session 对话
     * @param message 消息
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
     * 对话关闭回调方法
     * @param nextFilter 下一个过滤器
     * @param session 对话
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
     * 对话创建回调方法
     * @param nextFilter 下一个过滤器
     * @param session 对话
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
     * 对话超时回调方法
     * @param nextFilter 下一个过滤器
     * @param session 对话
     * @throws status 状态信息
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
     * 对话打开回调方法
     * @param nextFilter 下一个过滤器
     * @param session 对话
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
     * 设置合法ip列表
     *
     * @param validIps 有效ip集合
     */
    public void setValidIps(Set<String> validIps) {
        this.validIps = validIps;
    }

    /**
     * 验证这个session的远程ip是否合法
     *
     * @param session 对话
     * @return true表示合法
     */
    private boolean valid(IoSession session) {
        String ip = session.getRemoteAddress().toString();
        return validIps.contains(ip.substring(1, ip.indexOf(":")));
    }

    private void blockSession(IoSession session) {
        logger.warn("非法的ip连接：" + session.getRemoteAddress());
        session.close();
    }
}
