package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-8-1
 * Time: 17:22:13
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class ProductListBean {
  String errorCode = "000" 
  List<Product>  productList = new ArrayList<Product>()

}
