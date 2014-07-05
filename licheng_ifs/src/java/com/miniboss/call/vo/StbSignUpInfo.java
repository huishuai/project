package com.miniboss.call.vo;

/**
 * <p>Title: SMS �����п�����Ϣ����</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
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
    private SMSError subError;                  /* ���ش�����Ϣ */
    private String totalResults;                /* ���ؼ�¼������ */
    private StbSignUpDetail[] stbSignUpDetails; /* �����п�����Ϣ */

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