package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: Star
 * Date: 2010-8-7
 * Time: 16:49:17
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class Notify {
  String title    //����
  String content  //����
  Date sendDate   //����ʱ��
  Date endDate    //��ֹʱ��
  String operator //������
  String type //�������� �ʼ���
}
