package com.miniboss.bean.bankcard

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-6-30
 * Time: 10:17:30
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CommodityPlayReqBean {

  /**
   * �Ʒ����ͣ�01 �C �㲥 02 �C ����
   */
  static final String BILLINGTYPE_PLAY = "01"
  static final String BILLINGTYPE_MONTH = "02" 
  
  String uuId	                //�û���
  String productId	            //��ƷID
  String billingType	        //�Ʒ�����
  String billingCycleCount	//�Ʒ�������ֵ
  Date beginDate	            //��ʼʱ��
}
