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
 * ������Ϣ�����������ڴ���������Ϣ�ķ�����Ӧ
 * @author Daocren
 */
public class AbstractMsgHandler implements MessageHandler {
    /**
     * ����logger��Ϣ
     */
    protected static final Logger logger = Logger.getLogger(AbstractMsgHandler.class);

    /**
     * ������Ϣ����<br/>
     * ͨ����session�л�ȡ����Ĺ������б�����apache�����������й��˴���
     * @param session �Ի�
     * @param msg �账����Ϣ
     */
    public void messageReceived(IoSession session, Object msg) {
        Context ctx = new SimpleContext((AbstractMsg) msg);
        // ִ��filter
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
            logger.error("ִ��filter����", e);
        }
    }
}
