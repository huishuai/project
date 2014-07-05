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
   * 计费类型：01 – 点播 02 – 包月
   */
  static final String BILLINGTYPE_PLAY = "01"
  static final String BILLINGTYPE_MONTH = "02" 
  
  String uuId	                //用户号
  String productId	            //产品ID
  String billingType	        //计费类型
  String billingCycleCount	//计费周期量值
  Date beginDate	            //开始时间
}
