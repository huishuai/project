package com.miniboss.call.vo;

/**
 * <p>Title: SMS 内部帐户存取款交易信息</p>
 * <p>Description: 数字电视用户管理系统</p>
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
    private String transID;      /* 交易号 */
    private String customID;     /* 用户编号 */
    private String agencyNM;     /* 交易机构名称 */
    private float smsAmount;     /* 内部现金帐户充/取金额 */
    private float smsFreeAmount; /* 内部免费帐户充/取金额 */
    private float smsCardamount; /* 内部充值卡帐户充/取金额 */
    private float tradeAmount;   /* 交易金额 */
    private String transType;    /* 交易类型 */
    private String transDate;    /* 交易日期 */
    private String operatorID;   /* 操作员号 */
    private String operateDate;  /* 操作日期 */
    private String remark;       /* 备注 */

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