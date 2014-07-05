package com.bmsp.codec;

import com.bmsp.message.BaseMessage;
import com.bmsp.message.KeepAlive;
import com.bmsp.util.ConstantUtil;
import org.apache.mina.common.ByteBuffer;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-12
 * Time: 16:14:46
 * ����������Ϣ
 */
public class KeepAliveDecoder extends BaseDecoder {
     /**
     * ������Ϣͷ���жϽ����ʽ
     *
     * @param commandId ����ID
     * @return boolean �Ƿ�˱���
     */
    public boolean commandIdMatch(int commandId) {
        if (commandId == ConstantUtil.BOSS_KEEP_LIFE_REQ || commandId == ConstantUtil.BOSS_KEEP_LIFE_RES) {
            return true;
        } else {
            return false;
        }
    }

    protected BaseMessage decodeBody(ByteBuffer in, int length) {
        KeepAlive message = new KeepAlive(0);
        byte[] body = new byte[length];
        in.get(body);
        message.setBody(body);
        return message;
    }
}
