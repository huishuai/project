package com.dvn.miniboss.acct

import javax.xml.bind.annotation.XmlTransient
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlType

/**
 * ����ʵ��
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkOrder", namespace = "http://vo.call.sms.dvnchina.com")
class WorkOrder implements Serializable {
   //todo �ɸ��ݿͻ����������������ɹ��ˡ��ɹ�ʱ�䡢�ص��ˡ��ص�ʱ��

    String id
	String workOrderNo  //�������
    String name
    String typeName
    String customId       //�ͻ�ID
    String customName       //�ͻ�����
    String sendWorker     //�ɹ���
    String sendDate     //�ɹ�ʱ��
    String receiveWorker    //�ص���
    String receiveDate      //�ص�ʱ��
    String area       //��������
    @XmlTransient
    String type            //��������
    String subType           //�����ͣ��������͵�ϸ�֣����ݲ�ͬ�Ĺ�����������
    @XmlTransient
    String typeId          //�������Ͷ�Ӧ������ID
    Date createDate
    String status          //����״̬:δ���롢ִ���С����������
    String subStatus
    String assignee
    String processId       //������ID
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
    String description    //����
    public static final String STATUS_START = "1";//��ʼδִ��״̬
    public static final String STATUS_EXECUTE = "2";//ִ��״̬
    public static final String STATUS_PAUSE = "3";//��ͣ״̬
    public static final String STATUS_END = "4";//����״̬

	// ���ֹ�������״̬
	public static final String SUBSTATUS_DVNSTBINSTALL_NOTAKE = "1";//δ����
	public static final String SUBSTATUS_DVNSTBINSTALL_TAKE = "2";//������
	public static final String SUBSTATUS_DVNSTBINSTALL_EXECUTE = "3";//ʩ����
	public static final String SUBSTATUS_DVNSTBINSTALL_END = "4";//���
	public static final String SUBSTATUS_DVNSTBINSTALL_CANCEL = "5";//�ѳ���
	public static final String SUBSTATUS_DVNSTBINSTALL_PAUSE = "6";//�ѹ���

    public static final String ACTION_TAKE="1";
    public static final String ACTION_COMPLETE="2";

	public static final int workOrderNoNumberLength = 7;//��ŵ����ֱ�ų���

	public static final String WORKORDERNO_MASTER = "Z";//������ʶ
	public static final String WORKORDERNO_SLAVE = "F";//�ӻ���ʶ

	public static final String NAME_DVBSTBINSTALL = "DvbStbInstall";//��װ����

	public static final String BUSINESSTYPE_OPENCUSTOM = "0";//��������
	public static final String BUSINESSTYPE_STBSALE = "1";//�������۹���
	public static final String BUSINESSTYPE_CHANGECUSTOMFORWARD = "2";//ת������
	public static final String BUSINESSTYPE_MOVEADDRESS = "3";//Ǩַ����

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
