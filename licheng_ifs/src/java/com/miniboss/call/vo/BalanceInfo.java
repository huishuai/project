package com.miniboss.call.vo;

import com.dvn.miniboss.acct.AcctBalance;
import com.miniboss.call.tool.SMSError;
import com.miniboss.call.vo.BalanceDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS 内部帐户余额信息列表</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：SMSError 内容如下
//(1) SMSError.Code = ‘000’ 表示无错。
//(2) SMSError.Code = ‘300’表示SMS 查询记录时出错

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BalanceInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class BalanceInfo
{
    private SMSError subError;               /* 返回错误信息 */
    private String totalResults;             /* 返回记录总条数 */
    private AcctBalance[ ] balanceDetails; /* 内部帐户余额信息列表 */

    public BalanceInfo()
    {
    }

    public AcctBalance[] getBalanceDetails()
    {
        return balanceDetails;
    }
    public void setBalanceDetails(AcctBalance[] balanceDetails)
    {
        this.balanceDetails = balanceDetails;
    }
    public SMSError getSubError()
    {
        return subError;
    }
    public void setSubError(SMSError subError)
    {
        this.subError = subError;
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