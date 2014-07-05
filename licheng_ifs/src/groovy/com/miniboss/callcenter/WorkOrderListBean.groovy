package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-1
 * Time: 17:35:02
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class WorkOrderListBean {
  String errorCode = "000"
  List<WorkOrder> workOrderList = new ArrayList<WorkOrder>()    
}
