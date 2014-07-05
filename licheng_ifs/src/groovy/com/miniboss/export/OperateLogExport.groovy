package com.miniboss.export

import com.dvn.miniboss.log.OperateLog
import com.miniboss.acct.utils.DateUtil
import org.apache.log4j.Logger

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-4-2
 * Time: 6:08:46
 * To change this template use File | Settings | File Templates.
 */

class OperateLogExport {
    public static final String LOG_INTERNAL_SPLIT_STR = "@@";
    static Logger logger = Logger.getLogger("operateLogLogger")
    private static String insertSQL = "insert into OPERATELOG (ID,ACTION_ID,CREATEDATE,CUSTOMID,DESCRIPTION,OPERATEDATE,OPERATORID,USER_ID) values ("


    public static String toLog(OperateLog operateLog) {
        try {
            StringBuffer buffer = new StringBuffer(LOG_INTERNAL_SPLIT_STR)
            buffer.append(operateLog.id).append(LOG_INTERNAL_SPLIT_STR)
            buffer.append(operateLog?.action?.id).append(LOG_INTERNAL_SPLIT_STR)
            String createDateStr = DateUtil.getDateFullStr(operateLog.createDate)
            if (createDateStr != null) {
                buffer.append(createDateStr).append(LOG_INTERNAL_SPLIT_STR)
            } else {
                buffer.append("").append(LOG_INTERNAL_SPLIT_STR)
            }
            buffer.append(operateLog.customId).append(LOG_INTERNAL_SPLIT_STR)
            buffer.append(operateLog.description).append(LOG_INTERNAL_SPLIT_STR)
            String operateDateStr = DateUtil.getDateFullStr(operateLog.operateDate)
            if (operateDateStr != null) {
                buffer.append(operateDateStr).append(LOG_INTERNAL_SPLIT_STR)
            } else {
                buffer.append("").append(LOG_INTERNAL_SPLIT_STR)
            }
            buffer.append(operateLog.operatorId).append(LOG_INTERNAL_SPLIT_STR)
            buffer.append(operateLog.userId).append(LOG_INTERNAL_SPLIT_STR)
            return buffer.toString()
        } catch (Exception e) {
            logger.error(e)
        }
        return null;
    }

    private String getTimestampStr(String str) {
        if (str != null && str.length() == 19) {
            return "to_timestamp('" + str + "','yyyy-mm-dd  hh24:mi:ss')";
        }
        return "NULL"
    }

    private String getStr(String str) {
        if (str != null && !str.equals("null")) {
            return "'" + str + "'";
        }
        return "NULL"
    }

    public String toSQL(String log) {
        String[] valueList = log.split(LOG_INTERNAL_SPLIT_STR);
        try {
            if (valueList.length == 8) {
                StringBuffer buffer = new StringBuffer(insertSQL)
                buffer.append(valueList[0]).append(",")
                buffer.append(valueList[1]).append(",")
                buffer.append(getTimestampStr(valueList[2])).append(",")
                buffer.append(valueList[3]).append(",")
                buffer.append(getStr(valueList[4])).append(",")
                buffer.append(getTimestampStr(valueList[5])).append(",")
                buffer.append(getStr(valueList[6])).append(",")
                buffer.append(valueList[7]).append(");")
                return buffer.toString()
            }
        } catch (Exception e) {
            logger.error(e)
        }
        return null;
    }
}