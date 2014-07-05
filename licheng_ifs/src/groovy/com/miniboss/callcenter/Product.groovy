package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-8-1
 * Time: 17:20:27
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class Product {
  String name                 //产品名称
  String commodity        //所属商品名称
  String area             //地区
  String stbType          //机顶盒类型（主机、从机）
  String customType       //用户类型（城市、五保户…）
  Integer price               //价格
  String billingCycle         //计费周期
  String description        //描述
  Date createTime           //创建时间
  Date startTime            //开始时间
  Date endTime              //结束时间  
}
