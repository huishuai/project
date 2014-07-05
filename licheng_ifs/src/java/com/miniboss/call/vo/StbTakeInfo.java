package com.miniboss.call.vo;

/**
 * <p>Title: SMS �����й�����Ϣ����</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

import com.miniboss.call.tool.SMSError;
import com.miniboss.call.vo.StbTakeDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
//˵����SMSError ��������
//(1) SMSError.Code = ��000�� ��ʾ�޴�
//(2) SMSError.Code = ��300����ʾSMS ��ѯ��¼ʱ����

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StbTakeInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class StbTakeInfo
{
    private SMSError subError;              /* ���ش�����Ϣ */
    private String totalResults;            /* ���ؼ�¼������ */
    private StbTakeDetail[] stbTakeDetails; /* �����й�����Ϣ */

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

    public StbTakeDetail[] getStbTakeDetails() {
        return stbTakeDetails;
    }

    public void setStbTakeDetails(StbTakeDetail[] stbTakeDetails) {
        this.stbTakeDetails = stbTakeDetails;
    }
}