package com.dvn.miniboss.sync

/**
 * ���ڱ����BSMPͬ������ӰƬý����ϢBean
 */

class UmsPoint implements Serializable {

  String id    //id
  String uuid            //�û���UU��-------------------uuid
  String resourceId       //ӰƬ��ԴID
  int pointScore       //��������
  Date convertTime         //�һ�ʱ��


  static constraints = {
  }
  static mapping = {
    table "UMS_POINT"
    version false
    id generator: "uuid", column: 'ID'
    uuid column: 'UUID'
    resourceId column: 'RESOURCEID'
    pointScore column: 'POINT_SCORE'
    convertTime column: 'CONVERT_TIME'
  }
}