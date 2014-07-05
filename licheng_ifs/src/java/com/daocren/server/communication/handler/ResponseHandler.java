package com.daocren.server.communication.handler;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.CommonMsg;
import com.daocren.server.communication.message.MsgConstant;
import com.daocren.server.communication.util.SendControler;
import org.apache.log4j.Logger;
import org.apache.mina.common.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

/**
 * 相应消息处理器<br/>
 * 处理系统中出现的所有响应消息<br/>
 * 仅针对绑定响应消息做特殊处理<br/>
 * 如果是双向链接，则启动发送器，发送本地消息
 * @see com.daocren.server.communication.message.CommonMsg
 * @see com.daocren.server.communication.message.BindReq
 * @author Daocren
 */
public class ResponseHandler implements MessageHandler {
    private static final Logger logger = Logger.getLogger(ResponseHandler.class);

    /**
     * 消息接收方法
     * @param session 对话
     * @param m 处理的消息
     */
    public void messageReceived(IoSession session, Object m) {
        CommonMsg msg = (CommonMsg) m;
        // 处理消息返回，释放通道。如果返回结果不正确，还需要把消息记入错误日志
        ((SendControler) session.getAttachment()).onReply(msg.getSeqNo(), msg.getResult() == 0);

        switch ((int) msg.getCmdType()) {
            case Constant.BIND_RES:
                if (msg.getResult() == 0) {
                    logger.info(session.getRemoteAddress() + "-"
                            + session.getLocalAddress() + " 认证成功");
                    // 认证成功，根据bidirect 属性决定是否启动发送进程
                    Boolean isBidirect = ((Boolean) session
                            .getAttribute(MsgConstant.KEY_BIDI));
                    if (isBidirect) {
                        logger.info("双向连接");
                        // 只有双向连接才启动发送线程
                        ((SendControler) session.getAttachment()).start();
                    } else {
                        logger.info("单向连接");
                    }

                } else {
                    logger.info(session.getRemoteAddress() + "-"
                            + session.getLocalAddress() + " 认证失败");
                }
                break;
            default:
                logger.warn("收到命令 " + msg.getCmdType());
        }
    }

}
