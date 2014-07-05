package com.daocren.server.communication.util;

import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.apache.log4j.Logger;

/**
 * 用于apache数据链中的错误处理filter
 *
 * @author Daocren
 */
public class GuardFilter implements Filter {
    private static final Logger logger = Logger.getLogger(GuardFilter.class);

    /**
     * 执行方法
     * @param context
     * @return  执行结果
     * @throws Exception
     */
    public boolean execute(Context context) throws Exception {
        return false;
    }

    /**
     * 异常捕获回调方法
     * @param context
     * @param exception
     * @return 处理结果
     */
    public boolean postprocess(Context context, Exception exception) {
        if (exception == null)
            return false;
        logger.error("chain error", exception);
        return true;
    }

}
