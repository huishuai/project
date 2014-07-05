package com.miniboss.bean

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-9
 * Time: 14:53:31
 * To change this template use File | Settings | File Templates.
 */
class OrderPayReq {
    String uuId                    //用户号
    String productId                //产品ID
    String resourceId;              //资源ID
    String billingCycleCount    //计费周期量值
    Date beginDate                //开始时间
    String transId;                  //交易号
    long pay                   //付费
}
