package com.miniboss.bean.print
/**
 * Created by IntelliJ IDEA.
 * User: tb
 * Date: 2010-3-9
 * Time: 15:34:49
 * To change this template use File | Settings | File Templates.
 */
/**
 * Ӫҵ��ҵ������[ĸ��]
 */
public class NetPrintBaseBean implements Serializable{
    //��������
    String billDate
    //������
    String operatorId
    //�������
    String billCode
    //------�ͻ�����-----------------
    //�ͻ����
    String customNo
    //�ͻ�����
    String customnm
    //֤������
    String certificatetypeName
    //֤������
    String certificated
    //�û�����
    String typeCmngCustomtypeName
    //UU��
    String UUid
    //סլ�绰
    String phone
    //�ƶ��绰
    String mobilephone
    //װ����ַ
    String addressconn

    //------������Ϣ-----------------
    //���(��)
    long feeNum
    //����д
    String feeTxt

    //------ע������-----------------
    //ע������
    String warring

    //��ҳ��
    String localTemplate
    //ҳ���¼��
    int pageSize = 10
    //��ҳ��
    int pageCount = 1

}