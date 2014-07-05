package com.daocren.server.communication.handler;

import com.daocren.server.communication.engine.AbstractMsgHandler;
import com.daocren.server.communication.message.CommonMsg;
import com.daocren.server.communication.message.SeqMsgAdapter;
import org.apache.mina.common.IoSession;

/**
 * 抽象消息处理器
 * @author Daocren
 */
public class GeneralMsgHandler extends AbstractMsgHandler {
    /**
     * 返回命令类型
     */
    protected final long resCmdType;

    /**
     * 基本构造方法
     * @param resCmdType 返回命令类型
     */
    public GeneralMsgHandler(long resCmdType) {
        this.resCmdType = resCmdType;
    }

    /**
     * 消息接收处理方法
     * @param session 对话
     * @param msg 处理的消息
     */
    public void messageReceived(IoSession session, Object msg) {
        super.messageReceived(session, msg);
        CommonMsg res = new CommonMsg();
        SeqMsgAdapter req = (SeqMsgAdapter) msg;
        res.setSeqNo(req.getSeqNo());
        res.setCmdType(resCmdType);
        res.setResult((byte) 0);
        session.write(res);
    }
}
