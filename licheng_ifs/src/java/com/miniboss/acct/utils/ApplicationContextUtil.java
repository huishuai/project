package com.miniboss.acct.utils;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.BeansException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-10-22
 * Time: 16:41:58
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;
       public ApplicationContextUtil()
       {
       }
       public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
           this.applicationContext = applicationContext;
       }
       public static ApplicationContext getApplicationContext()
       {
           return applicationContext;
       }
       public static Object getBean(String name){
           return applicationContext.getBean(name);
       }
    
}
