package com.miniboss.call.vo;


import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS 可订购收视产品信息返回</p>
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
@XmlType(name = "SubscribeProductInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class SubscribeProductInfo
{
    private SMSError subError;                                /* 返回错误信息 */
    private String totalResults;                              /* 返回记录总条数 */
    private SubscribeProductDetail[] subscribeProductDetails; /* 可订购收视产品信息*/

    public SubscribeProductInfo()
    {
    }

    public SubscribeProductDetail[] getSubscribeProductDetails()
    {
        return subscribeProductDetails;
    }
    public void setSubscribeProductDetails(SubscribeProductDetail[] subscribeProductDetails)
    {
        this.subscribeProductDetails = subscribeProductDetails;
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