package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-23
 * Time: 3:01:17
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class User {
    long id  //�û���ʶ
    long dvbCostomId//�ͻ���ʶ
    String userTeamId   //�û����ʶ(�����û��㼶��ϵ-���ڵ�)
    String name //�û���¼��
    String password //���� ���Բ�����
    String status   //״̬
    String description  //����
    Date createDate //����ʱ��
    Date updateDate //����ʱ��
    Date pauseDate  //��ͣʱ��
    String classType //��������������(Ӧ������ҵ�ͼ���,���˲�����ʹ��)
    String groupType  //������ 
    String operatorFlag    //����������޸Ĳ���
    String uuid         //UU��

}
