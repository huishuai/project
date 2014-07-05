package com.miniboss.call.vo;


import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS ���ӷ�����Ϣ����</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����SMSError ��������
//(1) SMSError.Code = ��000�� ��ʾ�޴�
//(2) SMSError.Code = ��300����ʾSMS ��ѯ��¼ʱ����

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriptionInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class SubscriptionInfo
{
    private SMSError subError;                         /* ���ش�����Ϣ */
    private String totalResults;                       /* ���ؼ�¼������ */
    private SubscriptionDetail[ ] subscriptionDetails; /* ���ӷ�����Ϣ */

    public SubscriptionInfo()
    {
    }

    public SubscriptionDetail[] getSubscriptionDetails()
    {
        return subscriptionDetails;
    }
    public void setSubscriptionDetails(SubscriptionDetail[] subscriptionDetails)
    {
        this.subscriptionDetails = subscriptionDetails;
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