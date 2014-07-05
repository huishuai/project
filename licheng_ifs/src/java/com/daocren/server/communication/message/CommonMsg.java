package com.daocren.server.communication.message;

/**
 * ͨ����Ϣ,������Ϣȷ��
 *
 * @author Daocren
 */
public class CommonMsg extends SeqMsgAdapter {
    private static final long serialVersionUID = -4012667461595409461L;

    public CommonMsg() {
        length = 11;
    }

    private byte result;

    /**
     * ��ȡ�����Ϣ
     * @return �����Ϣ
     */
    public byte getResult() {
        return result;
    }

    /**
     * ���ý����Ϣ
     * @param result
     */
    public void setResult(byte result) {
        this.result = result;
    }

}
