package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS 交费交易信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PayDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class PayDetail
{
    private String transID;      /* 交易号 */
    private String customID;     /* 用户编号 */
    private String agencyNM;     /* 交易机构名称 */
    private float smsAmount;     /* 内部现金帐户扣款金额 */
    private float smsFreeAmount; /* 内部免费帐户扣款金额 */
    private float smsCardamount; /* 内部充值卡帐户扣款金额 */
    private float tradeAmount;   /* 交易金额 */
    private float handleFee;     /* 手续费 */
    private float surcharge;     /* 滞纳金 */
    private String transType;    /* 交易类型名称 */
    private String billtype;     /* 帐单类型名称 */
    private String transDate;    /* 交易日期 */
    private String bankBillNo;   /* 银行帐单号 */
    private String posBank;      /* POS 刷卡银行*/
    private String invoiceNo;    /* 发票号 */
    private String bankAccNM;    /* 银行帐户名 */
    private String bankAccID;    /* 银行帐号 */
    private String bankNM;       /* 开户银行名 */
    private String operatorID;   /* 操作员号 */
    private String operateDate;  /* 操作日期 */
    private String totalResults; /* 返回明细记录总条数 */
    private PayItem[] payItems;  /* 交费交易明细记录 */

    public PayDetail()
    {
    }

    public String getTransID()
    {
        return transID;
    }
    public void setTransID(String transID)
    {
        this.transID = transID;
    }
    public String getCustomID()
    {
        return customID;
    }
    public void setCustomID(String customID)
    {
        this.customID = customID;
    }
    public String getBillType()
    {
        return billtype;
    }
    public void setBillType(String billtype)
    {
        this.billtype = billtype;
    }
    public String getAgencyNM()
    {
        return agencyNM;
    }
    public void setAgencyNM(String agencyNM)
    {
        this.agencyNM = agencyNM;
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
    public float getTradeAmount()
    {
        return tradeAmount;
    }
    public void setTradeAmount(float tradeAmount)
    {
        this.tradeAmount = tradeAmount;
    }
    public float getHandleFee()
    {
        return handleFee;
    }
    public void setHandleFee(float handleFee)
    {
        this.handleFee = handleFee;
    }
    public float getSurcharge()
    {
        return surcharge;
    }
    public void setSurcharge(float surcharge)
    {
        this.surcharge = surcharge;
    }
    public String getTransType()
    {
        return transType;
    }
    public void setTransType(String transType)
    {
        this.transType = transType;
    }
    public String getTransDate()
    {
        return transDate;
    }
    public void setTransDate(String transDate)
    {
        this.transDate = transDate;
    }
    public String getBankBillNo()
    {
        return bankBillNo;
    }
    public void setBankBillNo(String bankBillNo)
    {
        this.bankBillNo = bankBillNo;
    }
    public String getPosBank()
    {
        return posBank;
    }
    public void setPosBank(String posBank)
    {
        this.posBank = posBank;
    }
    public String getInvoiceNo()
    {
        return invoiceNo;
    }
    public void setInvoiceNo(String invoiceNo)
    {
        this.invoiceNo = invoiceNo;
    }
    public String getBankAccNM()
    {
        return bankAccNM;
    }
    public void setBankAccNM(String bankAccNM)
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
    public PayItem[] getPayItems()
    {
        return payItems;
    }
    public void setPayItems(PayItem[] payItems)
    {
        this.payItems = payItems;
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