package com.daocren.server.communication.codec;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.message.CommonMsg;
import org.apache.mina.common.ByteBuffer;

/**
 * ������Ϣ������<br/>
 * �ṩ�Ի�������Ӧ��Ϣ�ı���
 * @author Daocren
 */
public class CommonDecoder extends AbstractDecoder {
    public boolean cmdMatch(long cmdType) {
        return (cmdType == Constant.BIND_RES);
    }

    protected AbstractMsg decodeBody(ByteBuffer in, long length) {
        if (in.remaining() < length)
            return null;

        CommonMsg res = new CommonMsg();
        res.setSeqNo(in.getUnsignedInt());
        res.setResult(in.get());
        return res;
    }

}
