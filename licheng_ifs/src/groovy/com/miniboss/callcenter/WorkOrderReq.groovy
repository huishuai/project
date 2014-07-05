package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-2
 * Time: 9:51:09
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class WorkOrderReq {
  long customerId        //客户编号
  String customName       //客户姓名
  String ID              //身份证号
  String uuid
  String areaid       //地区ID
  String netid        //网点ID
  String address
  String bussType  //业务类型，（新装还是维修）
  String CustomerType  //客户类型
  String workOrderNo  //工单编号
  String sendWorker     //派工人
  String sendDate     //派工时间
  String receiveWorker    //回单人
  String receiveDate      //回单时间
  String area       //所属地区
  Date createDate
  String status          //工单状态:1未领用 2已领用 3施工中 4完成 5已撤单 6已挂起
  String processId       //工作流ID
}
