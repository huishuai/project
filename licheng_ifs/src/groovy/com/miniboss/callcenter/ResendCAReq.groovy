package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:14:04
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ResendCAReq {
    long customerId        //�ͻ���ţ�����Ϊ���˿ͻ������ķ���Ϊ�������������л����з�����Ȩ��	  �ʼ��ϴ˲��� �� ע�����跢�͸��˿ͻ��Ŀͻ�id ��ѡ �ɲ�ѡ
    String operatorId       //����ԱID
    long[] serviceIdArray //��������Id������

}
