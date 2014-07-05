package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS δ���ѵ�����Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnPaidSheetDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class UnPaidSheetDetail
{
    private String transID; /* ���ѵ��ݺ� */
    private String customID; /* �û���� */
    private float billAmounts; /* ���ѵ��ݽ�� */
    private float handleFee; /* ������ */
    private float surcharge; /* ���ɽ� */
    private String billtype; /* �ʵ��������� */
    private String payStartDate; /* ���ѿ�ʼ���� */
    private String payEndDate; /* ���ѽ������� */
    private String operatorID; /* ����Ա�� */
    private String operateDate; /* �������� */
    private String totalResults; /* ������ϸ��¼������ */
    private UnpaidItem[ ] unpaidtems; /* δ���ѵ�����ϸ��¼ */

    public UnPaidSheetDetail()
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
    public float getBillAmounts()
    {
        return billAmounts;
    }
    public void setBillAmounts(float billAmounts)
    {
        this.billAmounts = billAmounts;
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
    public UnpaidItem[] getUnpaidItems()
    {
        return unpaidtems;
    }
    public void setUnpaidItems(UnpaidItem[] unpaidtems)
    {
        this.unpaidtems = unpaidtems;
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