package com.daocren.server.communication.engine;

import edu.emory.mathcs.backport.java.util.concurrent.RejectedExecutionHandler;
import edu.emory.mathcs.backport.java.util.concurrent.ThreadPoolExecutor;
import org.apache.mina.integration.spring.ThreadPoolExecutorFactoryBean;

/**
 * 线程执行工厂类
 * @author daocren
 */
public class ThreadExecutorFactoryBean extends
        ThreadPoolExecutorFactoryBean {
    public ThreadExecutorFactoryBean() {
        super.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());// 被拒的任务交由调用线程执行
        super.setQueueCapacity(-1);// 设置成同步队列
    }

    @Override
    public void setQueueCapacity(int queueCapacity) {
        throw new UnsupportedOperationException("the queueCapacity can't be set");
    }

    @Override
    public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        throw new UnsupportedOperationException("the rejectedExecutionHandler can't be set");
    }

}
