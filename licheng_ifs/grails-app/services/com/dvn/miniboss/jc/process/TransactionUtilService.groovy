package com.dvn.miniboss.jc.process

import com.dvn.miniboss.oldsms.CmngCustom
import com.miniboss.bean.BaseBean
import com.miniboss.exception.BaseException
import com.miniboss.exception.ExceptionCode
import com.miniboss.util.MessageUtil
import com.miniboss.error.ConstantUtil

class TransactionUtilService {

    boolean transactional = false 
    def BaseBean processTransaction(BaseBean baseBean,Closure closure) {
        BaseBean responseBean = new BaseBean()
        Object ret = null
        try {
            CmngCustom.withTransaction {
                ret = closure.call()
            }
            responseBean.setResult(true)
        } catch (Exception e) {
            e.printStackTrace()
            String retCode
            if (e instanceof BaseException) {
                retCode = ((BaseException) e).getCode()
                responseBean.setMessage(((BaseException) e).getErrorMessage())
            } else {
                retCode = ConstantUtil.UNKNOWN_ERROR
                responseBean.setMessage(MessageUtil.getMessage(ExceptionCode.SystemException))
                e.printStackTrace()
            }
            responseBean.setResult(false)
            responseBean.setCode(retCode)
        }
        return responseBean
    }
}