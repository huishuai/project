package com.bsmp.syncboss.webservice.jincheng.model;

/**
 * SP������Ϣʵ��
 */
public class Cooperater {
    /**
     * ��������ʶ
     */
    private String spId;
    /**
     * �����������
     */
    private String spName;
    /**
     * �������״̬
     */
    private String spStatus;
    /**
     * ��Ч����
     */
    private String effDate;
    /**
     * ʧЧ����
     */
    private String expDate;

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(String spStatus) {
        this.spStatus = spStatus;
    }

    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }


}