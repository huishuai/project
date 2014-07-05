package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 14:29:23
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ServiceListBeanRes {
  String errorCode = "000"   
  List<ServiceInfo>  serviceInfoList = new ArrayList<ServiceInfo>()
}
