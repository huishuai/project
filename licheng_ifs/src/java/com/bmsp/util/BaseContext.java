package com.bmsp.util;

import org.apache.commons.chain.Context;

import java.util.HashMap;

import com.bmsp.message.BaseMessage;

/**
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-13
 * Time: 23:12:38
 */
public class BaseContext extends HashMap implements Context {
    private final BaseMessage msg;

    /**
     * 通过消息构造上下文环境
     * @param msg
     */
    public BaseContext(BaseMessage msg) {
        this.msg = msg;
    }

    /**
     * 获取消息
     * @return 消息
     */
    public BaseMessage getMsg() {
        return msg;
    }
}
