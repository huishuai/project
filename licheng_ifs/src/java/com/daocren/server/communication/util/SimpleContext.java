package com.daocren.server.communication.util;

import com.daocren.server.communication.message.AbstractMsg;
import org.apache.commons.chain.Context;

import java.util.HashMap;

/**
 * ����apache�������еĻ���context����
 *
 * @author Daocren
 */
public class SimpleContext extends HashMap implements Context {
    private final AbstractMsg msg;

    /**
     * ͨ����Ϣ���������Ļ���
     * @param msg
     */
    public SimpleContext(AbstractMsg msg) {
        this.msg = msg;
    }

    /**
     * ��ȡ��Ϣ
     * @return ��Ϣ
     */
    public AbstractMsg getMsg() {
        return msg;
    }

}
