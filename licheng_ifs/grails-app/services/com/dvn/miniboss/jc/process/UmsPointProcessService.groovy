package com.dvn.miniboss.jc.process

import com.dvn.miniboss.sync.UmsPoint
import com.miniboss.sync.ftp.UmsPointBean
import org.apache.log4j.Logger

class UmsPointProcessService {

  public final static Logger logger = Logger.getLogger(UmsPointProcessService.class)

  def insertUmsPointToDB(InputStream inputStream) throws Exception {

    logger.info("--Read And Save UmsPoint!----Begin----")
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(inputStream))
      println UmsPoint.findAll()
      String line
       UmsPoint.withTransaction {
      while ((line = br.readLine()) != null) {
        UmsPointBean umsPointbean = new UmsPointBean()
        umsPointbean.convertFtpPointToBean(line)
        //±£¥ÊUmsPoint
        UmsPoint umsPoint = new UmsPoint()
        umsPoint.properties = umsPointbean.properties

             umsPoint.save()
        }
      }
    } catch (Exception e) {
      logger.error("Read And Save umsPoint Errir!", e)
      e.printStackTrace()
    } finally {
      try {
        if (br != null)
          br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    logger.info("--Read And Save umsPoint!----End----")


  }
}