package com.daocren.server.communication;

/**
 * 协议常量
 *
 * @author Daocren
 */
public class Constant {

    /**
     * 绑定消息请求类型
     */
    public static final int BIND_REQ = 0x0001;
    /**
     * 绑定消息返回类型
     */
    public static final int BIND_RES = 0x8001;
    /**
     * 防超时消息请求类型
     */
    public static final int KEEP_ALIVE_REQ = 0x0002;
    /**
     * 防超时消息响应类型
     */
    public static final int KEEP_ALIVE_RES = 0x8002;

}
