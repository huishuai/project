package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-3
 * Time: 18:45:29
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class BusinessTypeListBeanRes {
  String errorCode = "000"       
  List<BusinessType>  businessTypeList = new ArrayList<BusinessType>()
}
