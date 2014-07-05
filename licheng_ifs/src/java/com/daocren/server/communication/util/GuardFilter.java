package com.daocren.server.communication.util;

import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.apache.log4j.Logger;

/**
 * ����apache�������еĴ�����filter
 *
 * @author Daocren
 */
public class GuardFilter implements Filter {
    private static final Logger logger = Logger.getLogger(GuardFilter.class);

    /**
     * ִ�з���
     * @param context
     * @return  ִ�н��
     * @throws Exception
     */
    public boolean execute(Context context) throws Exception {
        return false;
    }

    /**
     * �쳣����ص�����
     * @param context
     * @param exception
     * @return ������
     */
    public boolean postprocess(Context context, Exception exception) {
        if (exception == null)
            return false;
        logger.error("chain error", exception);
        return true;
    }

}
