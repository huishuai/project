package com.dvn.miniboss.product

import com.dvn.miniboss.log.OperateAction

/**
 * ������ҵ��Ⱥ�û���Ȩ�������û���1003����ʷ��Ϣ��
 * User: humingxing
 * Date: 11-6-29
 * Time: ����4:30
 * To change this template use File | Settings | File Templates.
 * ��ע������������crm��Ҳ���ڣ��޸ĸ���ʱ��ע��ͬʱ�޸�����crm��
 */
class AreacontrolSpecialuserHistory {
    String id              //id
    long customId        //�ͻ�ID
    long userId          //�û�ID
    String goodsNumber   //�����кź�ic����
    Date createDate      //����ʱ��
    OperateAction action  //��Ϊ
    String areaId         //����ID
    String netId         //����ID
    String operatorId    //����ԱID
    String description   //����

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