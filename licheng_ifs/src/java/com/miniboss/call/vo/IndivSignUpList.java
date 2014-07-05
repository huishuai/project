package com.miniboss.call.vo;

/**
 * <p>Title: SMS</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author not attributable
 * @version 4.0
 */

import com.dvn.miniboss.oldsms.CmngCustominfo;
import com.miniboss.call.req.IndivSignUpSearchReq;
import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndivSignUpList", namespace = "http://vo.call.sms.dvnchina.com")
public class IndivSignUpList {
    private SMSError topError; /* ���ش�����Ϣ */
    private IndivSignUpSearchReq searchRequset; /* ����������Ϣ */
    private String totalResults; /* ����������� */
    private CmngCustominfo[] individuals; /* �����û����������Ϣ�б� */

    public SMSError getTopError() {
        return topError;
    }

    public void setTopError(SMSError topError) {
        this.topError = topError;
    }

    public IndivSignUpSearchReq getSearchRequset() {
        return searchRequset;
    }

    public void setSearchRequset(IndivSignUpSearchReq searchRequset) {
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