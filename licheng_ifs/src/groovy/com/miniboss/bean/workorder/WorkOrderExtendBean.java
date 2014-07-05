package com.miniboss.bean.workorder;

import com.dvn.miniboss.acct.CmngUser;

import java.util.Date;
import java.util.List;

/**
 * ��������չ��Ϣbean������ά��֮�����Ϣ
 * User: star
 * Date: 2010-08-13
 * Time: 14:58:51
 */
public class WorkOrderExtendBean {
    String maintainJobNumber;//ά�޹���
    String maintainDescription;//ά�����
    Date maintainDate;//ά��ʱ��
    String classType;//���������ͣ��������Ǵӻ�
    List<CmngUser> users;//�������������û�
    String businessType;//����ҵ�����ͣ����翪�����������۵�
    String reason;//����ԭ��
    String hung;//����ԭ��

    public String getHung() {
        return hung;
    }

    public void setHung(String hung) {
        this.hung = hung;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public List<CmngUser> getUsers() {
        return users;
    }

    public void setUsers(List<CmngUser> users) {
        this.users = users;
    }

    public String getMaintainJobNumber() {
        return maintainJobNumber;
    }

    public void setMaintainJobNumber(String maintainJobNumber) {
        this.maintainJobNumber = maintainJobNumber;
    }

    public String getMaintainDescription() {
        return maintainDescription;
    }

    public void setMaintainDescription(String maintainDescription) {
        this.maintainDescription = maintainDescription;
    }

    public Date getMaintainDate() {
        return maintainDate;
    }

    public void setMaintainDate(Date maintainDate) {
        this.maintainDate = maintainDate;
    }
}
