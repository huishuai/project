package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS ���ӷ����ʵ���Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscripBillDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SubscripBillDetail
{
    private String billID;       /* �����ʵ��� */
    private String serviceID;    /* ���ӷ���� */
    private String customerID;   /* �û���� */
    private String stbID;        /* �����к� */
    private String cardID;       /* ���ܿ��� */
    private String productID;    /* ��Ʒ��� */
    private String productNM;    /* ��Ʒ���� */
    private String startdate;    /* ����ʼ���� */
    private String endDate;      /* ����������� */
    private float  price;        /* ����۸� */
    private String isPaid;       /* �Ƿ��ѽ��� */
    private String payDate;      /* �������� */
    private String payStartDate; /* ���ѿ�ʼ���� */
    private String payEndDate;   /* ���ѽ������� */
    private String priority;     /* �ʵ����ȼ� */
    private String createDate;   /* �������� */
    private String operatorID;   /* ����Ա�� */
    private String operateDate;  /* �������� */

    public SubscripBillDetail()
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
    public String getStartDate()
    {
        return startdate;
    }
    public void setStartDate(String startdate)
    {
        this.startdate = startdate;
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    public float getPrice()
    {
        return price;
    }
    public void setPrice(float price)
    {
        this.price = price;
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
    public String getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
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