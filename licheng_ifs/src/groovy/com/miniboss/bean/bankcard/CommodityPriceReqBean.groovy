package com.miniboss.bean.bankcard

import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlTransient

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-6-29
 * Time: 20:11:51
 * To change this template use File | Settings | File Templates.
 */

@XmlAccessorType(XmlAccessType.FIELD)
class CommodityPriceReqBean {
    String uuId                 //�û���
    String productId	        //��ƷID
    @XmlTransient
    String resourceId           //��ԴID��
    String billingCycleCount	//�Ʒ�������ֵ
    Date beginDate	            //��ʼʱ��
}
