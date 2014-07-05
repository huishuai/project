package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS �����й����˵���Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StbBillDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbBillDetail
{
    private String billID;      /* �ʵ��� */
    private String tradeID;     /* ���������۽��׺� */
    private String customerID;  /* �û���� */
    private String area;        /* ���׵����� */
    private String agent;       /* ���������� */
    private String tradeDate;   /* �������� */
    private String sequence;    /* �ʵ���� */
    private String priority;    /* �ʵ����ȼ� */
    private float amount;       /* �۸� */
    private String isPaid;      /* �Ƿ��ѽ��� */
    private String payDate;     /* �������� */
    private String operatorID;  /* ����Ա�� */
    private String operateDate; /* ����Ա���� */

    public StbBillDetail()
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
    public String getTradeID()
    {
        return tradeID;
    }
    public void setTradeID(String tradeID)
    {
        this.tradeID = tradeID;
    }
    public String getCustomID()
    {
        return customerID;
    }
    public void setCustomID(String customerID)
    {
        this.customerID = customerID;
    }
    public String getArea()
    {
        return area;
    }
    public void setArea(String area)
    {
        this.area = area;
    }
    public String getAgent()
    {
        return agent;
    }
    public void setAgent(String agent)
    {
        this.agent = agent;
    }
    public String getTradeDate()
    {
        return tradeDate;
    }
    public void setTradeDate(String tradeDate)
    {
        this.tradeDate = tradeDate;
    }
    public String getSequence()
    {
        return sequence;
    }
    public void setSequence(String sequence)
    {
        this.sequence = sequence;
    }
    public String getPriority()
    {
        return priority;
    }
    public void setPriority(String priority)
    {
        this.priority = priority;
    }
    public float getAmount()
    {
        return amount;
    }
    public void setAmount(float amount)
    {
        this.amount = amount;
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