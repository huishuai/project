package com.miniboss.acct.utils;

import com.dvn.miniboss.oldsms.AuthService;
import com.miniboss.bean.BasePage;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.jdbc.support.JdbcUtils.getResultSetValue;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-4-21
 * Time: 11:34:14
 * To change this template use File | Settings | File Templates.
 */
public class JdbcUtils {
    //copy from Oracle9Dialect

    public static String getOracleLimitString(String sql, int firstResult, int maxResults) {

        sql = sql.trim();
        boolean isForUpdate = false;
        if (sql.toLowerCase().endsWith(" for update")) {
            sql = sql.substring(0, sql.length() - 11);
            isForUpdate = true;
        }

        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        if (firstResult >= 0 && maxResults >= 0) {
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        } else if (firstResult < 0 && maxResults >= 0) {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(sql);
        if (firstResult >= 0 && maxResults >= 0) {
            pagingSelect.append(" ) row_ where rownum <= ");
            pagingSelect.append(firstResult + maxResults);
            pagingSelect.append(") where rownum_ > ");
            pagingSelect.append(firstResult);
        } else if (firstResult < 0 && maxResults >= 0) {
            pagingSelect.append(" ) where rownum <= ");
            pagingSelect.append(maxResults);
        }

        if (isForUpdate) {
            pagingSelect.append(" for update");
        }

        return pagingSelect.toString();
    }

    //copy from MySQLDialect

    public static String getMySQLLimitString(String sql, int firstResult, int maxResults) {
        return new StringBuffer(sql.length() + 20)
                .append(sql)
                .append(" limit ")
                .append(firstResult)
                .append(",")
                .append(maxResults)
                .toString();
    }

    public static String getMySQLLimitString(String sql, int maxResults) {
        return new StringBuffer(sql.length() + 20)
                .append(sql)
                .append(" limit ")
                .append(maxResults)
                .toString();
    }

    /**
     * 获取一页查询结果
     * @param sql 查询SQL
     * @param page 翻页对象
     * @param conn Connection
     * @return 获取一页查询结果
     * @throws SQLException
     */
    public static List<Map> getResultList(String sql, BasePage page, Connection conn) throws SQLException {
        List<Map> result = new ArrayList<Map>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            if (StringUtils.isEmpty(sql) || (page == null) || (conn == null) || conn.isClosed()
                    || (sql.indexOf("?") > -1)) {
                throw new SQLException("不能计算结果");
            }

            if (page.isFetchTotal()) {
                calcCount(page, sql, conn);
            }

            sql = getOracleLimitString(sql, page);

            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int columnCount = rs.getMetaData().getColumnCount();
                Map<String, Object> row = new HashMap<String, Object>(columnCount);
                for (int j = 1; j <= columnCount; j++) {
                    String name = rs.getMetaData().getColumnName(j);
                    row.put(name, getResultSetValue(rs, j));
                }
                result.add(row);
            }
        } catch (SQLException e) {
            throw (new SQLException(e.getMessage()));
        } finally {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (st != null) {
                st.close();
                st = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        }
        return result;
    }

    /**
     * 获取分页SQL(支持ORACLE)
     * @param sql SQL语句
     * @param page 分页对象
     */
    public static String getOracleLimitString(String sql, BasePage page) {
        StringBuffer pageSQL = new StringBuffer(sql.length() + 200);
        pageSQL.append("select * from ( select row_.*, rownum rownum_ from (");
        pageSQL.append(sql);
        pageSQL.append(") row_ ) where rownum_ < = ").append(page.getRecordTo(page));
        pageSQL.append(" and rownum_ > ").append(page.getRecordStart(page) - 1);
        return pageSQL.toString();
    }


    /**
     * 记录总记录数
     * @param page 翻页条
     * @param sql 查询语句
     * @param conn 有效连接
     * @throws SQLException
     */
    public static void calcCount(BasePage page, String sql, Connection conn) throws SQLException {

        if (StringUtils.isEmpty(sql) || (page == null) || (conn == null) || conn.isClosed()
                || (sql.indexOf("?") > -1)) {
            throw new SQLException("不能计算总记录数");
        }
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement("select count(*) from (" + sql + ")");
        try {
            rs = st.executeQuery();

            if (rs.next()) {
                page.setRecordCount(rs.getInt(1));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * 获得记录总记录数
     * @param sql
     * @param conn
     * @return
     * @throws SQLException
     */
    public static int getResultCount(String sql, Connection conn) throws SQLException {
        int count = 0;
        PreparedStatement st = null;
        ResultSet rs = null;
        st = conn.prepareStatement("select count(*) from (" + sql + ")");
        try {
            rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }


    public void batchWriteAuthServiceToLocalFile(String sql, Connection conn, String localPath, String fileName) {

        int fetchSize = 500;
        int batchWriteCount = 2;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            stmt.setFetchSize(fetchSize);
            stmt.setEscapeProcessing(false);
            stmt.setFetchDirection(ResultSet.FETCH_FORWARD);
            rs = stmt.executeQuery(sql);


            int index = 1;
            StringBuffer syncDataBuffer = new StringBuffer();
            while (rs.next()) {
                //获取待同步订购关系
                AuthService authService = convertAuthServiceByResultSet(rs);
                syncDataBuffer.append(authService.getId() + "---" + authService.getProductname() + "\n");
                //每拼装batchWriteCount条数据后进行写文件操作
                if (index % batchWriteCount == 0) {
                    FileUtil fileUtil = new FileUtil();
                    fileUtil.writeSyncDataToFileFoot("D:\\home\\ifs", "syncData.DAT", syncDataBuffer.toString());
                    syncDataBuffer.delete(0, syncDataBuffer.length());
//                    syncDataBuffer.setLength(0)
//                    syncDataBuffer = new StringBuffer()
                }
                index++;
            }
            //当记录数小于批量提交记录数时，进行同步数据提交
            if (syncDataBuffer.length() > 0) {
                FileUtil fileUtil = new FileUtil();
                fileUtil.writeSyncDataToFileFoot("D:\\home\\ifs", "syncData.DAT", syncDataBuffer.toString());
                syncDataBuffer.delete(0, syncDataBuffer.length());
            }


        } catch (Exception e) {
            System.out.println("error: " + e);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException sqle) {
                System.out.println("SQLState: " + sqle.getSQLState());
                System.out.println("SQLErrorCode: 错误代码" + sqle.getErrorCode());
                System.out.println("SQLErrorMessage:错误情况的字符串 " + sqle.toString());
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException sqle1) {
                System.out.println("SQLState: " + sqle1.getSQLState());
                System.out.println("SQLErrorCode: 错误代码" + sqle1.getErrorCode());
                System.out.println("SQLErrorMessage:错误情况的字符串 " + sqle1.toString());
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle2) {
                System.out.println(sqle2.toString());
                System.out.println(sqle2.getSQLState());
                System.out.println(sqle2.getErrorCode());
            }

        }

    }

    /**
     * 通过结果集转换AuthService对象
     * @param rs
     * @return
     * @throws Exception
     */
    public AuthService convertAuthServiceByResultSet(ResultSet rs) throws Exception {

        AuthService authService = new AuthService();
        if (rs.getTimestamp("ACTIVEPAUSEDATE") != null)
            authService.setActivepausedate(new java.util.Date(rs.getTimestamp("ACTIVEPAUSEDATE").getTime()));
        authService.setAreaid(rs.getString("AREAID"));
        if (rs.getTimestamp("AUDITDATE") != null)
            authService.setAuditdate(new java.util.Date(rs.getTimestamp("AUDITDATE").getTime()));
        authService.setAuditorid(rs.getString("AUDITORID"));
        authService.setAuditremark(rs.getString("AUDITREMARK"));
        authService.setBillid(rs.getString("BILLID"));
        authService.setBillingCyclecount(rs.getLong("BILLINGCYCLECOUNT"));
        authService.setBillTypeId(rs.getString("BILLTYPEID"));
        authService.setBusinessType(rs.getString("BUSINESS_TYPE"));
        if (rs.getTimestamp("CLOSEDATE") != null)
            authService.setClosedate(new java.util.Date(rs.getTimestamp("CLOSEDATE").getTime()));
        authService.setCommodityId(rs.getString("COMMODITYID"));
        if (rs.getTimestamp("CREATEDATE") != null)
            authService.setCreatedate(new java.util.Date(rs.getTimestamp("CREATEDATE").getTime()));
        authService.setDeviceId(rs.getString("DEVICEID"));
        authService.setDivideid(rs.getString("DIVIDEID"));
        if (rs.getTimestamp("ENDDATE") != null)
            authService.setEnddate(new java.util.Date(rs.getTimestamp("ENDDATE").getTime()));
        authService.setFormerstatus(rs.getString("FORMERSTATUS"));
        if (rs.getTimestamp("FREEENDDATE") != null)
            authService.setFreeenddate(new java.util.Date(rs.getTimestamp("FREEENDDATE").getTime()));
        if (rs.getTimestamp("FREESTARTDATE") != null)
            authService.setFreestartdate(new java.util.Date(rs.getTimestamp("FREESTARTDATE").getTime()));
        authService.setId(rs.getLong("SERVICEID"));
        authService.setIsBankPay(rs.getString("ISBANKPAY"));
        authService.setIsFixedPrice(rs.getString("ISFIXEDPRICE"));
        authService.setIsGenBankPayRecord(rs.getString("ISGENBANKPAYRECORD"));
        authService.setIsgroup(rs.getString("ISGROUP"));
        authService.setIspass(rs.getString("ISPASS"));
        authService.setIsrenew(rs.getString("ISRENEW"));
        authService.setIssendcmdahead(rs.getString("ISSENDCMDAHEAD"));
        authService.setNetid(rs.getString("NETID"));
        if (rs.getTimestamp("OPERATEDATE") != null)
            authService.setOperatedate(new java.util.Date(rs.getTimestamp("OPERATEDATE").getTime()));
        authService.setOperatorid(rs.getString("OPERATORID"));
        authService.setOrderUserId(rs.getLong("ORDERUSERID"));
        if (rs.getTimestamp("PAUSEDATE") != null)
            authService.setPausedate(new java.util.Date(rs.getTimestamp("PAUSEDATE").getTime()));
        authService.setPaymode(rs.getString("PAYMODE"));
        authService.setPricefix(rs.getLong("PRICEFIX"));
        authService.setProductid(rs.getString("PRODUCTID"));
        authService.setProductname(rs.getString("PRODUCTNAME"));
        if (rs.getTimestamp("REALENDDATE") != null)
            authService.setRealenddate(new java.util.Date(rs.getTimestamp("REALENDDATE").getTime()));
        if (rs.getTimestamp("REALSTARTDATE") != null)
            authService.setRealstartdate(new java.util.Date(rs.getTimestamp("REALSTARTDATE").getTime()));
        authService.setRefpricefix(rs.getLong("REFPRICEFIX"));
        authService.setRemark(rs.getString("REMARK"));
        authService.setResourceModelId(rs.getString("RESOURCEMODELID"));
        if (rs.getTimestamp("RESUMEDATE") != null)
            authService.setResumedate(new java.util.Date(rs.getTimestamp("RESUMEDATE").getTime()));
        if (rs.getTimestamp("STARTDATE") != null)
            authService.setStartdate(new java.util.Date(rs.getTimestamp("STARTDATE").getTime()));
        authService.setStatus(rs.getString("STATUS"));
        authService.setStockid(rs.getString("STOCKID"));
        authService.setTwicePrice(rs.getLong("TWICE_PRICE"));
        if (rs.getTimestamp("UPDATEDATE") != null)
            authService.setUpdatedate(new java.util.Date(rs.getTimestamp("UPDATEDATE").getTime()));
        authService.setUserid(rs.getLong("USERID"));
        return authService;
    }


    /**
     * 分页查询Domain对象
     */
    private static void getDomainForCutPage() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url = "jdbc:oracle:thin:@192.168.50.115:1521:smsdb";
        String username = "ibossjc";
        String password = "ibossjc";

        try {

            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(url, username, password);
            String sql = "select * from AUTH_SERVICE a where a.status = '" + AuthService.STATUS_RUNNING + "' ";

            int count = getResultCount(sql, conn);
            BasePage basePage = new BasePage();
            basePage.setPageSize(2);
            basePage.setFetchTotal(false);
            basePage.setRecordCount(count);

            for (int index = 1; index <= count; index++) {
                basePage.setPageNo(index);

                conn = DriverManager.getConnection(url, username, password);
                List<Map> resultList = getResultList(sql, basePage, conn);
                for (Map resultMap : resultList) {
                    Map<String, Object> map = (Map<String, Object>) resultMap;
                    Object value = map.get("SERVICEID");
                    System.out.println("ID:" + value);
                }


            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            }
            catch (SQLException sqle2) {
                System.out.println(sqle2.toString());
                System.out.println(sqle2.getSQLState());
                System.out.println(sqle2.getErrorCode());
            }

        }
    }


    public static void main(String[] args) {

        Connection conn = null;
        String url = "jdbc:oracle:thin:@192.168.50.115:1521:smsdb";
        String username = "ibossjc";
        String password = "ibossjc";
        String sql = "select * from AUTH_SERVICE a where a.status = '" + AuthService.STATUS_RUNNING + "' ";

        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(url, username, password);
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.batchWriteAuthServiceToLocalFile(sql, conn, "D:\\home\\ifs", "syncData.DAT");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }


    }


}
