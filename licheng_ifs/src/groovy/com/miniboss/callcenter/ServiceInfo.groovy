package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 14:32:53
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ServiceInfo {
  String serviceId //�����
  String userId  //�û�ID
  String stbnum  // �����б��
  String iccard // IC����
  String productname // ��Ʒ��������
  java.util.Date startdate // ����ʼ
  java.util.Date enddate // �������
  String status  // ����״̬ 1����ʹ���� 2������ͣ  �û���ͣ 3Ƿ����ͣ 4������ֹ 5δ��Ч 6������װ״̬�� 7�û������˶� 9����������ͣ
  String userStatus   //�û�״̬  1��Ч 2��Ч 3�����ѱ�ͣ
  String paymode // ����ʽ 01Ԥ���� 02�󸶷�
}
