package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 13:31:57
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomLight {
  String dvbCostomId // 客户id
  String customNo // 客户编号
  String customnm // 客户名称
  String address  // 地址
  String maintele // 联系方式
  String type   // 客户类型
  String status // 状态 01正常 07正常销户 08初装 09 初装失败销户
  String idcard // 身份证号
  String customType // 客户类别 1个人客户 2企业客户 3集团客户
  String lease // 1租赁客户；0非租赁客户
}
