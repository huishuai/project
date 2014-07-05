package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS ���ѽ�����Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PayDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class PayDetail
{
    private String transID;      /* ���׺� */
    private String customID;     /* �û���� */
    private String agencyNM;     /* ���׻������� */
    private float smsAmount;     /* �ڲ��ֽ��ʻ��ۿ��� */
    private float smsFreeAmount; /* �ڲ�����ʻ��ۿ��� */
    private float smsCardamount; /* �ڲ���ֵ���ʻ��ۿ��� */
    private float tradeAmount;   /* ���׽�� */
    private float handleFee;     /* ������ */
    private float surcharge;     /* ���ɽ� */
    private String transType;    /* ������������ */
    private String billtype;     /* �ʵ��������� */
    private String transDate;    /* �������� */
    private String bankBillNo;   /* �����ʵ��� */
    private String posBank;      /* POS ˢ������*/
    private String invoiceNo;    /* ��Ʊ�� */
    private String bankAccNM;    /* �����ʻ��� */
    private String bankAccID;    /* �����ʺ� */
    private String bankNM;       /* ���������� */
    private String operatorID;   /* ����Ա�� */
    private String operateDate;  /* �������� */
    private String totalResults; /* ������ϸ��¼������ */
    private PayItem[] payItems;  /* ���ѽ�����ϸ��¼ */

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