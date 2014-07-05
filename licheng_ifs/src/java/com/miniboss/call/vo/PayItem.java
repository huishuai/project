package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS ���ѽ�����ϸ��¼��Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PayItem", namespace = "http://vo.call.sms.dvnchina.com")
public class PayItem
{
    private String billID;           /* �����ʵ��� */
    private String billType;         /* �������������� */
    private String productNM;        /* ��Ʒ���� */
    private String productStartDate; /* ����ʼ���� */
    private String productEndDate;   /* ����������� */
    private float realPrice;         /* ����۸� */
    private float surcharge;         /* ���ɽ� */
    private String payStartDate;     /* ���ѿ�ʼ���� */
    private String payEndDate;       /* ���ѽ������� */
    private float smsAmount;         /* �ڲ��ֽ��ʻ��ۿ��� */
    private float smsFreeAmount;     /* �ڲ�����ʻ��ۿ��� */
    private float smsCardamount;     /* �ڲ���ֵ���ʻ��ۿ��� */
    private String transDate;        /* �������� */
    private String bankAccNM;        /* �����ʻ��� */
    private String bankAccID;        /* �����ʺ� */
    private String bankNM;           /* ���������� */

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