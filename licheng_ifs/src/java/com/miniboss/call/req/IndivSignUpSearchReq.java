package com.miniboss.call.req;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 *
 * @author not attributable
 * @version 4.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndivSignUpSearchReq", namespace = "http://req.call.sms.dvnchina.com")
public class IndivSignUpSearchReq {
    private String clientID; /* SOAP�ͻ��˱�š�*/
    private String maxRows; /* ���෵�ؼ�¼��������趨��0��ʾ���� */
    private String districtID; /* ������ */
    private String signUpAreaID; /* ����������� */
    private String signUpDateFrom; /* ��������From */
    private String signUpDateTo;  /* ��������To */

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

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getSignUpAreaID() {
        return signUpAreaID;
    }

    public void setSignUpAreaID(String signUpAreaID) {
        this.signUpAreaID = signUpAreaID;
    }

    public String getSignUpDateFrom() {
        return signUpDateFrom;
    }

    public void setSignUpDateFrom(String signUpDateFrom) {
        this.signUpDateFrom = signUpDateFrom;
    }

    public String getSignUpDateTo() {
        return signUpDateTo;
    }

    public void setSignUpDateTo(String signUpDateTo) {
        this.signUpDateTo = signUpDateTo;
    }
}