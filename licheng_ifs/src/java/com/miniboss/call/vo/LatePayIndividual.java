package com.miniboss.call.vo;

/**
 * <p>Title: SMS</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wj
 * @version 4.0
 */
import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LatePayIndividual", namespace = "http://vo.call.sms.dvnchina.com")
public class LatePayIndividual
{
    private String customerID; /* �û���� */
    private String customerNM; /* �û����� */
    private String iDCard; /* ���֤�� */
    private String sex; /* �Ա� */
    private String birthday; /* ���� */
    private String sTBAddress; /* װ����ַ */
    private String districtNM; /* ���� */
    private String subDistrictNM; /* С�� */
    private String contactAddress; /* ��ϵ��ַ */
    private String zipCode; /* �ʱ� */
    private String email; /* EMAIL��ַ */
    private String homePhone; /* סլ�绰�� */
    private String fax; /* ����� */
    private String mobilePhone; /* �ֻ��� */
    private String bankNM; /* ������ */
    private String bankAccountID; /* �����ʺ� */
    private String bankAccountNM; /* �����ʻ��� */
    private String isBankAccount; /* �Ƿ������ʻ����� */
    private String analogID; /* ģ���û���� */
    private String singUpDate; /* �������� */
    private String singUpArea; /* �������� */
    private String singUpAgent; /* �������� */
    private String status; /* ״̬���� */
    private String customerType; /* �û��������� */
    private String discountType; /* �ۿ��������� */
    private String vodDiscountType; /* VOD�ۿ���������*/
    private String belongGroupID; /* �������ź� */
    private String belongGroupNM; /* ���������� */
    private String belongTeamNM; /* �������� */
    private String totalResults; /* ����������� */
    private SubscripBillDetail[] latePayBills;/* Ƿ���ʵ���Ϣ�б� */

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getsTBAddress() {
        return sTBAddress;
    }

    public void setsTBAddress(String sTBAddress) {
        this.sTBAddress = sTBAddress;
    }

    public String getDistrictNM() {
        return districtNM;
    }

    public void setDistrictNM(String districtNM) {
        this.districtNM = districtNM;
    }

    public String getSubDistrictNM() {
        return subDistrictNM;
    }

    public void setSubDistrictNM(String subDistrictNM) {
        this.subDistrictNM = subDistrictNM;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getBankNM() {
        return bankNM;
    }

    public void setBankNM(String bankNM) {
        this.bankNM = bankNM;
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

    public String getBankAccount() {
        return isBankAccount;
    }

    public void setBankAccount(String bankAccount) {
        isBankAccount = bankAccount;
    }

    public String getAnalogID() {
        return analogID;
    }

    public void setAnalogID(String analogID) {
        this.analogID = analogID;
    }

    public String getSingUpDate() {
        return singUpDate;
    }

    public void setSingUpDate(String singUpDate) {
        this.singUpDate = singUpDate;
    }

    public String getSingUpArea() {
        return singUpArea;
    }

    public void setSingUpArea(String singUpArea) {
        this.singUpArea = singUpArea;
    }

    public String getSingUpAgent() {
        return singUpAgent;
    }

    public void setSingUpAgent(String singUpAgent) {
        this.singUpAgent = singUpAgent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getVodDiscountType() {
        return vodDiscountType;
    }

    public void setVodDiscountType(String vodDiscountType) {
        this.vodDiscountType = vodDiscountType;
    }

    public String getBelongGroupID() {
        return belongGroupID;
    }

    public void setBelongGroupID(String belongGroupID) {
        this.belongGroupID = belongGroupID;
    }

    public String getBelongGroupNM() {
        return belongGroupNM;
    }

    public void setBelongGroupNM(String belongGroupNM) {
        this.belongGroupNM = belongGroupNM;
    }

    public String getBelongTeamNM() {
        return belongTeamNM;
    }

    public void setBelongTeamNM(String belongTeamNM) {
        this.belongTeamNM = belongTeamNM;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public SubscripBillDetail[] getLatePayBills() {
        return latePayBills;
    }

    public void setLatePayBills(SubscripBillDetail[] latePayBills) {
        this.latePayBills = latePayBills;
    }
}