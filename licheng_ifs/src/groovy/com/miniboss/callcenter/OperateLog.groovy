package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:08:12
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class OperateLog {
    long id                //��ˮ��
    String operatorId     //����ԱID
    long customId         //�ͻ�ID
    long userId           //�û�ID
    long operateActionId //��־����id
    String description    //��Ϊ����
    Date operateDate      //����ʱ��
    Date createDate       //����ʱ��

}
