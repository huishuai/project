package com.miniboss.bean.print
/**
 * Created by IntelliJ IDEA.
 * User: tb
 * Date: 2010-3-17
 * Time: 17:05:12
 * To change this template use File | Settings | File Templates.
 */

public class NetPrintPayBillBean implements Serializable{

    //项目ID
    String itemId
    //单据ID
    String billId
    //项目
    String itemName
    //说明
    String itemRemark
    //金额
    long itemFee

}