package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS ���⽻���ʵ���Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpecialBillDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SpecialBillDetail
{
    private String billID;           /* �����ʵ��� */
    private String customerID;       /* �û���� */
    private String oldBillID;        /* ԭ�ʵ��� */
    private String billType;         /* �ʵ����� */
    private String productNM;        /* ��Ʒ���� */
    private String productStartdate; /* ����ʼ���� */
    private String productEndDate;   /* ����������� */
    private float  realPrice;        /* �����۸� */
    private String payStartDate;     /* ���ѿ�ʼ���� */
    private String payEndDate;       /* ���ѽ������� */
    private String payDate;          /* �������� */
    private String priority;         /* �ʵ����ȼ� */
    private String isPaid;           /* �Ƿ��ѽ��� */
    private String operatorID;       /* ����Ա�� */
    private String operateDate;      /* �������� */

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