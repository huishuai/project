package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 2:54:29
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomerInfo {
    long id              //客户ID，系统使用
    String areaid       //地区ID
    String netid        //网点ID
    Date opendate       //开户日期
    String customNo     //客户唯一标识号，用于客户业务办理的时候使用
    String stockid       //储物柜编号
    String customType   //客户组织类型
    String bankAssign   //是否银行代扣标识:0-非代扣，1-代扣
    Date createDate     //创建时间
    String type       //客户类型：城市用户、农村用户
    String status    //状态
    String lease                    //是否为租赁客户,默认应该为非租赁客户
    String customnm               //客户名称
    String sex                     //性别
    Date birthday                  //生日
    String phone                   //手机
    String address                 //详细地址
    String zipcode                 //邮编
    String maintele                //电话
    String idcard                  //身份证号
    String email                   //电子邮箱
    String district                 //地区
    String bankassign              //是否为银行代扣
    String remark                  //备注
    List<User> userList = new ArrayList<User>()            //用户列表
}


