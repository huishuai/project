package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-1
 * Time: 16:53:50
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class WorkOrder {
   String id
	String workOrderNo   //工单编号
    String name            //工单名称
    String typeName        //工单类型名称
    String customId       //客户ID
    String customName       //客户姓名
    String sendWorker     //派工人
    String sendDate     //派工时间
    String receiveWorker    //回单人
    String receiveDate      //回单时间
    String area       //所属地区
    String type            //工单类型
    String subType           //子类型，工单类型的细分，根据不同的工单类型设置
    Date createDate       //创建日期
    String status          //工单状态:未申请、执行中、挂起、已完成
    String subStatus      //子状态 1未领用 2已领用 3施工中 4完成 5已撤单 6已挂起
    String taskName          //任务名称
    String operatorId           //操作员编号
    String extendsValue;          //扩展数据
    Date lastUpdateTime            //最后更新时间
    Date preCompleteTime           //完成时间
    String description    //描述
    String reasonForUp    //撤单原因or挂起原因
}
