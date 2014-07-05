package com.bmsp.handler;

import com.bmsp.message.BodyMessage;
import com.bmsp.message.BossMessage;
import com.bmsp.util.ConstantUtil;
import com.daocren.server.communication.sync.SyncMessageSender;
import org.apache.mina.common.IoSession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 11:47:15
 * 接受BOSS返回的处理结果
 */
public class BossClientMsgHandler extends BaseHandler {
    private static int num = 0;

    private SyncMessageSender sender; 

    public SyncMessageSender getSender() {
        return sender;
    }

    public void setSender(SyncMessageSender sender) {
        this.sender = sender;
    }

    /**
     * 接受BOSS返回的处理结果并激活线程
     *
     * @param session
     * @param msg
     */
    public void messageReceived(IoSession session, Object msg) {
        super.messageReceived(session, msg);
        BossMessage m = (BossMessage) msg;
        BodyMessage responseMsg = null;
        String xml = new String(m.getBody());
        if (logger.isDebugEnabled()) {
            logger.debug("接收到BOSS响应的ISCP消息，数据  ：" + xml);
        }
//        if (m.getCommandId() == ConstantUtil.BOSS_DISORDER_REQ || m.getCommandId() == ConstantUtil.BOSS_ORDER_REQ) {
//            responseMsg = this.getOrderMessage(xml);
//            boolean result = false;
//            if (m.getCommandId() == ConstantUtil.BOSS_ORDER_REQ) {
//                // todo BOSS发起订购
//                result = iscpService.orderByBoss(responseMsg.getDevNo(),
//                        responseMsg.getProductId(), "", "");
//                m.setCommandId(ConstantUtil.BOSS_ORDER_RES);
//                if (result) {
//                    responseMsg.setErrCode(StatusConstant.BILLING_SUCCESS);
//                    logger.debug("BOSS发起订购信息,ISCP处理成功！");
//                } else {
//                    responseMsg.setErrCode(StatusConstant.BILLING_NOT_ENOUGH_MONEY);
//                    logger.debug("BOSS发起订购信息,ISCP处理失败");
//                }
//                if (logger.isDebugEnabled()) {
//                    logger.debug("响应BOSS的订购消息：  " + responseMsg.getOrderXml());
//                }
//                resendMessageToBoss(result, responseMsg, session, m);
//            } else if (m.getCommandId() == ConstantUtil.BOSS_DISORDER_REQ) {
//                result = iscpService.disorderByBoss(responseMsg.getDevNo(),
//                        responseMsg.getProductId());
//                m.setCommandId(ConstantUtil.BOSS_DISORDER_RES);
//                if (result) {
//                    responseMsg.setErrCode(StatusConstant.BILLING_SUCCESS);
//                    logger.info("BOSS发起退订信息,ISCP处理成功！");
//                } else {
//                    responseMsg.setErrCode(StatusConstant.BILLING_NOT_ENOUGH_MONEY);
//                    logger.info("BOSS发起退订信息,ISCP处理失败");
//                }
//                if (logger.isDebugEnabled()) {
//                    logger.debug("响应BOSS的退订消息 ：" + responseMsg.getOrderXml());
//                }
//                resendMessageToBoss(result, responseMsg, session, m);
//            }
//        } else {
        if (m.getCommandId() == ConstantUtil.BOSS_DISORDER_RES) {
            responseMsg = this.getOrderResponseMessage(xml);
        } else if (m.getCommandId() == ConstantUtil.BOSS_ORDER_ONE_RES) {
            responseMsg = this.getBillingMessage(xml);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("客户端接受BOSS响应的数据，响应ISCP请求消息的数据个数：" + (++num));
        }
        sender.notifyResponseMessage(responseMsg);
//        }
    }

//    private void resendMessageToBoss(boolean result, BodyMessage responseMsg, IoSession session, BossMessage m) {
//        try {
//            BodyMessage responseToBossMsg = new BodyMessage();
//            responseToBossMsg.setErrCode(responseMsg.getErrCode());
//            responseToBossMsg.setTransType(responseMsg.getType());
//            responseToBossMsg.setDevNo(responseMsg.getDevNo());
//            responseToBossMsg.setProductId(responseMsg.getProductId());
//            m.setBody(responseToBossMsg.getOrderResponseXml().getBytes());
//            session.write(m);
//        } catch (Exception e) {
//            logger.error("返回给BOSS信息时出错", e);
//        }
//    }

    private BodyMessage getBillingMessage(String xml) {
        BodyMessage vodMessage = new BodyMessage();
        Pattern P = Pattern.compile(ConstantUtil.BILLING_REGEX, Pattern.MULTILINE + Pattern.DOTALL
                + Pattern.CASE_INSENSITIVE);
        Matcher m = P.matcher(xml);
        if (m.find()) {
            vodMessage.setSessionId(m.group(1));
            vodMessage.setType(m.group(2));
            vodMessage.setResult(m.group(3));
        }
        if (logger.isDebugEnabled()) {
            logger.debug("解析消息体完成，sessionId：" + vodMessage.getSessionId()+" type:"+vodMessage.getType()+" result:"+vodMessage.getResult());
        }
        return vodMessage;
    }

//    private BodyMessage getOrderMessage(String xml) {
//        BodyMessage vodMessage = new BodyMessage();
//        Pattern P = Pattern.compile(ConstantUtil.ORDER_MESSAGE_REGEX, Pattern.MULTILINE + Pattern.DOTALL
//                + Pattern.CASE_INSENSITIVE);
//        Matcher m = P.matcher(xml);
//        if (m.find()) {
//            vodMessage.setSessionId(m.group(1));
//            vodMessage.setType(m.group(2));
//            vodMessage.setProductId(m.group(3));
//            vodMessage.setDevNo(m.group(4));
//            vodMessage.setOrderType(m.group(5));
//        }
//        return vodMessage;
//    }

    private BodyMessage getOrderResponseMessage(String xml) {
        BodyMessage vodMessage = new BodyMessage();
        Pattern P = Pattern.compile(ConstantUtil.ORDER_RESPONSE_REGEX, Pattern.MULTILINE + Pattern.DOTALL
                + Pattern.CASE_INSENSITIVE);
        Matcher m = P.matcher(xml);
        if (m.find()) {
            vodMessage.setSessionId(m.group(1));
            vodMessage.setErrCode(m.group(2));
            vodMessage.setTransType(m.group(3));
            vodMessage.setDevNo(m.group(4));
            vodMessage.setProductId(m.group(5));
            vodMessage.setStartTime(m.group(6));
            vodMessage.setEndTime(m.group(7));
        }

        if (logger.isDebugEnabled()) {
            logger.debug("解析消息体完成，sessionId：" + vodMessage.getSessionId()+" errCode:"+vodMessage.getErrCode()+" TransType:"+vodMessage.getTransType()+" DevNo:"+vodMessage.getDevNo()+" ProductId:"+vodMessage.getProductId()+" StartTime:"+vodMessage.getStartTime()+" EndTime:"+vodMessage.getEndTime());
        }
        return vodMessage;
    }

//    private BodyMessage getOrderEveryTimeMessage(String xml) {
//        BodyMessage vodMessage = new BodyMessage();
//        Pattern P = Pattern.compile(ConstantUtil.ORDER_EVERY_TIME_REGEX, Pattern.MULTILINE + Pattern.DOTALL
//                + Pattern.CASE_INSENSITIVE);
//        Matcher m = P.matcher(xml);
//        if (m.find()) {
//            vodMessage.setSessionId(m.group(1));
//            vodMessage.setType(m.group(2));
//            vodMessage.setAssetId(m.group(3));
//            vodMessage.setSpId(m.group(4));
//            vodMessage.setStartTime(m.group(5));
//            vodMessage.setDevNo(m.group(6));
//        }
//        return vodMessage;
//    }
//
//    private String getSessionId(String xml) {
//        Pattern P = Pattern.compile(ConstantUtil.KEEP_LIVE_REGEX, Pattern.MULTILINE + Pattern.DOTALL
//                + Pattern.CASE_INSENSITIVE);
//        Matcher m = P.matcher(xml);
//        if (m.find()) {
//            return m.group(1);
//        }
//        return "";
//    }

//    public void setIscpService(IscpService iscpService) {
//        this.iscpService = iscpService;
//    }
}