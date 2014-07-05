package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS 系列价格信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只提供激活系列的信息。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscribeSeriesDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SubscribeSeriesDetail
{
    private String seriesID;     /* 系列号 */
    private String seriesNM;     /* 系列名 */
    private String durationType; /* 期限类型 */
    private String duration;     /* 期限长度 */
    private String refPrice;     /* 主机标准价格 */
    private String refSubPrice;  /* 从机标准价格 */
    private String status;       /* 状态名称 */
    private String createDate;   /* 创建日期 */
    private String modifyDate;   /* 修改日期 */

    public SubscribeSeriesDetail()
    {
    }

    public String getSeriesID()
    {
        return seriesID;
    }
    public void setSeriesID(String seriesID)
    {
        this.seriesID = seriesID;
    }
    public String getSeriesNM()
    {
        return seriesNM;
    }
    public void setSeriesNM(String seriesNM)
    {
        this.seriesNM = seriesNM;
    }
    public String getDurationType()
    {
        return durationType;
    }
    public void setDurationType(String durationType)
    {
        this.durationType = durationType;
    }
    public String getDuration()
    {
        return duration;
    }
    public void setDuration(String duration)
    {
        this.duration = duration;
    }
    public String getRefPrice()
    {
        return refPrice;
    }
    public void setRefPrice(String refPrice)
    {
        this.refPrice = refPrice;
    }
    public String getRefSubPrice()
    {
        return refSubPrice;
    }
    public void setRefSubPrice(String refSubPrice)
    {
        this.refSubPrice = refSubPrice;
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
    public String getModifyDate()
    {
        return modifyDate;
    }
    public void setModifyDate(String modifyDate)
    {
        this.modifyDate = modifyDate;
    }
 
}