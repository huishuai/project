package com.miniboss.call.req;

/**
 * <p>Title: SMS</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 *
 * @author not attributable
 * @version 4.0
 */

public class DistrictReq {
    private String ClientID; /* SOAP�ͻ��˱�� */
    private String AreaID; /* ������� */

    public String getClientID() {
        return ClientID;
    }

    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String AreaID) {
        this.AreaID = AreaID;
    }

    public void setClientID(String ClientID) {
        this.ClientID = ClientID;
    }

}