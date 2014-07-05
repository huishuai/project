package com.miniboss.call.vo;

/**
 * <p>Title: SMS 个人用户搜索返回结果列表 </p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

import com.dvn.miniboss.oldsms.CmngCustominfo;
import com.miniboss.call.req.IndividualSearchRequest;
import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualList", namespace = "http://vo.call.sms.dvnchina.com")
public class IndividualList
{
    private SMSError topError;                     /* 返回错误信息 */
    private IndividualSearchRequest searchRequset; /* 返回请求信息 */
    private String totalResults;                   /* 搜索结果条数 */
    private CmngCustominfo[] individuals;              /* 个人用户搜索结果信息列表 */

    public SMSError getTopError() {
        return topError;
    }

    public void setTopError(SMSError topError) {
        this.topError = topError;
    }

    public IndividualSearchRequest getSearchRequset() {
        return searchRequset;
    }

    public void setSearchRequset(IndividualSearchRequest searchRequset) {
        this.searchRequset = searchRequset;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public CmngCustominfo[] getIndividuals() {
        return individuals;
    }

    public void setIndividuals(CmngCustominfo[] individuals) {
        this.individuals = individuals;
    }
}