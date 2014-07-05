package com.miniboss.call.vo;


import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS 收视服务信息返回</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：SMSError 内容如下
//(1) SMSError.Code = ‘000’ 表示无错。
//(2) SMSError.Code = ‘300’表示SMS 查询记录时出错

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriptionInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class SubscriptionInfo
{
    private SMSError subError;                         /* 返回错误信息 */
    private String totalResults;                       /* 返回记录总条数 */
    private SubscriptionDetail[ ] subscriptionDetails; /* 收视服务信息 */

    public SubscriptionInfo()
    {
    }

    public SubscriptionDetail[] getSubscriptionDetails()
    {
        return subscriptionDetails;
    }
    public void setSubscriptionDetails(SubscriptionDetail[] subscriptionDetails)
    {
        this.subscriptionDetails = subscriptionDetails;
    }
    public SMSError getSubError()
    {
        return subError;
    }
    public void setSubError(SMSError subError)
    {
        this.subError = subError;
    }
    public String getTotalResults()
    {
        return totalResults;
    }
    public void setTotalResults(String totalResults)
    {
        this.totalResults = totalResults;
    }
}