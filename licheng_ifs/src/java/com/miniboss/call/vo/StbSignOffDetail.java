package com.miniboss.call.vo;

/**
 * <p>Title: SMS  机顶盒消信息</p>
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
@XmlType(name = "StbSignOffDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbSignOffDetail
{
    private String stbID;       /* 机顶盒号 */
    private String cardID;      /* 智能卡号 */
    private String customerID;  /* 用户编号 */
    private String area;        /* 消户地区 */
    private String agent;       /* 消户网点 */
    private String signUpDate;  /* 开户日期 */
    private String signOffDate; /* 消户日期 */
    private String attribute;   /* 机顶盒属性名称 */
    private String stbClass;       /* 机顶盒分类名称 */
    private String operatorID;  /* 操作员号 */

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