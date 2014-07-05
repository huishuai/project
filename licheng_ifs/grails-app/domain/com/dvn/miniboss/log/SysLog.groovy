package com.dvn.miniboss.log

/**
 * ϵͳ��־
 */
class SysLog {
  long id               //��ˮ��
  String operatorId     //����ԱID
  long customId         //�ͻ�ID
  long userId           //�û�ID
  SysAction action    //��Ϊ
  String description    //��Ϊ����
  Date operateDate      //����ʱ��
  Date createDate       //����ʱ��

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
