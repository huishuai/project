package com.miniboss.bean.ums

import com.dvn.miniboss.acct.CmngStbType

import com.miniboss.exception.BaseException
import com.dvn.miniboss.oldsms.AuthService

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-9
 * Time: 16:48:32
 * To change this template use File | Settings | File Templates.
 */

/**
 * ΪUMS�������ӷѲ�ѯ�ӿ�ʹ�ã�UmsProcessService.baseQuery��
 * �����������ݶ���
 */
class BaseServiceDataBean {


//    type=1,status=1,sumnopaid=2300,enddate=20100531;*;

    public static final String sign_comma = ","
    public static final String sign_equal = "="
    public static final String sign_colon = ":"
    public static final String sign_semicolon = ";"

    public static final String attr_type = "type"
    public static final String attr_status = "status"
    public static final String attr_sumnopaid = "sumnopaid"
    public static final String attr_enddate = "enddate"

    public static final String UMS_STATUS_RUNNING = "1"        //����
    public static final String UMS_STATUS_USER_PAUSE = "2"     //������ͣ-�û���ͣ
    public static final String UMS_STATUS_ARREAR_PAUSE = "3"   //Ƿ����ͣ
    public static final String UMS_STATUS_STOPPED = "4"        //��ֹ

    public static final String UMS_CLASSTYPE_MASTER = "1"        //����
    public static final String UMS_CLASSTYPE_SALVE = "2"         //�ӻ�

    public static final String UMS_DEFAUT_ENDDATE = "19000101" //����������ʱ�䲻���ڵ�Ĭ��ֵ

    //UMS�������ӷѲ�ѯ�ӿ��������ʺ����Ӧ�������Ϣ
    public static final String SUCCESS_RETURN_CODE = "0"
    public static final String SUCCESS_RETURN_MESSAGE = "Operate success!"

    //����UMS�������ӷѲ�ѯʱ��Ӧ�Ĳ�ͬ�Ļ�������Ʒ�Ʒ����ڳ���
    public static final long UMS_MONTH_BILLINGCYCLECOUNT = 1
    public static final long UMS_ONEYEAR_BILLINGCYCLECOUNT = 12
    public static final long UMS_THREEYEAR_BILLINGCYCLECOUNT = 36

    //����UMS�������ӷѲ�ѯʱ��Ӧ�Ĳ�ͬ�Ļ�������Ʒ�Ʒ����ڳ���
    public static final String UMS_MONTH_BILLINGCYCLECOUNT_KEY = "01"
    public static final String UMS_ONEYEAR_BILLINGCYCLECOUNT_KEY = "12"
    public static final String UMS_THREEYEAR_BILLINGCYCLECOUNT_KEY = "36"

    //�Ƿ����ʹ��һ��֧ͨ����UMS�������ӷѡ�
    public static final String ISORNOT_NOT = "0"    //������
    public static final String ISORNOT_YES = "1"    //����

    public static final String PAYING_CONTENT_COUNT = "count"
    public static final String PAYING_CONTENT_PAYING = "paying"

    //�ͱ������ƽ���Portal�������������������۽��(��)
    public static final long UMS_ALLOW_MIN_PRICE = 2300

    //��ĩ���Խ���Portal��������ѯ�Ͷ����Ľ�ֹʱ��
    public static final long ALLOW_BASE_QUERYORDER_HOUR = 18



    String type             //type��ʾ�˺���������ӻ��� 1��ʾ������ 2��ʾ�ӻ�
    String status           //Status��ʾ�˻��Ļ�������״̬��1-������2-������ͣ��3-Ƿ����ͣ��4-��ֹ
    String sumNoPaid        //sumnopaid��ʾδ�����ʵ��Ľ�Ŀǰû���õ�--������MSN��ȷ��
    String endDate          //������������ϵ��ֹʱ��

    long userId             //�û�ID
    long oneMonthPrice      //���»������۸�
    long oneYearPrice       //һ��������۸�
    long threeYearPrice     //����������۸�

    boolean canIaddUpPrice = false  //�Ƿ���н�������

    public void setType(String ibossClassType){
        if(ibossClassType.equals(CmngStbType.MASTER)){
            type = UMS_CLASSTYPE_MASTER
        }else if(ibossClassType.equals(CmngStbType.SLAVE)){
            type = UMS_CLASSTYPE_SALVE
        }else{
            throw new BaseException("Current ClassType Not Exist!","Current ClassType Not Exist!")
        }
    }

    public String getType(){
        return this.type
    }

    public String convertBeanToString(){
        StringBuffer retSB = new StringBuffer()
        retSB.append(attr_type).append(sign_equal).append(this.type).append(sign_comma)
        retSB.append(attr_status).append(sign_equal).append(this.status).append(sign_comma)
        retSB.append(attr_sumnopaid).append(sign_equal).append(this.sumNoPaid).append(sign_comma)
        retSB.append(attr_enddate).append(sign_equal).append(this.endDate)
        return retSB.toString()
    }

    //ƴװ<Paying>count=1,paying=2300;count=12,paying=27600;count=36,paying=80000;</Paying>������
    public static String getPayingStr(Map<String,Long> map){
        StringBuffer strBuffer = new StringBuffer()
        strBuffer.append(PAYING_CONTENT_COUNT).append(sign_equal).append(UMS_MONTH_BILLINGCYCLECOUNT).append(sign_comma)
        Long oneMonthPrice = map.get(UMS_MONTH_BILLINGCYCLECOUNT_KEY + "")
        if(oneMonthPrice == null)
            oneMonthPrice = 0
        strBuffer.append(PAYING_CONTENT_PAYING).append(sign_equal).append(oneMonthPrice).append(sign_semicolon)
        strBuffer.append(PAYING_CONTENT_COUNT).append(sign_equal).append(UMS_ONEYEAR_BILLINGCYCLECOUNT).append(sign_comma)
        Long oneYearPrice = map.get(UMS_ONEYEAR_BILLINGCYCLECOUNT_KEY + "")
        if(oneYearPrice == null)
            oneYearPrice = 0
        strBuffer.append(PAYING_CONTENT_PAYING).append(sign_equal).append(oneYearPrice).append(sign_semicolon)
        strBuffer.append(PAYING_CONTENT_COUNT).append(sign_equal).append(UMS_THREEYEAR_BILLINGCYCLECOUNT).append(sign_comma)
        Long threeYearPrice = map.get(UMS_THREEYEAR_BILLINGCYCLECOUNT_KEY + "")
        if(threeYearPrice == null)
            threeYearPrice = 0
        strBuffer.append(PAYING_CONTENT_PAYING).append(sign_equal).append(threeYearPrice).append(sign_semicolon)
        return strBuffer.toString()
    }

    // ������������е��û�ID��Ϣ
    // �����ĺ���λ�����ڣ�01��12��36����ǰ�����û�ID��
    // ����:13221601����132216�����û�ID����01��������)
    public static long getUserIdByPriceIndex(long priceIndex){
        String priceIndexStr = priceIndex+""
        int index = priceIndexStr.length() - 2
        String userID = priceIndexStr.substring(0, index)
        return Long.parseLong(userID)
    }

    // ������������е�������Ϣ���ṹͬgetUserIdByPriceIndex����
    public static String getBillingCycleCountByPriceIndex(long priceIndex){
        String priceIndexStr = priceIndex.toString()
        int index = priceIndexStr.length() - 2
        String billingCycleCount = priceIndexStr.substring(index, priceIndexStr.length())
        return billingCycleCount
    }


    public void makeBaseServiceDataBean(AuthService auth){
        long price = auth.getPriceResult().total
        //�в�������
        if(auth.billingCyclecount<=2)
            this.oneMonthPrice = price
        else if(auth.billingCyclecount<=13)
            this.oneYearPrice = price
        else(auth.billingCyclecount<=37)
            this.threeYearPrice = price

    }

}
