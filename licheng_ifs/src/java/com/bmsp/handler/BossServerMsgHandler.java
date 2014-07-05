package com.bmsp.handler;

import com.bmsp.message.BodyMessage;
import com.bmsp.message.BossMessage;
import com.bmsp.util.ConstantUtil;
import com.bmsp.util.StatusConstant;
import org.apache.mina.common.IoSession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 11:47:15
 * 处理BOSS发起的订退请求
 */
public class BossServerMsgHandler extends BaseHandler { 

    /**
     * 接受BOSS发起的处理请求，处理完成以后返回处理结果给BOSS
     *
     * @param session Socket消息Session
     * @param msg     接受到的消息
     */
    public void messageReceived(IoSession session, Object msg) {
        super.messageReceived(session, msg);
        BossMessage m = (BossMessage) msg;
        try {
            BodyMessage responseMsg = null;
            String xml = new String(m.getBody());
            if (logger.isDebugEnabled()) {
                logger.debug("接收到BOSS发起的请求消息，消息体是：" + xml);
            }
            responseMsg = this.getOrderMessage(xml);
            boolean result = false;
            if (m.getCommandId() == ConstantUtil.BOSS_ORDER_REQ) {
//                result = bossService.orderByBoss(responseMsg.getDevNo(),
//                        responseMsg.getProductId(), "", "", null);
                result=true;
                m.setCommandId(ConstantUtil.BOSS_ORDER_RES);
                if (result) {
                    responseMsg.setErrCode(StatusConstant.BILLING_SUCCESS);
                    logger.debug("BOSS发起订购信息,ISCP处理成功！");
                } else {
                    responseMsg.setErrCode(StatusConstant.BILLING_NOT_ENOUGH_MONEY);
                    logger.debug("BOSS发起订购信息,ISCP处理失败");
                }
                resendMessageToBoss(result, responseMsg, session, m);
            } else if (m.getCommandId() == ConstantUtil.BOSS_DISORDER_REQ) {
//                result = bossService.disorderByBoss(responseMsg.getDevNo(),
//                        responseMsg.getProductId(), null);
                result=true;
                m.setCommandId(ConstantUtil.BOSS_DISORDER_RES);
                if (result) {
                    responseMsg.setErrCode(StatusConstant.BILLING_SUCCESS);
                    logger.debug("BOSS发起退订信息,ISCP处理成功！");
                } else {
                    responseMsg.setErrCode(StatusConstant.BILLING_NOT_ENOUGH_MONEY);
                    logger.debug("BOSS发起退订信息,ISCP处理失败");
                }
                resendMessageToBoss(result, responseMsg, session, m);
            }
        } catch (Exception e) {
            logger.error("订/退处理失败");
        }
    }

    private void resendMessageToBoss(boolean result, BodyMessage responseMsg, IoSession session, BossMessage m) {
        try {
            BodyMessage responseToBossMsg = new BodyMessage();
            responseToBossMsg.setErrCode(responseMsg.getErrCode());
            responseToBossMsg.setSessionId(responseMsg.getSessionId());
            responseToBossMsg.setTransType(responseMsg.getType());
            responseToBossMsg.setDevNo(responseMsg.getDevNo());
            responseToBossMsg.setProductId(responseMsg.getProductId());
            m.setBody(responseToBossMsg.getOrderResponseXml().getBytes());
            session.write(m);
        } catch (Exception e) {
            logger.error("返回给BOSS信息时出错", e);
        }
    }


    private BodyMessage getOrderMessage(String xml) {
        BodyMessage vodMessage = new BodyMessage();
        Pattern P = Pattern.compile(ConstantUtil.ORDER_MESSAGE_REGEX, Pattern.MULTILINE + Pattern.DOTALL
                + Pattern.CASE_INSENSITIVE);
        Matcher m = P.matcher(xml);
        if (m.find()) {
            vodMessage.setSessionId(m.group(1));
            vodMessage.setType(m.group(2));
            vodMessage.setProductId(m.group(3));
            vodMessage.setDevNo(m.group(4));
            vodMessage.setOrderType(m.group(5));
        }
        return vodMessage;
    }

}