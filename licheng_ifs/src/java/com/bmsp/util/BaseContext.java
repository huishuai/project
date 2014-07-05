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
     * ͨ����Ϣ���������Ļ���
     * @param msg
     */
    public BaseContext(BaseMessage msg) {
        this.msg = msg;
    }

    /**
     * ��ȡ��Ϣ
     * @return ��Ϣ
     */
    public BaseMessage getMsg() {
        return msg;
    }
}
