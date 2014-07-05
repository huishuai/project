package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS 集团组的收视信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TeamSubscriptDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class TeamSubscriptDetail
{
    private String serviceID;     /* 收视服务号 */
    private String group;         /* 集团名 */
    private String team;          /* 组名 */
    private String area;          /* 交易地区名 */
    private String agent;         /* 交易网点名 */
    private String productID;     /* 产品编号 */
    private String productNM;     /* 产品名称 */
    private String payMode;       /* 交费方式名称 */
    private String createDate;    /* 创建日期 */
    private String realStartdate; /* 服务开始日期 */
    private String realEndDate;   /* 服务结束日期 */
    private String pauseDate;     /* 服务暂停日期 */
    private String status;        /* 服务状态名称 */
    private String freeStartdate; /*免费开始日期*/
    private String freeEndDate;   /*免费结束日期*/
    private float refPrice;       /*服务标准价格*/
    private float orderPrice;     /*服务定购时的价格*/
    private float currentPrice;   /*服务当前价格*/
    private String isRenew;       /*是否续服务*/
    private String operatorID;    /* 操作员号 */
    private String operateDate;   /* 操作日期 */

    public TeamSubscriptDetail()
    {
    }

    public String getServiceID()
    {
        return serviceID;
    }
    public void setServiceID(String serviceID)
    {
        this.serviceID = serviceID;
    }
    public String getGroup()
    {
        return group;
    }
    public void setGroup(String group)
    {
        this.group = group;
    }
    public String getArea()
    {
        return area;
    }
    public void setArea(String area)
    {
        this.area = area;
    }
    public String getAgent()
    {
        return agent;
    }
    public void setAgent(String agent)
    {
        this.agent = agent;
    }
    public String getTeam()
    {
        return team;
    }
    public void setTeam(String team)
    {
        this.team = team;
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
    public String getPayMode()
    {
        return payMode;
    }
    public void setPayMode(String payMode)
    {
        this.payMode = payMode;
    }
    public String getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    public String getRealStartDate()
    {
        return realStartdate;
    }
    public void setRealStartDate(String realStartdate)
    {
        this.realStartdate = realStartdate;
    }
    public String getRealEndDate()
    {
        return realEndDate;
    }
    public void setRealEndDate(String realEndDate)
    {
        this.realEndDate = realEndDate;
    }
    public String getPauseDate()
    {
        return pauseDate;
    }
    public void setPauseDate(String pauseDate)
    {
        this.pauseDate = pauseDate;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getFreeStartDate()
    {
        return freeStartdate;
    }
    public void setFreeStartDate(String freeStartdate)
    {
        this.freeStartdate = freeStartdate;
    }
    public String getFreeEndDate()
    {
        return freeEndDate;
    }
    public void setFreeEndDate(String freeEndDate)
    {
        this.freeEndDate = freeEndDate;
    }
    public float getRefPrice()
    {
        return refPrice;
    }
    public void setRefPrice(float refPrice)
    {
        this.refPrice = refPrice;
    }
    public float getOrderPrice()
    {
        return orderPrice;
    }
    public void setOrderPrice(float orderPrice)
    {
        this.orderPrice = orderPrice;
    }
    public float getCurrentPrice()
    {
        return currentPrice;
    }
    public void setCurrentPrice(float currentPrice)
    {
        this.currentPrice = currentPrice;
    }
    public String getIsRenew()
    {
        return isRenew;
    }
    public void setIsRenew(String isRenew)
    {
        this.isRenew = isRenew;
    }
    public String getOperatorID()
    {
        return operatorID;
    }
    public void setOperatorID(String operatorID)
    {
        this.operatorID = operatorID;
    }
    public String getOperateDate()
    {
        return operateDate;
    }
    public void setOperateDate(String operateDate)
    {
        this.operateDate = operateDate;
    }
 
}