package com.dvn.miniboss.jc.ftp

import com.miniboss.acct.bean.BsmpFtpFileBean
import com.miniboss.acct.bean.FtpStaticParameter
import java.text.SimpleDateFormat
import com.dvn.miniboss.oldsms.CmngCustom
import com.dvn.miniboss.oldsms.CmngCustominfo
import com.dvn.miniboss.oldsms.CmngStbcustomvdo
import com.dvn.miniboss.acct.CmngUser
import com.miniboss.acct.utils.Md5Util

/**
 * �û�������Ϣ-�û�ȫ����Ϣ  ׼ʵʱ����������
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-6-29
 * Time: 21:16:03
 * To change this template use File | Settings | File Templates.
 */
class JcPortalUserInfoIncrementService extends BaseFtpProcessService {
  boolean transactional = false

  public JcPortalUserInfoService() {
    //todo �޸�һ��
    this.type = "A"
    this.code = "01004"
  }

  void execute(BsmpFtpFileBean bean) {
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
           * �û�״̬ 01 -- ��Ч 02 -- ��Ч 03 -- ע��
           * �û����� 10 �C �����û� 20 �C ˫���û� 30 �C ����û� 40 �C �����û�
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
