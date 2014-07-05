package com.miniboss.call.vo;

/**
 * <p>Title: SMS</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author not attributable
 * @version 4.0
 */

import com.dvn.miniboss.system.AreaCode;
import com.miniboss.call.req.AllAreaReq;
import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AreaList", namespace = "http://vo.call.sms.dvnchina.com")
public class AreaList {
    private SMSError topError; /* ���ش�����Ϣ */
    private AllAreaReq allAreaRequset; /* ����������Ϣ */
    private String totalResults; /* ������� */
    private AreaCode[] areaCodes; /* ������Ϣ��ϸ��� */

    public SMSError getTopError() {
        return topError;
    }

    public void setTopError(SMSError topError) {
        this.topError = topError;
    }

    public AllAreaReq getAllAreaRequset() {
        return allAreaRequset;
    }

    public void setAllAreaRequset(AllAreaReq allAreaRequset) {
        this.allAreaRequset = allAreaRequset;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public AreaCode[] getAreaCodes() {
        return areaCodes;
    }

    public void setAreaCodes(AreaCode[] areaCodes) {
        this.areaCodes = areaCodes;
    }
}