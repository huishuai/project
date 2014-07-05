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
 * 通用转发器<br/>
 * 把消息从一个队列取出然后经过一系列过滤器的业务处理<br/>
 * 有可最终入到另外一个队列
 * @author Daocren
 */
public class ForwardWorker {
    private static final Logger logger = Logger.getLogger(ForwardWorker.class);

    // 转发器接收消息的源队列
    private String srcQname;

    // 接收消息的连接管理器
    private MessageToolFactory toolMgr;

    // 处理消息的filter
    private List filters = new ArrayList();

    private boolean running = true;

    private int maxWorker = 5;

    /**
     * 转发器初始化方法
     */
    public void init() {
        if (toolMgr == null) {
            throw new IllegalArgumentException("消息连接器toolMgr不能为空");
        }

        /*
           * 线程池， 里面的线程最少一个，最多九个，空闲线程会在五秒以后被回收．
           * 工作队列长度为一，这样，本线程池瞬间接收极限为10个任务，其中九个立即被工作线程执行，一个被放入工作队列．
           * 在已达到极限的情况下，如果有新的任务加入，根据reject policy :CallerRunsPolicy保证新任务被主线程阻塞执行
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
                logger.info("初始化转发器: " + srcQname);
                while (running) {
                    final Object obj = toolMgr.getTool(srcQname).receive();
                    if (obj != null) {
                        // 用线程池执行任务
                        try {
                            executor.execute(new Runnable() {

                                public void run() {
                                    Chain chain = new ChainBase();
                                    // 异常捕捉
                                    chain.addCommand(new GuardFilter());

                                    // 业务处理过滤器
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
                                        logger.error("转发器 " + srcQname
                                                + " 执行filter出错", e);
                                    }
                                }
                            });
                        } catch (InterruptedException e) {
                            logger.info("Thread Interrupted!");
                        }

                    }

                }


                executor.shutdownAfterProcessingCurrentlyQueuedTasks();
                logger.info("end转发器: " + srcQname);

            }
        }).start();

    }

    /**
     * 终止转发
     */
    public void stop() {
        running = false;
    }

    /**
     * 获取过滤器列表
     * @return 过滤器列表
     */
    public List getFilters() {
        return filters;
    }

    /**
     * 设置过滤器列表
     * @param filters 过滤器列表
     */
    public void setFilters(List filters) {
        this.filters = filters;
    }

    /**
     * 获取源队列名称
     * @return 源队列名称
     */
    public String getSrcQname() {
        return srcQname;
    }

    /**
     * 设置源队列名称
     * @param srcQname 源队列名称
     */
    public void setSrcQname(String srcQname) {
        this.srcQname = srcQname;
    }

    /**
     * 获取消息处理工厂
     * @return 消息处理工厂
     */
    public MessageToolFactory getToolMgr() {
        return toolMgr;
    }

    /**
     * 设置消息处理工厂
     * @param toolMgr 消息处理工厂
     */
    public void setToolMgr(MessageToolFactory toolMgr) {
        this.toolMgr = toolMgr;
    }

    /**
     * 获取可同时处理的worker数量
     * @return worker数量
     */
    public int getMaxWorker() {
        return maxWorker;
    }

    /**
     * 设置可同时处理的worker数量
     * @param maxWorker worker数量
     */
    public void setMaxWorker(int maxWorker) {
        this.maxWorker = maxWorker;
    }

}
