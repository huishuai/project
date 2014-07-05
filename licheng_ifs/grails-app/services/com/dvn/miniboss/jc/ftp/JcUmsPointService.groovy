package com.dvn.miniboss.jc.ftp

import com.dvn.miniboss.jc.process.UmsPointProcessService
import com.miniboss.acct.bean.BsmpFtpFileBean
import com.miniboss.acct.utils.DateUtil
import com.miniboss.acct.utils.FtpClient
import com.miniboss.acct.utils.FtpOperate
import org.apache.log4j.Logger

class JcUmsPointService extends BaseFtpProcessService {

  private final static Logger logger = Logger.getLogger(JcUmsPointService.class)
  def UmsPointProcessService umsPointProcessService
  def FtpClient ftpClientIfs;
  static String fileSeparator = "/";

  public JcUmsPointService() {
    this.type = "E"
    this.code = "02004"
  }

  public void run() {
//        BsmpFtpFileBean bean = buildFileBean(this.type, this.code, ftpClientIfs.remoteFilePath);
    execute(null);
  }

  void execute(BsmpFtpFileBean bean) {
    //资源同步 ftp
    //同步Bean入库

    logger.debug(">>>>>>>>>>>>>>>>>>>>>UmsPoint Sync：Begin<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    FtpOperate ftpOperate = null
    try {
      ftpOperate = this.ftpClientIfs.createFtpClient();
      ftpOperate.getFtpClient().enterLocalPassiveMode();
      if (ftpOperate.checkConnected()) {

        String fileName = DateUtil.getDateBasicStr(DateUtil.getPreDateByNum(new Date(), 1)) + ".txt"
        fileName = fileName.replaceAll("-", "_")
        // boolean flag = ftpOperate.checkFileByFtpClient(this.ftpClientIfs.remoteFilePath, fileName)

        InputStream inputStream = ftpOperate.readFtpFile2(ftpClientIfs.remoteFilePath, fileName);
        if(inputStream!=null){
            //将同步积分信息入库
            umsPointProcessService.insertUmsPointToDB(inputStream)
        }else{
            logger.error("No reads the file!");
        }
        ftpOperate.validateCommandAndClose();
      } else {
        logger.error("FTP Not Connection，Please Check Net！");
      }
    } catch (Exception e) {
      logger.error(">>>>>>>>>>>>>>>>>>>>>UmsPoint Sync：Exception<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
      e.printStackTrace();
    } finally {
      try {
        if (ftpOperate != null)
          ftpOperate.close();
      } catch (Exception e) {
        logger.error("Close FtpClient Error！");
      }
    }
    logger.debug(">>>>>>>>>>>>>>>>>>>>>UmsPoint Sync：End<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>影片媒资信息同步：正常结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
  }
}