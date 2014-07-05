package com.dvn.miniboss.jc.ftp

import com.miniboss.acct.bean.BsmpFtpFileBean
import com.dvn.miniboss.oldsms.CmngCustom
import com.dvn.miniboss.oldsms.CmngCustominfo
import com.miniboss.acct.utils.FtpOperate
import java.text.SimpleDateFormat
import com.miniboss.acct.bean.FtpStaticParameter

class JcCustomInfoService extends BaseFtpProcessService {

    boolean transactional = false

    public JcCustomInfoService() {
        //todo 修改一下
        this.type = "C"
        this.code = "01001"
    }

    void execute(BsmpFtpFileBean bean) {
        //todo 客户信息同步   BOSS-->PORTAL

      /*
       * 客户信息：客户编码 客户名称 客户性别 证件类型 证件号码 家庭电话 移动电话
       */
      InputStream inputStream = new ByteArrayInputStream()
      def customs = CmngCustom.findAll()
      def customInfo = null
      String info ,sex ,certificateType
      int i = 1
      for(CmngCustom custom : customs){
        customInfo = CmngCustominfo.get(custom.customidCmngCustominfo)
        if(customInfo){
          /*
           *判断性别
           *  01 – 男
           *  02 – 女
           *  00 – 未知
           */
          if(customInfo.sex){
            if(customInfo.sex == "0"){
              sex = "01"
            }else{
              sex = "02"
            }
          } else {
            sex = "00"
          }
          /*
           *判断证件类型
           *  01 - 身份证
           *  02 - 驾驶证
           *  03 - 军官证
           *  04 - 企业执照
           *  05 - 护照
           *  06 - 组织机构代码证
           */
          if(customInfo.certificatetype){
             if(customInfo.certificatetype == "ID_Number"){
               certificateType = "01"
             }
          }else{
            certificateType = "01"
          }
          if(i < customs.size()){
            info = custom.customNo + FtpStaticParameter.SEPARATOR + customInfo.customnm + FtpStaticParameter.SEPARATOR + customInfo.sex + FtpStaticParameter.SEPARATOR + certificateType + FtpStaticParameter.SEPARATOR + customInfo.certificated + FtpStaticParameter.SEPARATOR + customInfo.maintele + FtpStaticParameter.SEPARATOR + customInfo.mobilephone + FtpStaticParameter.ENTER
          } else {
            info = custom.customNo + FtpStaticParameter.SEPARATOR + customInfo.customnm + FtpStaticParameter.SEPARATOR + customInfo.sex + FtpStaticParameter.SEPARATOR + certificateType + FtpStaticParameter.SEPARATOR + customInfo.certificated + FtpStaticParameter.SEPARATOR + customInfo.maintele + FtpStaticParameter.SEPARATOR + customInfo.mobilephone
          }
          inputStream.read(info.getBytes())
          i++
        }
      }

      //上传到FTP服务器  /receive/date(yyyymmdd)   /error/date(yyyymmdd)
      FtpOperate ftpOperate = this.ftpClient.createFtpClient()
      try{
        ftpOperate.uploadFile("/receive/" + new SimpleDateFormat("yyyyMMdd").format(new Date()), bean.getFileName(), inputStream)
      }catch(Exception e){
        ftpOperate.uploadFile("/error/" + new SimpleDateFormat("yyyyMMdd").format(new Date()), bean.getFileName(), inputStream)
      }finally{
          ftpOperate.close()
      }
      
    }

}
