package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS 机顶盒购买账单信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StbBillDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class StbBillDetail
{
    private String billID;      /* 帐单号 */
    private String tradeID;     /* 机顶盒销售交易号 */
    private String customerID;  /* 用户编号 */
    private String area;        /* 交易地区名 */
    private String agent;       /* 交易网点名 */
    private String tradeDate;   /* 交易日期 */
    private String sequence;    /* 帐单序号 */
    private String priority;    /* 帐单优先级 */
    private float amount;       /* 价格 */
    private String isPaid;      /* 是否已交费 */
    private String payDate;     /* 交费日期 */
    private String operatorID;  /* 操作员号 */
    private String operateDate; /* 操作员日期 */

    public StbBillDetail()
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
    public String getTradeID()
    {
        return tradeID;
    }
    public void setTradeID(String tradeID)
    {
        this.tradeID = tradeID;
    }
    public String getCustomID()
    {
        return customerID;
    }
    public void setCustomID(String customerID)
    {
        this.customerID = customerID;
    }
    public String getArea()
    {
        return area;
    }
    public void setArea(String area)
    {
        this.area = area;
    }
    public String getAgent()
    {
        return agent;
    }
    public void setAgent(String agent)
    {
        this.agent = agent;
    }
    public String getTradeDate()
    {
        return tradeDate;
    }
    public void setTradeDate(String tradeDate)
    {
        this.tradeDate = tradeDate;
    }
    public String getSequence()
    {
        return sequence;
    }
    public void setSequence(String sequence)
    {
        this.sequence = sequence;
    }
    public String getPriority()
    {
        return priority;
    }
    public void setPriority(String priority)
    {
        this.priority = priority;
    }
    public float getAmount()
    {
        return amount;
    }
    public void setAmount(float amount)
    {
        this.amount = amount;
    }
    public String getIsPaid()
    {
        return isPaid;
    }
    public void setIsPaid(String isPaid)
    {
        this.isPaid = isPaid;
    }
    public String getPayDate()
    {
        return payDate;
    }
    public void setPayDate(String payDate)
    {
        this.payDate = payDate;
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