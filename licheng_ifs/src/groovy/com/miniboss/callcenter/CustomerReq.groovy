package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:11:57
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomerReq {
    long customerId   //�ͻ����
    String customerName //�ͻ�����
    String ID              //���֤��
    String stbId         //�����б��
    String uuid
}
