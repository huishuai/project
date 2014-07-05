package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS PPV �ʵ���Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PpvBillDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class PpvBillDetail
{
    private String billID;         /* �УУַ����ʵ��� */
    private String serviceID;      /* �УУַ���� */
    private String customerID;     /* �û���� */
    private String stbID;          /* �����к� */
    private String cardID;         /* ���ܿ��� */
    private String programID;      /* ��Ŀ��� */
    private String programNM;      /* ��Ŀ���� */
    private String productNM;      /* ��Ʒ���� */
    private String epochStartTime; /* ��Ŀ����ʱ�� */
    private String orderTime;      /* ����ʱ�� */
    private float  refPrice;       /* ��׼�۸� */
    private String price;          /* �۸� */
    private String payStartDate;   /* ���ѿ�ʼ���� */
    private String payEndDate;     /* ���ѽ������� */
    private String payDate;        /* �������� */
    private String priority;       /* �ʵ����ȼ� */
    private String isPaid;         /* �Ƿ��ѽ��� */
    private String operatorID;     /* ����Ա */
    private String operateDate;    /* �������� */

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