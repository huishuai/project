package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-24
 * Time: 6:53:48
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class AuthService {
    String productid    //产品实例id
    String productname   //产品名称
    String isgroup    //等同于客户上的groupType,和cmngUser上的groupType含义不同
    String paymode    //付费模式
    Date createdate
    Date updatedate //修改日期
    Date startdate   //订购开始日期
    Date enddate     //订购截止日期
    Date pausedate    //预报停日期
    Date activepausedate
    Date resumedate  //机顶盒复通
    Date realstartdate  //实际开始时间
    Date realenddate   //实际结束日期
    String formerstatus//前次服务状态
    String status
    Date freestartdate  //免费开始日期
    Date freeenddate   //免费结束日期
    long refpricefix    //参考价格
    long pricefix      //价格
    String isrenew    //是否续服务

}
