package com.miniboss.call.req;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 *
 * @author wj
 * @version 4.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllNetCodeReq", namespace = "http://req.call.sms.dvnchina.com")
public class AllNetCodeReq {
    private String clientID;                   /* SOAP�ͻ��˱�š�*/

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}