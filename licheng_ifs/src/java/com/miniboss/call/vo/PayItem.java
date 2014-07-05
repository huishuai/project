package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS 交费交易明细记录信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PayItem", namespace = "http://vo.call.sms.dvnchina.com")
public class PayItem
{
    private String billID;           /* 服务帐单号 */
    private String billType;         /* 服务帐类型名称 */
    private String productNM;        /* 产品名称 */
    private String productStartDate; /* 服务开始日期 */
    private String productEndDate;   /* 服务结束日期 */
    private float realPrice;         /* 服务价格 */
    private float surcharge;         /* 滞纳金 */
    private String payStartDate;     /* 交费开始日期 */
    private String payEndDate;       /* 交费结束日期 */
    private float smsAmount;         /* 内部现金帐户扣款金额 */
    private float smsFreeAmount;     /* 内部免费帐户扣款金额 */
    private float smsCardamount;     /* 内部充值卡帐户扣款金额 */
    private String transDate;        /* 付款日期 */
    private String bankAccNM;        /* 银行帐户名 */
    private String bankAccID;        /* 银行帐号 */
    private String bankNM;           /* 开户银行名 */

    public PayItem()
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
    public float getSmsAmount()
    {
        return smsAmount;
    }
    public void setSmsAmount(float smsAmount)
    {
        this.smsAmount = smsAmount;
    }
    public float getSmsFreeAmount()
    {
        return smsFreeAmount;
    }
    public void setSmsFreeAmount(float smsFreeAmount)
    {
        this.smsFreeAmount = smsFreeAmount;
    }
    public float getSmsCardamount()
    {
        return smsCardamount;
    }
    public void setSmsCardamount(float smsCardamount)
    {
        this.smsCardamount = smsCardamount;
    }
    public float getSurcharge()
    {
        return surcharge;
    }
    public void setSurcharge(float surcharge)
    {
        this.surcharge = surcharge;
    }
    public String getTransDate()
    {
        return transDate;
    }
    public void setTransDate(String transDate)
    {
        this.transDate = transDate;
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
    public String getBankAccNM()
    {
        return bankAccNM;
    }
    public void seBankAccNM(String bankAccNM)
    {
        this.bankAccNM = bankAccNM;
    }
    public String getBankAccID()
    {
        return bankAccID;
    }
    public void setBankAccID(String bankAccID)
    {
        this.bankAccID = bankAccID;
    }
    public String getBankNM()
    {
        return bankNM;
    }
    public void setBankNM(String bankNM)
    {
        this.bankNM = bankNM;
    }

}