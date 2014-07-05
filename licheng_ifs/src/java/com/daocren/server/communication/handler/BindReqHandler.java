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
 * ����������
 * 
 * @author Daocren
 */
public class BindReqHandler implements MessageHandler {
    private static final Logger logger = Logger.getLogger(BindReqHandler.class);

    /**
     * ��Ϣ������<br/>
     * @param session �Ի�
     * @param msg ����Ϣ
     */
    public void messageReceived(IoSession session, Object msg) {
        BindReq m = (BindReq) msg;
        logger.info("������֤. reqQname " + m.getReqQname());

        CommonMsg res = new CommonMsg();
        res.setCmdType(Constant.BIND_RES);
        res.setSeqNo(m.getSeqNo());
        res.setResult((byte) 0);
        session.write(res);

        // �������֤��Ϣ,����������Ϣ�Ŀͻ���
        // ��ȡ����id����������Ӧ����Ϣ�ͻ���
        String reqQname = String.valueOf(m.getReqQname());

        MessageToolFactory toolMgr = (MessageToolFactory) session.getAttribute(MsgConstant.KEY_TMGR);
        // ����joinId ����ȡ��Ϣ�Ķ���
        MessageTool tool = toolMgr.getTool(reqQname);
        int windowSize = (Integer) session.getAttribute(MsgConstant.KEY_WINDOWSIZE);
        SendControler sender = new SendControler(session, tool, windowSize);
        session.setAttachment(sender);
        ((SendControler) session.getAttachment()).start();
    }

}
