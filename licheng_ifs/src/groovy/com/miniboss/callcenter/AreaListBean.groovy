package com.miniboss.callcenter


import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 1:37:43
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class AreaListBean {
    String errCode = "000"  
    List<AreaCode> areaList = new ArrayList<AreaCode>()
}
