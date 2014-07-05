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
	String workOrderNo   //�������
    String name            //��������
    String typeName        //������������
    String customId       //�ͻ�ID
    String customName       //�ͻ�����
    String sendWorker     //�ɹ���
    String sendDate     //�ɹ�ʱ��
    String receiveWorker    //�ص���
    String receiveDate      //�ص�ʱ��
    String area       //��������
    String type            //��������
    String subType           //�����ͣ��������͵�ϸ�֣����ݲ�ͬ�Ĺ�����������
    Date createDate       //��������
    String status          //����״̬:δ���롢ִ���С����������
    String subStatus      //��״̬ 1δ���� 2������ 3ʩ���� 4��� 5�ѳ��� 6�ѹ���
    String taskName          //��������
    String operatorId           //����Ա���
    String extendsValue;          //��չ����
    Date lastUpdateTime            //������ʱ��
    Date preCompleteTime           //���ʱ��
    String description    //����
    String reasonForUp    //����ԭ��or����ԭ��
}
