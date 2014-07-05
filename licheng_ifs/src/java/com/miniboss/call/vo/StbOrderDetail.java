package com.miniboss.call.vo;

/**
 * <p>Title: SMS �����й�������Ϣ</p>
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
@XmlType(name = "StbOrderDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbOrderDetail  
{
    private String tradeID;               /* ���������۽��׺� */
    private String customerID;            /* �û���� */
    private String area;                  /* ���׵����� */
    private String agent;                 /* ���������� */
    private String tradeDate;             /* �������� */
    private String payTimes;              /* �ּ��θ��� */
    private String isPaid;                /* �Ƿ��ѽ��� */
    private String isTake;                /* �Ƿ������ */
    private String takeDate;              /* ������� */
    private String operatorID;            /* ����Ա�� */
    private String operateDate;           /* ����Ա���� */
    private String totalResults;          /* ��ϸ���� */
    private StbOrderItem[] stbOrderItems; /* ������ϸ��Ϣ */

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

    public String getPayTimes() {
        return payTimes;
    }

    public void setPayTimes(String payTimes) {
        this.payTimes = payTimes;
    }

    public String getPaid() {
        return isPaid;
    }

    public void setPaid(String paid) {
        isPaid = paid;
    }

    public String getTake() {
        return isTake;
    }

    public void setTake(String take) {
        isTake = take;
    }

    public String getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(String takeDate) {
        this.takeDate = takeDate;
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

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public StbOrderItem[] getStbOrderItems() {
        return stbOrderItems;
    }

    public void setStbOrderItems(StbOrderItem[] stbOrderItems) {
        this.stbOrderItems = stbOrderItems;
    }
}