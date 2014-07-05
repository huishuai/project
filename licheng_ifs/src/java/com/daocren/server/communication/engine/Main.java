package com.daocren.server.communication.engine;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动spring的工具类
 */
public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    /**
     * spring环境信息
     */
    static ApplicationContext ctx = null;

    /**
     * 用静态初化块代替原来在main函数中初始化，用以保证shutdown hook 可以最早执行
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                logger.info("关闭系统......");
                try {
                    if (ctx != null) {
                        ((AbstractApplicationContext) ctx).close();
                    }
                } catch (Exception e) {
                    logger.error("关闭出错", e);
                }
                logger.info("...已关闭系统");
            }
        });
    }

    /**
     * 主方法，启动spring
     * @param args
     */
    public static void main(String[] args) {
        // 设定
        System.setProperty("activemq.broker.disable-clean-shutdown", "true");
        //每分钟检查一下log4j配置
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
//		logger.info("系统启动中......");
//		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
//				args);
//		context.registerShutdownHook();
//		logger.info("系统启动完毕......");
    }

}
