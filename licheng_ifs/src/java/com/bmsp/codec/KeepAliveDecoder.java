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
 * 解码心跳信息
 */
public class KeepAliveDecoder extends BaseDecoder {
     /**
     * 根据消息头来判断解码格式
     *
     * @param commandId 命令ID
     * @return boolean 是否此编码
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
