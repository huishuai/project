package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 7:28:04
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ConsumeInfoListBean {
    String errorCode = "000" 
    List<ConsumeInfo> customerInfoList = new ArrayList<ConsumeInfo>()

}
