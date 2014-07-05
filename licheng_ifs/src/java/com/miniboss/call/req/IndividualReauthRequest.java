package com.miniboss.call.req;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS ϵ�м۸���Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualReauthRequest", namespace = "http://req.call.sms.dvnchina.com")
public class IndividualReauthRequest
{
    private String clientID;     /* SOAP �ͻ��˱�� */
    private String totalNumber;  /* �ظ���Ȩ�������� */
    private String[] serviceIDs; /* ���ӷ���� */
    

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String[] getServiceIDs() {
        return serviceIDs;
    }

    public void setServiceIDs(String[] serviceIDs) {
        this.serviceIDs = serviceIDs;
    }
}