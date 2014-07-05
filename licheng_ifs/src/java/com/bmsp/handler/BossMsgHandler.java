package com.bmsp.handler;

import com.bmsp.impl.IProcess;
import com.bmsp.message.BodyMessage;
import com.bmsp.message.BossMessage;
import com.bmsp.util.ConstantUtil;
import com.bmsp.util.StatusConstant;
import org.apache.mina.common.IoSession;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 11:47:15
 * 模拟服务端接受请求，并返回响应结果
 */
public class BossMsgHandler extends BaseHandler {
    private IProcess process;

    public IProcess getProcess() {
        return process;
    }

    public void setProcess(IProcess process) {
        this.process = process;
    }


    private static int num = 0;

    /**
     * 模拟BOSS服务端，并解析消息体，然后返回消息
     *
     * @param session
     * @param msg
     */
    public void messageReceived(IoSession session, Object msg) {
        super.messageReceived(session, msg);
        BossMessage m = (BossMessage) msg;
        if (logger.isDebugEnabled()) {
            logger.debug("BOSS服务端接收到ISCP请求消息，消息体为：" + new String(m.getBody()));
        }
        if (m.getCommandId() == ConstantUtil.BOSS_DISORDER_REQ || m.getCommandId() == ConstantUtil.BOSS_ORDER_ONE_REQ || m.getCommandId() == ConstantUtil.BOSS_ORDER_REQ) {
            BodyMessage responseMessage = null;
            String resStr = new String(m.getBody());
            if (m.getCommandId() == ConstantUtil.BOSS_DISORDER_REQ || m.getCommandId() == ConstantUtil.BOSS_ORDER_REQ) {
                m.setCommandId(ConstantUtil.BOSS_DISORDER_RES);
                BodyMessage requestMessage = getOrderMessage(resStr);
                if (requestMessage == null) {
                    logger.error("解析请求的数据格式失败：" + resStr);
                    return;
                } else {
                    try {
                        responseMessage = process.monthDeal(requestMessage);
                    } catch (Exception e) {
                        logger.error("处理代码出现异常，返回处理失败：" + e.getMessage());
                        responseMessage = new BodyMessage();
                        responseMessage.setSessionId(requestMessage.getSessionId());
                        responseMessage.setErrCode(StatusConstant.ORDER_PROCESS_ERROR);
                    }
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("BOSS服务端接收到ISCP请求的包月订购/退订消息，响应ISCP请求消息：" + responseMessage.getOrderResponseXml());
                }
                if (responseMessage != null)
                    m.setBody(responseMessage.getOrderResponseXml().getBytes());
            } else if (m.getCommandId() == ConstantUtil.BOSS_ORDER_ONE_REQ) {
                m.setCommandId(ConstantUtil.BOSS_ORDER_ONE_RES);
                BodyMessage requestMessage = getOrderEveryTimeMessage(resStr);
                if (requestMessage == null) {
                    logger.error("解析请求的数据格式失败：" + resStr);
                    return;
                } else {
                    try {
                        responseMessage = process.orderOnce(requestMessage);
                    } catch (Exception e) {
                        logger.error("处理代码出现异常，返回处理失败：" + e.getMessage());
                        responseMessage = new BodyMessage();
                        responseMessage.setSessionId(requestMessage.getSessionId());
                        responseMessage.setResult(StatusConstant.ORDER_PROCESS_ERROR);
                    }
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("BOSS服务端接收到ISCP请求的点播消息，响应ISCP请求消息：" + responseMessage.getBillingXml());
                }
                if (responseMessage != null)
                    m.setBody(responseMessage.getBillingXml().getBytes());
            }
            if (logger.isDebugEnabled()) {
                logger.debug("服务端将回发ISCP请求消息，这是第 （" + (++num) + "）个消息，消息ID为：" + responseMessage.getSessionId());
            }
            session.write(m);
        }
    }

    private String getDate() {
        SimpleDateFormat dateFm = new SimpleDateFormat(ConstantUtil.yyyyMMddHHmmss);
        String dateTime = dateFm.format(new java.util.Date());
        return dateTime;
    }

    private BodyMessage getOrderMessage(String xml) {
        BodyMessage vodMessage = null;
        try {
            vodMessage = new BodyMessage();
            Pattern P = Pattern.compile(ConstantUtil.ORDER_MESSAGE_REGEX, Pattern.MULTILINE + Pattern.DOTALL);
            Matcher m = P.matcher(xml);
            if (m.find()) {
                vodMessage.setSessionId(m.group(1));
                vodMessage.setIdentityType(m.group(2));
                vodMessage.setDevNo(m.group(3));
                vodMessage.setOperType(m.group(4));
                vodMessage.setProductId(m.group(5));
                vodMessage.setOrderType(m.group(6));
                vodMessage.setBillCycleCount(Integer.parseInt(m.group(7)));
            }
        } catch (NumberFormatException e) {
            logger.error("socket format error:", e);
        }
        return vodMessage;
    }

    private BodyMessage getOrderEveryTimeMessage(String xml) {
        BodyMessage vodMessage = null;
        try {
            vodMessage = new BodyMessage();
            Pattern P = Pattern.compile(ConstantUtil.ORDER_EVERY_TIME_REGEX, Pattern.MULTILINE + Pattern.DOTALL);
            Matcher m = P.matcher(xml);
            if (m.find()) {
                vodMessage.setSessionId(m.group(1));
                vodMessage.setType(m.group(2));
                vodMessage.setAssetId(m.group(3));
                vodMessage.setSpId(m.group(4));
                vodMessage.setStartTime(m.group(5));
                vodMessage.setDevNo(m.group(6));
            }
        } catch (Exception e) {
            logger.error("socket format error:", e);
        }
        return vodMessage;
    }
}
