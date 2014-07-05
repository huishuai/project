package com.miniboss.callcenter


import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 1:39:14
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class NetListBean {
    String errorCode = "000" 
    List<NetCode> netList = new ArrayList<NetCode>()

}
