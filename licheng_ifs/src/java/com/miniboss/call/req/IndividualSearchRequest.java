package com.miniboss.call.req;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS �����û���������</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */


/* ˵����
      1) SOAP �ͻ��˱����SMS ���䡣
      2)�����趨 SOAP �ͻ��˱�źͷ��ؼ�¼�������Ҳ�ѯ���������ٱ���ָ��һ�
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualSearchRequest", namespace = "http://req.call.sms.dvnchina.com")
public class IndividualSearchRequest
{
    private String clientID;       /* SOAP �ͻ��˱�� */
    private String maxRows;        /* ���෵�ؼ�¼��������趨��0 ��ʾ���� */
    private String sTBID;          /* �����кţ�����Ϊnull */
    private String smartCardID;    /* ���ܿ��ţ�����Ϊnull */
    private String customerID;     /* �û���ţ�����Ϊnull */
    private String customerNM;     /* �û�����������Ϊnull */
    private String iDCard;         /* ���֤�ţ�����Ϊnull */
    private String sTBAddress;     /* װ����ַ������Ϊnull */
    private String contactAddress; /* ��ϵ��ַ������Ϊnull */
    private String email;          /* EMAIL ��ַ������Ϊnull */
    private String homePhone;      /* סլ�绰�ţ�����Ϊnull */
    private String mobilePhone;    /* �ֻ��ţ�����Ϊnull */
    private String bankAccountID;  /* �����ʺţ�����Ϊnull */
    private String bankAccountNM;  /* �����ʻ���������Ϊnull */
    private String analogID;       /* ģ���û���ţ�����Ϊnull */
    private String areaID;         /* �û����ڵ�����ţ�����Ϊnull */

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