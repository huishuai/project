package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 2:54:29
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class CustomerInfo {
    long id              //�ͻ�ID��ϵͳʹ��
    String areaid       //����ID
    String netid        //����ID
    Date opendate       //��������
    String customNo     //�ͻ�Ψһ��ʶ�ţ����ڿͻ�ҵ������ʱ��ʹ��
    String stockid       //�������
    String customType   //�ͻ���֯����
    String bankAssign   //�Ƿ����д��۱�ʶ:0-�Ǵ��ۣ�1-����
    Date createDate     //����ʱ��
    String type       //�ͻ����ͣ������û���ũ���û�
    String status    //״̬
    String lease                    //�Ƿ�Ϊ���޿ͻ�,Ĭ��Ӧ��Ϊ�����޿ͻ�
    String customnm               //�ͻ�����
    String sex                     //�Ա�
    Date birthday                  //����
    String phone                   //�ֻ�
    String address                 //��ϸ��ַ
    String zipcode                 //�ʱ�
    String maintele                //�绰
    String idcard                  //���֤��
    String email                   //��������
    String district                 //����
    String bankassign              //�Ƿ�Ϊ���д���
    String remark                  //��ע
    List<User> userList = new ArrayList<User>()            //�û��б�
}


