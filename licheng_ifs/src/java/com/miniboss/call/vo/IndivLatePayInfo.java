package com.miniboss.call.vo;

/**
 * <p>Title: SMS</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author not attributable
 * @version 4.0
 */
import com.miniboss.call.req.IndivLatePaySearchReq;
import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndivLatePayInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class IndivLatePayInfo
{

    private SMSError topError; /* ���ش�����Ϣ */
    private IndivLatePaySearchReq searchRequset; /* ����������Ϣ */
    private String totalResults; /* ����������� */
    private LatePayIndividual[] individuals; /* Ƿ�Ѹ����û����������Ϣ�б� */

    public SMSError getTopError() {
        return topError;
    }

    public void setTopError(SMSError topError) {
        this.topError = topError;
    }

    public IndivLatePaySearchReq getSearchRequset() {
        return searchRequset;
    }

    public void setSearchRequset(IndivLatePaySearchReq searchRequset) {
        this.searchRequset = searchRequset;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public LatePayIndividual[] getIndividuals() {
        return individuals;
    }

    public void setIndividuals(LatePayIndividual[] individuals) {
        this.individuals = individuals;
    }
}