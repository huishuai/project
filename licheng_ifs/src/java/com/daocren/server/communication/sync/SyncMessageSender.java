package com.daocren.server.communication.sync;

import org.apache.log4j.Logger;
import org.apache.mina.common.IoSession;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-13
 * Time: 11:30:47
 * ͬ����Ϣ��
 */
public class SyncMessageSender {
    /**
     * ����logger��Ϣ
     */
    private final Logger logger = Logger.getLogger(getClass());
    private static int num = 0;
    private static int num2 = 0;
    public static int waitTime = 0;

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    private SessionsContainer sessionContainer = new SessionsContainer();
    private String sessionName = "default";
    private SyncThreadContainer syncThreadContainer = new SyncThreadContainer();
    private SyncResMessageContainer syncResMessageContainer = new SyncResMessageContainer();

    public SyncMessageSender(String sessionName, SessionsContainer sessionContainer) {
        this.sessionName = sessionName;
        this.sessionContainer = sessionContainer;
    }

    public void setSyncThreadContainer(SyncThreadContainer syncThreadContainer) {
        this.syncThreadContainer = syncThreadContainer;
    }

    public void setSyncResMessageContainer(SyncResMessageContainer syncResMessageContainer) {
        this.syncResMessageContainer = syncResMessageContainer;
    }

    /**
     * ������Ϣ��Ȼ���õ�ǰ�̵߳ȴ������̷߳����߳������У�ֱ����������ߵȴ���ʱ�Ž���ȴ������ͷ��߳������е��߳�
     *
     * @param syncMessage ��Ϣ
     * @param thread      ��ǰ�߳�
     * @return SyncMessage ��Ӧ��Ϣ
     */
    public SyncMessage sendMessage(SyncMessage syncMessage, Thread thread) {
        try {
            synchronized (thread) {
                IoSession ioSession = sessionContainer.getSession(sessionName);
                syncThreadContainer.addThread(syncMessage.getSyncMessageId(), thread);
                if (logger.isDebugEnabled()) {
                    logger.debug("ISCP������Ϣ�߳���ӽ����� :" + syncMessage.getSyncMessageId());
                    logger.debug("ISCP������Ϣ���͸�BOSS��������" + (++num));
                    logger.debug("ISCP������Ϣ���͸�BOSS�� sessionName��" + sessionName);
                    logger.debug("ISCP������Ϣ���͸�BOSS�� ioSession��" + ioSession);
                }
                synchronized (ioSession) {
                    logger.info("ISCP������Ϣ���͸�BOSS sessionId: " + syncMessage.getSyncMessageId());
                    ioSession.write(syncMessage);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("ISCP������Ϣ�̴߳��ڵȴ�!" + syncMessage.getSyncMessageId());
                }
                thread.wait(waitTime);
                if (logger.isDebugEnabled()) {
                    logger.debug("ISCP������Ϣ�߳��Ѿ�������! " + syncMessage.getSyncMessageId() + "  �ѱ������̵߳�������" + (++num2));
                }
                SyncMessage message = syncResMessageContainer.getResponseMessage(syncMessage.getSyncMessageId());
                syncThreadContainer.deleteThread(syncMessage.getSyncMessageId());
                if (logger.isDebugEnabled()) {
                    logger.debug("ISCP������Ϣ�߳��������̸߳��� " + syncThreadContainer.size());
                }
                syncResMessageContainer.deleteResponseMessage(syncMessage.getSyncMessageId());
                return message;
            }
        } catch (Exception e) {
            logger.error("ISCP������Ϣ���͸�BOSSʧ�ܣ���ϢID��" + syncMessage.getSyncMessageId(), e);
            try {
                if (syncThreadContainer.containsKey(syncMessage.getSyncMessageId())) {
                    syncThreadContainer.deleteThread(syncMessage.getSyncMessageId());
                }
            } catch (Exception e2) {
                logger.error("ISCP������Ϣ�߳�������߳������е��߳�ʧ�ܣ���ϢID��" + syncMessage.getSyncMessageId(), e);
            }
        }
        return null;
    }

    /**
     * ����ȴ��߳�
     *
     * @param respMessage ��Ӧ��Ϣ
     */
    public void notifyResponseMessage(SyncMessage respMessage) {
        if (respMessage != null && respMessage.getSyncMessageId() != null) {
            try {
                Thread thread = syncThreadContainer.getThread(respMessage.getSyncMessageId());
                if (thread == null) {
                   logger.warn("���߳������л�ȡ�߳�ʧ�ܣ��߳��Ѿ�����ǰ�ͷ���:" + respMessage.getSyncMessageId());
                } else {
                    syncResMessageContainer.addResponseMessage(respMessage.getSyncMessageId(), respMessage);
                    synchronized (thread) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("BOSS�Ѿ�������ISCP������Ϣ�������߳�:" + respMessage.getSyncMessageId());
                        }
                        thread.notify();
                    }
                }
            } catch (Exception e) {
                logger.error("BOSS�Ѿ�������ISCP������Ϣ�������߳�ʧ�� ID��" + respMessage.getSyncMessageId());
            }
        }
    }
}
