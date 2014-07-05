package com.bmsp.util;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-20
 * Time: 10:13:15
 */
public class StatusConstant {
//    000000	请求成功
//    300001	对不起，查询预存款余额信息失败，请稍候查询
//    300002	对不起，此客户的预存款余额信息未建立，请到营业前厅进行相应业务的办理
//    300003	对不起，此产品包信息未被发布，不能订购
//    300004	对不起，您的预存款余额不足，不能订购此产品
//    300005	对不起，订购失败，请稍候重试
//    300006	对不起，您无权订购此产品包，请重新选择其他产品包
//    300007	对不起，您已经订购了此产品包，不能重复订购
//    300008	对不起，此产品包只能在营业前厅办理订购业务，请到营业前厅办理
//    300009	对不起，你们没有订购产品信息
//    300010	对不起，查询订购信息失败，请稍候查询
//    300099	对不起，连接异常，请稍候重试
//    999999	数据格式错误
//

    public static final String BILLING_SUCCESS = "000000";  //鉴权结果 正常

    public static final String BILLING_USER_HAVE_ORDER = "300007";  //对不起，您已经订购了此产品包，不能重复订购
    public static final String PRODUCT_NOT_EXIST = "300003";  //对不起，此产品包信息未被发布，不能订购
    public static final String ORDER_PROCESS_ERROR = "300005";         //对不起，订购失败，请稍候重试
    public static final String DISORDER_PROCESS_ERROR = "300015";         //对不起，退订失败，请稍候重试
    public static final String CONNECT_ERROE = "300099";  //对不起，您已经订购了此产品包，不能重复订购
    public static final String NOT_ORDER = "300009";  //对不起，你们没有订购产品信息
    public static final String USER_INFO_ERROR = "300011";  //用户及其相关信息不不存在或不完整
    public static final String PublishAsset_INFO_ERROR = "300012";  //影片资源及其相关信息不不存在或不完整
    public static final String FORMAT_ERROR="999999";
    public static final String BILLING_NOT_ENOUGH_MONEY = "300004";  //鉴权结果 金额不足

    public static final String BILLING_NOT_ENOUGH_MESSAGE = "300008";  //信息不全，需要到营业厅办理开户业务


    public static final String BILLING_NOT_SUCCESS="0001";     //其他未知错误

    public static final String BILLING_NOT_MONEY_CANNOT_ORDER = "3003";  //欠费状态下，不允许订购

    public static final String BILLING_FAILE_TRYAGAIN_CANNOT_ORDER = "3004";  //欠费状态下，不允许订购

    public static final String BILLING_USERINFO_ERROR_CANNOT_ORDER = "3005";  //用户信息不对,不允许定购

    public static final String BILLING_PRODUCTINFO_ERROR_CANNOT_ORDER = "3006";  //产品信息不对，不允许的定购

    public static final String BILLING_SYSTEM_BUSY_WAIT_MOMENT = "3007";  //系统忙，稍后再定购

    public static final String BILLING_USERINFO_ERROR_CANNOT_DISORDER = "3008";  //用户信息不对,不允许退定

    public static final String BILLING_PRODUCTINFO_ERROR_CANNOT_DISORDER = "3009";  //产品信息不对，不允许的退定
 

    public static final String BILLING_PRICE_MODEL_ERROR = "3601";  //定价计划错误

    public static final String BILLING_ALLOCATIONOFFUNDS_ERROR = "3602";  //资费组合策略错误

    public static final String BILLING_PARAGRAPH_ERROR = "3603";  //段落错误

    public static final String BILLING_EXPENDITURE_ERROR = "3604";  //资费错误
}