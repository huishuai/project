package com.daocren.server.communication.filter;

import EDU.oswego.cs.dl.util.concurrent.PooledExecutor;
import com.daocren.server.communication.lightmessage.MessageToolFactory;
import com.daocren.server.communication.message.AbstractMsg;
import com.daocren.server.communication.util.GeneralCommand;
import com.daocren.server.communication.util.GuardFilter;
import com.daocren.server.communication.util.SimpleContext;
import org.apache.commons.chain.Chain;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ͨ��ת����<br/>
 * ����Ϣ��һ������ȡ��Ȼ�󾭹�һϵ�й�������ҵ����<br/>
 * �п������뵽����һ������
 * @author Daocren
 */
public class ForwardWorker {
    private static final Logger logger = Logger.getLogger(ForwardWorker.class);

    // ת����������Ϣ��Դ����
    private String srcQname;

    // ������Ϣ�����ӹ�����
    private MessageToolFactory toolMgr;

    // ������Ϣ��filter
    private List filters = new ArrayList();

    private boolean running = true;

    private int maxWorker = 5;

    /**
     * ת������ʼ������
     */
    public void init() {
        if (toolMgr == null) {
            throw new IllegalArgumentException("��Ϣ������toolMgr����Ϊ��");
        }

        /*
           * �̳߳أ� ������߳�����һ�������Ÿ��������̻߳��������Ժ󱻻��գ�
           * �������г���Ϊһ�����������̳߳�˲����ռ���Ϊ10���������оŸ������������߳�ִ�У�һ�������빤�����У�
           * ���Ѵﵽ���޵�����£�������µ�������룬����reject policy :CallerRunsPolicy��֤���������߳�����ִ��
           */
        // final Executor executor = new ThreadPoolExecutor(1, maxWorker, 5,
        // TimeUnit.SECONDS, new LinkedBlockingQueue(1),
        // new ThreadPoolExecutor.CallerRunsPolicy());

        /* update by eric 2006-8-21 for tomcat cant use ThreadPoolExecutor (it seems be that)
           * use PooledExecutor instead
           * DEFAULT_MINIMUMPOOLSIZE = 1;
           * DEFAULT_KEEPALIVETIME = 60 * 1000;
           * real thread number is maxWorker + 1 ,for the master thread is also run for service
           */
        final PooledExecutor executor = new PooledExecutor(maxWorker);

        new Thread(new Runnable() {
            public void run() {
                logger.info("��ʼ��ת����: " + srcQname);
                while (running) {
                    final Object obj = toolMgr.getTool(srcQname).receive();
                    if (obj != null) {
                        // ���̳߳�ִ������
                        try {
                            executor.execute(new Runnable() {

                                public void run() {
                                    Chain chain = new ChainBase();
                                    // �쳣��׽
                                    chain.addCommand(new GuardFilter());

                                    // ҵ���������
                                    for (Iterator iter = filters.iterator(); iter
                                            .hasNext();) {
                                        MsgFilter f = (MsgFilter) iter.next();
                                        chain.addCommand(new GeneralCommand(f));
                                    }
                                    Context ctx = new SimpleContext(
                                            (AbstractMsg) obj);
                                    try {
                                        chain.execute(ctx);
                                    } catch (Exception e) {
                                        logger.error("ת���� " + srcQname
                                                + " ִ��filter����", e);
                                    }
                                }
                            });
                        } catch (InterruptedException e) {
                            logger.info("Thread Interrupted!");
                        }

                    }

                }


                executor.shutdownAfterProcessingCurrentlyQueuedTasks();
                logger.info("endת����: " + srcQname);

            }
        }).start();

    }

    /**
     * ��ֹת��
     */
    public void stop() {
        running = false;
    }

    /**
     * ��ȡ�������б�
     * @return �������б�
     */
    public List getFilters() {
        return filters;
    }

    /**
     * ���ù������б�
     * @param filters �������б�
     */
    public void setFilters(List filters) {
        this.filters = filters;
    }

    /**
     * ��ȡԴ��������
     * @return Դ��������
     */
    public String getSrcQname() {
        return srcQname;
    }

    /**
     * ����Դ��������
     * @param srcQname Դ��������
     */
    public void setSrcQname(String srcQname) {
        this.srcQname = srcQname;
    }

    /**
     * ��ȡ��Ϣ������
     * @return ��Ϣ������
     */
    public MessageToolFactory getToolMgr() {
        return toolMgr;
    }

    /**
     * ������Ϣ������
     * @param toolMgr ��Ϣ������
     */
    public void setToolMgr(MessageToolFactory toolMgr) {
        this.toolMgr = toolMgr;
    }

    /**
     * ��ȡ��ͬʱ�����worker����
     * @return worker����
     */
    public int getMaxWorker() {
        return maxWorker;
    }

    /**
     * ���ÿ�ͬʱ�����worker����
     * @param maxWorker worker����
     */
    public void setMaxWorker(int maxWorker) {
        this.maxWorker = maxWorker;
    }

}
