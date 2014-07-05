package com.daocren.server.communication.util;

import com.daocren.server.communication.filter.MsgFilter;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * ����apahce�������еĻ��������װ
 *
 * @author Daocren
 */
public class GeneralCommand implements Command {
    private final MsgFilter filter;

    /**
     * ʹ�ù�������������
     * @param filter ������
     */
    public GeneralCommand(MsgFilter filter) {
        this.filter = filter;
    }

    /**
     * ִ�з���
     * @param context �����Ļ���
     * @return ִ�н��
     * @throws Exception �쳣��Ϣ
     */
    public boolean execute(Context context) throws Exception {
        return filter.dealMessage(((SimpleContext) context).getMsg());
    }

}
