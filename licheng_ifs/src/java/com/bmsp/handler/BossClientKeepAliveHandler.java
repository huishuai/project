package com.bmsp.handler;

import org.apache.mina.common.IoSession;
import com.bmsp.message.KeepAlive;
import com.bmsp.util.ConstantUtil;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-13
 * Time: 11:28:12
 * �ͻ��˽���BOSS������Ϣ
 */
public class BossClientKeepAliveHandler extends BaseHandler {
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
        logger.debug(name + "�ͻ��˽���BOSS������Ϣ�ɹ�");
        // session.write(m);
    }

//    private String getSessionId(String xml) {
//        Pattern P = Pattern.compile(ConstantUtil.KEEP_LIVE_REGEX, Pattern.MULTILINE + Pattern.DOTALL);
//        Matcher m = P.matcher(xml);
//        if (m.find()) {
//            return m.group(1);
//        }
//        return "";
//    }
}