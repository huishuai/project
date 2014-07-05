package com.daocren.server.communication.util;

import com.daocren.server.communication.filter.MsgFilter;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 用于apahce数据链中的基本命令封装
 *
 * @author Daocren
 */
public class GeneralCommand implements Command {
    private final MsgFilter filter;

    /**
     * 使用过滤器构造命令
     * @param filter 过滤器
     */
    public GeneralCommand(MsgFilter filter) {
        this.filter = filter;
    }

    /**
     * 执行方法
     * @param context 上下文环境
     * @return 执行结果
     * @throws Exception 异常信息
     */
    public boolean execute(Context context) throws Exception {
        return filter.dealMessage(((SimpleContext) context).getMsg());
    }

}
