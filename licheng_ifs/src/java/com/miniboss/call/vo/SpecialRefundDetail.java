package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS �����˿���Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpecialRefundDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SpecialRefundDetail
{
    private String billID;           /* �˿��ʵ��� */
    private String customerID;       /* �û���� */
    private String oldBillID;        /* ԭ�ʵ��� */
    private String billType;         /* �ʵ����� */
    private String productNM;        /* ��Ʒ���� */
    private String productStartDate; /* ����ʼ���� */
    private String productEndDate;   /* ����������� */
    private String refundAmount;     /* �˿��� */
    private String refundDate;       /* �˿����� */
    private String isRefund;         /* �Ƿ����˿� */
    private String operatorID;       /* ����Ա�� */
    private String operateDate;      /* �������� */

    public SpecialRefundDetail()
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
    public String getRefundAmount()
    {
        return refundAmount;
    }
    public void setRefundAmount(String refundAmount)
    {
        this.refundAmount = refundAmount;
    }
    public String getIsRefund()
    {
        return isRefund;
    }
    public void setIsRefund(String isRefund)
    {
        this.isRefund = isRefund;
    }
    public String getRefundDate()
    {
        return refundDate;
    }
    public void setRefundDate(String refundDate)
    {
        this.refundDate = refundDate;
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