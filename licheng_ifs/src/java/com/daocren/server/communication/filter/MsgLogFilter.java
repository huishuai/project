package com.daocren.server.communication.filter;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.util.StringUtils;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * ��Ϣ��־������
 * @author Daocren
 */
public class MsgLogFilter implements MsgFilter {

    private static final Logger logger = Logger.getLogger(MsgLogFilter.class);


    /**
     * ������Ϣ
     * @param msg �账����Ϣ
     * @return ������
     */
    public boolean dealMessage(AbstractMsg msg) {
        if (msg.getCmdType() == Constant.BIND_REQ) {
        }
        return false;
    }
}
