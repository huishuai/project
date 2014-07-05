package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 14:32:53
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ServiceInfo {
  String serviceId //服务号
  String userId  //用户ID
  String stbnum  // 机顶盒编号
  String iccard // IC卡号
  String productname // 产品服务名称
  java.util.Date startdate // 服务开始
  java.util.Date enddate // 服务结束
  String status  // 服务状态 1正常使用中 2主动暂停  用户报停 3欠费暂停 4服务终止 5未生效 6机卡安装状态中 7用户主动退订 9基本服务暂停
  String userStatus   //用户状态  1有效 2无效 3服务已报停
  String paymode // 服务方式 01预付费 02后付费
}
