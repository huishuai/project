package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 15:30:34
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class TransDetail {
  String id                   //交易号
  String cusotmerId         //客户标识
  String productName        //产品名称 - 缴费类型
  Date createDate           //交易日期    
  Date srvstartDate         //服务开始日期
  Date srvendDate           //服务结束日期
  long serviceDays         //服务时间(天)
  long realprice           //交费金额
  String billType          //单据类型(名称)
  String transName         //交易名称（点播、积分兑换、游戏充值币）
  String transContent     //内容（片名、电视剧名称）
  String happenType       //发生金额：扣费/充值
}
