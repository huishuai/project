package com.daocren.server.communication.util;

import com.daocren.server.communication.lightmessage.MessageTool;
import com.daocren.server.communication.message.MsgConstant;
import com.daocren.server.communication.message.SeqMsgAdapter;
import org.apache.log4j.Logger;
import org.apache.mina.common.IoSession;

import java.util.LinkedList;

/**
 * ��Ϣ���Ϳ�����
 *
 * @author Daocren
 */
public class SendControler {
    private static final Logger logger = Logger.getLogger(SendControler.class);

    private static final String NUMBER = "#";

    private static final String SUC = "�ɹ�";

    private static final String FULL = "��������";

    private static final String REL = "�ͷ�";

    private static final String ERR = "����";

    private static final String OVERTIME = "��ʱ";

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
     * ͨ����Ϣ�����߹���
     * @param session �Ի�
     * @param tool ��Ϣ����
     * @param channelMaxCount ��󲢷���������
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
     * ͨ����Ϣ�����߹���
     * @param session �Ի�
     * @param tool ��Ϣ����
     * @param channelMaxCount ��󲢷���������
     * @param errTool ������Ϣ������
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
     * ��������
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
                        logger.warn("ȡ��Ϣ����", e);
                        obj = null;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                        }
                    }
                }
                logger.info("��ʼ����������");
                clear();
            }
        });
        worker.setName("sender- "
                + tool.getJmsTemplate().getDefaultDestinationName());
        logger.info("���������߳� " + worker.getName() + sessionStr);
        worker.start();

    }

    /**
     * ���Է��ͣ����û�п��ô��ڣ���ʧ��
     *
     * @return ���ͽ��
     */
    public boolean trySend(SeqMsgAdapter obj) {
        synchronized (lock) {
            // ֻ�������ӣ��ŷ�
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
                logger.error("�����ѹرգ�����,����ֹͣ");
                stop();
                return false;
            }
        }
    }

    /**
     * ֹͣ���ͷ���
     */
    public void stop() {
        logger.error("ֹͣsendcontrol!");
        running = false;
    }

    /**
     * ���±�������棬����ж�Ӧ��Ϣ����ֱ���Ƴ�
     *
     * @param seqNo ��Ϣ��ˮ��
     * @param success ������
     * @return ��������
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
                    // �����͵Ķ��󷵻ظ������߳�
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
                logger.warn("! #" + seqNo + sessionStr + " �����ѷ�������,�����ͷ�,���ڴ�С "
                        + size + " �������� " + sb.toString());
            }

            return null;
        }
    }


    /**
     * ��鳬ʱ��������Ϣ��ʱ��˳����뻺�棬����ֻ�迴��һ���������ʱ�������Ƴ���
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
                //��ʱȥ����ʱ�ط�,����Ϣ����100��֮����
                //tool.send(mw.obj, 8);
//				
                if (errTool == null) {
                    logger.error("û�����ó�ʱ��������Ϣ���У�����Ϣ��" + mw.obj);
                } else {
                    errTool.send(mw.obj, 8);
                    logger.error("��Ϣ��ʱ�����볬ʱ���У�����Ϣ��" + mw.obj);
                }
            }
        }
    }

    private void clear() {
        logger.info("���ӶϿ��������ֳ�...");
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
     * ��Ϣ��װ��
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
