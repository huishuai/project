package com.bmsp.codec;

import com.bmsp.BsmpConstant;
import com.bmsp.message.BusinessInfoRefMsg;
import com.daocren.server.communication.codec.AbstractDecoder;
import com.daocren.server.communication.message.AbstractMsg;
import org.apache.mina.common.ByteBuffer;

/**
 * �û�ʹ�ü�¼��Ϣ������
 */
public class BusinessInfoRefMsgDecoder extends AbstractDecoder {
    /**
     * ��֤��Ϣ����
     *
     * @param cmdType ��Ϣ����
     * @return boolean
     */
    public boolean cmdMatch(long cmdType) {
        return cmdType == BsmpConstant.BUSINESSINFOREF_MSG_REQ;
    }

    /**
     * ������Ϣ��
     *
     * @param in     ��Ϣ��
     * @param length ��Ϣ����
     * @return BusinessInfoRefMsg
     */
    protected AbstractMsg decodeBody(ByteBuffer in, long length) {
        BusinessInfoRefMsg message = new BusinessInfoRefMsg();
        message.setSeqNo(in.getUnsignedInt());
        byte[] bytes = new byte[in.limit() - 10];
        try {
            in.get(bytes);
            message.setBody(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}
