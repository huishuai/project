package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-27
 * Time: 15:43:41
 * To change this template use File | Settings | File Templates.
 */

/**
 * 营业厅业务受理单[订购关系]
 */
public class NetPrintAuthItemBean implements Serializable {
     //机顶盒号
    String stdId
    //UUid
    String uuId
    //服务名称
    String authServeName
    //价格
    long price

    //计费周期
    String feePeriodTypeName

    //-----------服务订购---------------
    //开始日期
    String realstartdate
    //结束日期
    String realenddate
    //订购周期数(月)
    long billingCyclecount;

    //-----------服务退订---------------
    //受理日期
    String revertStartDate //closedate
    //终止日期
    String revertEndDate   //realenddate

    //-----------服务暂停---------------
    //暂停开始日期
    String pauseStartDate
    //暂停结束日期
    String pauseEndDate

    //-----------服务恢复---------------
    //服务恢复日期
    String resumeStartDate
    //服务到期日期
    String resumeEndDate

    //合计
    long total
    
}
