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
   *  �����01 �C �ɹ� 02 �C ʧ��
   */
  static final String RESULT_SUCCESS = "01"
  static final String RESULT_FAILURE = "02"

  String result	//���
  String message	//��Ϣ  
}
