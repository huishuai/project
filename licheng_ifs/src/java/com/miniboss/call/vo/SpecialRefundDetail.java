package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS 特殊退款信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpecialRefundDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SpecialRefundDetail
{
    private String billID;           /* 退款帐单号 */
    private String customerID;       /* 用户编号 */
    private String oldBillID;        /* 原帐单号 */
    private String billType;         /* 帐单类型 */
    private String productNM;        /* 产品名称 */
    private String productStartDate; /* 服务开始日期 */
    private String productEndDate;   /* 服务结束日期 */
    private String refundAmount;     /* 退款金额 */
    private String refundDate;       /* 退款日期 */
    private String isRefund;         /* 是否已退款 */
    private String operatorID;       /* 操作员号 */
    private String operateDate;      /* 操作日期 */

    public SpecialRefundDetail()
    {
    }

    public String getBillID()
    {
        return billID;
    }
    public void setBillID(String billID)
    {
        this.billID = billID;
    }
    public String getCustomID()
    {
        return customerID;
    }
    public void setCustomID(String customerID)
    {
        this.customerID = customerID;
    }
    public String getOldBillID()
    {
        return oldBillID;
    }
    public void setOldBillID(String oldBillID)
    {
        this.oldBillID = oldBillID;
    }
    public String getBillType()
    {
        return billType;
    }
    public void setBillType(String billType)
    {
        this.billType = billType;
    }
    public String getProductNM()
    {
        return productNM;
    }
    public void setProductNM(String productNM)
    {
        this.productNM = productNM;
    }
    public String getProductStartDate()
    {
        return productStartDate;
    }
    public void setProductStartDate(String productStartDate)
    {
        this.productStartDate = productStartDate;
    }
    public String getProductEndDate()
    {
        return productEndDate;
    }
    public void setProductEndDate(String productEndDate)
    {
        this.productEndDate = productEndDate;
    }
    public String getRefundAmount()
    {
        return refundAmount;
    }
    public void setRefundAmount(String refundAmount)
    {
        this.refundAmount = refundAmount;
    }
    public String getIsRefund()
    {
        return isRefund;
    }
    public void setIsRefund(String isRefund)
    {
        this.isRefund = isRefund;
    }
    public String getRefundDate()
    {
        return refundDate;
    }
    public void setRefundDate(String refundDate)
    {
        this.refundDate = refundDate;
    }
    public String getOperatorID()
    {
        return operatorID;
    }
    public void setOperatorID(String operatorID)
    {
        this.operatorID = operatorID;
    }
    public String getOperateDate()
    {
        return operateDate;
    }
    public void setOperateDate(String operateDate)
    {
        this.operateDate = operateDate;
    }
 
}