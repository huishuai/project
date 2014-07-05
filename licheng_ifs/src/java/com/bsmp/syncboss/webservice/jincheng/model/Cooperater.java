package com.bsmp.syncboss.webservice.jincheng.model;

/**
 * SP请求信息实体
 */
public class Cooperater {
    /**
     * 合作伙伴标识
     */
    private String spId;
    /**
     * 合作伙伴名称
     */
    private String spName;
    /**
     * 合作伙伴状态
     */
    private String spStatus;
    /**
     * 生效日期
     */
    private String effDate;
    /**
     * 失效日期
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