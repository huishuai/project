package com.dvn.miniboss.acct

import javax.xml.bind.annotation.XmlTransient
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlType

/**
 * 工单实体
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkOrder", namespace = "http://vo.call.sms.dvnchina.com")
class WorkOrder implements Serializable {
   //todo 可根据客户姓名、所属区域、派工人、派工时间、回单人、回单时间

    String id
	String workOrderNo  //工单编号
    String name
    String typeName
    String customId       //客户ID
    String customName       //客户姓名
    String sendWorker     //派工人
    String sendDate     //派工时间
    String receiveWorker    //回单人
    String receiveDate      //回单时间
    String area       //所属地区
    @XmlTransient
    String type            //工单类型
    String subType           //子类型，工单类型的细分，根据不同的工单类型设置
    @XmlTransient
    String typeId          //工单类型对应的主键ID
    Date createDate
    String status          //工单状态:未申请、执行中、挂起、已完成
    String subStatus
    String assignee
    String processId       //工作流ID
    @XmlTransient
    String taskId
    String taskName
    String taskActivityName
    String taskState
    @XmlTransient
    String action
    String operatorId
    String extendsValue;
    Date lastUpdateTime
    Date preCompleteTime 
    @XmlTransient
    long version
    String description    //描述
    public static final String STATUS_START = "1";//开始未执行状态
    public static final String STATUS_EXECUTE = "2";//执行状态
    public static final String STATUS_PAUSE = "3";//暂停状态
    public static final String STATUS_END = "4";//结束状态

	// 各种工单的子状态
	public static final String SUBSTATUS_DVNSTBINSTALL_NOTAKE = "1";//未领用
	public static final String SUBSTATUS_DVNSTBINSTALL_TAKE = "2";//已领用
	public static final String SUBSTATUS_DVNSTBINSTALL_EXECUTE = "3";//施工中
	public static final String SUBSTATUS_DVNSTBINSTALL_END = "4";//完成
	public static final String SUBSTATUS_DVNSTBINSTALL_CANCEL = "5";//已撤单
	public static final String SUBSTATUS_DVNSTBINSTALL_PAUSE = "6";//已挂起

    public static final String ACTION_TAKE="1";
    public static final String ACTION_COMPLETE="2";

	public static final int workOrderNoNumberLength = 7;//编号的数字编号长度

	public static final String WORKORDERNO_MASTER = "Z";//主机标识
	public static final String WORKORDERNO_SLAVE = "F";//从机标识

	public static final String NAME_DVBSTBINSTALL = "DvbStbInstall";//初装工单

	public static final String BUSINESSTYPE_OPENCUSTOM = "0";//开户工单
	public static final String BUSINESSTYPE_STBSALE = "1";//机卡销售工单
	public static final String BUSINESSTYPE_CHANGECUSTOMFORWARD = "2";//转机工单
	public static final String BUSINESSTYPE_MOVEADDRESS = "3";//迁址工单

    static constraints = {
        extendsValue(nullable: true) 
        taskId(nullable: true)
        taskName(nullable: true)
        taskActivityName(nullable: true)
        taskState(nullable: true)
        assignee(nullable: true)
        lastUpdateTime(nullable: true)
        preCompleteTime(nullable: true)
        sendDate(nullable: true)
        sendWorker(nullable: true)
        receiveDate(nullable: true)
        receiveWorker(nullable: true)
        operatorId(nullable: true)
        subStatus(nullable: true)
        subType(nullable: true)
        customId(nullable: true)
        action(nullable: true)
        area(nullable: true)
        customName(nullable: true)
        description(nullable: true,size:0..3000)
    }
    static mapping = {
        id generator: 'uuid'
        table "cmng_workorder"
        version false
        processId column: 'PROCESSID'
        typeId column: 'TYPEID' 
        customId column: 'CUSTOMID' 
        taskId column: 'TASKID'
        operatorId column: 'OPERATORID'
        extendsValue column: 'EXTENDSVALUE',type:"text"
        createDate column: 'CREATEDATE'
        lastUpdateTime column: 'UPDATEDATE'
        preCompleteTime column: 'PRECOMPLETEDATE'
    }
}
