package com.bmsp.handler;

import com.bmsp.BsmpConstant;
import com.bmsp.message.BusinessInfoRefMsg;
import com.daocren.server.communication.engine.AbstractMsgHandler;
import com.daocren.server.communication.message.CommonMsg;
import org.apache.mina.common.IoSession;

/**
 * �ײ�ҵ����Ϣ��ϵ��Ϣ������
 */
public class BusinessInfoRefMsgHandler extends AbstractMsgHandler {
    /**
     * ������Ӧ��Ϣ
     *
     * @param session IoSession
     * @param msg     ������Ϣ
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
