package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:01:17
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class User {
    long id  //用户标识
    long dvbCostomId//客户标识
    String userTeamId   //用户组标识(保存用户层级关系-父节点)
    String name //用户登录名
    String password //密码 可以不考虑
    String status   //状态
    String description  //描述
    Date createDate //创建时间
    Date updateDate //更新时间
    Date pauseDate  //报停时间
    String classType //机顶盒主从类型(应用于企业和集团,个人不允许使用)
    String groupType  //组类型 
    String operatorFlag    //标记新增、修改操作
    String uuid         //UU号

}
