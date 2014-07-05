package com.dvn.miniboss.jc.ftp

import com.miniboss.acct.bean.BsmpFtpFileBean

import java.text.SimpleDateFormat
import com.dvn.miniboss.acct.CmngUser
import com.dvn.miniboss.oldsms.CmngCustom
import com.miniboss.acct.bean.FtpStaticParameter
import com.dvn.miniboss.oldsms.CmngCustominfo
import com.dvn.miniboss.oldsms.CmngStbcustomvdo
import com.miniboss.acct.utils.Md5Util

class JcPortalUserInfoService extends BaseFtpProcessService {

  boolean transactional = false

  public JcPortalUserInfoService() {
    //todo �޸�һ��
    this.type = "C"
    this.code = "01003"
  }

  void execute(BsmpFtpFileBean bean) {
    //todo �û���Ϣͬ��   BOSS-->PORTAL
    /*
    * �û���Ϣ��
    *    �ͻ�����
    *    �û����(CmngStbcustomvdo uu��)
    *    �û���ַ(ȡ�ͻ���ַ)
    *    �û�����(ȡ�ͻ�����)
    *    �û�����
    *    �û�״̬
    *    �û���
    *    �û�����
    */
    InputStream inputStream = new ByteArrayInputStream()
    def cmngUsers = CmngUser.findAll()
    def cmngCustom
    def cmngCustominfo
    def cmngStbcustomvdo
    String info, userType
    int i = 1
    for (CmngUser cmngUser: cmngUsers) {
      if (cmngUser.dvbCostomId && cmngCustom.customidCmngCustominfo) {
        cmngCustom = CmngCustom.get(cmngUser.dvbCostomId)
        cmngCustominfo = CmngCustominfo.get(cmngCustom.customidCmngCustominfo)
        cmngStbcustomvdo = CmngStbcustomvdo.find("from CmngStbcustomvdo c where c.useridCmngUser=" + cmngUser.id)
        if (cmngCustom && cmngCustominfo && cmngStbcustomvdo) {
          /**
           * ������룺
           * 01 -- ֣�� 02 -- ���� 03 -- ���� 04 -- ƽ��ɽ 05 -- ���� 06 -- ���� 07 -- �ױ� 08 -- ���� 09 -- ���
           * 10 -- ��� 11 -- ��� 12 -- ����Ͽ 13 -- ���� 14 -- ���� 15 -- ���� 16 -- �ܿ� 17 -- פ��� 18 -- ��Դ
           */
          //TODO �û�������ϵͳ���ݲ�һ��

         /**
          *  �û�״̬ 01 -- ��Ч 02 -- ��Ч 03 -- ע��
           * �û����� 10 �C �����û� 20 �C ˫���û� 30 �C ����û� 40 �C �����û� (����˫��)
           */
          userType = "30"
          
          if (i < cmngUsers.size()) {
            info = cmngCustom.customNo + FtpStaticParameter.SEPARATOR + cmngStbcustomvdo.smcode + FtpStaticParameter.SEPARATOR + cmngCustominfo.address + FtpStaticParameter.SEPARATOR + cmngCustominfo.zone + FtpStaticParameter.SEPARATOR + userType + FtpStaticParameter.SEPARATOR + cmngUser.status + FtpStaticParameter.SEPARATOR + cmngUser.name + FtpStaticParameter.SEPARATOR + Md5Util.byte2hex(Md5Util.encryptMD5(cmngUser.password.getBytes())) + FtpStaticParameter.ENTER
          } else {
            info = cmngCustom.customNo + FtpStaticParameter.SEPARATOR + cmngStbcustomvdo.smcode + FtpStaticParameter.SEPARATOR + cmngCustominfo.address + FtpStaticParameter.SEPARATOR + cmngCustominfo.zone + FtpStaticParameter.SEPARATOR + userType + FtpStaticParameter.SEPARATOR + cmngUser.status + FtpStaticParameter.SEPARATOR + cmngUser.name + FtpStaticParameter.SEPARATOR + Md5Util.byte2hex(Md5Util.encryptMD5(cmngUser.password.getBytes()))
          }
          inputStream.read(info.getBytes())
        }
      }
      i++
    }

    //�ϴ���FTP������  /receive/date(yyyymmdd)   /error/date(yyyymmdd)
    def ftpOperate = this.ftpClient.createFtpClient()
    try {
      ftpOperate.uploadFile("/receive/" + new SimpleDateFormat("yyyyMMdd").format(new Date()), bean.getFileName(), inputStream)
    } catch (Exception e) {
      ftpOperate.uploadFile("/error/" + new SimpleDateFormat("yyyyMMdd").format(new Date()), bean.getFileName(), inputStream)
    }finally{
          ftpOperate.close()
      }
  }

}