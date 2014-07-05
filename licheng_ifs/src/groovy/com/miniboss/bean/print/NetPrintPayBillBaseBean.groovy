package com.miniboss.bean.print
/**
 * Created by IntelliJ IDEA.
 * User: tb
 * Date: 2010-3-17
 * Time: 17:08:44
 * To change this template use File | Settings | File Templates.
 */

public class NetPrintPayBillBaseBean implements Serializable {

    //------票据抬头-----------------
    //票据代码
    String billId
    //收费部门
    String deptName
    //票据日期 年
    String year
    //票据日期 月
    String month
    //票据日期 日
    String day
    //票据号码
    String billCode
    
    //------客户资料-----------------
    //用户姓名
    String customnm
    //装机地址
    String addressconn
    //电话
    String tel
    //单位
    String unitName
    //证件号码
    String certificated
    //用户编号
    String customCode

     //------费用信息-----------------
    //金额
    long feeNum
    //金额大写
    String feeTxt

    //------票据结尾-----------------
    //收款人
    String operatorName
    //税控码
    String taxCode

}