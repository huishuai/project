package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS 特殊交费帐单信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpecialBillDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SpecialBillDetail
{
    private String billID;           /* 补交帐单号 */
    private String customerID;       /* 用户编号 */
    private String oldBillID;        /* 原帐单号 */
    private String billType;         /* 帐单类型 */
    private String productNM;        /* 产品名称 */
    private String productStartdate; /* 服务开始日期 */
    private String productEndDate;   /* 服务结束日期 */
    private float  realPrice;        /* 补交价格 */
    private String payStartDate;     /* 交费开始日期 */
    private String payEndDate;       /* 交费结束日期 */
    private String payDate;          /* 付款日期 */
    private String priority;         /* 帐单优先级 */
    private String isPaid;           /* 是否已交费 */
    private String operatorID;       /* 操作员号 */
    private String operateDate;      /* 操作日期 */

    public SpecialBillDetail()
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
    public String getCustomID()
    {
        return customerID;
    }
    public void setCustomID(String customerID)
    {
        this.customerID = customerID;
    }
    public String getOldBillID()
    {
        return oldBillID;
    }
    public void setOldBillID(String oldBillID)
    {
        this.oldBillID = oldBillID;
    }
    public String getBillType()
    {
        return billType;
    }
    public void setBillType(String billType)
    {
        this.billType = billType;
    }
    public String getProductNM()
    {
        return productNM;
    }
    public void setProductNM(String productNM)
    {
        this.productNM = productNM;
    }
    public String getProductStartDate()
    {
        return productStartdate;
    }
    public void setProductStartDate(String productStartdate)
    {
        this.productStartdate = productStartdate;
    }
    public String getProductEndDate()
    {
        return productEndDate;
    }
    public void setProductEndDate(String productEndDate)
    {
        this.productEndDate = productEndDate;
    }
    public float getRealPrice()
    {
        return realPrice;
    }
    public void setRealPrice(float realPrice)
    {
        this.realPrice = realPrice;
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