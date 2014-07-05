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
    //todo 修改一下
    this.type = "C"
    this.code = "01003"
  }

  void execute(BsmpFtpFileBean bean) {
    //todo 用户信息同步   BOSS-->PORTAL
    /*
    * 用户信息：
    *    客户编码
    *    用户编号(CmngStbcustomvdo uu号)
    *    用户地址(取客户地址)
    *    用户区域(取客户区域)
    *    用户类型
    *    用户状态
    *    用户名
    *    用户密码
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
           * 区域代码：
           * 01 -- 郑州 02 -- 开封 03 -- 洛阳 04 -- 平顶山 05 -- 安阳 06 -- 焦作 07 -- 鹤壁 08 -- 新乡 09 -- 濮阳
           * 10 -- 许昌 11 -- 漯河 12 -- 三门峡 13 -- 南阳 14 -- 商丘 15 -- 信阳 16 -- 周口 17 -- 驻马店 18 -- 济源
           */
          //TODO 用户区域与系统数据不一致

         /**
          *  用户状态 01 -- 有效 02 -- 无效 03 -- 注销
           * 用户类型 10 – 单向用户 20 – 双项用户 30 – 宽带用户 40 – 其他用户 (均是双向)
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

    //上传到FTP服务器  /receive/date(yyyymmdd)   /error/date(yyyymmdd)
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