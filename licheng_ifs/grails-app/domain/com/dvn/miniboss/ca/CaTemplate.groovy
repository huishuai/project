package com.dvn.miniboss.ca

/**
 * 指令模板表
 * （注：此类在历城crm中也存在，修改该类时，注意同时修改历城crm）
 */
class CaTemplate implements Serializable {
    //指令模板ID主键
    String id
    //指令模板名称
    String name
    //状态:1或null为启用； 0为禁用
    String status

    //模板状态
    public static final String STATUS_RUNNING = "1"   //启用
    public static final String STATUS_STOPPED = "0"   //禁用

    public static final String SPECIALUSER = "1000" //特殊用户包
    public static final String VILLAGE = "1001" //农网用户
    public static final String CITY = "1002" //城网用户
    public static final String VILLAGE_SPECIALUSER = "1003" //农网特殊用户包
    public static final String CITY_SPECIALUSER = "1004" //城网特殊用户包

    static constraints = {
        id(size: 0..20, nullable: false)
        name(size: 0..100, nullable: true)
        status(size: 1..1, blank: false)
    }

    static mapping = {
        table "ca_template"
        version false
        id generator: 'assigned', column: 'ID'
        cache false
    }
}
