package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 13:55:49
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomStbNum {
  String userId  //�û�ID
  String status  //�û�״̬  1��Ч 2��Ч 3�����ѱ�ͣ
  String stbnum  //�����к�
  String iccard   //IC��
  String description  //����
  Date createDate     //����ʱ��
  String groupType    //������ 1.��ͨ�û� 2.��ҵ�û��� 3.���ſͻ���
}
