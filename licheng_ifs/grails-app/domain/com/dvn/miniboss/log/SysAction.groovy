package com.dvn.miniboss.log

class SysAction {
  long id               //流水号
  String name           //名称
  String description    //描述
  Date createDate       //创建时间

  static constraints = {
    description(size: 0..1000, nullable: true)
    name(nullable: true)
    createDate(nullable: true)
  }

  static mapping = {
    table 'SYSACTION'
    version false
    createDate column: 'CREATEDATE'
    id generator: 'assigned'
  }
}
