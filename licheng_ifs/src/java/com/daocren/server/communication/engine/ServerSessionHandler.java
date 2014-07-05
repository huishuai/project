package com.daocren.server.communication.engine;

import com.daocren.server.communication.codec.CodecFactory;
import com.daocren.server.communication.filter.LoggingFilter;
import com.daocren.server.communication.handler.BindReqHandler;
import com.daocren.server.communication.handler.KeepAliveHandler;
import com.daocren.server.communication.handler.ResponseHandler;
import com.daocren.server.communication.message.BindReq;
import com.daocren.server.communication.message.CommonMsg;
import com.daocren.server.communication.message.KeepAlive;
import com.daocren.server.communication.message.MsgConstant;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.handler.demux.MessageHandler;

import java.util.List;
import java.util.Map;

/**
 * 服务器端对话处理器
 *
 * @author Daocren
 */
@SuppressWarnings(value = "unchecked")
public class ServerSessionHandler extends DemuxingIoHandlerAdapter {
    public ServerSessionHandler() {
        setName("server");
    }

    /**
     * 对话创建回调方法<br/>
     * 设置消息处理器<br/>
     * 设置协议工厂信息
     *
     * @param session
     * @throws Exception
     */
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);

        CodecFactory codecFactory = new CodecFactory(true);
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
        addMessageHandler(BindReq.class, new BindReqHandler());
    }

    public void sessionOpened(IoSession session) throws Exception {
        sessionsContainer.addSession(this.getName(),session);
        session.setAttribute(MsgConstant.KEY_CONNECTOR_NAME, this.getName());
        super.sessionOpened(session);
    }

    public void sessionClosed(IoSession session) throws Exception {
        sessionsContainer.deleteSession((String) session.getAttribute(MsgConstant.KEY_CONNECTOR_NAME),session);
        super.sessionClosed(session);
    }
}
