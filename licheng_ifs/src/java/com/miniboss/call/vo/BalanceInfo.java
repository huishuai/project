package com.miniboss.call.vo;

import com.dvn.miniboss.acct.AcctBalance;
import com.miniboss.call.tool.SMSError;
import com.miniboss.call.vo.BalanceDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS �ڲ��ʻ������Ϣ�б�</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����SMSError ��������
//(1) SMSError.Code = ��000�� ��ʾ�޴���
//(2) SMSError.Code = ��300����ʾSMS ��ѯ��¼ʱ����

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BalanceInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class BalanceInfo
{
    private SMSError subError;               /* ���ش�����Ϣ */
    private String totalResults;             /* ���ؼ�¼������ */
    private AcctBalance[ ] balanceDetails; /* �ڲ��ʻ������Ϣ�б� */

    public BalanceInfo()
    {
    }

    public AcctBalance[] getBalanceDetails()
    {
        return balanceDetails;
    }
    public void setBalanceDetails(AcctBalance[] balanceDetails)
    {
        this.balanceDetails = balanceDetails;
    }
    public SMSError getSubError()
    {
        return subError;
    }
    public void setSubError(SMSError subError)
    {
        this.subError = subError;
    }
    public String getTotalResults()
    {
        return totalResults;
    }
    public void setTotalResults(String totalResults)
    {
        this.totalResults = totalResults;
    }
}