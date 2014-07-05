package com.miniboss.call.vo;

/**
 * <p>Title: SMS</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author not attributable
 * @version 4.0
 */

import com.dvn.miniboss.system.NetCode;
import com.miniboss.call.req.AllNetCodeReq;
import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetCodeList", namespace = "http://vo.call.sms.dvnchina.com")
public class NetCodeList {
    private SMSError topError; /* 返回错误信息 */
    private AllNetCodeReq allNetCodeReq; /* 返回请求信息 */
    private String totalResults; /* 结果总数 */
    private NetCode[] netCodes; /* 地区信息详细结果 */

    public SMSError getTopError() {
        return topError;
    }

    public void setTopError(SMSError topError) {
        this.topError = topError;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public AllNetCodeReq getAllNetCodeReq() {
        return allNetCodeReq;
    }

    public void setAllNetCodeReq(AllNetCodeReq allNetCodeReq) {
        this.allNetCodeReq = allNetCodeReq;
    }

    public NetCode[] getNetCodes() {
        return netCodes;
    }

    public void setNetCodes(NetCode[] netCodes) {
        this.netCodes = netCodes;
    }
}