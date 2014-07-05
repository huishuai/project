package com.dvn.miniboss.product

import com.dvn.miniboss.log.OperateAction

/**
 * 分区多业务群用户授权（特殊用户包1003）历史信息表
 * User: humingxing
 * Date: 11-6-29
 * Time: 下午4:30
 * To change this template use File | Settings | File Templates.
 * （注：此类在历城crm中也存在，修改该类时，注意同时修改历城crm）
 */
class AreacontrolSpecialuserHistory {
    String id              //id
    long customId        //客户ID
    long userId          //用户ID
    String goodsNumber   //机顶盒号和ic卡号
    Date createDate      //创建时间
    OperateAction action  //行为
    String areaId         //地区ID
    String netId         //网点ID
    String operatorId    //操作员ID
    String description   //描述

    static mapping = {
        table 't_areacl_sluser_history'
        version false
        id generator: 'uuid', column: 'id'
        customId column: 'custom_id'
        userId column: 'user_id'
        goodsNumber column: 'goods_number'
        createDate column: 'create_date'
        areaId column: 'area_id'
        netId column: 'net_id'
        operatorId column: 'operator_id'
        description column: 'description'
    }

    static constraints = {
        description(size: 0..1000)
        customId(nullable: true)
        userId(nullable: true)
        description(nullable: true)
        goodsNumber(nullable: false)
        createDate(nullable: false)
        areaId(nullable: false)
        netId(nullable: false)
        operatorId(nullable: false)
    }
}