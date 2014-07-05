package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 13:18:43
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomListReq {
  String customNo  // 客户编号
  String customnm // 客户姓名
  String maintele // 电话
  String address  // 地址
  String stbid    // 机顶盒编号
  String uuid     // UU号
  String type     // 客户类型
  String cardId   // 智能卡号
}
