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
@XmlType(name = "WorkOrderReq", namespace = "http://req.call.sms.dvnchina.com")
public class WorkOrderReq {
    private String clientID;                   /* SOAP�ͻ��˱�š�*/
    private String customId;                   /* �ͻ���š�*/

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }
}