package com.bsmp.syncboss.webservice.jincheng.model;

/**
 * webservice返回信息实体
 */
public class Affirm {
    /**
     * 错误代码
     */
    private String rtnCode;
    /**
     * 合作伙伴名称
     */
    private String rtnMsg;

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }
}