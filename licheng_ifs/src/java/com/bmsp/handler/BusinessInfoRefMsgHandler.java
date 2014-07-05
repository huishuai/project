package com.bmsp.handler;

import com.bmsp.BsmpConstant;
import com.bmsp.message.BusinessInfoRefMsg;
import com.daocren.server.communication.engine.AbstractMsgHandler;
import com.daocren.server.communication.message.CommonMsg;
import org.apache.mina.common.IoSession;

/**
 * 套餐业务信息关系消息处理器
 */
public class BusinessInfoRefMsgHandler extends AbstractMsgHandler {
    /**
     * 返回响应消息
     *
     * @param session IoSession
     * @param msg     接收消息
     */
    public void messageReceived(IoSession session, Object msg) {
        super.messageReceived(session, msg);
        BusinessInfoRefMsg m = (BusinessInfoRefMsg) msg;
        CommonMsg res = new CommonMsg();
        res.setCmdType(BsmpConstant.BUSINESSINFOREF_MSG_RES);
        res.setSeqNo(m.getSeqNo());
        res.setResult((byte) 0);
        session.write(res);
    }
}
