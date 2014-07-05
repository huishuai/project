package com.miniboss.bean.workorder;

import com.dvn.miniboss.acct.CmngUser;

import java.util.Date;
import java.util.List;

/**
 * 工单的扩展信息bean，保存维修之类的信息
 * User: star
 * Date: 2010-08-13
 * Time: 14:58:51
 */
public class WorkOrderExtendBean {
    String maintainJobNumber;//维修工号
    String maintainDescription;//维修情况
    Date maintainDate;//维修时间
    String classType;//机顶盒类型，主机还是从机
    List<CmngUser> users;//工单所包含的用户
    String businessType;//工单业务类型，比如开户，机卡销售等
    String reason;//撤销原因
    String hung;//挂起原因

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
