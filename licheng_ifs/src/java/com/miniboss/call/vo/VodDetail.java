package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS VOD 服务信息</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//说明：只返回有效的记录。

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VodDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class VodDetail
{
    private String serviceID;      /* VOD 服务号 */
    private String customerID;     /* 用户编号 */
    private String area;           /* 交易地区名 */
    private String agent;          /* 交易网点名 */
    private String stbID;          /* 机顶盒号 */
    private String cardID;         /* 智能卡号 */
    private String packageID;      /* VOD 服务包号 */
    private String packageNM;      /* VOD 服务包名 */
    private String calMode;        /* 计费方式名称 */
    private String payMode;        /* 付费方式名称 */
    private String startdate;      /* 开始日期 */
    private String endDate;        /* 结束日期 */
    private String pauseDate;      /* 暂停日期 */
    private String status;         /* 状态名称 */
    private String discType;       /* VOD 服务包折扣名称 */
    private String totalTimes;     /* 累计点播次数 */
    private String totalMoney;     /* 累计现金点播金额 */
    private String createDate;     /* 创建日期 */
    private String createOperator; /* 创建操作员 */
    private String modifOperator;  /* 修改操作员 */
    private String modifDate;      /*修改日期*/

    public VodDetail()
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
    public String getPackageID()
    {
        return packageID;
    }
    public void setPackageID(String packageID)
    {
        this.packageID = packageID;
    }
    public String getPackageNM()
    {
        return packageNM;
    }
    public void setPackageNM(String packageNM)
    {
        this.packageNM = packageNM;
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
    public String getStartDate()
    {
        return startdate;
    }
    public void setStartDate(String startdate)
    {
        this.startdate = startdate;
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
        return discType;
    }
    public void setDiscType(String discType)
    {
        this.discType = discType;
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