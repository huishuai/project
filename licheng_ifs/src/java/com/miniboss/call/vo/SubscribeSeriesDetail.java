package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS ϵ�м۸���Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ�ṩ����ϵ�е���Ϣ��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscribeSeriesDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SubscribeSeriesDetail
{
    private String seriesID;     /* ϵ�к� */
    private String seriesNM;     /* ϵ���� */
    private String durationType; /* �������� */
    private String duration;     /* ���޳��� */
    private String refPrice;     /* ������׼�۸� */
    private String refSubPrice;  /* �ӻ���׼�۸� */
    private String status;       /* ״̬���� */
    private String createDate;   /* �������� */
    private String modifyDate;   /* �޸����� */

    public SubscribeSeriesDetail()
    {
    }

    public String getSeriesID()
    {
        return seriesID;
    }
    public void setSeriesID(String seriesID)
    {
        this.seriesID = seriesID;
    }
    public String getSeriesNM()
    {
        return seriesNM;
    }
    public void setSeriesNM(String seriesNM)
    {
        this.seriesNM = seriesNM;
    }
    public String getDurationType()
    {
        return durationType;
    }
    public void setDurationType(String durationType)
    {
        this.durationType = durationType;
    }
    public String getDuration()
    {
        return duration;
    }
    public void setDuration(String duration)
    {
        this.duration = duration;
    }
    public String getRefPrice()
    {
        return refPrice;
    }
    public void setRefPrice(String refPrice)
    {
        this.refPrice = refPrice;
    }
    public String getRefSubPrice()
    {
        return refSubPrice;
    }
    public void setRefSubPrice(String refSubPrice)
    {
        this.refSubPrice = refSubPrice;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    public String getModifyDate()
    {
        return modifyDate;
    }
    public void setModifyDate(String modifyDate)
    {
        this.modifyDate = modifyDate;
    }
 
}