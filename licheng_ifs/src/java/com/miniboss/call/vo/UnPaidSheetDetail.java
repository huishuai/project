package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS 未交费单据信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnPaidSheetDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class UnPaidSheetDetail
{
    private String transID; /* 交费单据号 */
    private String customID; /* 用户编号 */
    private float billAmounts; /* 交费单据金额 */
    private float handleFee; /* 手续费 */
    private float surcharge; /* 滞纳金 */
    private String billtype; /* 帐单类型名称 */
    private String payStartDate; /* 交费开始日期 */
    private String payEndDate; /* 交费结束日期 */
    private String operatorID; /* 操作员号 */
    private String operateDate; /* 操作日期 */
    private String totalResults; /* 返回明细记录总条数 */
    private UnpaidItem[ ] unpaidtems; /* 未交费单据明细记录 */

    public UnPaidSheetDetail()
    {
    }

    public String getTransID()
    {
        return transID;
    }
    public void setTransID(String transID)
    {
        this.transID = transID;
    }
    public String getCustomID()
    {
        return customID;
    }
    public void setCustomID(String customID)
    {
        this.customID = customID;
    }
    public String getBillType()
    {
        return billtype;
    }
    public void setBillType(String billtype)
    {
        this.billtype = billtype;
    }
    public float getBillAmounts()
    {
        return billAmounts;
    }
    public void setBillAmounts(float billAmounts)
    {
        this.billAmounts = billAmounts;
    }
    public float getHandleFee()
    {
        return handleFee;
    }
    public void setHandleFee(float handleFee)
    {
        this.handleFee = handleFee;
    }
    public float getSurcharge()
    {
        return surcharge;
    }
    public void setSurcharge(float surcharge)
    {
        this.surcharge = surcharge;
    }
    public String getPayStartDate()
    {
        return payStartDate;
    }
    public void setPayStartDate(String payStartDate)
    {
        this.payStartDate = payStartDate;
    }
    public String getPayEndDate()
    {
        return payEndDate;
    }
    public void setPayEndDate(String payEndDate)
    {
        this.payEndDate = payEndDate;
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
    public UnpaidItem[] getUnpaidItems()
    {
        return unpaidtems;
    }
    public void setUnpaidItems(UnpaidItem[] unpaidtems)
    {
        this.unpaidtems = unpaidtems;
    }
    public String getTotalResults()
    {
        return totalResults;
    }
    public void setTotalResults(String totalResults)
    {
        this.totalResults = totalResults;
    }

     
}