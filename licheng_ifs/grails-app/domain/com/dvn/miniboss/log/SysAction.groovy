package com.dvn.miniboss.log

class SysAction {
  long id               //��ˮ��
  String name           //����
  String description    //����
  Date createDate       //����ʱ��

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
