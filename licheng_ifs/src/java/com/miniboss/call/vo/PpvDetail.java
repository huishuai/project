package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS PPV ������Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ������Ч�ļ�¼��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PpvDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class PpvDetail
{
    private String serviceID;      /* Ppv ����� */
    private String customerID;     /* �û���� */
    private String area;           /* ���׵����� */
    private String agent;          /* ���������� */
    private String stbID;          /* �����к� */
    private String cardID;         /* ���ܿ��� */
    private String calMode;        /* �Ʒѷ�ʽ���� */
    private String payMode;        /* ���ѷ�ʽ���� */
    private String disctype;       /* Ppv �ۿ��������� */
    private String isIppv;         /* �Ƿ���IPPV */
    private String status;         /* ����״̬ */
    private String startDate;      /* ����ʼ���� */
    private String endDate;        /* ����������� */
    private String pauseDate;      /* ������ͣ���� */
    private String totalTimes;     /* �ۼƵ㲥���� */
    private String totalMoney;     /* �ۼ��ֽ�㲥��� */
    private String createDate;     /* �������� */
    private String createOperator; /* ��������Ա */
    private String modifOperator;  /* �޸Ĳ���Ա */
    private String modifDate;      /* �޸����� */

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