package com.miniboss.ums

import com.dvn.miniboss.jc.process.UmsProcessService
import com.dvn.sys.dass.sms.po.account.request.SMSAccountAPIRequest
import com.dvn.sys.dass.sms.po.account.response.SMSAccountAPIResponse
import com.dvn.sys.dass.sms.po.balance.request.SMSBalanceAPIRequest
import com.dvn.sys.dass.sms.po.balance.response.SMSBalanceAPIResponse
import com.dvn.sys.dass.sms.po.pwdmod.request.SMSPwdModAPIRequest
import com.dvn.sys.dass.sms.po.pwdmod.response.SMSPwdModAPIResponse
import com.dvn.sys.dass.sms.po.pwdval.request.SMSPwdValAPIRequest
import com.dvn.sys.dass.sms.po.pwdval.response.SMSPwdValAPIResponse
import com.dvn.sys.dass.sms.po.recharge.request.SMSRechargeAPIRequest
import com.dvn.sys.dass.sms.po.recharge.response.SMSRechargeAPIResponse
import com.miniboss.util.HttpUtil
import com.dvn.sys.dass.sms.po.basequery.response.SMSBaseQueryAPIResponse
import com.dvn.sys.dass.sms.po.basequery.request.SMSBaseQueryAPIRequest
import com.dvn.sys.dass.sms.po.baseaccount.response.SMSBaseAccoBankAPIResponse
import com.dvn.sys.dass.sms.po.baseaccount.request.SMSBaseAccoBankAPIRequest
import com.miniboss.bean.ums.BaseServiceDataBean
import com.dvn.miniboss.acct.AcctPaymentMethod

class UmsProcessController {
    def UmsProcessService umsProcessService
    def account = {
        String requestStr = HttpUtil.getPost(request)
        log.info "ums account request:"+requestStr
        String responseStr = umsProcessService.process("account", requestStr, SMSAccountAPIRequest.class, SMSAccountAPIResponse.class)

        log.info "ums account response:"+responseStr
        render(contentType: "text/xml", text: "${responseStr}")
    }

    def balance = {
        String requestStr = HttpUtil.getPost(request)
        log.info "ums balance request:"+requestStr
        String responseStr = umsProcessService.process("balance", requestStr, SMSBalanceAPIRequest.class, SMSBalanceAPIResponse.class)

        log.info "ums balance response:"+responseStr
        render(contentType: "text/xml", text: "${responseStr}")
    }

    def recharge = {
        String requestStr = HttpUtil.getPost(request)
        log.info "ums recharge request:"+requestStr
        String responseStr = umsProcessService.process("recharge", requestStr, SMSRechargeAPIRequest.class, SMSRechargeAPIResponse.class)

        log.info "ums recharge response"+responseStr
        render(contentType: "text/xml", text: "${responseStr}")
    }

    def passwordValid = {
        String requestStr = HttpUtil.getPost(request)
        log.info "ums passwordValid request:"+requestStr
        String responseStr = umsProcessService.process("passwordValid", requestStr, SMSPwdValAPIRequest.class, SMSPwdValAPIResponse.class)

        log.info "ums passwordValid response:"+responseStr
        render(contentType: "text/xml", text: "${responseStr}")
    }

    def passwordModify = {
        String requestStr = HttpUtil.getPost(request)
        log.info "ums passwordModify request:"+requestStr
        String responseStr = umsProcessService.process("passwordModify", requestStr, SMSPwdModAPIRequest.class, SMSPwdModAPIResponse.class)

        log.info "ums passwordModify response:"+responseStr
        render(contentType: "text/xml", text: "${responseStr}")
    }
    /**
     * 3.18	UMS基本收视费查询接口
     */
    def baseQuery = {
        String requestStr = HttpUtil.getPost(request)
        log.info "ums baseQuery request:"+requestStr
        String responseStr = umsProcessService.process("baseQuery", requestStr, SMSBaseQueryAPIRequest.class, SMSBaseQueryAPIResponse.class)

        log.info "ums baseQuery response:"+responseStr
        render(contentType: "text/xml", text: "${responseStr}")
    }
    /**
     * 3.19	UMS基本收视费缴费接口
     */
    def baseAccoBank = {
        String requestStr = HttpUtil.getPost(request)
        log.info "ums baseAccoBank request:"+requestStr
        String responseStr = umsProcessService.process("baseAccoBank", requestStr, SMSBaseAccoBankAPIRequest.class, SMSBaseAccoBankAPIResponse.class)

        log.info "ums baseAccoBank response:"+responseStr
        render(contentType: "text/xml", text: "${responseStr}")
    }


}
