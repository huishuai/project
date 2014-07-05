package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-24
 * Time: 6:49:28
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class AreaCode {
    String id              //地区编号
    String areanm           //名称
    String parentid         //父地区
    String areatype         //类型
    String status           //状态
    String areaNo           //编号，用于模糊查找输入功能
    Date createdate         //创建时间
    String description      //描述

}
