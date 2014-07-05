package com.bmsp.codec;

import com.bmsp.message.BaseMessage;
import com.bmsp.message.BossMessage;
import com.bmsp.util.ConstantUtil;
import org.apache.mina.common.ByteBuffer;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 13:10:09
 * 解码BOSS发送过来的消息
 */
public class BossMsgDecoder extends BaseDecoder {
    /**
     * 根据消息头来判断解码格式
     *
     * @param commandId 命令ID
     * @return boolean 是否此编码
     */
    public boolean commandIdMatch(int commandId) {
        logger.debug("CommandId :" + commandId);
        if (commandId == ConstantUtil.BOSS_DISORDER_REQ || commandId == ConstantUtil.BOSS_DISORDER_RES || commandId == ConstantUtil.BOSS_ORDER_ONE_REQ || commandId == ConstantUtil.BOSS_ORDER_ONE_RES || commandId == ConstantUtil.BOSS_ORDER_RES || commandId == ConstantUtil.BOSS_ORDER_REQ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 解码消息体
     *
     * @param in     字节码字符
     * @param length 消息体长度
     * @return BaseMessage 消息
     */
    protected BaseMessage decodeBody(ByteBuffer in, int length) {
        logger.debug("消息头解析正确下面读取消息体  CommandId:" + commandId);
        BossMessage message = new BossMessage(0);
        byte[] body = new byte[length];
        in.get(body);
        message.setBody(body);
        return message;
    }
}
