package com.bsmp.syncboss.webservice.jincheng.model;

/**
 * 产品资源绑定实体
 */
public class PorductPackageAssest {
    private String productId; //内容元数据Id         Boss系统中产品ID
    private String assetId; //内容编码            BSMP系统中资源PublishAssetID
    private String productName; //所属产品包id    资源所属产品ID
    private String productState; //元数据状态     3-绑定关系,5-删除关系

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }
}