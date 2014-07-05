package com.miniboss.call.vo;

/**
 * <p>Title: SMS �����й�������Ϣ����</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */


import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


//˵����SMSError ��������
//(1) SMSError.Code = ��000�� ��ʾ�޴�
//(2) SMSError.Code = ��300����ʾSMS ��ѯ��¼ʱ����

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StbOrderInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class StbOrderInfo
{
    private SMSError subError;                       /* ���ش�����Ϣ */
    private String totalResults;                     /* ���ؼ�¼������ */
    private StbOrderDetail[] stbOrderDetails;        /* �����й�������Ϣ�б� */

    public SMSError getSubError() {
        return subError;
    }

    public void setSubError(SMSError subError) {
        this.subError = subError;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public StbOrderDetail[] getStbOrderDetails() {
        return stbOrderDetails;
    }

    public void setStbOrderDetails(StbOrderDetail[] stbOrderDetails) {
        this.stbOrderDetails = stbOrderDetails;
    }
}
