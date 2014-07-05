package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS 频道信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只提供激活系列的信息。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscribeChannelDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SubscribeChannelDetail
{
    private String channelID;       /* 频道号 */
    private String channelNM;       /* 频道名 */
    private String status;          /* 状态名称 */
    private String createDate;      /* 创建日期 */
    private String isCctvProduct;   /* 是否是CCTV 频道 */
    private String cctvProductCode; /* CCTV 频道号 */

    public SubscribeChannelDetail()
    {
    }

    public String getChannelID()
    {
        return channelID;
    }
    public void setChannelID(String channelID)
    {
        this.channelID = channelID;
    }
    public String getChannelNM()
    {
        return channelNM;
    }
    public void setChannelNM(String channelNM)
    {
        this.channelNM = channelNM;
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
    public String getIsCctvProduct()
    {
        return isCctvProduct;
    }
    public void setIsCctvProduct(String isCctvProduct)
    {
        this.isCctvProduct = isCctvProduct;
    }
    public String getCctvProductCode()
    {
        return cctvProductCode;
    }
    public void setCctvProductCode(String cctvProductCode)
    {
        this.cctvProductCode = cctvProductCode;
    }

}