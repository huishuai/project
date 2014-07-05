package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS PPV 服务信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PpvDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class PpvDetail
{
    private String serviceID;      /* Ppv 服务号 */
    private String customerID;     /* 用户编号 */
    private String area;           /* 交易地区名 */
    private String agent;          /* 交易网点名 */
    private String stbID;          /* 机顶盒号 */
    private String cardID;         /* 智能卡号 */
    private String calMode;        /* 计费方式名称 */
    private String payMode;        /* 付费方式名称 */
    private String disctype;       /* Ppv 折扣类型名称 */
    private String isIppv;         /* 是否是IPPV */
    private String status;         /* 服务状态 */
    private String startDate;      /* 服务开始日期 */
    private String endDate;        /* 服务结束日期 */
    private String pauseDate;      /* 服务暂停日期 */
    private String totalTimes;     /* 累计点播次数 */
    private String totalMoney;     /* 累计现金点播金额 */
    private String createDate;     /* 创建日期 */
    private String createOperator; /* 创建操作员 */
    private String modifOperator;  /* 修改操作员 */
    private String modifDate;      /* 修改日期 */

    public PpvDetail()
    {
    }

    public String getServiceID()
    {
        return serviceID;
    }
    public void setServiceID(String serviceID)
    {
        this.serviceID = serviceID;
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
    public String getStbID()
    {
        return stbID;
    }
    public void setStbID(String stbID)
    {
        this.stbID = stbID;
    }
    public String getCardID()
    {
        return cardID;
    }
    public void setCardID(String cardID)
    {
        this.cardID = cardID;
    }
    public String getCalMode()
    {
        return calMode;
    }
    public void setCalMode(String calMode)
    {
        this.calMode = calMode;
    }
    public String getPayMode()
    {
        return payMode;
    }
    public void setPayMode(String payMode)
    {
        this.payMode = payMode;
    }
    public String getIsIppv()
    {
        return isIppv;
    }
    public void setIsIppv(String isIppv)
    {
        this.isIppv = isIppv;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    public String getPauseDate()
    {
        return pauseDate;
    }
    public void setPauseDate(String pauseDate)
    {
        this.pauseDate = pauseDate;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getDiscType()
    {
        return disctype;
    }
    public void setDiscType(String disctype)
    {
        this.disctype = disctype;
    }
    public String getTotalTimes()
    {
        return totalTimes;
    }
    public void setTotalTimes(String totalTimes)
    {
        this.totalTimes = totalTimes;
    }
    public String getTotalMoney()
    {
        return totalMoney;
    }
    public void setTotalMoney(String totalMoney)
    {
        this.totalMoney = totalMoney;
    }
    public String getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    public String getCreateOperator()
    {
        return createOperator;
    }
    public void setCreateOperator(String createOperator)
    {
        this.createOperator = createOperator;
    }
    public String getModifOperator()
    {
        return modifOperator;
    }
    public void setModifOperator(String modifOperator)
    {
        this.modifOperator = modifOperator;
    }
    public String getModifDate()
    {
        return modifDate;
    }
    public void setModifDate(String modifDate)
    {
        this.modifDate = modifDate;
    }

}