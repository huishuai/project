package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 13:31:57
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomLight {
  String dvbCostomId // �ͻ�id
  String customNo // �ͻ����
  String customnm // �ͻ�����
  String address  // ��ַ
  String maintele // ��ϵ��ʽ
  String type   // �ͻ�����
  String status // ״̬ 01���� 07�������� 08��װ 09 ��װʧ������
  String idcard // ���֤��
  String customType // �ͻ���� 1���˿ͻ� 2��ҵ�ͻ� 3���ſͻ�
  String lease // 1���޿ͻ���0�����޿ͻ�
}
