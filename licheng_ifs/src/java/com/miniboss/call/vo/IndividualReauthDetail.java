package com.miniboss.call.vo;

/**
 * <p>Title: SMS �ظ���Ȩ��ϸ���</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualReauthDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class IndividualReauthDetail
{
    private SMSError subError; /* ���ش�����Ϣ */
    private String serviceID;  /* ���ض�Ӧ�����ӷ���� */


    public SMSError getSubError() {
        return subError;
    }

    public void setSubError(SMSError subError) {
        this.subError = subError;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }
}