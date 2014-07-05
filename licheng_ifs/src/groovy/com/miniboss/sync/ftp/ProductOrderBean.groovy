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

    long userId           //用户编号-------------------orderUserId
    String stbId          //用户业务号码(用户tvn编号)----uuId
    long orderNum         //产品订购流水---------------authservicId
    String businessInfoId //产品标识------------------commodityId
    String status         //产品订购状态---------------status
    Date orderDate        //产品订购时间--------------createDate
    Date startDate        //产品生效时间--------------startDate
    Date cancleDate       //产品失效时间--------------endDate

    //初始化当前Bean
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
        //顺序以接口文档为依据
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
