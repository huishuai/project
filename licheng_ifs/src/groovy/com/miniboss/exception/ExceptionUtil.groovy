package com.miniboss.exception

import com.dvn.sys.dass.sms.StringUtil
import com.miniboss.util.MessageUtil

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-18
 * Time: 16:54:50
 * To change this template use File | Settings | File Templates.
 */
class ExceptionUtil {

    //CBS返回的余额不足代码，需要进行异常编码转换：CBS-->IFS-->UMS
    public static final String CanUseBalanceNotEnough = "DepositDetail.CanUseBalanceNotEnough"
    public static final String NeedMoreMoney = "AcctBalance.NeedMoreMoney"


    public static String getUmsExceptionCode(String exCodeForCBS) {
        if (exCodeForCBS != null) {

            if (CanUseBalanceNotEnough.equals(exCodeForCBS) || NeedMoreMoney.equals(exCodeForCBS))
                return StringUtil.NOT_ENOUGTH_MONEY
        }
        return StringUtil.PROCESS_ERROR
    }

    public static String getUmsExceptionMessage(String exCodeForCBS) {
        if (exCodeForCBS != null) {

            if (CanUseBalanceNotEnough.equals(exCodeForCBS) || NeedMoreMoney.equals(exCodeForCBS))
                return MessageUtil.getMessage("Balance.Not.Enought")
        }
        return MessageUtil.getMessage("RequestService.request.error")
    }


}
