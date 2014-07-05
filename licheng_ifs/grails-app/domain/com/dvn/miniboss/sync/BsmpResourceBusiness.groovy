package com.dvn.miniboss.sync

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-12-10
 * Time: 13:53:51
 * To change this template use File | Settings | File Templates.
 */

class BsmpResourceBusiness {
  String id
  String businessCode
  String businessName
  String assetId
  String assetName

  static constraints = {
    businessCode(nullable: false)
    businessName(size: 0..100, nullable: false)
    assetId(size: 0..60, nullable: false)
    assetName(size: 0..100, nullable: false)
  }
  static mapping = {
    table "BSMP_RESOURCE_BUSINESS"
    version false
    id generator: "uuid", column: 'ID'
    businessCode column: 'BUSINESSCODE'
    businessName column: 'BUSINESSNAME'
    assetId column: 'ASSETID'
    assetName column: 'ASSETNAME'
  }
}