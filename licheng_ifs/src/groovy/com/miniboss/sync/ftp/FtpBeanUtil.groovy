package com.miniboss.sync.ftp

import com.miniboss.acct.utils.DateUtil

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-6-30
 * Time: 16:27:43
 * To change this template use File | Settings | File Templates.
 */
class FtpBeanUtil {

    public static final String defautDateTypeValue = "1900-01-01"

    //String为null内容设置为“”
    public static String convertStringToLog(String value) {
        if (value == null || value.trim().length() == 0) {
            value = ""
        } else {
            value = value.replaceAll("\\|", "\"|\"")
            value = value.replaceAll("\r", "")
            value = value.replaceAll("\n", "")
        }
        return value
    }

    //BSMP接口文档中定义时间为空设置为'1900-01-01'；
    public static String convertDateToLog(Date value) {
        return convertDateToLog(value, null)
    }

    public static String convertDateToLog(Date value, String defautStr) {

        if (value == null) {
            if(defautStr != null){
                //当前返回值用于其它第三方接口的空时间类型字段定义
                return defautStr
            }else{
                //当前默认值用于同BSMP接口之间的空时间类型字段定义
                return defautDateTypeValue
            }
        } else {
            return DateUtil.getDateBasicStr(value)
        }
    }



    //数字型为空设置为0
    public static String convertNumberToLog(Long value) {
        if (value == null)
            return 0
        else
            return value.toString()
    }
      //数字型为空设置为0
    public static String convertBigDecimalToLog(BigDecimal value) {
        if (value == null)
            return 0
        else
            return value.toString()
    }

}
