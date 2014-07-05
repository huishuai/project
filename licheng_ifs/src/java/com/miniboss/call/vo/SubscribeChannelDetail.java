package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS Ƶ����Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����ֻ�ṩ����ϵ�е���Ϣ��

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscribeChannelDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SubscribeChannelDetail
{
    private String channelID;       /* Ƶ���� */
    private String channelNM;       /* Ƶ���� */
    private String status;          /* ״̬���� */
    private String createDate;      /* �������� */
    private String isCctvProduct;   /* �Ƿ���CCTV Ƶ�� */
    private String cctvProductCode; /* CCTV Ƶ���� */

    public SubscribeChannelDetail()
    {
    }

    public String getChannelID()
    {
        return channelID;
    }
    public void setChannelID(String channelID)
    {
        this.channelID = channelID;
    }
    public String getChannelNM()
    {
        return channelNM;
    }
    public void setChannelNM(String channelNM)
    {
        this.channelNM = channelNM;
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
    public String getIsCctvProduct()
    {
        return isCctvProduct;
    }
    public void setIsCctvProduct(String isCctvProduct)
    {
        this.isCctvProduct = isCctvProduct;
    }
    public String getCctvProductCode()
    {
        return cctvProductCode;
    }
    public void setCctvProductCode(String cctvProductCode)
    {
        this.cctvProductCode = cctvProductCode;
    }

}