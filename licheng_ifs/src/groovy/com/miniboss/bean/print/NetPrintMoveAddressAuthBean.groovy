package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-27
 * Time: 9:43:43
 * To change this template use File | Settings | File Templates.
 */
/**
 * 营业厅业务受理单[网内迁址-订购关系]
 */
public class NetPrintMoveAddressAuthBean implements Serializable {
    //服务名称
    String authServeName
     //价格
    long price
    //计费周期
    String feePeriodTypeName
     //开始日期
    String realstartdate
    //结束日期
    String realenddate
     //迁出日期
    String moveAddressStartDate 
}
