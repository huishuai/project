package com.bmsp.filter;

import com.bmsp.message.BaseMessage;

/**
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-13
 * Time: 23:07:20
 *
 * �������ӿ�<br/>
 * ����ʵ�ֱ��ӿڵ���Ϣ���������������ͨ�����õķ�ʽ���뵽��Ϣ����������
 *
 * @author daocren
 */
public interface BaseFilter {

    /**
     * ������Ϣ����
     * @param msg �账����Ϣ
     * @return �Ƿ�����ִ���������е�ʣ�ද��,�������false����Ŵ�����һ��filter,����true�򲻴���
     */
    public boolean dealMessage(BaseMessage msg);
}
