package com.daocren.server.communication.engine;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.codec.CodecFactory;
import com.daocren.server.communication.filter.LoggingFilter;
import com.daocren.server.communication.handler.KeepAliveHandler;
import com.daocren.server.communication.handler.ResponseHandler;
import com.daocren.server.communication.lightmessage.MessageTool;
import com.daocren.server.communication.message.BindReq;
import com.daocren.server.communication.message.CommonMsg;
import com.daocren.server.communication.message.KeepAlive;
import com.daocren.server.communication.message.MsgConstant;
import com.daocren.server.communication.util.SendControler;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.handler.demux.MessageHandler;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * �ڲ�Э��ͻ��˴�����
 *
 * @author Daocren
 */
public class ClientSessionHandler extends DemuxingIoHandlerAdapter {

    private Class keepAliveClass;

    public ClientSessionHandler() {
        setName("client");
    }

    // �����������ִ��������Ϊ��֤��
    private static final String xorSource = "txblxdyy";

    private Random rand = new Random();

    //������Ϣʱ�������Ϣ��ʱ100�룬�����˶���
    private String timeoutQname = "-99";

    /**
     * ��ȡ���ӳ�ʱ������Ϣ
     *
     * @return ���ӳ�ʱ������Ϣ
     */
    public String getTimeoutQname() {
        return timeoutQname;
    }

    /**
     * �������ӳ�ʱ��������
     *
     * @param timeoutQname ������
     */
    public void setTimeoutQname(String timeoutQname) {
        this.timeoutQname = timeoutQname;
    }


    /**
     * �Ի������ɹ��ص�����<br/>
     * ָ�����빤��������ӻ�������Ϣ��Ӧ����
     *
     * @param session �Ի�
     * @throws Exception һ����Ϣ
     */
    @SuppressWarnings(value = "unchecked")
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        CodecFactory codecFactory = new CodecFactory(false);
        SessionHandlerConfig config = getSessionHandlerConfig();
        List<Class> list = config.getCoders();
        for (Class clazz : list) {
            codecFactory.register(clazz);
        }
        session.getFilterChain().addLast("protocolFilter", new ProtocolCodecFilter(codecFactory));
        session.getFilterChain().addFirst("logger", new LoggingFilter());

        Map<Class, MessageHandler> classAbstractMsgHandlerMap = config.getClazzHandlerMap();
        for (Class aClass : classAbstractMsgHandlerMap.keySet()) {
            addMessageHandler(aClass, classAbstractMsgHandlerMap.get(aClass));
        }
        addMessageHandler(CommonMsg.class, new ResponseHandler());
        addMessageHandler(KeepAlive.class, new KeepAliveHandler());
        setKeepAliveClass(config.getKeepAliveClass());

    }

    /**
     * �Ի����ûص�����<br/>
     * �Ի�����ʱ��ÿ��һ��ʱ�䷢�ͷ���ʱ��Ϣ
     *
     * @param session �Ի�
     * @param status  ״̬
     * @throws Exception �쳣��Ϣ
     */
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        // logger.debug("���ͷ���ʱ��Ϣ@" + session.getRemoteAddress());
        if (keepAliveClass != null) {
            session.write(keepAliveClass.newInstance());
        } else {
            KeepAlive req = new KeepAlive();
            req.setCmdType(Constant.KEEP_ALIVE_REQ);
            session.write(req);
        }
    }

    /**
     * �Ի��رջص�����
     *
     * @param session �Ի�
     * @throws Exception һ����Ϣ
     */
    public void sessionClosed(IoSession session) throws Exception {
        sessionsContainer.deleteSession((String) session.getAttribute(MsgConstant.KEY_CONNECTOR_NAME), session);
        super.sessionClosed(session);
    }

    /**
     * �Ի��򿪻ص�����<br/>
     * ���Ͱ�������Ϣ<br/>
     * ��ʼ���Ի�session��Ϣ
     *
     * @param session �Ի�
     * @throws Exception �쳣��Ϣ
     */
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        if (localQname != null && localQname.length() > 0) {
            if (toolMgr != null) {
                MessageTool tool = toolMgr.getTool(localQname);
                SendControler sender = new SendControler(session, tool, windowSize, toolMgr.getTool(timeoutQname));
                session.setAttachment(sender);
                if (getRemoteQname() < 0) {
                    BindReq req = new BindReq();
                    rand.nextBytes(req.getRandCode());
                    for (int i = 0; i < xorSource.length(); i++) {
                        req.getRandAuth()[i] = (byte) (req.getRandCode()[i] ^ ((byte) xorSource.charAt(i)));
                    }
                    // ��session�����ӡ����͡�������ԣ�ͨ����Ϊ��http����tcp
                    req.setReqQname(getRemoteQname());
                    sender.trySend(req);
                }
            }
        }
    }

    /**
     * ���ñ������ӵ���Ϣ����
     *
     * @param keepAliveClass �������ӵ���Ϣ����
     */
    public void setKeepAliveClass(Class keepAliveClass) {
        this.keepAliveClass = keepAliveClass;
    }


}
