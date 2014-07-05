package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS PPV 帐单信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PpvBillDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class PpvBillDetail
{
    private String billID;         /* ＰＰＶ服务帐单号 */
    private String serviceID;      /* ＰＰＶ服务号 */
    private String customerID;     /* 用户编号 */
    private String stbID;          /* 机顶盒号 */
    private String cardID;         /* 智能卡号 */
    private String programID;      /* 节目编号 */
    private String programNM;      /* 节目名称 */
    private String productNM;      /* 产品名称 */
    private String epochStartTime; /* 节目开播时间 */
    private String orderTime;      /* 订购时间 */
    private float  refPrice;       /* 标准价格 */
    private String price;          /* 价格 */
    private String payStartDate;   /* 交费开始日期 */
    private String payEndDate;     /* 交费结束日期 */
    private String payDate;        /* 交费日期 */
    private String priority;       /* 帐单优先级 */
    private String isPaid;         /* 是否已交费 */
    private String operatorID;     /* 操作员 */
    private String operateDate;    /* 操作日期 */

    public PpvBillDetail()
    {
    }

    public String getBillID()
    {
        return billID;
    }
    public void setBillID(String billID)
    {
        this.billID = billID;
    }
    public String getServiceID()
    {
        return serviceID;
    }
    public void setServiceID(String serviceID)
    {
        this.serviceID = serviceID;
    }
    public String getCustomID()
    {
        return customerID;
    }
    public void setCustomID(String customerID)
    {
        this.customerID = customerID;
    }
    public String getStbID()
    {
        return stbID;
    }
    public void setStbID(String stbID)
    {
        this.stbID = stbID;
    }
    public String getCardID()
    {
        return cardID;
    }
    public void setCardID(String cardID)
    {
        this.cardID = cardID;
    }
    public String getProgramID()
    {
        return programID;
    }
    public void setProgramID(String programID)
    {
        this.programID = programID;
    }
    public String getProgramNM()
    {
        return programNM;
    }
    public void setProgramNM(String programNM)
    {
        this.programNM = programNM;
    }
    public String getProductNM()
    {
        return productNM;
    }
    public void setProductNM(String productNM)
    {
        this.productNM = productNM;
    }
    public String getEpochStartTime()
    {
        return epochStartTime;
    }
    public void setEpochStartTime(String epochStartTime)
    {
        this.epochStartTime = epochStartTime;
    }
    public String getOrderTime()
    {
        return orderTime;
    }
    public void setOrderTime(String orderTime)
    {
        this.orderTime = orderTime;
    }
    public float getRefPrice()
    {
        return refPrice;
    }
    public void setRefPrice(float refPrice)
    {
        this.refPrice = refPrice;
    }
    public String getPrice()
    {
        return price;
    }
    public void setPrice(String price)
    {
        this.price = price;
    }
    public String getPayStartDate()
    {
        return payStartDate;
    }
    public void setPayStartDate(String payStartDate)
    {
        this.payStartDate = payStartDate;
    }
    public String getPayEndDate()
    {
        return payEndDate;
    }
    public void setPayEndDate(String payEndDate)
    {
        this.payEndDate = payEndDate;
    }
    public String getPriority()
    {
        return priority;
    }
    public void setPriority(String priority)
    {
        this.priority = priority;
    }
    public String getIsPaid()
    {
        return isPaid;
    }
    public void setIsPaid(String isPaid)
    {
        this.isPaid = isPaid;
    }
    public String getPayDate()
    {
        return payDate;
    }
    public void setPayDate(String payDate)
    {
        this.payDate = payDate;
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