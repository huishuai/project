package com.miniboss.callcenter

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2010-6-24
 * Time: 6:53:48
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class AuthService {
    String productid    //��Ʒʵ��id
    String productname   //��Ʒ����
    String isgroup    //��ͬ�ڿͻ��ϵ�groupType,��cmngUser�ϵ�groupType���岻ͬ
    String paymode    //����ģʽ
    Date createdate
    Date updatedate //�޸�����
    Date startdate   //������ʼ����
    Date enddate     //������ֹ����
    Date pausedate    //Ԥ��ͣ����
    Date activepausedate
    Date resumedate  //�����и�ͨ
    Date realstartdate  //ʵ�ʿ�ʼʱ��
    Date realenddate   //ʵ�ʽ�������
    String formerstatus//ǰ�η���״̬
    String status
    Date freestartdate  //��ѿ�ʼ����
    Date freeenddate   //��ѽ�������
    long refpricefix    //�ο��۸�
    long pricefix      //�۸�
    String isrenew    //�Ƿ�������

}
