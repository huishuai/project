package com.miniboss.sync.ftp

import com.dvn.miniboss.oldsms.AuthService
import com.miniboss.exception.BaseException
import com.dvn.miniboss.serv.Empowerment
import com.miniboss.util.EmpowermentUtil

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-6-30
 * Time: 16:07:57
 * To change this template use File | Settings | File Templates.
 */
class ProductOrderBean implements FtpBean {

    long userId           //�û����-------------------orderUserId
    String stbId          //�û�ҵ�����(�û�tvn���)----uuId
    long orderNum         //��Ʒ������ˮ---------------authservicId
    String businessInfoId //��Ʒ��ʶ------------------commodityId
    String status         //��Ʒ����״̬---------------status
    Date orderDate        //��Ʒ����ʱ��--------------createDate
    Date startDate        //��Ʒ��Чʱ��--------------startDate
    Date cancleDate       //��ƷʧЧʱ��--------------endDate

    //��ʼ����ǰBean
    public ProductOrderBean(Empowerment empowerment, String uuId) throws Exception {
        if (empowerment == null || uuId == null)
            throw new BaseException("Request Paramater Is Null!", "Empowerment or uuId is Null!")
        this.userId = empowerment.userId
        this.stbId = uuId
        this.orderNum = empowerment.id
        this.businessInfoId = empowerment.serviceId
        boolean isValid = EmpowermentUtil.isValidEmpowermentForMaxEndDate(empowerment)
        if(isValid){
            this.status = AuthService.STATUS_RUNNING
        }else{
            this.status = AuthService.STATUS_STOPPED
        }
        this.orderDate = empowerment.createDate
        this.startDate = empowerment.minBeginDate
        this.cancleDate = empowerment.maxEndDate
    }


    def String convertBeanToFtpLog() throws Exception {
        //˳���Խӿ��ĵ�Ϊ����
        StringBuffer result = new StringBuffer()
        result.append(FtpBeanUtil.convertNumberToLog((Long) this.userId)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.stbId)).append(SPLIT)
        result.append(FtpBeanUtil.convertNumberToLog(this.orderNum)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.businessInfoId)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.status)).append(SPLIT)
        result.append(FtpBeanUtil.convertDateToLog(this.orderDate)).append(SPLIT)
        result.append(FtpBeanUtil.convertDateToLog(this.startDate)).append(SPLIT)
        result.append(FtpBeanUtil.convertDateToLog(this.cancleDate))
        result.append(LINE_END);
        return result.toString();
    }

    def void convertFtpLogToBean(String ftpLog) {
    }
}
