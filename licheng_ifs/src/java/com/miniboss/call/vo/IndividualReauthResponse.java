package com.miniboss.call.vo;

/**
 * <p>Title: SMS 系列价格信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */
 
import com.miniboss.call.req.IndividualReauthRequest;
import com.miniboss.call.tool.SMSError;
import com.miniboss.call.vo.IndividualReauthDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualReauthResponse", namespace = "http://vo.call.sms.dvnchina.com")
public class IndividualReauthResponse
{
    private SMSError topError;                      /* 返回错误信息 */
    private IndividualReauthRequest reauthRequset;  /* 返回请求信息 */
    private String totalResults;                    /* 重复授权详细结果总数 */
    private IndividualReauthDetail[] reauthDetails; /* 重复授权详细结果 */

    public SMSError getTopError() {
        return topError;
    }

    public void setTopError(SMSError topError) {
        this.topError = topError;
    }

    public IndividualReauthRequest getReauthRequset() {
        return reauthRequset;
    }

    public void setReauthRequset(IndividualReauthRequest reauthRequset) {
        this.reauthRequset = reauthRequset;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public IndividualReauthDetail[] getReauthDetails() {
        return reauthDetails;
    }

    public void setReauthDetails(IndividualReauthDetail[] reauthDetails) {
        this.reauthDetails = reauthDetails;
    }
}