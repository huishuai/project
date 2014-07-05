package com.miniboss.call.req;

/**
 * <p>Title: SMS �����û���ѯ����</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */
 

import com.miniboss.call.vo.IndividualOperationMask;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualLookUpRequest", namespace = "http://req.call.sms.dvnchina.com")
public class IndividualLookUpRequest
{
    private String clientID;                          /* SOAP �ͻ��˱�� */
    private String maxRows;                           /* ���෵�ؼ�¼��������趨��0 ��ʾ���� */
    private String customerID;                        /* �û���� */
    private IndividualOperationMask operationRequest; /* ��ѯ��Ϣѡ�� */

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(String maxRows) {
        this.maxRows = maxRows;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public IndividualOperationMask getOperationRequest() {
        return operationRequest;
    }

    public void setOperationRequest(IndividualOperationMask operationRequest) {
        this.operationRequest = operationRequest;
    }
}