package com.bmsp.handler;

import org.apache.mina.common.IoSession;
import com.bmsp.message.KeepAlive;
import com.bmsp.util.ConstantUtil;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-13
 * Time: 11:28:12
 * ����˽���������Ϣ������������������Ϣ
 */
public class BossKeepAliveHandler   extends BaseHandler {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void messageReceived(IoSession session, Object msg) {
        super.messageReceived(session, msg);
        KeepAlive m = (KeepAlive) msg;
        m.setCommandId(ConstantUtil.BOSS_KEEP_LIFE_RES);
        logger.debug(name+"����˽���������Ϣ�ɹ�");
        session.write(m);
        logger.debug(name+"����˷�������Ϣ");
    }
}
