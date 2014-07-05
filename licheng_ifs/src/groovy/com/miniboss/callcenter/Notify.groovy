package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: Star
 * Date: 2010-8-7
 * Time: 16:49:17
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class Notify {
  String title    //标题
  String content  //内容
  Date sendDate   //发起时间
  Date endDate    //截止时间
  String operator //发起人
  String type //公告类型 邮件等
}
