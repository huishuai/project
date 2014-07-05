package com.miniboss.call.req;

import com.miniboss.call.vo.IndividualOperationMask;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author not attributable
 * @version 4.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndivSelfLookUpReq", namespace = "http://req.call.sms.dvnchina.com")
public class IndivSelfLookUpReq
{

    private String clientID; /* SOAP�ͻ��˱�š�*/
    private String maxRows; /* ���෵�ؼ�¼��������趨��0��ʾ���� */
    private String customerID; /* �û���� */
    private String password; /* �û����� */
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IndividualOperationMask getOperationRequest() {
        return operationRequest;
    }

    public void setOperationRequest(IndividualOperationMask operationRequest) {
        this.operationRequest = operationRequest;
    }
}