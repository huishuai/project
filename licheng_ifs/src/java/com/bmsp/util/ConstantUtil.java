package com.bmsp.util;

/**
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-14
 * Time: 14:44:48
 */
public class ConstantUtil {
    /**
     * 包月产品订购 心跳 请求
     */
    public static final  int BOSS_ORDER_MONTH_KEEPLIVE_REQ = 0x81000001;
    /**
     * 包月产品订购 心跳 响应
     */
    public static final int BOSS_ORDER_MONTH_KEEPLIVE_RES = 0x82000001;

    public static final int BOSS_MARK = 0x00E7C6B5;

    public static final int BOSS_ORDER_MARK = 0x00E60001;
    /**
     * 按次点播产品定购
     */
    public static final int BOSS_ORDER_ONE_REQ = 0x00000002;
    /**
     * 按次点播鉴全结果返回
     */
    public static final int BOSS_ORDER_ONE_RES = 0x80000002;
    /**
     * 心跳信息
     */
    public static final int BOSS_KEEP_LIFE_REQ = 0x00000001;
    /**
     * 心跳返回信息
     */
    public static final int BOSS_KEEP_LIFE_RES = 0x80000001;
    /**
     * 产品定购
     */
    public static final int BOSS_ORDER_REQ = 0x60000001;
    /**
     * 产品定购应答
     */
    public static final int BOSS_ORDER_RES = 0x62000001;
    /**
     * 产品退订
     */
    public static final int BOSS_DISORDER_REQ = 0x60000001;
    /**
     * 产品退订应答
     */
    public static final int BOSS_DISORDER_RES = 0x62000001;
    /**
     * 订购信息正则
     */
//    public static final String ORDER_MESSAGE_REGEX = "<session_id>(.*?)</session.*?<identity_type>(.*?)</identity_type.*?<dev_no>(.*?)</dev_no.*?<oper_type>(.*?)</oper_type.*?<prod_id>(.*?)</prod_id.*?<order_type>(.*?)</order_type>";
    public static final String ORDER_MESSAGE_REGEX = "<session_id>(.*?)</session_id.*?<identity_type>(.*?)</identity_type.*?<dev_no>(.*?)</dev_no.*?<oper_type>(.*?)</oper_type.*?<prod_id>(.*?)</prod_id.*?<order_type>(.*?)</order_type.*?</billCycleCount>(.*?)</billCycleCount>";
    /**
     * 点播信息正则
     */
    public static final String ORDER_EVERY_TIME_REGEX = "<session_id>(.*?)</session_id>.*?<type>(.*?)</type>.*?<asset_id>(.*?)</asset_id>*?<sp_id>(.*?)</sp_id>.*?<start_time>(.*?)</start_time>.*?<dev_no>(.*?)</dev_no>";
    /**
     * 订购返回信息正则
     */
    public static final String ORDER_RESPONSE_REGEX = "<session_id>(.*?)</session_id>.*?<err_code>(.*?)</err_code>.*?<trans_type>(.*?)</trans_type>.*?<dev_no>(.*?)</dev_no>.*?<prod_id>(.*?)</prod_id>.*?<start_date>(.*?)</start_date>.*?<end_date>(.*?)</end_date>";
    /**
     * 心跳信息正则
     */
    public static final String KEEP_LIVE_REGEX = "<session_id>(.*?)</session_id>";
    /**
     * 点播返回信息正则
     */
    public static final String BILLING_REGEX = "<session_id>(.*?)</session.*?<type>(.*?)</type.*?<result>(.*?)</result";
    /**
     * 时间格式
     */
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

 
}
