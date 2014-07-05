package com.daocren.server.communication.util;

import com.daocren.server.communication.lightmessage.MessageTool;
import com.daocren.server.communication.message.MsgConstant;
import com.daocren.server.communication.message.SeqMsgAdapter;
import org.apache.log4j.Logger;
import org.apache.mina.common.IoSession;

import java.util.LinkedList;

/**
 * 消息发送控制器
 *
 * @author Daocren
 */
public class SendControler {
    private static final Logger logger = Logger.getLogger(SendControler.class);

    private static final String NUMBER = "#";

    private static final String SUC = "成功";

    private static final String FULL = "窗口已满";

    private static final String REL = "释放";

    private static final String ERR = "错误";

    private static final String OVERTIME = "超时";

    private final String sessionStr;

    private long id = 1;

    private int channelMaxCount;

    private MessageTool tool;

    private MessageTool errTool;

    private IoSession session;

    private LinkedList cache = new LinkedList();

    private Object lock = new Object();

    private boolean reSend;

    /**
     * 通过消息处理工具构造
     * @param session 对话
     * @param tool 消息工具
     * @param channelMaxCount 最大并发处理数量
     */
    public SendControler(IoSession session, MessageTool tool,
                         int channelMaxCount) {
        this.session = session;
        this.tool = tool;
        this.channelMaxCount = channelMaxCount;
        sessionStr = "@" + session.getRemoteAddress() + "-"
                + session.getLocalAddress();
        this.reSend = Boolean.valueOf(session.getAttribute(
                MsgConstant.KEY_RESEND).toString());
    }

    /**
     * 通过消息处理工具构造
     * @param session 对话
     * @param tool 消息工具
     * @param channelMaxCount 最大并发处理数量
     * @param errTool 错误消息处理工具
     */
    public SendControler(IoSession session, MessageTool tool,
                         int channelMaxCount, MessageTool errTool) {
        this.session = session;
        this.tool = tool;
        this.channelMaxCount = channelMaxCount;
        sessionStr = "@" + session.getRemoteAddress() + "-"
                + session.getLocalAddress();
        this.reSend = Boolean.valueOf(session.getAttribute(
                MsgConstant.KEY_RESEND).toString());
        this.errTool = errTool;
    }

    private boolean running = true;

    /**
     * 启动方法
     */
    public void start() {

        Thread worker = new Thread(new Runnable() {

            public void run() {
                Object obj = null;

                while (running) {
                    if (obj != null) {
                        if (!trySend((SeqMsgAdapter) obj)) {
                            try {
                                Thread.sleep(10);
                            } catch (Exception e) {
                            }
                            checkOverTime();
                            continue;
                        }
                    }

                    try {
                        obj = tool.receive();
                    } catch (Exception e) {
                        logger.warn("取消息出错", e);
                        obj = null;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                        }
                    }
                }
                logger.info("开始做清理工作。");
                clear();
            }
        });
        worker.setName("sender- "
                + tool.getJmsTemplate().getDefaultDestinationName());
        logger.info("启动发送线程 " + worker.getName() + sessionStr);
        worker.start();

    }

    /**
     * 尝试发送，如果没有可用窗口，则失败
     *
     * @return 发送结果
     */
    public boolean trySend(SeqMsgAdapter obj) {
        synchronized (lock) {
            // 只有已连接，才发
            if (session.isConnected()) {
                if (cache.size() < channelMaxCount) {
                    obj.setSeqNo(id++);
                    cache.addLast(new MsgWrapper(obj));
                    session.write(obj);

                    if (logger.isDebugEnabled()) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(NUMBER);
                        sb.append(obj.getSeqNo());
                        sb.append(sessionStr);
                        sb.append(SUC);
                        logger.debug(sb.toString());
                    }
                    return true;
                } else {
                    StringBuffer sb = new StringBuffer();
                    sb.append(sessionStr);
                    sb.append(FULL);
                    logger.warn(sb.toString());
                    return false;
                }
            } else {
                logger.error("连接已关闭！！！,主动停止");
                stop();
                return false;
            }
        }
    }

    /**
     * 停止发送方法
     */
    public void stop() {
        logger.error("停止sendcontrol!");
        running = false;
    }

    /**
     * 用下标遍历缓存，如果有对应消息，则直接移除
     *
     * @param seqNo 消息流水号
     * @param success 处理结果
     * @return 处理后对象
     */
    public Object onReply(long seqNo, boolean success) {
        synchronized (lock) {
            int size = cache.size();
            for (int i = 0; i < size; i++) {
                MsgWrapper mw = (MsgWrapper) cache.get(i);
                if (mw.obj.getSeqNo() == seqNo) {
                    if (logger.isDebugEnabled()) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(NUMBER);
                        sb.append(seqNo);
                        sb.append(sessionStr);
                        sb.append(REL);
                        logger.debug(sb.toString());
                    }

                    cache.remove(i);

                    if (!success) {
                        StringBuffer sbErr = new StringBuffer();
                        sbErr.append(NUMBER);
                        sbErr.append(seqNo);
                        sbErr.append(sessionStr);
                        sbErr.append(ERR);
                        logger.error(sbErr.toString());
                        tool.send(mw.obj, 8);
                    }
                    // 将发送的对象返回给处理线程
                    return mw.obj;
                }

            }

            if (size == cache.size()) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < size; i++) {
                    MsgWrapper mw = (MsgWrapper) cache.get(i);
                    sb.append(mw.obj.getSeqNo());
                    sb.append(',');
                }
                logger.warn("! #" + seqNo + sessionStr + " 不在已发队列中,不能释放,窗口大小 "
                        + size + " 窗口内容 " + sb.toString());
            }

            return null;
        }
    }


    /**
     * 检查超时，由于消息按时间顺序放入缓存，所以只需看第一条，如果超时，立即移除。
     */
    private void checkOverTime() {
        synchronized (lock) {
            if (cache.size() == 0)
                return;
            MsgWrapper mw = (MsgWrapper) cache.getFirst();
            if (System.currentTimeMillis() - mw.tick > 100000) {
                cache.removeFirst();
                StringBuffer sb = new StringBuffer();
                sb.append(NUMBER);
                sb.append(mw.obj.getSeqNo());
                sb.append(sessionStr);
                sb.append(OVERTIME);
                logger.info(sb.toString());
                //暂时去掉超时重发,当消息超过100秒之后丢弃
                //tool.send(mw.obj, 8);
//				
                if (errTool == null) {
                    logger.error("没有配置超时丢弃的消息队列！，消息：" + mw.obj);
                } else {
                    errTool.send(mw.obj, 8);
                    logger.error("消息超时，放入超时队列！，消息：" + mw.obj);
                }
            }
        }
    }

    private void clear() {
        logger.info("连接断开，清理现场...");
        if (reSend) {
            synchronized (lock) {
                while (cache.size() > 0) {

                    tool.send(((MsgWrapper) cache.removeFirst()).obj, 8);
                }
            }
        } else {
            synchronized (lock) {
                cache.clear();
            }
        }
    }

    /**
     * 消息包装类
     *
     * @author Administrator
     */
    private class MsgWrapper {
        public long tick;

        public SeqMsgAdapter obj;

        public MsgWrapper(SeqMsgAdapter obj) {
            tick = System.currentTimeMillis();
            this.obj = obj;
        }
    }
}
