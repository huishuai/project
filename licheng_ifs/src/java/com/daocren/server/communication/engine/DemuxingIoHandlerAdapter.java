package com.daocren.server.communication.engine;

import com.daocren.server.communication.lightmessage.MessageToolFactory;
import com.daocren.server.communication.message.MsgConstant;
import com.daocren.server.communication.sync.SessionsContainer;
import com.daocren.server.communication.util.SendControler;
import org.apache.log4j.Logger;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoSession;
import org.apache.mina.handler.demux.DemuxingIoHandler;

import static java.lang.Integer.valueOf;
import java.util.ArrayList;
import java.util.List;

/**
 * 多路消息处理适配器
 *
 * @author Daocren
 */
public abstract class DemuxingIoHandlerAdapter extends DemuxingIoHandler {
    private String name;
    /**
     * 日志logger
     */
    protected static final Logger logger = Logger.getLogger(DemuxingIoHandlerAdapter.class);

    // 窗口大小
    protected int windowSize = 5;

    protected MessageToolFactory toolMgr;

    protected int remoteQname = -1;

    private int idleTime = 5;// 以秒为单位

    // 过滤器列表
    private List filters = new ArrayList();

    // 连接是否为双向
    private boolean bidirect = true;

    /**
     * 本地队列名称，对于connector，可以通过这个属性指定取消息的队列名称
     */
    protected String localQname;

    /**
     * session断开重连后是否重发，默认true要重发
     */
    protected boolean reSend = true;
    protected SessionHandlerConfig sessionHandlerConfig = new SessionHandlerConfig();
    protected SessionsContainer sessionsContainer = new SessionsContainer();

    /**
     * 获取消息处理工厂
     *
     * @return 消息处理工厂
     */
    public MessageToolFactory getToolMgr() {
        return toolMgr;
    }

    /**
     * 设置消息处理工厂
     *
     * @param toolMgr 消息处理工厂
     */
    public void setToolMgr(MessageToolFactory toolMgr) {
        this.toolMgr = toolMgr;
    }

    /**
     * 获取是否允许消息发送失败后重新发送
     *
     * @return 允许结果
     */
    public boolean isReSend() {
        return reSend;
    }

    /**
     * 设置是否可以允许发送失败后重新发送
     *
     * @param reSend 设置结果
     */
    public void setReSend(boolean reSend) {
        this.reSend = reSend;
    }

    /**
     * 异常产生后回调方法
     *
     * @param session 对话
     * @param arg1    捕获异常
     * @throws Exception 异常信息
     */
    public void exceptionCaught(IoSession session, Throwable arg1)
            throws Exception {
        logger.warn(name + " session @" + session.getRemoteAddress() + " 出错", arg1);
        session.close();
    }

    /**
     * 对话关闭回调方法<br/>
     * 移除session中相应信息
     *
     * @param session 对话
     * @throws Exception 异常信息
     */
    public void sessionClosed(IoSession session) throws Exception {
        logger.info(name + "关闭session: @" + session.getRemoteAddress() + "-"
                + session.getLocalAddress());
        if (session.getAttachment() != null) {
            ((SendControler) session.getAttachment()).stop();
            session.setAttachment(null);
            session.removeAttribute(MsgConstant.KEY_TMGR);
            session.removeAttribute(MsgConstant.KEY_WINDOWSIZE);
            session.removeAttribute(MsgConstant.KEY_FILTERS);
            session.removeAttribute(MsgConstant.KEY_CON_TYPE);
            session.removeAttribute(MsgConstant.KEY_BIDI);
            session.removeAttribute(MsgConstant.KEY_RESEND);
            session.removeAttribute(MsgConstant.KEY_CONNECTOR_NAME);
        } else {
            logger.warn("没有send controler");
        }

    }

    /**
     * 对话打开回调方法
     *
     * @param session 对话
     * @throws Exception 异常信息
     */
    public void sessionOpened(IoSession session) throws Exception {
        logger.info(name + "创建session: " + session.getRemoteAddress() + "-"
                + session.getLocalAddress() + ", 滑动窗口 " + windowSize + ", 类型　"
                + remoteQname);
        session.setIdleTime(IdleStatus.BOTH_IDLE, idleTime);
        if (toolMgr != null) {
            session.setAttribute(MsgConstant.KEY_TMGR, toolMgr);
        }
        session.setAttribute(MsgConstant.KEY_WINDOWSIZE, valueOf(windowSize));
        session.setAttribute(MsgConstant.KEY_FILTERS, filters);
        session.setAttribute(MsgConstant.KEY_CON_TYPE, remoteQname);
        session.setAttribute(MsgConstant.KEY_BIDI, bidirect);
        session.setAttribute(MsgConstant.KEY_RESEND, reSend);
    }

    /**
     * 获取可以同时处理的窗口数量
     *
     * @return 窗口数量
     */
    public int getWindowSize() {
        return windowSize;
    }

    /**
     * 设置可以同时处理的窗口数量
     *
     * @param windowSize 窗口数量
     */
    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    /**
     * 获取超时时间信息
     *
     * @return 超时时间
     */
    public int getIdleTime() {
        return idleTime;
    }

    /**
     * 设置超时时间信息
     *
     * @param idleTime 超时时间
     */
    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

    /**
     * 获取过滤器列表信息
     *
     * @return 过滤器列表
     */
    public List getFilters() {
        return filters;
    }

    /**
     * 设置过滤器列表信息
     *
     * @param filters 过滤器列表
     */
    public void setFilters(List filters) {
        this.filters = filters;
    }

    /**
     * 获取需要获取的远程队列名称
     *
     * @return 远程队列名称
     */
    public int getRemoteQname() {
        return remoteQname;
    }

    /**
     * 设置需要获取的远程队列名称
     *
     * @param remoteQname 远程队列名称
     */
    public void setRemoteQname(int remoteQname) {
        this.remoteQname = remoteQname;
    }

    /**
     * 获取是否为双向发送机制
     *
     * @return 双向标记
     */
    public boolean isBidirect() {
        return bidirect;
    }

    /**
     * 设置是否为双向发送机制
     *
     * @param bidirect 双向标记
     */
    public void setBidirect(boolean bidirect) {
        this.bidirect = bidirect;
    }

    /**
     * 获取需要发送的本地队列名称
     *
     * @return 本地队列名称
     */
    public String getLocalQname() {
        return localQname;
    }

    /**
     * 设置需要发送的本地队列名称
     *
     * @param name 本地队列名称
     */
    public void setLocalQname(String name) {
        localQname = name;
    }

    /**
     * 获取名称
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取对话配置信息
     *
     * @return 对话配置信息
     */
    public SessionHandlerConfig getSessionHandlerConfig() {
        return sessionHandlerConfig;
    }

    /**
     * 设置对话配置信息
     *
     * @param sessionHandlerConfig 对话配置信息
     */
    public void setSessionHandlerConfig(SessionHandlerConfig sessionHandlerConfig) {
        this.sessionHandlerConfig = sessionHandlerConfig;
    }

    /**
     * 获取对话容器
     *
     * @return 对话容器
     */
    public SessionsContainer getSessionsContainer() {
        return sessionsContainer;
    }

    /**
     * 设置对话容器
     *
     * @param sessionsContainer 对话容器
     */
    public void setSessionsContainer(SessionsContainer sessionsContainer) {
        this.sessionsContainer = sessionsContainer;
    }

    public void destroy() {
        List<IoSession> list = sessionsContainer.getIoSessions();
        for (IoSession session : list) {
            try {
                logger.debug("关闭session!");
                sessionClosed(session);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
