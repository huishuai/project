package com.miniboss.call.vo;

/**
 * <p>Title: SMS 机顶盒开户信息返回</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

import com.miniboss.call.tool.SMSError;
import com.miniboss.call.vo.StbSignUpDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StbSignUpInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class StbSignUpInfo
{
    private SMSError subError;                  /* 返回错误信息 */
    private String totalResults;                /* 返回记录总条数 */
    private StbSignUpDetail[] stbSignUpDetails; /* 机顶盒开户信息 */

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

    public StbSignUpDetail[] getStbSignUpDetails() {
        return stbSignUpDetails;
    }

    public void setStbSignUpDetails(StbSignUpDetail[] stbSignUpDetails) {
        this.stbSignUpDetails = stbSignUpDetails;
    }
}