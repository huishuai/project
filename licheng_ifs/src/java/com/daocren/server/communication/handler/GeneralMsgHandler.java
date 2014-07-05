package com.daocren.server.communication.handler;

import com.daocren.server.communication.engine.AbstractMsgHandler;
import com.daocren.server.communication.message.CommonMsg;
import com.daocren.server.communication.message.SeqMsgAdapter;
import org.apache.mina.common.IoSession;

/**
 * ������Ϣ������
 * @author Daocren
 */
public class GeneralMsgHandler extends AbstractMsgHandler {
    /**
     * ������������
     */
    protected final long resCmdType;

    /**
     * �������췽��
     * @param resCmdType ������������
     */
    public GeneralMsgHandler(long resCmdType) {
        this.resCmdType = resCmdType;
    }

    /**
     * ��Ϣ���մ�����
     * @param session �Ի�
     * @param msg �������Ϣ
     */
    public void messageReceived(IoSession session, Object msg) {
        super.messageReceived(session, msg);
        CommonMsg res = new CommonMsg();
        SeqMsgAdapter req = (SeqMsgAdapter) msg;
        res.setSeqNo(req.getSeqNo());
        res.setCmdType(resCmdType);
        res.setResult((byte) 0);
        session.write(res);
    }
}
