package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-27
 * Time: 15:43:41
 * To change this template use File | Settings | File Templates.
 */

/**
 * Ӫҵ��ҵ������[������ϵ]
 */
public class NetPrintAuthItemBean implements Serializable {
     //�����к�
    String stdId
    //UUid
    String uuId
    //��������
    String authServeName
    //�۸�
    long price

    //�Ʒ�����
    String feePeriodTypeName

    //-----------���񶩹�---------------
    //��ʼ����
    String realstartdate
    //��������
    String realenddate
    //����������(��)
    long billingCyclecount;

    //-----------�����˶�---------------
    //��������
    String revertStartDate //closedate
    //��ֹ����
    String revertEndDate   //realenddate

    //-----------������ͣ---------------
    //��ͣ��ʼ����
    String pauseStartDate
    //��ͣ��������
    String pauseEndDate

    //-----------����ָ�---------------
    //����ָ�����
    String resumeStartDate
    //����������
    String resumeEndDate

    //�ϼ�
    long total
    
}
