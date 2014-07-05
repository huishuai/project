package com.bmsp.util;

/**
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-14
 * Time: 14:44:48
 */
public class ConstantUtil {
    /**
     * ���²�Ʒ���� ���� ����
     */
    public static final  int BOSS_ORDER_MONTH_KEEPLIVE_REQ = 0x81000001;
    /**
     * ���²�Ʒ���� ���� ��Ӧ
     */
    public static final int BOSS_ORDER_MONTH_KEEPLIVE_RES = 0x82000001;

    public static final int BOSS_MARK = 0x00E7C6B5;

    public static final int BOSS_ORDER_MARK = 0x00E60001;
    /**
     * ���ε㲥��Ʒ����
     */
    public static final int BOSS_ORDER_ONE_REQ = 0x00000002;
    /**
     * ���ε㲥��ȫ�������
     */
    public static final int BOSS_ORDER_ONE_RES = 0x80000002;
    /**
     * ������Ϣ
     */
    public static final int BOSS_KEEP_LIFE_REQ = 0x00000001;
    /**
     * ����������Ϣ
     */
    public static final int BOSS_KEEP_LIFE_RES = 0x80000001;
    /**
     * ��Ʒ����
     */
    public static final int BOSS_ORDER_REQ = 0x60000001;
    /**
     * ��Ʒ����Ӧ��
     */
    public static final int BOSS_ORDER_RES = 0x62000001;
    /**
     * ��Ʒ�˶�
     */
    public static final int BOSS_DISORDER_REQ = 0x60000001;
    /**
     * ��Ʒ�˶�Ӧ��
     */
    public static final int BOSS_DISORDER_RES = 0x62000001;
    /**
     * ������Ϣ����
     */
//    public static final String ORDER_MESSAGE_REGEX = "<session_id>(.*?)</session.*?<identity_type>(.*?)</identity_type.*?<dev_no>(.*?)</dev_no.*?<oper_type>(.*?)</oper_type.*?<prod_id>(.*?)</prod_id.*?<order_type>(.*?)</order_type>";
    public static final String ORDER_MESSAGE_REGEX = "<session_id>(.*?)</session_id.*?<identity_type>(.*?)</identity_type.*?<dev_no>(.*?)</dev_no.*?<oper_type>(.*?)</oper_type.*?<prod_id>(.*?)</prod_id.*?<order_type>(.*?)</order_type.*?</billCycleCount>(.*?)</billCycleCount>";
    /**
     * �㲥��Ϣ����
     */
    public static final String ORDER_EVERY_TIME_REGEX = "<session_id>(.*?)</session_id>.*?<type>(.*?)</type>.*?<asset_id>(.*?)</asset_id>*?<sp_id>(.*?)</sp_id>.*?<start_time>(.*?)</start_time>.*?<dev_no>(.*?)</dev_no>";
    /**
     * ����������Ϣ����
     */
    public static final String ORDER_RESPONSE_REGEX = "<session_id>(.*?)</session_id>.*?<err_code>(.*?)</err_code>.*?<trans_type>(.*?)</trans_type>.*?<dev_no>(.*?)</dev_no>.*?<prod_id>(.*?)</prod_id>.*?<start_date>(.*?)</start_date>.*?<end_date>(.*?)</end_date>";
    /**
     * ������Ϣ����
     */
    public static final String KEEP_LIVE_REGEX = "<session_id>(.*?)</session_id>";
    /**
     * �㲥������Ϣ����
     */
    public static final String BILLING_REGEX = "<session_id>(.*?)</session.*?<type>(.*?)</type.*?<result>(.*?)</result";
    /**
     * ʱ���ʽ
     */
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

 
}
