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
        //todo �޸�һ��
        this.type = "C"
        this.code = "01001"
    }

    void execute(BsmpFtpFileBean bean) {
        //todo �ͻ���Ϣͬ��   BOSS-->PORTAL

      /*
       * �ͻ���Ϣ���ͻ����� �ͻ����� �ͻ��Ա� ֤������ ֤������ ��ͥ�绰 �ƶ��绰
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
           *�ж��Ա�
           *  01 �C ��
           *  02 �C Ů
           *  00 �C δ֪
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
           *�ж�֤������
           *  01 - ���֤
           *  02 - ��ʻ֤
           *  03 - ����֤
           *  04 - ��ҵִ��
           *  05 - ����
           *  06 - ��֯��������֤
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

      //�ϴ���FTP������  /receive/date(yyyymmdd)   /error/date(yyyymmdd)
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
