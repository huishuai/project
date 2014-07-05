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
 * ����BOSS���͹�������Ϣ
 */
public class BossMsgDecoder extends BaseDecoder {
    /**
     * ������Ϣͷ���жϽ����ʽ
     *
     * @param commandId ����ID
     * @return boolean �Ƿ�˱���
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
     * ������Ϣ��
     *
     * @param in     �ֽ����ַ�
     * @param length ��Ϣ�峤��
     * @return BaseMessage ��Ϣ
     */
    protected BaseMessage decodeBody(ByteBuffer in, int length) {
        logger.debug("��Ϣͷ������ȷ�����ȡ��Ϣ��  CommandId:" + commandId);
        BossMessage message = new BossMessage(0);
        byte[] body = new byte[length];
        in.get(body);
        message.setBody(body);
        return message;
    }
}
