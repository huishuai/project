package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-8-1
 * Time: 17:20:27
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class Product {
  String name                 //��Ʒ����
  String commodity        //������Ʒ����
  String area             //����
  String stbType          //���������ͣ��������ӻ���
  String customType       //�û����ͣ����С��屣������
  Integer price               //�۸�
  String billingCycle         //�Ʒ�����
  String description        //����
  Date createTime           //����ʱ��
  Date startTime            //��ʼʱ��
  Date endTime              //����ʱ��  
}
