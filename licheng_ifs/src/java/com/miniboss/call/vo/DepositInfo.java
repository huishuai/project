package com.miniboss.call.vo;

/**
 * <p>Title: SMS ������Ϣ����</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

import com.miniboss.call.tool.SMSError;
import com.miniboss.call.vo.AccOperDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepositInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class DepositInfo
{
    private SMSError subError;              /* ���ش�����Ϣ */
    private String totalResults;            /* ���ؼ�¼������ */
    private AccOperDetail[] depositDetails; /* �ڲ��ʻ�������Ϣ */

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

    public AccOperDetail[] getDepositDetails() {
        return depositDetails;
    }

    public void setDepositDetails(AccOperDetail[] depositDetails) {
        this.depositDetails = depositDetails;
    }
}