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
  String id                   //���׺�
  String cusotmerId         //�ͻ���ʶ
  String productName        //��Ʒ���� - �ɷ�����
  Date createDate           //��������    
  Date srvstartDate         //����ʼ����
  Date srvendDate           //�����������
  long serviceDays         //����ʱ��(��)
  long realprice           //���ѽ��
  String billType          //��������(����)
  String transName         //�������ƣ��㲥�����ֶһ�����Ϸ��ֵ�ң�
  String transContent     //���ݣ�Ƭ�������Ӿ����ƣ�
  String happenType       //�������۷�/��ֵ
}
