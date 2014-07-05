package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-24
 * Time: 6:50:24
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class NetCode {
    String id             //网点号
    String netnm          //网点名
    String isbase         //类型标志
    String address        //联系地址
    String zipcode        //邮政编码
    String maintele       //联系电话
    String mainfax        //联系传真
    String status          //状态
    String areaCodeId     //地区编号
    String locationid     //可使用的仓库代码
    Date createdate       //创建日期
    String description    //描述

}
