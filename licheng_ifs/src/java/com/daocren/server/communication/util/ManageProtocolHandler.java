package com.daocren.server.communication.util;

import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.log4j.Logger;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectInstance;
import javax.management.remote.JMXConnector;
import java.util.Set;

/**
 * 用于监测队列状态<br/>
 * stat 查询所有队列中消息数量<br/>
 * quit 退出查询<br/>
 * shutdown 关闭系统
 * @author Daocren
 */
public class ManageProtocolHandler extends IoHandlerAdapter {
    private static final Logger logger = Logger.getLogger(ManageProtocolHandler.class);


    private JMXConnector jmxConnector;

    /**
     * 获取JMX链接器
     * @return JMX链接器
     */
    public JMXConnector getJmxConnector() {
        return jmxConnector;
    }

    /**
     * 设置JMX连接器
     * @param jmxConnector
     */
    public void setJmxConnector(JMXConnector jmxConnector) {
        this.jmxConnector = jmxConnector;
    }

    /**
     * 对话创建回调方法
     * @param session
     * @throws Exception
     */
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        session.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory()));
    }
    /**
     * 异常关闭回调方法
     * @param session
     * @throws Exception
     */
    public void exceptionCaught(IoSession session, Throwable cause) {
        session.close();
        logger.warn("manager service access exception, close connection", cause);
    }

    /**
     * 对话关闭回调方法
     * @param session
     * @throws Exception
     */
    public void sessionClosed(IoSession session) throws Exception {
//		online = false;
    }

    /**
     * 处理命令行信息
     * @param session
     * @param message
     */
    public void messageReceived(IoSession session, Object message) {
        String str = message.toString();
        logger.info("执行命令: " + str);
        String[] cmds = str.split(":|,");
        if (cmds.length >= 1) {
            try {
                if ("quit".equals(cmds[0])) {
                    session.write("bye!");
                    session.close();
                }//-----------------------------   sqg blacklist
                else if ("stat".equals(cmds[0])) {
                    MBeanServerConnection mbserverCon = jmxConnector.getMBeanServerConnection();
                    Set set = mbserverCon.queryMBeans(null, null);
                    for (Object o : set) {
                        ObjectInstance oi = (ObjectInstance) o;
                        if (oi.getClassName().equals("org.apache.activemq.broker.jmx.QueueView")) {
                            Object proxy = MBeanServerInvocationHandler.newProxyInstance(mbserverCon, oi.getObjectName(), QueueViewMBean.class, true);
                            QueueViewMBean qw = (QueueViewMBean) proxy;
                            session.write(" queue " + qw.getName() + ":" + qw.getQueueSize());
                        }
                    }
                } else if ("shutdown".equals(cmds[0])) {
                    logger.info("手动关闭服务!");
                    session.write("bye!");
                    session.close();
                    System.exit(0);
                } else if ("?".equals(cmds[0])) {
                    help(session);
                } else {
                    session.write(" cmd invalid-  " + str);
                }
                session.write("  done");
            } catch (Exception e) {
                session.write(" cmd invalid-  " + e.getMessage());
                prtException(session, e);
                logger.error("执行命令出错 ", e);
            }
        } else {
            session.write(" cmd invalid-  " + str);
        }
    }

    private static void prtException(IoSession session, Exception e) {
        session.write(e);
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement aTrace : trace) session.write("\tat " + aTrace);
    }

    /**
     * 对话打开回调方法<br/>
     * 打印帮助信息
     * @param session
     * @throws Exception
     */
    public void sessionOpened(IoSession session) throws Exception {
        help(session);
    }

    private void help(IoSession session) {
        session.write("++++++++++++++++++++++++++++++++++++++++++");
        session.write("                                          ");
        session.write(" stat  to list all queueName and it's message count ");
        session.write("                                          ");
        session.write(" quit  to close connection");
        session.write(" shutdown  to shutdown the application");
        session.write(" ?  to show help");
        session.write("++++++++++++++++++++++++++++++++++++++++++");
    }

}
