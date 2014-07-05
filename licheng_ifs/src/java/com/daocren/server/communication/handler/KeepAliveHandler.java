package com.daocren.server.communication.handler;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.KeepAlive;
import org.apache.log4j.Logger;
import org.apache.mina.common.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

/**
 * ������Ϣ������
 * @author Daocren
 */
public class KeepAliveHandler implements MessageHandler {
    private static final Logger logger = Logger.getLogger(KeepAliveHandler.class);

    /**
     * ��Ϣ������
     * @param session �Ի�
     * @param obj �������Ϣ
     */
    public void messageReceived(IoSession session, Object obj) {
        KeepAlive msg = (KeepAlive) obj;
        if (msg.getCmdType() == Constant.KEEP_ALIVE_REQ) {
            KeepAlive res = new KeepAlive();
            res.setCmdType(Constant.KEEP_ALIVE_RES);
            session.write(res);
        } else if (msg.getCmdType() != Constant.KEEP_ALIVE_RES) {
            logger.warn("�յ�δ֪���� " + msg.getCmdType());

        }
    }

}
