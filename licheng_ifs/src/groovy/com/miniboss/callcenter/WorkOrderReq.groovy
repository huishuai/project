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
  long customerId        //�ͻ����
  String customName       //�ͻ�����
  String ID              //���֤��
  String uuid
  String areaid       //����ID
  String netid        //����ID
  String address
  String bussType  //ҵ�����ͣ�����װ����ά�ޣ�
  String CustomerType  //�ͻ�����
  String workOrderNo  //�������
  String sendWorker     //�ɹ���
  String sendDate     //�ɹ�ʱ��
  String receiveWorker    //�ص���
  String receiveDate      //�ص�ʱ��
  String area       //��������
  Date createDate
  String status          //����״̬:1δ���� 2������ 3ʩ���� 4��� 5�ѳ��� 6�ѹ���
  String processId       //������ID
}
