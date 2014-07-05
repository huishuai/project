package com.daocren.server.communication.message;

/**
 * ����ˮ�ŵĻ�����Ϣ����
 *
 * @author Daocren
 */
public class SeqMsgAdapter extends AbstractMsg {

    private static final long serialVersionUID = 4863656389961655121L;
    /**
     * ��Ϣ��ˮ��
     */
    protected long seqNo;

    /**
     * ��ȡ��Ϣ��ˮ��
     * @return ��Ϣ��ˮ��
     */
    public long getSeqNo() {
        return seqNo;
    }

    /**
     * ������Ϣ��ˮ��
     * @param seqNo
     */
    public void setSeqNo(long seqNo) {
        this.seqNo = seqNo;
    }

}
