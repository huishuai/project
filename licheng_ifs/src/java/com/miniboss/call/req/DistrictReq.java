package com.miniboss.call.req;

/**
 * <p>Title: SMS</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 *
 * @author not attributable
 * @version 4.0
 */

public class DistrictReq {
    private String ClientID; /* SOAP客户端编号 */
    private String AreaID; /* 地区编号 */

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