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
    String uuid            //�û���UU��-------------------uuid
    String resourceId       //ӰƬ��ԴID
    int pointScore       //��������
    Date convertTime         //�һ�ʱ��
   public static final String SPARATOR_LINE_REGEX = "\\|";

    public void convertFtpPointToBean(String ftpStr) throws BaseException {
        if (ftpStr == null)
            throw new BaseException("Request Paramater Is Null!", "FtpPoint is Null!")
        //����FtpPoint
        String[] parameter = ftpStr.split(this.SPARATOR_LINE_REGEX)
        String tempTime = null;
        if (parameter.length == 4) {
            this.uuid = parameter[0] //�û���UU��
            this.resourceId = parameter[1]//ӰƬ��ԴID
            this.pointScore =Integer.valueOf(parameter[2]) //��������
            tempTime = parameter[3].substring(0,parameter[3].length()-2);
            this.convertTime = DateUtil.getDateByStrNoMis(tempTime); //�һ�ʱ��
        }
    }

}