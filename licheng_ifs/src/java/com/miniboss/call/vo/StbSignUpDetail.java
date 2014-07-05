package com.miniboss.call.vo;

/**
 * <p>Title: SMS �����п�����Ϣ</p>
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
@XmlType(name = "StbSignUpDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbSignUpDetail
{
    private String stbID;      /* �����к� */
    private String cardID;     /* ���ܿ��� */
    private String customerID; /* �û���� */
    private String area;       /* ������������ */
    private String agent;      /* ������������ */
    private String signUpDate; /* �������� */
    private float fee;        /* ������ */
    private String status;     /* ״̬���� */
    private String attribute;  /* �������������� */
    private String stbClass;   /* �����з������� */
    private String operatorID; /* ����Ա�� */

    public String getStbID() {
        return stbID;
    }

    public void setStbID(String stbID) {
        this.stbID = stbID;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
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

    public String getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getStbClass() {
        return stbClass;
    }

    public void setStbClass(String stbClass) {
        this.stbClass = stbClass;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }
}