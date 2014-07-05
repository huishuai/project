package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-3
 * Time: 18:33:46
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomTypeListBeanRes {
  String errorCode = "000"       
  List<CustomType>  customTypeList = new ArrayList<CustomType>()
}
