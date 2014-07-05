package com.miniboss.call.vo;

/**
 * <p>Title: SMS  ����������Ϣ</p>
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
@XmlType(name = "StbSignOffDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbSignOffDetail
{
    private String stbID;       /* �����к� */
    private String cardID;      /* ���ܿ��� */
    private String customerID;  /* �û���� */
    private String area;        /* �������� */
    private String agent;       /* �������� */
    private String signUpDate;  /* �������� */
    private String signOffDate; /* �������� */
    private String attribute;   /* �������������� */
    private String stbClass;       /* �����з������� */
    private String operatorID;  /* ����Ա�� */

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

    public String getSignOffDate() {
        return signOffDate;
    }

    public void setSignOffDate(String signOffDate) {
        this.signOffDate = signOffDate;
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