package com.miniboss.call.vo;

/**
 * <p>Title: SMS 机顶盒购买交易信息</p>
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
@XmlType(name = "StbOrderDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbOrderDetail  
{
    private String tradeID;               /* 机顶盒销售交易号 */
    private String customerID;            /* 用户编号 */
    private String area;                  /* 交易地区名 */
    private String agent;                 /* 交易网点名 */
    private String tradeDate;             /* 交易日期 */
    private String payTimes;              /* 分几次付款 */
    private String isPaid;                /* 是否已交费 */
    private String isTake;                /* 是否已提货 */
    private String takeDate;              /* 提货日期 */
    private String operatorID;            /* 操作员号 */
    private String operateDate;           /* 操作员日期 */
    private String totalResults;          /* 明细条数 */
    private StbOrderItem[] stbOrderItems; /* 机卡明细信息 */

    public String getTradeID() {
        return tradeID;
    }

    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
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

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getPayTimes() {
        return payTimes;
    }

    public void setPayTimes(String payTimes) {
        this.payTimes = payTimes;
    }

    public String getPaid() {
        return isPaid;
    }

    public void setPaid(String paid) {
        isPaid = paid;
    }

    public String getTake() {
        return isTake;
    }

    public void setTake(String take) {
        isTake = take;
    }

    public String getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(String takeDate) {
        this.takeDate = takeDate;
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

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public StbOrderItem[] getStbOrderItems() {
        return stbOrderItems;
    }

    public void setStbOrderItems(StbOrderItem[] stbOrderItems) {
        this.stbOrderItems = stbOrderItems;
    }
}