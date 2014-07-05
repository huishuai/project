package com.miniboss.call.tool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Title: SMS</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 *
 * @author sandish
 * @version 4.0
 */

/*
 (1) SMSError.Code = ‘000’ 表示无错。
 (2) SMSError.Code = ‘100’ 表示客户端编号有错误。
 (3) SMSError.Code = ‘200’ 表示没有指定查询条件。
 (4) SMSError.Code = ‘300’表示SMS 搜索记录时出错。
 ... ...
  */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SMSError", namespace = "http://tool.call.sms.dvnchina.com")
public class SMSError {
    private String codeid;
//    private String codenm;
    private String message;

    public final static String NO_ERROR = "000";
    public final static String CLIENTID_ERROR = "100";
    public final static String NO_RECORD = "110";
    public final static String NO_QUERY_COND = "200";
    public final static String QUERY_ERROR = "300";

    public String getCodeid() {
        return codeid;
    }

    public void setCodeid(String codeid) {
        this.codeid = codeid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

