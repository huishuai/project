package com.miniboss.call.vo;

/**
 * <p>Title: SMS ȡ�����Ϣ����</p>
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
@XmlType(name = "WithdrawInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class WithdrawInfo
{
    private SMSError subError;               /* ���ش�����Ϣ */
    private String totalResults;             /* ���ؼ�¼������ */
    private AccOperDetail[] withdrawDetails; /* �ڲ��ʻ�ȡ�����Ϣ */

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

    public AccOperDetail[] getWithdrawDetails() {
        return withdrawDetails;
    }

    public void setWithdrawDetails(AccOperDetail[] withdrawDetails) {
        this.withdrawDetails = withdrawDetails;
    }
}