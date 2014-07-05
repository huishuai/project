package com.daocren.server.communication.engine;

import edu.emory.mathcs.backport.java.util.concurrent.RejectedExecutionHandler;
import edu.emory.mathcs.backport.java.util.concurrent.ThreadPoolExecutor;
import org.apache.mina.integration.spring.ThreadPoolExecutorFactoryBean;

/**
 * �߳�ִ�й�����
 * @author daocren
 */
public class ThreadExecutorFactoryBean extends
        ThreadPoolExecutorFactoryBean {
    public ThreadExecutorFactoryBean() {
        super.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());// ���ܵ������ɵ����߳�ִ��
        super.setQueueCapacity(-1);// ���ó�ͬ������
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
