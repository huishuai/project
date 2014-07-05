package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 13:45:42
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomInfoRes {
  String errorCode = "000"
  String customnm // 客户姓名
  String customNo // 客户编号
  Date createDate // 开户时间
  String maintele // 电话
  String userType // 用户类型  1.普通用户 2.企业用户组 3.集团客户组
  String voucher // 证件名称
  String idcard  // 证件号码
  String stbnum // 终端个数
  Date mainDate // 主机安装日期
  String status // 是否有效 01有效
  String type // 客户类型
  String balance // 现金账号余额
  String address // 联系地址
  String customType // 客户类别"1"为个人客户，"2"为企业客户，"3"为集团客户

  List<CustomStbNum> customStbNumList = new ArrayList<CustomStbNum>() //机顶盒号列表
}
