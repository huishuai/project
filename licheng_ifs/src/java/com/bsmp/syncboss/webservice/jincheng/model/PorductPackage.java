package com.bsmp.syncboss.webservice.jincheng.model;

import java.util.Date;

/**
 * ��Ʒ��ʵ��
 */
public class PorductPackage {
    private String productId; //��Ʒ����ʶ
    private String productName; //��Ʒ������
    private String productState; //��Ʒ��״̬
    private String productDescription; //��Ʒ������
    private Date deployDate; //��Ʒ����������
    private Date cancleDate; //��Ʒ��ʧЧ����
    private String productType; //��Ʒ����
    private String billType;     //��Ʒ����Ŀ����
    private String totalClass; //��������
    private int totalNum; //��������
    private String prodBSMPFlag;//���� ˫���־

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getProdBSMPFlag() {
        return prodBSMPFlag;
    }

    public void setProdBSMPFlag(String prodBSMPFlag) {
        this.prodBSMPFlag = prodBSMPFlag;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Date getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(Date deployDate) {
        this.deployDate = deployDate;
    }

    public Date getCancleDate() {
        return cancleDate;
    }

    public void setCancleDate(Date cancleDate) {
        this.cancleDate = cancleDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTotalClass() {
        return totalClass;
    }

    public void setTotalClass(String totalClass) {
        this.totalClass = totalClass;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}