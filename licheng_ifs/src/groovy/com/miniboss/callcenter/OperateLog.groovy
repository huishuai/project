package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:08:12
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class OperateLog {
    long id                //流水号
    String operatorId     //操作员ID
    long customId         //客户ID
    long userId           //用户ID
    long operateActionId //日志类型id
    String description    //行为描述
    Date operateDate      //操作时间
    Date createDate       //创建时间

}
