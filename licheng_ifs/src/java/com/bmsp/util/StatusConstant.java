package com.bmsp.util;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-20
 * Time: 10:13:15
 */
public class StatusConstant {
//    000000	����ɹ�
//    300001	�Բ��𣬲�ѯԤ��������Ϣʧ�ܣ����Ժ��ѯ
//    300002	�Բ��𣬴˿ͻ���Ԥ��������Ϣδ�������뵽Ӫҵǰ��������Ӧҵ��İ���
//    300003	�Բ��𣬴˲�Ʒ����Ϣδ�����������ܶ���
//    300004	�Բ�������Ԥ������㣬���ܶ����˲�Ʒ
//    300005	�Բ��𣬶���ʧ�ܣ����Ժ�����
//    300006	�Բ�������Ȩ�����˲�Ʒ����������ѡ��������Ʒ��
//    300007	�Բ������Ѿ������˴˲�Ʒ���������ظ�����
//    300008	�Բ��𣬴˲�Ʒ��ֻ����Ӫҵǰ��������ҵ���뵽Ӫҵǰ������
//    300009	�Բ�������û�ж�����Ʒ��Ϣ
//    300010	�Բ��𣬲�ѯ������Ϣʧ�ܣ����Ժ��ѯ
//    300099	�Բ��������쳣�����Ժ�����
//    999999	���ݸ�ʽ����
//

    public static final String BILLING_SUCCESS = "000000";  //��Ȩ��� ����

    public static final String BILLING_USER_HAVE_ORDER = "300007";  //�Բ������Ѿ������˴˲�Ʒ���������ظ�����
    public static final String PRODUCT_NOT_EXIST = "300003";  //�Բ��𣬴˲�Ʒ����Ϣδ�����������ܶ���
    public static final String ORDER_PROCESS_ERROR = "300005";         //�Բ��𣬶���ʧ�ܣ����Ժ�����
    public static final String DISORDER_PROCESS_ERROR = "300015";         //�Բ����˶�ʧ�ܣ����Ժ�����
    public static final String CONNECT_ERROE = "300099";  //�Բ������Ѿ������˴˲�Ʒ���������ظ�����
    public static final String NOT_ORDER = "300009";  //�Բ�������û�ж�����Ʒ��Ϣ
    public static final String USER_INFO_ERROR = "300011";  //�û����������Ϣ�������ڻ�����
    public static final String PublishAsset_INFO_ERROR = "300012";  //ӰƬ��Դ���������Ϣ�������ڻ�����
    public static final String FORMAT_ERROR="999999";
    public static final String BILLING_NOT_ENOUGH_MONEY = "300004";  //��Ȩ��� ����

    public static final String BILLING_NOT_ENOUGH_MESSAGE = "300008";  //��Ϣ��ȫ����Ҫ��Ӫҵ��������ҵ��


    public static final String BILLING_NOT_SUCCESS="0001";     //����δ֪����

    public static final String BILLING_NOT_MONEY_CANNOT_ORDER = "3003";  //Ƿ��״̬�£���������

    public static final String BILLING_FAILE_TRYAGAIN_CANNOT_ORDER = "3004";  //Ƿ��״̬�£���������

    public static final String BILLING_USERINFO_ERROR_CANNOT_ORDER = "3005";  //�û���Ϣ����,��������

    public static final String BILLING_PRODUCTINFO_ERROR_CANNOT_ORDER = "3006";  //��Ʒ��Ϣ���ԣ�������Ķ���

    public static final String BILLING_SYSTEM_BUSY_WAIT_MOMENT = "3007";  //ϵͳæ���Ժ��ٶ���

    public static final String BILLING_USERINFO_ERROR_CANNOT_DISORDER = "3008";  //�û���Ϣ����,�������˶�

    public static final String BILLING_PRODUCTINFO_ERROR_CANNOT_DISORDER = "3009";  //��Ʒ��Ϣ���ԣ���������˶�
 

    public static final String BILLING_PRICE_MODEL_ERROR = "3601";  //���ۼƻ�����

    public static final String BILLING_ALLOCATIONOFFUNDS_ERROR = "3602";  //�ʷ���ϲ��Դ���

    public static final String BILLING_PARAGRAPH_ERROR = "3603";  //�������

    public static final String BILLING_EXPENDITURE_ERROR = "3604";  //�ʷѴ���
}