package com.miniboss.bean.print
/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-4-29
 * Time: 15:28:22
 * To change this template use File | Settings | File Templates.
 */

/**
 * �ɹ���ĸ��
 */
public class PrintWorkOrderBean implements Serializable{
    //�ɹ��������
    String billCode
  
    //------�ͻ�����-----------------
    //�ͻ����
    String customNo
    //�ͻ�����
    String customnm
    //סլ�绰
    String phone
    //�ƶ��绰
    String mobilephone
     //װ����ַ
    String addressconn
    //�˿���
    String ports
    //ҵ������
    String buzType
    //�û�����
    String customType

    //------��������-----------------
    //�µ�ʱ��
    java.util.Date timeCreate
    //�ɹ�ʱ��
    java.util.Date timeAssign
    //��װʱ��
    java.util.Date timeSetup
    //ʩ����
    String setupOperator
  
    //------��������-----------------
    //�ص�ʱ��
    java.util.Date timeReturn
    //����ʱ��
    java.util.Date timeCheck

    //��ע
     String remark
    
    //��ҳ��
    String localTemplate
    //ҳ���¼��
    int pageSize = 10
    //��ҳ��
    int pageCount = 1
    
  

}