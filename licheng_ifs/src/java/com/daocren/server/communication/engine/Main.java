package com.daocren.server.communication.engine;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ����spring�Ĺ�����
 */
public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    /**
     * spring������Ϣ
     */
    static ApplicationContext ctx = null;

    /**
     * �þ�̬���������ԭ����main�����г�ʼ�������Ա�֤shutdown hook ��������ִ��
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                logger.info("�ر�ϵͳ......");
                try {
                    if (ctx != null) {
                        ((AbstractApplicationContext) ctx).close();
                    }
                } catch (Exception e) {
                    logger.error("�رճ���", e);
                }
                logger.info("...�ѹر�ϵͳ");
            }
        });
    }

    /**
     * ������������spring
     * @param args
     */
    public static void main(String[] args) {
        // �趨
        System.setProperty("activemq.broker.disable-clean-shutdown", "true");
        //ÿ���Ӽ��һ��log4j����
        org.apache.log4j.PropertyConfigurator.configureAndWatch("classes/log4j.properties", 1000);
//		ctx = new ClassPathXmlApplicationContext(args);
        ctx = new ClassPathXmlApplicationContext("spring.iscp.xml");
//        new Thread() {
//            public void run() {
//                Random r = new Random();
//                MessageToolFactory tf = (MessageToolFactory) ctx.getBean("msgToolMgr");
//                MessageTool messageTool = tf.getTool("msg");
//                UserClickMsg userClickMsg = new UserClickMsg();
//                while (true) {
//                    userClickMsg.setUserId(StringUtils.fixLength("daocren", 16).getBytes());
//                    userClickMsg.setServiceId(StringUtils.fixLength("serviceId" + r.nextInt(2), 32).getBytes());
//                    userClickMsg.setClickParaName(StringUtils.fixLength("para" + r.nextInt(2), 16).getBytes());
//                    messageTool.send(userClickMsg);
//                    System.out.println("add");
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();

//		Logger logger = Logger.getLogger(Main.class);
//		logger.info("ϵͳ������......");
//		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
//				args);
//		context.registerShutdownHook();
//		logger.info("ϵͳ�������......");
    }

}
