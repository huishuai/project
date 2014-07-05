package com.bmsp.util;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import com.bmsp.filter.BaseFilter;

/**
 * ����apahce�������еĻ��������װ
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-13
 * Time: 23:09:20
 */
public class BaseCommand  implements Command {
    private final BaseFilter filter;

       /**
        * ʹ�ù�������������
        * @param filter ������
        */
       public BaseCommand(BaseFilter filter) {
           this.filter = filter;
       }

       /**
        * ִ�з���
        * @param context �����Ļ���
        * @return ִ�н��
        * @throws Exception �쳣��Ϣ
        */
       public boolean execute(Context context) throws Exception {
           return filter.dealMessage(((BaseContext) context).getMsg());
       }

}
