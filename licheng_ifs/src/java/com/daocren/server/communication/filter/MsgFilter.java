package com.daocren.server.communication.filter;

import com.daocren.server.communication.message.AbstractMsg;

/**
 * �������ӿ�<br/>
 * ����ʵ�ֱ��ӿڵ���Ϣ���������������ͨ�����õķ�ʽ���뵽��Ϣ����������
 *
 * @author daocren
 */
public interface MsgFilter {

    /**
     * ������Ϣ����
     * @param msg �账����Ϣ
     * @return �Ƿ�����ִ���������е�ʣ�ද��,�������false����Ŵ�����һ��filter,����true�򲻴���
     */
    public boolean dealMessage(AbstractMsg msg);
}
