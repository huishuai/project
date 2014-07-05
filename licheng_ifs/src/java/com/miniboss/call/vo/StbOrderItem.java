package com.miniboss.call.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS ������ϸ��Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StbOrderItem", namespace = "http://vo.call.sms.dvnchina.com")
public class StbOrderItem
{
    private String stbtype;      /* �������ͺ����� */
    private String stbNo;        /* ���������� */
    private String stbUnitPrice; /* �����е����۸� */
    private String cardtype;     /* ���ܿ��ͺ����� */
    private String cardNo;       /* ���ܿ����� */
    private float cardUnitPrice; /* ���ܿ������۸� */
    private float totalPrice;    /* �����ܼ۸� */
    private float handelFee;     /* ������ */
    private float installFee;    /* ��װ�� */
    private float otherFee;      /* �������� */

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