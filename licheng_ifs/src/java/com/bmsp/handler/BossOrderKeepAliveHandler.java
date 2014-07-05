package com.bmsp.handler;

import com.bmsp.message.KeepAlive;
import com.bmsp.message.KeepOrderAlive;
import com.bmsp.util.ConstantUtil;
import org.apache.mina.common.IoSession;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-13
 * Time: 11:28:12
 * ����˽���������Ϣ������������������Ϣ
 */
public class BossOrderKeepAliveHandler extends BaseHandler {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void messageReceived(IoSession session, Object msg) {
        super.messageReceived(session, msg);
        KeepOrderAlive m = (KeepOrderAlive) msg;
        m.setCommandId(ConstantUtil.BOSS_ORDER_MONTH_KEEPLIVE_RES);
        logger.debug(name+"����˽���������Ϣ�ɹ�");
        session.write(m);
        logger.debug(name+"����˷�������Ϣ");
    }
}