package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:09:52
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class SystemLog {
    long id                //��ˮ��
    long operateActionId //��־����id
    String description    //��Ϊ����
    Date operateDate      //����ʱ��
    Date createDate       //����ʱ��
}
