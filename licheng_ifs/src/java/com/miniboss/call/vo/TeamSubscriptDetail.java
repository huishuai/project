package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS �������������Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TeamSubscriptDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class TeamSubscriptDetail
{
    private String serviceID;     /* ���ӷ���� */
    private String group;         /* ������ */
    private String team;          /* ���� */
    private String area;          /* ���׵����� */
    private String agent;         /* ���������� */
    private String productID;     /* ��Ʒ��� */
    private String productNM;     /* ��Ʒ���� */
    private String payMode;       /* ���ѷ�ʽ���� */
    private String createDate;    /* �������� */
    private String realStartdate; /* ����ʼ���� */
    private String realEndDate;   /* ����������� */
    private String pauseDate;     /* ������ͣ���� */
    private String status;        /* ����״̬���� */
    private String freeStartdate; /*��ѿ�ʼ����*/
    private String freeEndDate;   /*��ѽ�������*/
    private float refPrice;       /*�����׼�۸�*/
    private float orderPrice;     /*���񶨹�ʱ�ļ۸�*/
    private float currentPrice;   /*����ǰ�۸�*/
    private String isRenew;       /*�Ƿ�������*/
    private String operatorID;    /* ����Ա�� */
    private String operateDate;   /* �������� */

    public TeamSubscriptDetail()
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
    public String getGroup()
    {
        return group;
    }
    public void setGroup(String group)
    {
        this.group = group;
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
    public String getTeam()
    {
        return team;
    }
    public void setTeam(String team)
    {
        this.team = team;
    }
    public String getProductID()
    {
        return productID;
    }
    public void setProductID(String productID)
    {
        this.productID = productID;
    }
    public String getProductNM()
    {
        return productNM;
    }
    public void setProductNM(String productNM)
    {
        this.productNM = productNM;
    }
    public String getPayMode()
    {
        return payMode;
    }
    public void setPayMode(String payMode)
    {
        this.payMode = payMode;
    }
    public String getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    public String getRealStartDate()
    {
        return realStartdate;
    }
    public void setRealStartDate(String realStartdate)
    {
        this.realStartdate = realStartdate;
    }
    public String getRealEndDate()
    {
        return realEndDate;
    }
    public void setRealEndDate(String realEndDate)
    {
        this.realEndDate = realEndDate;
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
    public String getFreeStartDate()
    {
        return freeStartdate;
    }
    public void setFreeStartDate(String freeStartdate)
    {
        this.freeStartdate = freeStartdate;
    }
    public String getFreeEndDate()
    {
        return freeEndDate;
    }
    public void setFreeEndDate(String freeEndDate)
    {
        this.freeEndDate = freeEndDate;
    }
    public float getRefPrice()
    {
        return refPrice;
    }
    public void setRefPrice(float refPrice)
    {
        this.refPrice = refPrice;
    }
    public float getOrderPrice()
    {
        return orderPrice;
    }
    public void setOrderPrice(float orderPrice)
    {
        this.orderPrice = orderPrice;
    }
    public float getCurrentPrice()
    {
        return currentPrice;
    }
    public void setCurrentPrice(float currentPrice)
    {
        this.currentPrice = currentPrice;
    }
    public String getIsRenew()
    {
        return isRenew;
    }
    public void setIsRenew(String isRenew)
    {
        this.isRenew = isRenew;
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