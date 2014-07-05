package com.dvn.miniboss.sync

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-12-9
 * Time: 20:16:33
 * To change this template use File | Settings | File Templates.
 */

class BsmpResource {

  String id
  String resourceId
  String resourceName
  String provider
  String providerId

  static constraints = {
    resourceId(size: 0..50, nullable: false)
    resourceName(nullable: true)
    provider(nullable: true)
    providerId(size: 0..50, nullable: false)
  }

  static mapping = {
    table "BSMP_RESOURCE"
    version false
    id generator: "uuid", column: 'ID'
    resourceId column: 'RESOURCEID'
    resourceName column: 'RESOURCENAME'
    provider column: 'PROVIDER'
    providerId column: 'PROVIDERID'
  }
}