package com.bmsp.handler;

import org.apache.mina.handler.demux.MessageHandler;
import org.apache.mina.common.IoSession;
import org.apache.log4j.Logger;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Chain;
import org.apache.commons.chain.impl.ChainBase;
import com.daocren.server.communication.util.GuardFilter;
import com.daocren.server.communication.message.MsgConstant;
import com.bmsp.message.BaseMessage;
import com.bmsp.filter.BaseFilter;
import com.bmsp.util.BaseContext;
import com.bmsp.util.BaseCommand;

import java.util.List;
import java.util.Iterator;

/**
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-13
 * Time: 23:03:03
 * ����������
 */
public class BaseHandler implements MessageHandler {
    /**
     * ����logger��Ϣ
     */
    protected final Logger logger = Logger.getLogger(getClass());

    /**
     * ������Ϣ����<br/>
     * ͨ����session�л�ȡ����Ĺ������б�����apache�����������й��˴���
     * @param session �Ի�
     * @param msg �账����Ϣ
     */
    public void messageReceived(IoSession session, Object msg) {
        Context ctx = new BaseContext((BaseMessage) msg);
        // ִ��filter
        try {

            List filters = (List) session.getAttribute(MsgConstant.KEY_FILTERS);
            Chain chain = new ChainBase();
            chain.addCommand(new GuardFilter());
            for (Iterator iter = filters.iterator(); iter.hasNext();) {
                BaseFilter f = (BaseFilter) iter.next();
                chain.addCommand(new BaseCommand(f));
            }
            chain.execute(ctx);
        } catch (Exception e) {
            logger.error("ִ��filter����", e);
        }
    }

}
