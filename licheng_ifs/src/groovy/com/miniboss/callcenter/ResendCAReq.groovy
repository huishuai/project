package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:14:04
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ResendCAReq {
    long customerId        //客户编号，集团为个人客户订购的服务，为避免向集团下所有机顶盒发送授权，	  故加上此参数 ， 注意是需发送个人客户的客户id 可选 可不选
    String operatorId       //操作员ID
    long[] serviceIdArray //订购服务Id的数组

}
