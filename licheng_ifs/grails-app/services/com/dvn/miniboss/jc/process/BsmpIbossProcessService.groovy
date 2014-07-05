package com.dvn.miniboss.jc.process

import org.apache.log4j.Logger
import java.sql.Connection
import javax.sql.DataSource
import java.sql.Statement
import java.sql.ResultSet
import java.sql.PreparedStatement
import java.sql.SQLException
import com.dvn.miniboss.sync.BsmpResourceBusiness

class BsmpIbossProcessService {

  boolean transactional = true

  public final static Logger logger = Logger.getLogger(BsmpIbossProcessService.class)
  DataSource bsmpDataSource
  DataSource dataSource

  def dataSync() throws Exception {
    logger.info("--Bsmp sync iboss !----Begin----")
    Connection conn = null;
    Statement stat = null;
    ResultSet rs = null;
    Statement stmt2 = null;
    Connection conn2 = null;
    String querySql = 'select ib.id, ib.businesscode,ib.name,vpa.assetid,vpa.assetname  from vodproduct_productcontentref vpc, vodproduct_productserviceref vps, ismp_businessinfo ib, vodproduct_publishasset vpa where vps.product = vpc.product and vps.serviceid = ib.id and vpc.contentid = vpa.assetid'
    System.out.println("querySql: " + querySql);

    try {
      conn = bsmpDataSource.getConnection()
      stat = conn.createStatement();
      rs = stat.executeQuery(querySql);

      //ÿ��ͬ��ǰ����� BSMP_RESOURCE_BUSINESS ��
      BsmpResourceBusiness.withTransaction {
        String delSql = "delete from bsmp_resource_business";
        conn2 = dataSource.getConnection();
        stmt2 = conn2.createStatement();
        stmt2.executeUpdate(delSql);
        //����ͬ����iboss���ݿ�

        BsmpResourceBusiness bsmpResourceBusiness = null
        while (rs.next()) {
          bsmpResourceBusiness = new BsmpResourceBusiness()
          bsmpResourceBusiness.businessCode = rs.getString("businesscode")
          bsmpResourceBusiness.businessName = rs.getString("name")
          bsmpResourceBusiness.assetId = rs.getString("assetid")
          bsmpResourceBusiness.assetName = rs.getString("assetname")
          bsmpResourceBusiness.save()
          //System.out.println("����ɹ�:");
        }
      }

    } catch (Exception e) {
      logger.error("Bsmp sync iboss Errir!", e)
      e.printStackTrace()
    } finally {
      try {
        if (rs != null)
          rs.close();
        if (stat != null)
          stat.close();
        if (conn != null)
          conn.close();
        if (stmt2 != null)
          stmt2.close();
        if (conn2 != null)
          conn2.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    logger.info("--Bsmp sync iboss!----End----")
  }
}