package com.daocren.server.communication.handler;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.lightmessage.MessageTool;
import com.daocren.server.communication.lightmessage.MessageToolFactory;
import com.daocren.server.communication.message.BindReq;
import com.daocren.server.communication.message.CommonMsg;
import com.daocren.server.communication.message.MsgConstant;
import com.daocren.server.communication.util.SendControler;
import org.apache.log4j.Logger;
import org.apache.mina.common.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

/**
 * 绑定请求处理器
 * 
 * @author Daocren
 */
public class BindReqHandler implements MessageHandler {
    private static final Logger logger = Logger.getLogger(BindReqHandler.class);

    /**
     * 消息处理方法<br/>
     * @param session 对话
     * @param msg 绑定消息
     */
    public void messageReceived(IoSession session, Object msg) {
        BindReq m = (BindReq) msg;
        logger.info("处理认证. reqQname " + m.getReqQname());

        CommonMsg res = new CommonMsg();
        res.setCmdType(Constant.BIND_RES);
        res.setSeqNo(m.getSeqNo());
        res.setResult((byte) 0);
        session.write(res);

        // 如果是认证信息,则建立接收消息的客户端
        // 获取网关id，并建立对应的消息客户端
        String reqQname = String.valueOf(m.getReqQname());

        MessageToolFactory toolMgr = (MessageToolFactory) session.getAttribute(MsgConstant.KEY_TMGR);
        // 根据joinId 决定取消息的队列
        MessageTool tool = toolMgr.getTool(reqQname);
        int windowSize = (Integer) session.getAttribute(MsgConstant.KEY_WINDOWSIZE);
        SendControler sender = new SendControler(session, tool, windowSize);
        session.setAttachment(sender);
        ((SendControler) session.getAttachment()).start();
    }

}
