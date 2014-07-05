package com.daocren.server.communication.sync;

import org.apache.log4j.Logger;
import org.apache.mina.common.IoSession;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-13
 * Time: 11:30:47
 * 同布消息器
 */
public class SyncMessageSender {
    /**
     * 基本logger信息
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
     * 发送消息，然后让当前线程等待，将线程放入线程容器中，直到被激活或者等待超时才解除等待，并释放线程容器中的线程
     *
     * @param syncMessage 消息
     * @param thread      当前线程
     * @return SyncMessage 响应消息
     */
    public SyncMessage sendMessage(SyncMessage syncMessage, Thread thread) {
        try {
            synchronized (thread) {
                IoSession ioSession = sessionContainer.getSession(sessionName);
                syncThreadContainer.addThread(syncMessage.getSyncMessageId(), thread);
                if (logger.isDebugEnabled()) {
                    logger.debug("ISCP请求消息线程添加进容器 :" + syncMessage.getSyncMessageId());
                    logger.debug("ISCP请求消息发送给BOSS的总数：" + (++num));
                    logger.debug("ISCP请求消息发送给BOSS的 sessionName：" + sessionName);
                    logger.debug("ISCP请求消息发送给BOSS的 ioSession：" + ioSession);
                }
                synchronized (ioSession) {
                    logger.info("ISCP请求消息发送给BOSS sessionId: " + syncMessage.getSyncMessageId());
                    ioSession.write(syncMessage);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("ISCP请求消息线程处于等待!" + syncMessage.getSyncMessageId());
                }
                thread.wait(waitTime);
                if (logger.isDebugEnabled()) {
                    logger.debug("ISCP请求消息线程已经被激活! " + syncMessage.getSyncMessageId() + "  已被激活线程的总数：" + (++num2));
                }
                SyncMessage message = syncResMessageContainer.getResponseMessage(syncMessage.getSyncMessageId());
                syncThreadContainer.deleteThread(syncMessage.getSyncMessageId());
                if (logger.isDebugEnabled()) {
                    logger.debug("ISCP请求消息线程容器中线程个数 " + syncThreadContainer.size());
                }
                syncResMessageContainer.deleteResponseMessage(syncMessage.getSyncMessageId());
                return message;
            }
        } catch (Exception e) {
            logger.error("ISCP请求消息发送给BOSS失败，消息ID：" + syncMessage.getSyncMessageId(), e);
            try {
                if (syncThreadContainer.containsKey(syncMessage.getSyncMessageId())) {
                    syncThreadContainer.deleteThread(syncMessage.getSyncMessageId());
                }
            } catch (Exception e2) {
                logger.error("ISCP请求消息线程中清除线程容器中的线程失败，消息ID：" + syncMessage.getSyncMessageId(), e);
            }
        }
        return null;
    }

    /**
     * 激活等待线程
     *
     * @param respMessage 响应消息
     */
    public void notifyResponseMessage(SyncMessage respMessage) {
        if (respMessage != null && respMessage.getSyncMessageId() != null) {
            try {
                Thread thread = syncThreadContainer.getThread(respMessage.getSyncMessageId());
                if (thread == null) {
                   logger.warn("从线程容器中获取线程失败，线程已经被提前释放了:" + respMessage.getSyncMessageId());
                } else {
                    syncResMessageContainer.addResponseMessage(respMessage.getSyncMessageId(), respMessage);
                    synchronized (thread) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("BOSS已经返回了ISCP请求消息，激活线程:" + respMessage.getSyncMessageId());
                        }
                        thread.notify();
                    }
                }
            } catch (Exception e) {
                logger.error("BOSS已经返回了ISCP请求消息，激活线程失败 ID：" + respMessage.getSyncMessageId());
            }
        }
    }
}
