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
 * ���ڼ�����״̬<br/>
 * stat ��ѯ���ж�������Ϣ����<br/>
 * quit �˳���ѯ<br/>
 * shutdown �ر�ϵͳ
 * @author Daocren
 */
public class ManageProtocolHandler extends IoHandlerAdapter {
    private static final Logger logger = Logger.getLogger(ManageProtocolHandler.class);


    private JMXConnector jmxConnector;

    /**
     * ��ȡJMX������
     * @return JMX������
     */
    public JMXConnector getJmxConnector() {
        return jmxConnector;
    }

    /**
     * ����JMX������
     * @param jmxConnector
     */
    public void setJmxConnector(JMXConnector jmxConnector) {
        this.jmxConnector = jmxConnector;
    }

    /**
     * �Ի������ص�����
     * @param session
     * @throws Exception
     */
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        session.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory()));
    }
    /**
     * �쳣�رջص�����
     * @param session
     * @throws Exception
     */
    public void exceptionCaught(IoSession session, Throwable cause) {
        session.close();
        logger.warn("manager service access exception, close connection", cause);
    }

    /**
     * �Ի��رջص�����
     * @param session
     * @throws Exception
     */
    public void sessionClosed(IoSession session) throws Exception {
//		online = false;
    }

    /**
     * ������������Ϣ
     * @param session
     * @param message
     */
    public void messageReceived(IoSession session, Object message) {
        String str = message.toString();
        logger.info("ִ������: " + str);
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
                    logger.info("�ֶ��رշ���!");
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
                logger.error("ִ��������� ", e);
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
     * �Ի��򿪻ص�����<br/>
     * ��ӡ������Ϣ
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
