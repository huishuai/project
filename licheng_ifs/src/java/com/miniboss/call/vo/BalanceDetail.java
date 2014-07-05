package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS �ڲ��ʻ������Ϣ�б�</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BalanceDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class BalanceDetail
{
    //����-Ѻ��-����
    private String accountNM;      /* �ʻ����� */
    private float smsAmount;       /* �ֽ��ʻ���� */
    private float smsFreeAmount;   /* ����ʻ���� */
    private float smsCardamount;   /* ��ֵ���ʻ���� */
    private float smsAmount_1;     /* �ֽ��ʻ���� */
    private float smsFreeAmount_1; /* ����ʻ���� */
    private float smsCardamount_1; /* ��ֵ���ʻ���� */
    private float smsAmount_2;     /* �ֽ��ʻ���� */
    private float smsFreeAmount_2; /* ����ʻ���� */
    private float smsCardamount_2; /* ��ֵ���ʻ���� */
    private float smsAmount_3;     /* �ֽ��ʻ���� */
    private float smsFreeAmount_3; /* ����ʻ���� */
    private float smsCardamount_3; /* ��ֵ���ʻ���� */

    public BalanceDetail()
    {
    }

    public String getAccountNM()
    {
        return accountNM;
    }
    public void setAccountNM(String accountNM)
    {
        this.accountNM = accountNM;
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
    public float getSmsAmount_1()
    {
        return smsAmount_1;
    }
    public void setSmsAmount_1(float smsAmount_1)
    {
        this.smsAmount_1 = smsAmount_1;
    }
    public float getSmsFreeAmount_1()
    {
        return smsFreeAmount_1;
    }
    public void setSmsFreeAmount_1(float smsFreeAmount_1)
    {
        this.smsFreeAmount_1 = smsFreeAmount_1;
    }
    public float getSmsCardamount_1()
    {
        return smsCardamount_1;
    }
    public void setSmsCardamount_1(float smsCardamount_1)
    {
        this.smsCardamount_1 = smsCardamount_1;
    }
    public float getSmsAmount_2()
    {
        return smsAmount_2;
    }
    public void setSmsAmount_2(float smsAmount_2)
    {
        this.smsAmount_2 = smsAmount_2;
    }
    public float getSmsFreeAmount_2()
    {
        return smsFreeAmount_2;
    }
    public void setSmsFreeAmount_2(float smsFreeAmount_2)
    {
        this.smsFreeAmount_2 = smsFreeAmount_2;
    }
    public float getSmsCardamount_2()
    {
        return smsCardamount_2;
    }
    public void setSmsCardamount_2(float smsCardamount_2)
    {
        this.smsCardamount_2 = smsCardamount_2;
    }
    public float getSmsAmount_3()
    {
        return smsAmount_3;
    }
    public void setSmsAmount_3(float smsAmount_3)
    {
        this.smsAmount_3 = smsAmount_3;
    }
    public float getSmsFreeAmount_3()
    {
        return smsFreeAmount_3;
    }
    public void setSmsFreeAmount_3(float smsFreeAmount_3)
    {
        this.smsFreeAmount_3 = smsFreeAmount_3;
    }
    public float getSmsCardamount_3()
    {
        return smsCardamount_3;
    }
    public void setSmsCardamount_3(float smsCardamount_3)
    {
        this.smsCardamount_3 = smsCardamount_3;
    }

}