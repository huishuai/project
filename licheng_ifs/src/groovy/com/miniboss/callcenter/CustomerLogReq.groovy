package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:12:41
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomerLogReq {
    long customerId   //�ͻ����
    String customerName //�ͻ�����
    String ID              //���֤��
    String stbId         //�����б��
    long operateActionId //��־����id
    Date startDate       //��ʼʱ��
    Date endDate        //����ʱ��
}
