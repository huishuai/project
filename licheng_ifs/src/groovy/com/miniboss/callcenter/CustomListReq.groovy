package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-4
 * Time: 13:18:43
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomListReq {
  String customNo  // �ͻ����
  String customnm // �ͻ�����
  String maintele // �绰
  String address  // ��ַ
  String stbid    // �����б��
  String uuid     // UU��
  String type     // �ͻ�����
  String cardId   // ���ܿ���
}
