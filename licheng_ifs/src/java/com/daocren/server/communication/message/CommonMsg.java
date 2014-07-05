package com.daocren.server.communication.message;

/**
 * 通用消息,用于消息确认
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
     * 获取结果信息
     * @return 结果信息
     */
    public byte getResult() {
        return result;
    }

    /**
     * 设置结果信息
     * @param result
     */
    public void setResult(byte result) {
        this.result = result;
    }

}
