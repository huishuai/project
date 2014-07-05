package com.miniboss.call.vo;

/**
 * <p>Title: SMS ������Ϣ</p>
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
@XmlType(name = "StbChangeDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbChangeDetail
{
    private String serialNO;    /* ��� */
    private String customerID;  /* �û���� */
    private String area;        /* ���׵����� */
    private String agent;       /* ���������� */
    private String changeType;  /* ������������ */
    private String newStbID;    /* �»����к� */
    private String newStbType;  /* �»������ͺ����� */
    private String newCardID;   /* �����ܿ��� */
    private String newCardType; /* �����ܿ��ͺ����� */
    private String oldStbID;    /* �ɻ����к� */
    private String oldStbType;  /* �ɻ������ͺ� */
    private String oldCardID;   /* �����ܿ��� */
    private String oldCardType; /* �����ܿ��ͺ� */
    private String operatorID;  /* ����Ա�� */
    private String operateDate; /* ����Ա���� */

    public String getSerialNO() {
        return serialNO;
    }

    public void setSerialNO(String serialNO) {
        this.serialNO = serialNO;
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

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getNewStbID() {
        return newStbID;
    }

    public void setNewStbID(String newStbID) {
        this.newStbID = newStbID;
    }

    public String getNewStbType() {
        return newStbType;
    }

    public void setNewStbType(String newStbType) {
        this.newStbType = newStbType;
    }

    public String getNewCardID() {
        return newCardID;
    }

    public void setNewCardID(String newCardID) {
        this.newCardID = newCardID;
    }

    public String getNewCardType() {
        return newCardType;
    }

    public void setNewCardType(String newCardType) {
        this.newCardType = newCardType;
    }

    public String getOldStbID() {
        return oldStbID;
    }

    public void setOldStbID(String oldStbID) {
        this.oldStbID = oldStbID;
    }

    public String getOldStbType() {
        return oldStbType;
    }

    public void setOldStbType(String oldStbType) {
        this.oldStbType = oldStbType;
    }

    public String getOldCardID() {
        return oldCardID;
    }

    public void setOldCardID(String oldCardID) {
        this.oldCardID = oldCardID;
    }

    public String getOldCardType() {
        return oldCardType;
    }

    public void setOldCardType(String oldCardType) {
        this.oldCardType = oldCardType;
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