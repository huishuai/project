package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS 可订购收视产品信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：
//1) 只提供激活产品的信息。
//2) 只提与用户类型所允许订购的产品。
//3) 只提与用户所属地区相关的产品（包括从上级地区继承的产品）。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscribeProductDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SubscribeProductDetail
{
    private String productID;                         /* 产品编号 */
    private String productNM;                         /* 产品名称 */
    private String productType;                       /* 使用类型名称 */
    private String serviceType;                       /* 服务类型名称 */
    private String status;                            /* 状态 */
    private String createDate;                        /* 创建日期 */
    private String channelTotalResults;               /* 返回频道信息总条数 */
    SubscribeChannelDetail[] subscribeChannelDetails; /* 频道信息 */
    private String seriesTotalResults;                /* 返回系列价格信息总条数 */
    SubscribeSeriesDetail[] subscribeSeriesDetails;   /* 系列价格信息 */

    public SubscribeProductDetail()
    {
    }

    public String getProductID()
    {
        return productID;
    }
    public void setProductID(String productID)
    {
        this.productID = productID;
    }
    public String getProductNM()
    {
        return productNM;
    }
    public void setProductNM(String productNM)
    {
        this.productNM = productNM;
    }
    public String getProductType()
    {
        return productType;
    }
    public void setProductType(String productType)
    {
        this.productType = productType;
    }
    public String getServiceType()
    {
        return serviceType;
    }
    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    public SubscribeChannelDetail[] getSubscribeChannelDetails()
    {
        return subscribeChannelDetails;
    }
    public void setSubscribeChannelDetails(SubscribeChannelDetail[] subscribeChannelDetails)
    {
        this.subscribeChannelDetails = subscribeChannelDetails;
    }
    public String getChannelTotalResults()
    {
        return channelTotalResults;
    }
    public void setChannelTotalResults(String channelTotalResults)
    {
        this.channelTotalResults = channelTotalResults;
    }
    public SubscribeSeriesDetail[] getSubscribeSeriesDetails()
    {
        return subscribeSeriesDetails;
    }
    public void setSubscribeSeriesDetails(SubscribeSeriesDetail[] subscribeSeriesDetails)
    {
        this.subscribeSeriesDetails = subscribeSeriesDetails;
    }
    public String getSeriesTotalResults()
    {
        return seriesTotalResults;
    }
    public void setSeriesTotalResults(String seriesTotalResults)
    {
        this.seriesTotalResults = seriesTotalResults;
    }
 
}