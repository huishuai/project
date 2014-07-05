package com.miniboss.sync.ftp

import com.miniboss.exception.BaseException
import com.miniboss.acct.utils.DateUtil

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-11-08
 * Time: 16:07:57
 * To change this template use File | Settings | File Templates.
 */

class UmsPointBean {
    String uuid            //用户的UU号-------------------uuid
    String resourceId       //影片资源ID
    int pointScore       //积分数额
    Date convertTime         //兑换时间
   public static final String SPARATOR_LINE_REGEX = "\\|";

    public void convertFtpPointToBean(String ftpStr) throws BaseException {
        if (ftpStr == null)
            throw new BaseException("Request Paramater Is Null!", "FtpPoint is Null!")
        //解析FtpPoint
        String[] parameter = ftpStr.split(this.SPARATOR_LINE_REGEX)
        String tempTime = null;
        if (parameter.length == 4) {
            this.uuid = parameter[0] //用户的UU号
            this.resourceId = parameter[1]//影片资源ID
            this.pointScore =Integer.valueOf(parameter[2]) //积分数额
            tempTime = parameter[3].substring(0,parameter[3].length()-2);
            this.convertTime = DateUtil.getDateByStrNoMis(tempTime); //兑换时间
        }
    }

}