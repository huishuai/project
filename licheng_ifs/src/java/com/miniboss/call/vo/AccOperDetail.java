package com.miniboss.call.vo;

/**
 * <p>Title: SMS �ڲ��ʻ���ȡ�����Ϣ</p>
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
@XmlType(name = "AccOperDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class AccOperDetail
{
    private String transID;      /* ���׺� */
    private String customID;     /* �û���� */
    private String agencyNM;     /* ���׻������� */
    private float smsAmount;     /* �ڲ��ֽ��ʻ���/ȡ��� */
    private float smsFreeAmount; /* �ڲ�����ʻ���/ȡ��� */
    private float smsCardamount; /* �ڲ���ֵ���ʻ���/ȡ��� */
    private float tradeAmount;   /* ���׽�� */
    private String transType;    /* �������� */
    private String transDate;    /* �������� */
    private String operatorID;   /* ����Ա�� */
    private String operateDate;  /* �������� */
    private String remark;       /* ��ע */

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }

    public String getCustomID() {
        return customID;
    }

    public void setCustomID(String customID) {
        this.customID = customID;
    }

    public String getAgencyNM() {
        return agencyNM;
    }

    public void setAgencyNM(String agencyNM) {
        this.agencyNM = agencyNM;
    }

    public float getSmsAmount() {
        return smsAmount;
    }

    public void setSmsAmount(float smsAmount) {
        this.smsAmount = smsAmount;
    }

    public float getSmsFreeAmount() {
        return smsFreeAmount;
    }

    public void setSmsFreeAmount(float smsFreeAmount) {
        this.smsFreeAmount = smsFreeAmount;
    }

    public float getSmsCardamount() {
        return smsCardamount;
    }

    public void setSmsCardamount(float smsCardamount) {
        this.smsCardamount = smsCardamount;
    }

    public float getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(float tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}