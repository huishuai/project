package com.daocren.server.communication.engine;

import org.apache.mina.handler.demux.MessageHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


/**
 * Created by IntelliJ IDEA.
 * User: Daocren
 * Date: 2008-8-4
 * Time: 14:19:42
 * 对话处理器配置<br/>
 * 可以同时配置编解码器，及类对应的处理器
 */
public class SessionHandlerConfig {
    private List<Class> coders = new ArrayList<Class>();
    private Map<Class, MessageHandler> clazzHandlerMap = new LinkedHashMap<Class, MessageHandler>();
    private Class keepAliveClass;

    /**
     * 获取编解码列表
     *
     * @return 编解码列表
     */
    public List<Class> getCoders() {
        return coders;
    }

    /**
     * 设置编解码列表
     *
     * @param coders 编解码列表
     */
    public void setCoders(List<Class> coders) {
        this.coders = coders;
    }


    /**
     * 获取类对应的处理器
     *
     * @return 类对应的处理器
     */
    public Map<Class, MessageHandler> getClazzHandlerMap() {
        return clazzHandlerMap;
    }

    /**
     * 设置类对应的处理器
     *
     * @param clazzHandlerMap 类对应的处理器
     */
    public void setClazzHandlerMap(Map<Class, MessageHandler> clazzHandlerMap) {
        this.clazzHandlerMap = clazzHandlerMap;
    }

    /**
     * 设置保持链接的消息类型
     *
     * @param keepAliveClass 保持链接的消息类型
     */
    public void setKeepAliveClass(Class keepAliveClass) {
        this.keepAliveClass = keepAliveClass;
    }

    /**
     * 获取保持链接的消息类型
     *
     * @return keepAliveClass 保持链接的消息类型
     */
    public Class getKeepAliveClass() {
        return keepAliveClass;
    }
}
