package com.miniboss.sync.ftp

import com.dvn.miniboss.acct.CmngUser
import com.dvn.miniboss.oldsms.CmngCustom
import com.dvn.miniboss.oldsms.CmngCustominfo
import com.dvn.miniboss.oldsms.CmngStbcustomvdo
import com.miniboss.exception.BaseException
import com.miniboss.acct.utils.DateUtil

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-3
 * Time: 17:01:34
 * To change this template use File | Settings | File Templates.
 */
class CmngCustomInfoBean implements FtpBean {

    String dvbCustomId            //客户编号
    String stbId                //机顶盒号
    String icId                 //IC卡号
    String stbStatus            //机顶盒状态
    String smCode               //UU号
    String customType           //客户类型：1-个人客户，2-企业客户，3-集团客户
    String sex                  //性别
    Date endDate                //免费结束日期
    String customNM            //客户姓名
    String address              //客户地址
    String idCard               //身份证号
    String maintele             //联系电话
    String stbtype           //机顶盒类型

    //初始化当前Bean
    public CmngCustomInfoBean(Map userInfeMap, int baseServiceStatus, boolean isFreeUser) throws Exception {
        if (userInfeMap == null || baseServiceStatus == null)
            throw new BaseException("Request Paramater Is Null!", "UserInfeMap or b aseServiceStatus is Null")
        this.dvbCustomId = (String)userInfeMap.get("CUSTOMNO")
        this.customType = (String)userInfeMap.get("TYPE")
        //需要赋给免费期结束日期，根据iscp同步免费用户数据判断
        //免费用户到"2011-12-31"，其他到"2010-07-01"
        this.endDate = DateUtil.getDateByBasicStr("2010-07-01")
        if(isFreeUser)
            this.endDate = DateUtil.getDateByBasicStr("2011-12-31")
        this.stbId = userInfeMap.get("STBID")
        this.icId = userInfeMap.get("ICID")
        this.smCode = userInfeMap.get("SMCODE")
        this.stbStatus = baseServiceStatus
        this.sex = userInfeMap.get("SEX")
        this.customNM = userInfeMap.get("CUSTOMNM")
        this.address = userInfeMap.get("ADDRESS")
        this.idCard = userInfeMap.get("IDCARD")
        this.maintele = userInfeMap.get("MAINTELE")
        this.stbtype = userInfeMap.get("RESOURCE_MODEL_ID")
    }


    def String convertBeanToFtpLog() {
        //顺序以接口文档为依据
        StringBuffer result = new StringBuffer()
        result.append(FtpBeanUtil.convertStringToLog(this.dvbCustomId)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.stbId)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.icId)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.stbStatus)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.smCode)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.customType)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.sex)).append(SPLIT)
        result.append(FtpBeanUtil.convertDateToLog(this.endDate)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.customNM)).append(SPLIT)
        //地址字段中可能会有回车符，需要过过滤掉，否则在导出文件中会出现不正常的分行
        String address = this.address;
        if (address != null && address.length() > 0) {
            address = address.replaceAll("\n", "");
            address = address.replaceAll("\r", "");
        }
        result.append(FtpBeanUtil.convertStringToLog(this.address)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.idCard)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.maintele)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.stbtype))
        result.append(LINE_END);
        return result.toString();
    }

    def void convertFtpLogToBean(String ftpLog) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
