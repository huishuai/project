package com.miniboss.bean.print
/**
 * Created by IntelliJ IDEA.
 * User: tb
 * Date: 2010-3-17
 * Time: 17:08:44
 * To change this template use File | Settings | File Templates.
 */

public class NetPrintPayBillBaseBean implements Serializable {

    //------Ʊ��̧ͷ-----------------
    //Ʊ�ݴ���
    String billId
    //�շѲ���
    String deptName
    //Ʊ������ ��
    String year
    //Ʊ������ ��
    String month
    //Ʊ������ ��
    String day
    //Ʊ�ݺ���
    String billCode
    
    //------�ͻ�����-----------------
    //�û�����
    String customnm
    //װ����ַ
    String addressconn
    //�绰
    String tel
    //��λ
    String unitName
    //֤������
    String certificated
    //�û����
    String customCode

     //------������Ϣ-----------------
    //���
    long feeNum
    //����д
    String feeTxt

    //------Ʊ�ݽ�β-----------------
    //�տ���
    String operatorName
    //˰����
    String taxCode

}