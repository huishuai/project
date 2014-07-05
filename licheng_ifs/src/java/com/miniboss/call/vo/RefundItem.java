package com.miniboss.call.vo;

/**
 * <p>Title: SMS 退款交易明细记录</p>
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
@XmlType(name = "RefundItem", namespace = "http://vo.call.sms.dvnchina.com")
public class RefundItem
{
    private String billID;           /* 退款号 */
    private String billType;         /* 服务帐类型名称 */
    private String productNM;        /* 产品名称 */
    private String productStartDate; /* 服务开始日期 */
    private String productEndDate;   /* 服务结束日期 */
    private float amount;            /* 退款金额 */
    private String transDate;        /* 交易日期 */

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getProductNM() {
        return productNM;
    }

    public void setProductNM(String productNM) {
        this.productNM = productNM;
    }

    public String getProductStartDate() {
        return productStartDate;
    }

    public void setProductStartDate(String productStartDate) {
        this.productStartDate = productStartDate;
    }

    public String getProductEndDate() {
        return productEndDate;
    }

    public void setProductEndDate(String productEndDate) {
        this.productEndDate = productEndDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
}

