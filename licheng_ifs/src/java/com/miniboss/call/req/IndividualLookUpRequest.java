package com.miniboss.call.req;

/**
 * <p>Title: SMS 个人用户查询请求</p>
 * <p>Description: 数字电视用户管理系统</p>
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
    private String clientID;                          /* SOAP 客户端编号 */
    private String maxRows;                           /* 各类返回记录最大条数设定，0 表示无限 */
    private String customerID;                        /* 用户编号 */
    private IndividualOperationMask operationRequest; /* 查询信息选择 */

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