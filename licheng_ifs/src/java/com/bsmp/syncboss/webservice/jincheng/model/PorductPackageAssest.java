package com.bsmp.syncboss.webservice.jincheng.model;

/**
 * ��Ʒ��Դ��ʵ��
 */
public class PorductPackageAssest {
    private String productId; //����Ԫ����Id         Bossϵͳ�в�ƷID
    private String assetId; //���ݱ���            BSMPϵͳ����ԴPublishAssetID
    private String productName; //������Ʒ��id    ��Դ������ƷID
    private String productState; //Ԫ����״̬     3-�󶨹�ϵ,5-ɾ����ϵ

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