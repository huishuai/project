package com.miniboss.bean.bankcard

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-6-30
 * Time: 10:17:39
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CommodityPlayResBean {

  /**
   *  结果：01 – 成功 02 – 失败
   */
  static final String RESULT_SUCCESS = "01"
  static final String RESULT_FAILURE = "02"

  String result	//结果
  String message	//消息  
}
