package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:12:41
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomerLogReq {
    long customerId   //客户编号
    String customerName //客户姓名
    String ID              //身份证号
    String stbId         //机顶盒编号
    long operateActionId //日志类型id
    Date startDate       //开始时间
    Date endDate        //结束时间
}
