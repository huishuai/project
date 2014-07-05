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

    //StringΪnull��������Ϊ����
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

    //BSMP�ӿ��ĵ��ж���ʱ��Ϊ������Ϊ'1900-01-01'��
    public static String convertDateToLog(Date value) {
        return convertDateToLog(value, null)
    }

    public static String convertDateToLog(Date value, String defautStr) {

        if (value == null) {
            if(defautStr != null){
                //��ǰ����ֵ���������������ӿڵĿ�ʱ�������ֶζ���
                return defautStr
            }else{
                //��ǰĬ��ֵ����ͬBSMP�ӿ�֮��Ŀ�ʱ�������ֶζ���
                return defautDateTypeValue
            }
        } else {
            return DateUtil.getDateBasicStr(value)
        }
    }



    //������Ϊ������Ϊ0
    public static String convertNumberToLog(Long value) {
        if (value == null)
            return 0
        else
            return value.toString()
    }
      //������Ϊ������Ϊ0
    public static String convertBigDecimalToLog(BigDecimal value) {
        if (value == null)
            return 0
        else
            return value.toString()
    }

}
