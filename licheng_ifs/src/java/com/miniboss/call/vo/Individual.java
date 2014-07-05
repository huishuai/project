package com.miniboss.call.vo;

/**
 * <p>Title: SMS 个人用户信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

import java.util.*;
import javax.servlet.http.*;


public class Individual { 
    private String CustomerID;      /* 用户编号 */
    private String CustomerNM;      /* 用户姓名 */
    private String IDCard;          /* 身份证号 */
    private String Sex;             /* 性别 */
    private String Birthday;        /* 生日 */
    private String STBAddress;      /* 装机地址 */
    private String DistrictNM;      /* 区域 */
    private String SubDistrictNM;   /* 小区 */
    private String ContactAddress;  /* 联系地址 */
    private String ZipCode;         /* 邮编 */
    private String Email;           /* EMAIL 地址 */
    private String HomePhone;       /* 住宅电话号 */
    private String Fax;             /* 传真号 */
    private String MobilePhone;     /* 手机号 */
    private String BankNM;          /* 银行名 */
    private String BankAccountID;   /* 银行帐号 */
    private String BankAccountNM;   /* 银行帐户名 */
    private String IsBankAccount;   /* 是否银行帐户扣帐 */
    private String AnalogID;        /* 模拟用户编号 */
    private String SingUpDate;      /* 开户日期 */
    private String SingUpArea;      /* 开户地区 */
    private String SingUpAgent;     /* 开户网点 */
    private String Status;          /* 状态名称 */
    private String CustomerType;    /* 用户类型名称 */
    private String DiscountType;    /* 折扣类型名称 */
    private String VodDiscountType; /* VOD 折扣类型名称*/
    private String BelongGroupID;   /* 所属集团号 */
    private String BelongGroupNM;   /* 所属集团名 */
    private String BelongTeamNM;    /* 所属组名 */

    public String getAnalogID() {
        return AnalogID;
    }

    public void setAnalogID(String AnalogID) {
        this.AnalogID = AnalogID;
    }

    public String getBankAccountID() {
        return BankAccountID;
    }

    public void setBankAccountID(String BankAccountID) {
        this.BankAccountID = BankAccountID;
    }

    public String getBankAccountNM() {
        return BankAccountNM;
    }

    public void setBankAccountNM(String BankAccountNM) {
        this.BankAccountNM = BankAccountNM;
    }

    public String getBankNM() {
        return BankNM;
    }

    public void setBankNM(String BankNM) {
        this.BankNM = BankNM;
    }

    public String getBelongGroupID() {
        return BelongGroupID;
    }

    public void setBelongGroupID(String BelongGroupID) {
        this.BelongGroupID = BelongGroupID;
    }

    public String getBelongGroupNM() {
        return BelongGroupNM;
    }

    public void setBelongGroupNM(String BelongGroupNM) {
        this.BelongGroupNM = BelongGroupNM;
    }

    public String getBelongTeamNM() {
        return BelongTeamNM;
    }

    public void setBelongTeamNM(String BelongTeamNM) {
        this.BelongTeamNM = BelongTeamNM;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }

    public String getContactAddress() {
        return ContactAddress;
    }

    public void setContactAddress(String ContactAddress) {
        this.ContactAddress = ContactAddress;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getCustomerNM() {
        return CustomerNM;
    }

    public void setCustomerNM(String CustomerNM) {
        this.CustomerNM = CustomerNM;
    }

    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String CustomerType) {
        this.CustomerType = CustomerType;
    }

    public String getDiscountType() {
        return DiscountType;
    }

    public void setDiscountType(String DiscountType) {
        this.DiscountType = DiscountType;
    }

    public String getDistrictNM() {
        return DistrictNM;
    }

    public void setDistrictNM(String DistrictNM) {
        this.DistrictNM = DistrictNM;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public String getHomePhone() {
        return HomePhone;
    }

    public void setHomePhone(String HomePhone) {
        this.HomePhone = HomePhone;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getIsBankAccount() {
        return IsBankAccount;
    }

    public void setIsBankAccount(String IsBankAccount) {
        this.IsBankAccount = IsBankAccount;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getSingUpAgent() {
        return SingUpAgent;
    }

    public void setSingUpAgent(String SingUpAgent) {
        this.SingUpAgent = SingUpAgent;
    }

    public String getSingUpArea() {
        return SingUpArea;
    }

    public void setSingUpArea(String SingUpArea) {
        this.SingUpArea = SingUpArea;
    }

    public String getSingUpDate() {
        return SingUpDate;
    }

    public void setSingUpDate(String SingUpDate) {
        this.SingUpDate = SingUpDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getSTBAddress() {
        return STBAddress;
    }

    public void setSTBAddress(String STBAddress) {
        this.STBAddress = STBAddress;
    }

    public String getSubDistrictNM() {
        return SubDistrictNM;
    }

    public void setSubDistrictNM(String SubDistrictNM) {
        this.SubDistrictNM = SubDistrictNM;
    }

    public String getVodDiscountType() {
        return VodDiscountType;
    }

    public void setVodDiscountType(String VodDiscountType) {
        this.VodDiscountType = VodDiscountType;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

}