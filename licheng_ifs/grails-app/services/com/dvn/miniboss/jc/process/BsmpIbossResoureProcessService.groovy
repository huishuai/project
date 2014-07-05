package com.dvn.miniboss.jc.process

import org.apache.log4j.Logger
import java.sql.Connection
import javax.sql.DataSource
import java.sql.Statement
import java.sql.ResultSet
import java.sql.PreparedStatement
import java.sql.SQLException
import com.dvn.miniboss.sync.BsmpResource

class BsmpIbossResoureProcessService {

  boolean transactional = true


  public final static Logger logger = Logger.getLogger(BsmpIbossResoureProcessService.class)
  DataSource bsmpDataSource
  DataSource dataSource

  def resoureDataSync() throws Exception {
    logger.info("--Resoure from Bsmp sync to iboss !----Begin----")
    Connection conn = null;
    Statement stat = null;
    ResultSet rs = null;
    Statement stmt2 = null;
    Connection conn2 = null;
    String querySql = 'select vp.assetid, vp.assetname, vpro.providerid, vpro.providername from VODPRODUCT_PUBLISHASSET vp, VODCONTENT_VODASSET vv, vodcontent_distributeinfo vd, vodcontent_provider vpro where vp.assetid = vv.id and vv.id = vd.vodasset and vd.provider = vpro.id'
    System.out.println("querySql: " + querySql);

    try {
      conn = bsmpDataSource.getConnection();
      stat = conn.createStatement();
      rs = stat.executeQuery(querySql);

      //同步前先清空 BSMP_RESOURCE 表
      BsmpResource.withTransaction {
        String delSql = "delete from bsmp_resource";
        conn2 = dataSource.getConnection();
        stmt2 = conn2.createStatement();
        stmt2.executeUpdate(delSql);

        BsmpResource bsmpResource = null;
        while (rs.next()) {
          bsmpResource = new BsmpResource()
          bsmpResource.resourceId = rs.getString("assetid")
          bsmpResource.resourceName = rs.getString("assetname")
          bsmpResource.providerId = rs.getString("providerid")
          bsmpResource.provider = rs.getString("providername")
          bsmpResource.save()
          //System.out.println("插入成功:");

        }
      }
    } catch (Exception e) {
      logger.error("Resoure from Bsmp sync to iboss Errir!", e)
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
    logger.info("--Resoure from Bsmp sync to iboss !----End----")
  }
}
