package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 13:55:49
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomStbNum {
  String userId  //用户ID
  String status  //用户状态  1有效 2无效 3服务已报停
  String stbnum  //机顶盒号
  String iccard   //IC卡
  String description  //描述
  Date createDate     //创建时间
  String groupType    //组类型 1.普通用户 2.企业用户组 3.集团客户组
}
