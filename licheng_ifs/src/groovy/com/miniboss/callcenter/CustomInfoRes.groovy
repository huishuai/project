package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 13:45:42
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomInfoRes {
  String errorCode = "000"
  String customnm // �ͻ�����
  String customNo // �ͻ����
  Date createDate // ����ʱ��
  String maintele // �绰
  String userType // �û�����  1.��ͨ�û� 2.��ҵ�û��� 3.���ſͻ���
  String voucher // ֤������
  String idcard  // ֤������
  String stbnum // �ն˸���
  Date mainDate // ������װ����
  String status // �Ƿ���Ч 01��Ч
  String type // �ͻ�����
  String balance // �ֽ��˺����
  String address // ��ϵ��ַ
  String customType // �ͻ����"1"Ϊ���˿ͻ���"2"Ϊ��ҵ�ͻ���"3"Ϊ���ſͻ�

  List<CustomStbNum> customStbNumList = new ArrayList<CustomStbNum>() //�����к��б�
}
