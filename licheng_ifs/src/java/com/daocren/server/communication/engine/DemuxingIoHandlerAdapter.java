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
 * ��·��Ϣ����������
 *
 * @author Daocren
 */
public abstract class DemuxingIoHandlerAdapter extends DemuxingIoHandler {
    private String name;
    /**
     * ��־logger
     */
    protected static final Logger logger = Logger.getLogger(DemuxingIoHandlerAdapter.class);

    // ���ڴ�С
    protected int windowSize = 5;

    protected MessageToolFactory toolMgr;

    protected int remoteQname = -1;

    private int idleTime = 5;// ����Ϊ��λ

    // �������б�
    private List filters = new ArrayList();

    // �����Ƿ�Ϊ˫��
    private boolean bidirect = true;

    /**
     * ���ض������ƣ�����connector������ͨ���������ָ��ȡ��Ϣ�Ķ�������
     */
    protected String localQname;

    /**
     * session�Ͽ��������Ƿ��ط���Ĭ��trueҪ�ط�
     */
    protected boolean reSend = true;
    protected SessionHandlerConfig sessionHandlerConfig = new SessionHandlerConfig();
    protected SessionsContainer sessionsContainer = new SessionsContainer();

    /**
     * ��ȡ��Ϣ������
     *
     * @return ��Ϣ������
     */
    public MessageToolFactory getToolMgr() {
        return toolMgr;
    }

    /**
     * ������Ϣ������
     *
     * @param toolMgr ��Ϣ������
     */
    public void setToolMgr(MessageToolFactory toolMgr) {
        this.toolMgr = toolMgr;
    }

    /**
     * ��ȡ�Ƿ�������Ϣ����ʧ�ܺ����·���
     *
     * @return ������
     */
    public boolean isReSend() {
        return reSend;
    }

    /**
     * �����Ƿ����������ʧ�ܺ����·���
     *
     * @param reSend ���ý��
     */
    public void setReSend(boolean reSend) {
        this.reSend = reSend;
    }

    /**
     * �쳣������ص�����
     *
     * @param session �Ի�
     * @param arg1    �����쳣
     * @throws Exception �쳣��Ϣ
     */
    public void exceptionCaught(IoSession session, Throwable arg1)
            throws Exception {
        logger.warn(name + " session @" + session.getRemoteAddress() + " ����", arg1);
        session.close();
    }

    /**
     * �Ի��رջص�����<br/>
     * �Ƴ�session����Ӧ��Ϣ
     *
     * @param session �Ի�
     * @throws Exception �쳣��Ϣ
     */
    public void sessionClosed(IoSession session) throws Exception {
        logger.info(name + "�ر�session: @" + session.getRemoteAddress() + "-"
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
            logger.warn("û��send controler");
        }

    }

    /**
     * �Ի��򿪻ص�����
     *
     * @param session �Ի�
     * @throws Exception �쳣��Ϣ
     */
    public void sessionOpened(IoSession session) throws Exception {
        logger.info(name + "����session: " + session.getRemoteAddress() + "-"
                + session.getLocalAddress() + ", �������� " + windowSize + ", ���͡�"
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
     * ��ȡ����ͬʱ����Ĵ�������
     *
     * @return ��������
     */
    public int getWindowSize() {
        return windowSize;
    }

    /**
     * ���ÿ���ͬʱ����Ĵ�������
     *
     * @param windowSize ��������
     */
    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    /**
     * ��ȡ��ʱʱ����Ϣ
     *
     * @return ��ʱʱ��
     */
    public int getIdleTime() {
        return idleTime;
    }

    /**
     * ���ó�ʱʱ����Ϣ
     *
     * @param idleTime ��ʱʱ��
     */
    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

    /**
     * ��ȡ�������б���Ϣ
     *
     * @return �������б�
     */
    public List getFilters() {
        return filters;
    }

    /**
     * ���ù������б���Ϣ
     *
     * @param filters �������б�
     */
    public void setFilters(List filters) {
        this.filters = filters;
    }

    /**
     * ��ȡ��Ҫ��ȡ��Զ�̶�������
     *
     * @return Զ�̶�������
     */
    public int getRemoteQname() {
        return remoteQname;
    }

    /**
     * ������Ҫ��ȡ��Զ�̶�������
     *
     * @param remoteQname Զ�̶�������
     */
    public void setRemoteQname(int remoteQname) {
        this.remoteQname = remoteQname;
    }

    /**
     * ��ȡ�Ƿ�Ϊ˫���ͻ���
     *
     * @return ˫����
     */
    public boolean isBidirect() {
        return bidirect;
    }

    /**
     * �����Ƿ�Ϊ˫���ͻ���
     *
     * @param bidirect ˫����
     */
    public void setBidirect(boolean bidirect) {
        this.bidirect = bidirect;
    }

    /**
     * ��ȡ��Ҫ���͵ı��ض�������
     *
     * @return ���ض�������
     */
    public String getLocalQname() {
        return localQname;
    }

    /**
     * ������Ҫ���͵ı��ض�������
     *
     * @param name ���ض�������
     */
    public void setLocalQname(String name) {
        localQname = name;
    }

    /**
     * ��ȡ����
     *
     * @return ����
     */
    public String getName() {
        return name;
    }

    /**
     * ��������
     *
     * @param name ����
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��ȡ�Ի�������Ϣ
     *
     * @return �Ի�������Ϣ
     */
    public SessionHandlerConfig getSessionHandlerConfig() {
        return sessionHandlerConfig;
    }

    /**
     * ���öԻ�������Ϣ
     *
     * @param sessionHandlerConfig �Ի�������Ϣ
     */
    public void setSessionHandlerConfig(SessionHandlerConfig sessionHandlerConfig) {
        this.sessionHandlerConfig = sessionHandlerConfig;
    }

    /**
     * ��ȡ�Ի�����
     *
     * @return �Ի�����
     */
    public SessionsContainer getSessionsContainer() {
        return sessionsContainer;
    }

    /**
     * ���öԻ�����
     *
     * @param sessionsContainer �Ի�����
     */
    public void setSessionsContainer(SessionsContainer sessionsContainer) {
        this.sessionsContainer = sessionsContainer;
    }

    public void destroy() {
        List<IoSession> list = sessionsContainer.getIoSessions();
        for (IoSession session : list) {
            try {
                logger.debug("�ر�session!");
                sessionClosed(session);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
