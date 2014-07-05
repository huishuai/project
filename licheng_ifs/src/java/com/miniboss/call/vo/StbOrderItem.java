package com.miniboss.call.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS 机卡明细信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StbOrderItem", namespace = "http://vo.call.sms.dvnchina.com")
public class StbOrderItem
{
    private String stbtype;      /* 机顶盒型号名称 */
    private String stbNo;        /* 机顶盒数量 */
    private String stbUnitPrice; /* 机顶盒单个价格 */
    private String cardtype;     /* 智能卡型号名称 */
    private String cardNo;       /* 智能卡数量 */
    private float cardUnitPrice; /* 智能卡单个价格 */
    private float totalPrice;    /* 机卡总价格 */
    private float handelFee;     /* 手续费 */
    private float installFee;    /* 安装费 */
    private float otherFee;      /* 其它费用 */

    public String getStbtype() {
        return stbtype;
    }

    public void setStbtype(String stbtype) {
        this.stbtype = stbtype;
    }

    public String getStbNo() {
        return stbNo;
    }

    public void setStbNo(String stbNo) {
        this.stbNo = stbNo;
    }

    public String getStbUnitPrice() {
        return stbUnitPrice;
    }

    public void setStbUnitPrice(String stbUnitPrice) {
        this.stbUnitPrice = stbUnitPrice;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public float getCardUnitPrice() {
        return cardUnitPrice;
    }

    public void setCardUnitPrice(float cardUnitPrice) {
        this.cardUnitPrice = cardUnitPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getHandelFee() {
        return handelFee;
    }

    public void setHandelFee(float handelFee) {
        this.handelFee = handelFee;
    }

    public float getInstallFee() {
        return installFee;
    }

    public void setInstallFee(float installFee) {
        this.installFee = installFee;
    }

    public float getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(float otherFee) {
        this.otherFee = otherFee;
    }
}