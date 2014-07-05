package com.dvn.miniboss.log

/**
 * 系统日志
 */
class SysLog {
  long id               //流水号
  String operatorId     //操作员ID
  long customId         //客户ID
  long userId           //用户ID
  SysAction action    //行为
  String description    //行为描述
  Date operateDate      //操作时间
  Date createDate       //创建时间

  static constraints = {
    description(size: 0..1000, nullable: true)
    operateDate(nullable: true)
    userId(nullable: true)
    customId(nullable: true)
    operatorId(nullable: true)
  }

  static mapping = {
    table 'SYSLOG'
    version false
    id generator: 'assigned'
    createDate column: 'CREATEDATE'
    operateDate column: 'OPERATEDATE'
    customId column: 'CUSTOMID'
    operatorId column: 'OPERATORID'
  }
}
