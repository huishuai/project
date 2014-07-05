package com.miniboss.call.vo;

import java.util.*;
import javax.servlet.http.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Title: SMS �ɶ������Ӳ�Ʒ��Ϣ</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author wesley
 * @version 4.0
 */

//˵����
//1) ֻ�ṩ�����Ʒ����Ϣ��
//2) ֻ�����û��������������Ĳ�Ʒ��
//3) ֻ�����û�����������صĲ�Ʒ���������ϼ������̳еĲ�Ʒ����

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscribeProductDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class SubscribeProductDetail
{
    private String productID;                         /* ��Ʒ��� */
    private String productNM;                         /* ��Ʒ���� */
    private String productType;                       /* ʹ���������� */
    private String serviceType;                       /* ������������ */
    private String status;                            /* ״̬ */
    private String createDate;                        /* �������� */
    private String channelTotalResults;               /* ����Ƶ����Ϣ������ */
    SubscribeChannelDetail[] subscribeChannelDetails; /* Ƶ����Ϣ */
    private String seriesTotalResults;                /* ����ϵ�м۸���Ϣ������ */
    SubscribeSeriesDetail[] subscribeSeriesDetails;   /* ϵ�м۸���Ϣ */

    public SubscribeProductDetail()
    {
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
    public String getProductType()
    {
        return productType;
    }
    public void setProductType(String productType)
    {
        this.productType = productType;
    }
    public String getServiceType()
    {
        return serviceType;
    }
    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
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
    public SubscribeChannelDetail[] getSubscribeChannelDetails()
    {
        return subscribeChannelDetails;
    }
    public void setSubscribeChannelDetails(SubscribeChannelDetail[] subscribeChannelDetails)
    {
        this.subscribeChannelDetails = subscribeChannelDetails;
    }
    public String getChannelTotalResults()
    {
        return channelTotalResults;
    }
    public void setChannelTotalResults(String channelTotalResults)
    {
        this.channelTotalResults = channelTotalResults;
    }
    public SubscribeSeriesDetail[] getSubscribeSeriesDetails()
    {
        return subscribeSeriesDetails;
    }
    public void setSubscribeSeriesDetails(SubscribeSeriesDetail[] subscribeSeriesDetails)
    {
        this.subscribeSeriesDetails = subscribeSeriesDetails;
    }
    public String getSeriesTotalResults()
    {
        return seriesTotalResults;
    }
    public void setSeriesTotalResults(String seriesTotalResults)
    {
        this.seriesTotalResults = seriesTotalResults;
    }
 
}