package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:07:46
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class OperateLogListBean {
    String errorCode = "000"    
    List<OperateLog> operateLogList = new ArrayList<OperateLog>()

}
