package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 7:31:57
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ConsumeInfo {
    long id
    long cusotmerId           //客户标识
    Date createdate           //服务创建日期
    Date srvstartdate         //服务开始日期
    Date srvenddate           //服务结束日期
    long servicedays         //服务时间(天)
    long realprice                      //实际交费金额
    long refund                         //退款金额--退款在调账中处理
    Date refunddate           //退款日期--退款在调账中处理
    String productId          //产品标识
    String productName        //产品名称

}
