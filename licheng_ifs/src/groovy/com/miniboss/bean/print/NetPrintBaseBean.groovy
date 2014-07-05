package com.miniboss.bean.print
/**
 * Created by IntelliJ IDEA.
 * User: tb
 * Date: 2010-3-9
 * Time: 15:34:49
 * To change this template use File | Settings | File Templates.
 */
/**
 * 营业厅业务受理单[母版]
 */
public class NetPrintBaseBean implements Serializable{
    //受理单日期
    String billDate
    //受理工号
    String operatorId
    //受理单编号
    String billCode
    //------客户资料-----------------
    //客户编号
    String customNo
    //客户姓名
    String customnm
    //证件名称
    String certificatetypeName
    //证件号码
    String certificated
    //用户类型
    String typeCmngCustomtypeName
    //UU号
    String UUid
    //住宅电话
    String phone
    //移动电话
    String mobilephone
    //装机地址
    String addressconn

    //------费用信息-----------------
    //金额(分)
    long feeNum
    //金额大写
    String feeTxt

    //------注意事项-----------------
    //注意事项
    String warring

    //子页面
    String localTemplate
    //页面记录数
    int pageSize = 10
    //总页数
    int pageCount = 1

}