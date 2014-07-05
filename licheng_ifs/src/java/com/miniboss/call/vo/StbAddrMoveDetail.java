package com.miniboss.call.vo;

/**
 * <p>Title: SMS �������ƻ���Ϣ</p>
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
@XmlType(name = "StbAddrMoveDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbAddrMoveDetail
{
    private String billID;      /* �ʵ����*/
    private String customerID;  /* �û���� */
    private String newAddress;  /* ��װ����ַ */
    private String oldAddress;  /* ��װ����ַ */
    private float refPrice;     /* �ƻ��ѱ�׼�۸� */
    private float price;        /* �ƻ��۸� */
    private String priority;    /* �ʵ����ȼ� */
    private String createDate;  /* �������� */
    private String isPaid;      /* �Ƿ��ѽ��� */
    private String payDate;     /* �������� */
    private String operatorID;  /* ����Ա�� */
    private String operateDate; /* �������� */

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public void setOldAddress(String oldAddress) {
        this.oldAddress = oldAddress;
    }

    public float getRefPrice() {
        return refPrice;
    }

    public void setRefPrice(float refPrice) {
        this.refPrice = refPrice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPaid() {
        return isPaid;
    }

    public void setPaid(String paid) {
        isPaid = paid;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }
}