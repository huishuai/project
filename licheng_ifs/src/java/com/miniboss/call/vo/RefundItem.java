package com.miniboss.call.vo;

/**
 * <p>Title: SMS �˿����ϸ��¼</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */
import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefundItem", namespace = "http://vo.call.sms.dvnchina.com")
public class RefundItem
{
    private String billID;           /* �˿�� */
    private String billType;         /* �������������� */
    private String productNM;        /* ��Ʒ���� */
    private String productStartDate; /* ����ʼ���� */
    private String productEndDate;   /* ����������� */
    private float amount;            /* �˿��� */
    private String transDate;        /* �������� */

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getProductNM() {
        return productNM;
    }

    public void setProductNM(String productNM) {
        this.productNM = productNM;
    }

    public String getProductStartDate() {
        return productStartDate;
    }

    public void setProductStartDate(String productStartDate) {
        this.productStartDate = productStartDate;
    }

    public String getProductEndDate() {
        return productEndDate;
    }

    public void setProductEndDate(String productEndDate) {
        this.productEndDate = productEndDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
}

