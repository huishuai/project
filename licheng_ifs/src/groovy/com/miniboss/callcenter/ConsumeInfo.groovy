package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 7:31:57
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ConsumeInfo {
    long id
    long cusotmerId           //�ͻ���ʶ
    Date createdate           //���񴴽�����
    Date srvstartdate         //����ʼ����
    Date srvenddate           //�����������
    long servicedays         //����ʱ��(��)
    long realprice                      //ʵ�ʽ��ѽ��
    long refund                         //�˿���--�˿��ڵ����д���
    Date refunddate           //�˿�����--�˿��ڵ����д���
    String productId          //��Ʒ��ʶ
    String productName        //��Ʒ����

}
