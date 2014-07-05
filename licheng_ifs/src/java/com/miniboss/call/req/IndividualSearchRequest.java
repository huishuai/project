package com.miniboss.call.req;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS 个人用户搜索请求</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */


/* 说明：
      1) SOAP 客户端编号由SMS 分配。
      2)必须设定 SOAP 客户端编号和返回记录条数，且查询条件中最少必须指定一项。
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualSearchRequest", namespace = "http://req.call.sms.dvnchina.com")
public class IndividualSearchRequest
{
    private String clientID;       /* SOAP 客户端编号 */
    private String maxRows;        /* 各类返回记录最大条数设定，0 表示无限 */
    private String sTBID;          /* 机顶盒号，可以为null */
    private String smartCardID;    /* 智能卡号，可以为null */
    private String customerID;     /* 用户编号，可以为null */
    private String customerNM;     /* 用户姓名，可以为null */
    private String iDCard;         /* 身份证号，可以为null */
    private String sTBAddress;     /* 装机地址，可以为null */
    private String contactAddress; /* 联系地址，可以为null */
    private String email;          /* EMAIL 地址，可以为null */
    private String homePhone;      /* 住宅电话号，可以为null */
    private String mobilePhone;    /* 手机号，可以为null */
    private String bankAccountID;  /* 银行帐号，可以为null */
    private String bankAccountNM;  /* 银行帐户名，可以为null */
    private String analogID;       /* 模拟用户编号，可以为null */
    private String areaID;         /* 用户所在地区编号，可以为null */

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(String maxRows) {
        this.maxRows = maxRows;
    }

    public String getsTBID() {
        return sTBID;
    }

    public void setsTBID(String sTBID) {
        this.sTBID = sTBID;
    }

    public String getSmartCardID() {
        return smartCardID;
    }

    public void setSmartCardID(String smartCardID) {
        this.smartCardID = smartCardID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerNM() {
        return customerNM;
    }

    public void setCustomerNM(String customerNM) {
        this.customerNM = customerNM;
    }

    public String getiDCard() {
        return iDCard;
    }

    public void setiDCard(String iDCard) {
        this.iDCard = iDCard;
    }

    public String getsTBAddress() {
        return sTBAddress;
    }

    public void setsTBAddress(String sTBAddress) {
        this.sTBAddress = sTBAddress;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getBankAccountID() {
        return bankAccountID;
    }

    public void setBankAccountID(String bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

    public String getBankAccountNM() {
        return bankAccountNM;
    }

    public void setBankAccountNM(String bankAccountNM) {
        this.bankAccountNM = bankAccountNM;
    }

    public String getAnalogID() {
        return analogID;
    }

    public void setAnalogID(String analogID) {
        this.analogID = analogID;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }
}