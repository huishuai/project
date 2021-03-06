package com.daocren.server.communication.filter;

import com.daocren.server.communication.message.AbstractMsg;

/**
 * 过滤器接口<br/>
 * 所有实现本接口的消息处理过滤器都可以通过配置的方式加入到消息处理流程中
 *
 * @author daocren
 */
public interface MsgFilter {

    /**
     * 处理消息方法
     * @param msg 需处理消息
     * @return 是否续继执行责任链中的剩余动作,如果返回false则接着处理下一个filter,返回true则不处理
     */
    public boolean dealMessage(AbstractMsg msg);
}
