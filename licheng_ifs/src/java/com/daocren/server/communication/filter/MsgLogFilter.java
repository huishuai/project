package com.daocren.server.communication.filter;

import com.daocren.server.communication.Constant;
import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.util.StringUtils;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * 消息日志过滤器
 * @author Daocren
 */
public class MsgLogFilter implements MsgFilter {

    private static final Logger logger = Logger.getLogger(MsgLogFilter.class);


    /**
     * 处理消息
     * @param msg 需处理消息
     * @return 处理结果
     */
    public boolean dealMessage(AbstractMsg msg) {
        if (msg.getCmdType() == Constant.BIND_REQ) {
        }
        return false;
    }
}
