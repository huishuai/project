package com.miniboss.call.vo;

/**
 * <p>Title: SMS 机顶盒消信息返回</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

import com.miniboss.call.tool.SMSError;
import com.miniboss.call.vo.StbSignOffDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StbSignOffInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class StbSignOffInfo
{
    private SMSError subError;                    /* 返回错误信息 */
    private String totalResults;                  /* 返回记录总条数 */
    private StbSignOffDetail[] stbSignOffDetails; /* 机顶盒消信息 */

    public SMSError getSubError() {
        return subError;
    }

    public void setSubError(SMSError subError) {
        this.subError = subError;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public StbSignOffDetail[] getStbSignOffDetails() {
        return stbSignOffDetails;
    }

    public void setStbSignOffDetails(StbSignOffDetail[] stbSignOffDetails) {
        this.stbSignOffDetails = stbSignOffDetails;
    }
}