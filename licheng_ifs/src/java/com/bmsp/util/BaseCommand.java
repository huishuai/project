package com.bmsp.util;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import com.bmsp.filter.BaseFilter;

/**
 * 用于apahce数据链中的基本命令封装
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-13
 * Time: 23:09:20
 */
public class BaseCommand  implements Command {
    private final BaseFilter filter;

       /**
        * 使用过滤器构造命令
        * @param filter 过滤器
        */
       public BaseCommand(BaseFilter filter) {
           this.filter = filter;
       }

       /**
        * 执行方法
        * @param context 上下文环境
        * @return 执行结果
        * @throws Exception 异常信息
        */
       public boolean execute(Context context) throws Exception {
           return filter.dealMessage(((BaseContext) context).getMsg());
       }

}
