package com.miniboss.call.vo;

/**
 * <p>Title: SMS �������˻���Ϣ</p>
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
@XmlType(name = "StbReturnDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbReturnDetail
{
    private String tradeID;     /* ���������۽��׺� */
    private String customerID;  /* �û���� */
    private String area;        /* ���׵����� */
    private String agent;       /* ���������� */
    private String tradeDate;   /* �������� */
    private String stbID;       /* �����к� */
    private String stbType;     /* �������ͺ����� */
    private String cardID;      /* ���ܿ��� */
    private String cardType;    /* ���ܿ��ͺ����� */
    private String refundType;  /* �˿ʽ���� */
    private float stbRefund;    /*�������˿��� */
    private float cardRefund;   /* ���ܿ��˿��� */
    private float totalRefund;  /* �����ϼ��˿��� */
    private String isRefund;    /* �Ƿ����˿� */
    private String operatorID;  /* ����Ա�� */
    private String operateDate; /* ����Ա���� */

    public String getTradeID() {
        return tradeID;
    }

    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getStbID() {
        return stbID;
    }

    public void setStbID(String stbID) {
        this.stbID = stbID;
    }

    public String getStbType() {
        return stbType;
    }

    public void setStbType(String stbType) {
        this.stbType = stbType;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public float getStbRefund() {
        return stbRefund;
    }

    public void setStbRefund(float stbRefund) {
        this.stbRefund = stbRefund;
    }

    public float getCardRefund() {
        return cardRefund;
    }

    public void setCardRefund(float cardRefund) {
        this.cardRefund = cardRefund;
    }

    public float getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(float totalRefund) {
        this.totalRefund = totalRefund;
    }

    public String getRefund() {
        return isRefund;
    }

    public void setRefund(String refund) {
        isRefund = refund;
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