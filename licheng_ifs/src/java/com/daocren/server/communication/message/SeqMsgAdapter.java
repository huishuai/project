package com.daocren.server.communication.message;

/**
 * 带流水号的基本消息父类
 *
 * @author Daocren
 */
public class SeqMsgAdapter extends AbstractMsg {

    private static final long serialVersionUID = 4863656389961655121L;
    /**
     * 消息流水号
     */
    protected long seqNo;

    /**
     * 获取消息流水号
     * @return 消息流水号
     */
    public long getSeqNo() {
        return seqNo;
    }

    /**
     * 设置消息流水号
     * @param seqNo
     */
    public void setSeqNo(long seqNo) {
        this.seqNo = seqNo;
    }

}
