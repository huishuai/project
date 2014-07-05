package com.bmsp;

/**
 * Copyright lr.
 * User: admin
 * Date: 2008-8-14
 * Time: 13:30:06
 */
public class BsmpConstant {
    /**
     * 业务信息请求消息类型
     */
    public static final int BUSINESSINFO_MSG_REQ = 0x0006;
    /**
     * 业务信息响应消息类型
     */
    public static final int BUSINESSINFO_MSG_RES = 0x8006;
    /**
     * 用户使用记录消息请求类型
     */
    public static final int USERECORD_MSG_REQ = 0x0007;
    /**
     * 用户使用记录消息响应类型
     */
    public static final int USERECORD_MSG_RES = 0x8007;
    /**
     * 套餐业务信息关系请求消息类型
     */
    public static final int BUSINESSINFOREF_MSG_REQ = 0x0008;
    /**
     * 套餐业务信息关系响应消息类型
     */
    public static final int BUSINESSINFOREF_MSG_RES = 0x8008;

    /**
     * 产品定购
     */
    public static final int BOSS_MSG_REQ = 0x00000002;
    /**
     * 鉴全结果返回
     */
    public static final int BOSS_MSG_RES = 0x80000002;
    /**
     * 心跳信息
     */
    public static final int BOSS_HERT_REQ = 0x00000001;
    /**
     * 心跳返回信息
     */
    public static final int BOSS_HERT_RES = 0x80000001;
}
