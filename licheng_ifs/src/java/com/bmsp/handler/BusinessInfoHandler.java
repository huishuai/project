package com.bmsp.handler;

import com.bmsp.BsmpConstant;
import com.bmsp.message.BusinessInfoMessage;
import com.daocren.server.communication.engine.AbstractMsgHandler;
import com.daocren.server.communication.message.CommonMsg;
import org.apache.mina.common.IoSession;

/**
 * 业务西悉尼处理器
 */
public class BusinessInfoHandler extends AbstractMsgHandler {
    @Override
    public void messageReceived(IoSession session, Object msg) {
        // TODO Auto-generated method stub
        super.messageReceived(session, msg);
        BusinessInfoMessage m = (BusinessInfoMessage) msg;
        CommonMsg res = new CommonMsg();
        res.setCmdType(BsmpConstant.BUSINESSINFO_MSG_RES);
        res.setSeqNo(m.getSeqNo());
        res.setResult((byte) 0);
        session.write(res);
    }
}
