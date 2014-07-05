package com.daocren.server.communication.handler;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.CommonMsg;
import com.daocren.server.communication.message.MsgConstant;
import com.daocren.server.communication.util.SendControler;
import org.apache.log4j.Logger;
import org.apache.mina.common.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

/**
 * ��Ӧ��Ϣ������<br/>
 * ����ϵͳ�г��ֵ�������Ӧ��Ϣ<br/>
 * ����԰���Ӧ��Ϣ�����⴦��<br/>
 * �����˫�����ӣ������������������ͱ�����Ϣ
 * @see com.daocren.server.communication.message.CommonMsg
 * @see com.daocren.server.communication.message.BindReq
 * @author Daocren
 */
public class ResponseHandler implements MessageHandler {
    private static final Logger logger = Logger.getLogger(ResponseHandler.class);

    /**
     * ��Ϣ���շ���
     * @param session �Ի�
     * @param m �������Ϣ
     */
    public void messageReceived(IoSession session, Object m) {
        CommonMsg msg = (CommonMsg) m;
        // ������Ϣ���أ��ͷ�ͨ����������ؽ������ȷ������Ҫ����Ϣ���������־
        ((SendControler) session.getAttachment()).onReply(msg.getSeqNo(), msg.getResult() == 0);

        switch ((int) msg.getCmdType()) {
            case Constant.BIND_RES:
                if (msg.getResult() == 0) {
                    logger.info(session.getRemoteAddress() + "-"
                            + session.getLocalAddress() + " ��֤�ɹ�");
                    // ��֤�ɹ�������bidirect ���Ծ����Ƿ��������ͽ���
                    Boolean isBidirect = ((Boolean) session
                            .getAttribute(MsgConstant.KEY_BIDI));
                    if (isBidirect) {
                        logger.info("˫������");
                        // ֻ��˫�����Ӳ����������߳�
                        ((SendControler) session.getAttachment()).start();
                    } else {
                        logger.info("��������");
                    }

                } else {
                    logger.info(session.getRemoteAddress() + "-"
                            + session.getLocalAddress() + " ��֤ʧ��");
                }
                break;
            default:
                logger.warn("�յ����� " + msg.getCmdType());
        }
    }

}
