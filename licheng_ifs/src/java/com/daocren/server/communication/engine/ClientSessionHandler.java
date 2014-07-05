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
 * 内部协议客户端处理器
 *
 * @author Daocren
 */
public class ClientSessionHandler extends DemuxingIoHandlerAdapter {

    private Class keepAliveClass;

    public ClientSessionHandler() {
        setName("client");
    }

    // 把随机数与此字串作异或，作为认证码
    private static final String xorSource = "txblxdyy";

    private Random rand = new Random();

    //发送消息时，如果消息超时100秒，则放入此队列
    private String timeoutQname = "-99";

    /**
     * 获取链接超时队列信息
     *
     * @return 链接超时队列信息
     */
    public String getTimeoutQname() {
        return timeoutQname;
    }

    /**
     * 设置链接超时队列名称
     *
     * @param timeoutQname 队列名
     */
    public void setTimeoutQname(String timeoutQname) {
        this.timeoutQname = timeoutQname;
    }


    /**
     * 对话创建成功回调方法<br/>
     * 指定编码工厂，并添加基本的消息响应处理
     *
     * @param session 对话
     * @throws Exception 一场信息
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
     * 对话闲置回调方法<br/>
     * 对话闲置时，每隔一定时间发送防超时消息
     *
     * @param session 对话
     * @param status  状态
     * @throws Exception 异常信息
     */
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        // logger.debug("发送防超时消息@" + session.getRemoteAddress());
        if (keepAliveClass != null) {
            session.write(keepAliveClass.newInstance());
        } else {
            KeepAlive req = new KeepAlive();
            req.setCmdType(Constant.KEEP_ALIVE_REQ);
            session.write(req);
        }
    }

    /**
     * 对话关闭回调方法
     *
     * @param session 对话
     * @throws Exception 一场信息
     */
    public void sessionClosed(IoSession session) throws Exception {
        sessionsContainer.deleteSession((String) session.getAttribute(MsgConstant.KEY_CONNECTOR_NAME), session);
        super.sessionClosed(session);
    }

    /**
     * 对话打开回调方法<br/>
     * 发送绑定请求信息<br/>
     * 初始化对话session信息
     *
     * @param session 对话
     * @throws Exception 异常信息
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
                    // 在session中增加”类型“这个属性，通常是为了http还是tcp
                    req.setReqQname(getRemoteQname());
                    sender.trySend(req);
                }
            }
        }
    }

    /**
     * 设置保持链接的消息类型
     *
     * @param keepAliveClass 保持链接的消息类型
     */
    public void setKeepAliveClass(Class keepAliveClass) {
        this.keepAliveClass = keepAliveClass;
    }


}
