package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS VOD ������Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VodDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class VodDetail
{
    private String serviceID;      /* VOD ����� */
    private String customerID;     /* �û���� */
    private String area;           /* ���׵����� */
    private String agent;          /* ���������� */
    private String stbID;          /* �����к� */
    private String cardID;         /* ���ܿ��� */
    private String packageID;      /* VOD ������� */
    private String packageNM;      /* VOD ������� */
    private String calMode;        /* �Ʒѷ�ʽ���� */
    private String payMode;        /* ���ѷ�ʽ���� */
    private String startdate;      /* ��ʼ���� */
    private String endDate;        /* �������� */
    private String pauseDate;      /* ��ͣ���� */
    private String status;         /* ״̬���� */
    private String discType;       /* VOD ������ۿ����� */
    private String totalTimes;     /* �ۼƵ㲥���� */
    private String totalMoney;     /* �ۼ��ֽ�㲥��� */
    private String createDate;     /* �������� */
    private String createOperator; /* ��������Ա */
    private String modifOperator;  /* �޸Ĳ���Ա */
    private String modifDate;      /*�޸�����*/

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