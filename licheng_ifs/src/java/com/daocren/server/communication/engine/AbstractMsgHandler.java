package com.daocren.server.communication.engine;

import com.daocren.server.communication.filter.MsgFilter;
import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.message.MsgConstant;
import com.daocren.server.communication.util.GeneralCommand;
import com.daocren.server.communication.util.GuardFilter;
import com.daocren.server.communication.util.SimpleContext;
import org.apache.commons.chain.Chain;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.log4j.Logger;
import org.apache.mina.common.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

import java.util.Iterator;
import java.util.List;

/**
 * 抽象消息处理器，用于处理所有消息的返回响应
 * @author Daocren
 */
public class AbstractMsgHandler implements MessageHandler {
    /**
     * 基本logger信息
     */
    protected static final Logger logger = Logger.getLogger(AbstractMsgHandler.class);

    /**
     * 处理消息方法<br/>
     * 通过从session中获取处理的过滤器列表，按照apache的责任链进行过滤处理
     * @param session 对话
     * @param msg 需处理消息
     */
    public void messageReceived(IoSession session, Object msg) {
        Context ctx = new SimpleContext((AbstractMsg) msg);
        // 执行filter
        try {

            List filters = (List) session.getAttribute(MsgConstant.KEY_FILTERS);
            Chain chain = new ChainBase();
            chain.addCommand(new GuardFilter());
            for (Iterator iter = filters.iterator(); iter.hasNext();) {
                MsgFilter f = (MsgFilter) iter.next();
                chain.addCommand(new GeneralCommand(f));
            }
            chain.execute(ctx);
        } catch (Exception e) {
            logger.error("执行filter出错", e);
        }
    }
}
