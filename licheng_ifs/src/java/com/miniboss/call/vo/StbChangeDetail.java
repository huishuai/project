package com.miniboss.call.vo;

/**
 * <p>Title: SMS 换机信息</p>
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
@XmlType(name = "StbChangeDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbChangeDetail
{
    private String serialNO;    /* 序号 */
    private String customerID;  /* 用户编号 */
    private String area;        /* 交易地区名 */
    private String agent;       /* 交易网点名 */
    private String changeType;  /* 换机类型名称 */
    private String newStbID;    /* 新机顶盒号 */
    private String newStbType;  /* 新机顶盒型号名称 */
    private String newCardID;   /* 新智能卡号 */
    private String newCardType; /* 新智能卡型号名称 */
    private String oldStbID;    /* 旧机顶盒号 */
    private String oldStbType;  /* 旧机顶盒型号 */
    private String oldCardID;   /* 旧智能卡号 */
    private String oldCardType; /* 旧智能卡型号 */
    private String operatorID;  /* 操作员号 */
    private String operateDate; /* 操作员日期 */

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