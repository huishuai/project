package com.daocren.server.communication.util;

import com.daocren.server.communication.message.AbstractMsg;
import org.apache.commons.chain.Context;

import java.util.HashMap;

/**
 * 用于apache数据链中的基本context对象
 *
 * @author Daocren
 */
public class SimpleContext extends HashMap implements Context {
    private final AbstractMsg msg;

    /**
     * 通过消息构造上下文环境
     * @param msg
     */
    public SimpleContext(AbstractMsg msg) {
        this.msg = msg;
    }

    /**
     * 获取消息
     * @return 消息
     */
    public AbstractMsg getMsg() {
        return msg;
    }

}
