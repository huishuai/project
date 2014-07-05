package com.miniboss.call.vo;

/**
 * <p>Title: SMS �����û��������ؽ���б� </p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
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
    private SMSError topError;                     /* ���ش�����Ϣ */
    private IndividualSearchRequest searchRequset; /* ����������Ϣ */
    private String totalResults;                   /* ����������� */
    private CmngCustominfo[] individuals;              /* �����û����������Ϣ�б� */

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