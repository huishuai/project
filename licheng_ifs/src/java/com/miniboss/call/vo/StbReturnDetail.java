package com.miniboss.call.vo;

/**
 * <p>Title: SMS 机顶盒退货信息</p>
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
@XmlType(name = "StbReturnDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbReturnDetail
{
    private String tradeID;     /* 机顶盒销售交易号 */
    private String customerID;  /* 用户编号 */
    private String area;        /* 交易地区名 */
    private String agent;       /* 交易网点名 */
    private String tradeDate;   /* 交易日期 */
    private String stbID;       /* 机顶盒号 */
    private String stbType;     /* 机顶盒型号名称 */
    private String cardID;      /* 智能卡号 */
    private String cardType;    /* 智能卡型号名称 */
    private String refundType;  /* 退款方式名称 */
    private float stbRefund;    /*机顶盒退款金额 */
    private float cardRefund;   /* 智能卡退款金额 */
    private float totalRefund;  /* 机卡合计退款金额 */
    private String isRefund;    /* 是否已退款 */
    private String operatorID;  /* 操作员号 */
    private String operateDate; /* 操作员日期 */

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

    public String getStbID() {
        return stbID;
    }

    public void setStbID(String stbID) {
        this.stbID = stbID;
    }

    public String getStbType() {
        return stbType;
    }

    public void setStbType(String stbType) {
        this.stbType = stbType;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public float getStbRefund() {
        return stbRefund;
    }

    public void setStbRefund(float stbRefund) {
        this.stbRefund = stbRefund;
    }

    public float getCardRefund() {
        return cardRefund;
    }

    public void setCardRefund(float cardRefund) {
        this.cardRefund = cardRefund;
    }

    public float getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(float totalRefund) {
        this.totalRefund = totalRefund;
    }

    public String getRefund() {
        return isRefund;
    }

    public void setRefund(String refund) {
        isRefund = refund;
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