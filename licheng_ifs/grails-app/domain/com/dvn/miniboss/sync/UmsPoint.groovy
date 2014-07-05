package com.dvn.miniboss.sync

/**
 * 用于保存从BSMP同步到的影片媒资信息Bean
 */

class UmsPoint implements Serializable {

  String id    //id
  String uuid            //用户的UU号-------------------uuid
  String resourceId       //影片资源ID
  int pointScore       //积分数额
  Date convertTime         //兑换时间


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