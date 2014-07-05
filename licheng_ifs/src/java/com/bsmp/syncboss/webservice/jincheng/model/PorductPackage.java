package com.bsmp.syncboss.webservice.jincheng.model;

import java.util.Date;

/**
 * 产品包实体
 */
public class PorductPackage {
    private String productId; //产品包标识
    private String productName; //产品包名称
    private String productState; //产品包状态
    private String productDescription; //产品包描述
    private Date deployDate; //产品包发布日期
    private Date cancleDate; //产品包失效日期
    private String productType; //产品类型
    private String billType;     //产品的账目类型
    private String totalClass; //积量种类
    private int totalNum; //积量数量
    private String prodBSMPFlag;//单向 双向标志

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