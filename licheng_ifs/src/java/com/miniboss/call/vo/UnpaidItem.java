package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS 未交费单据明细记录</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnpaidItem", namespace = "http://vo.call.sms.dvnchina.com")
public class UnpaidItem
{
    private String billID;           /* 服务帐单号 */
    private String billType;         /* 服务帐类型 */
    private String productNM;        /* 产品名称 */
    private String productStartDate; /* 服务开始日期 */
    private String productEndDate;   /* 服务结束日期 */
    private float realPrice;         /* 服务价格 */
    private float surcharge;         /* 滞纳金 */
    private String payStartDate;     /* 交费开始日期 */
    private String payEndDate;       /* 交费结束日期 */

    public UnpaidItem()
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
        return productStartDate;
    }
    public void setProductStartDate(String productStartDate)
    {
        this.productStartDate = productStartDate;
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
    public float getSurcharge()
    {
        return surcharge;
    }
    public void setSurcharge(float surcharge)
    {
        this.surcharge = surcharge;
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

}