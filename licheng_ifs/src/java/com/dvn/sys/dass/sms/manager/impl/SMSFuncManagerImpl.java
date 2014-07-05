package com.dvn.sys.dass.sms.manager.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import com.dvn.sys.dass.sms.common.tool.FunctionResult;
import com.dvn.sys.dass.sms.common.tool.ResultDefine;
import com.dvn.sys.dass.sms.manager.SMSFuncManager;

public class SMSFuncManagerImpl implements SMSFuncManager {

	private static final Logger logger = Logger.getLogger(SMSFuncManagerImpl.class);

	private String url;

	private String driver;
	
	private String userName;
	
	private String password;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void main(String[] srg) {

//		callFuncPasswordVerify("DASS-UMS-01", "066666",
//				"UMS200908141705011230666666",
//				"test_callcallFuncPasswordVerify", "01234567");
//
//		callFuncPasswordModify("DASS-UMS-01", "066666",
//				"UMS200908141705011230666666",
//				"test_callcallFuncPasswordVerify", "01234567", "12345678");
//
//		callFuncPasswordVerify("DASS-UMS-01", "066666",
//				"UMS200908141705011230666666",
//				"test_callcallFuncPasswordVerify", "01234567");
//
//		callFuncPasswordVerify("DASS-UMS-01", "066666",
//				"UMS200908141705011230666666",
//				"test_callcallFuncPasswordVerify", "12345678");
//
//		callFuncBalanceQuery("DASS-UMS-01", "066666",
//				"UMS200908141705011230666666", "test_callFuncBalanceQuery");
//		 callFuncRecharge("DASS-UMS-01", "066666",
//		 "UMS200908141705011230666666", "test_callFuncRecharge", 1300,
//		 "REAL", "CASH");
//		 callFuncBalanceQuery("DASS-UMS-01", "066666",
//		 "UMS200908141705011230666666", "test_callFuncBalanceQuery");
		// callFuncRecharge("DASS-UMS-01", "066666",
		// "UMS200908141705011230666666", "test_callFuncRecharge", 2700,
		// "VIRTUAL", "BANK");
		// callFuncBalanceQuery("DASS-UMS-01", "066666",
		// "UMS200908141705011230666666", "test_callFuncBalanceQuery");
//		 callFuncDeduct("DASS-UMS-01", "066666",
//		 "UMS200908141705011230666666",
//		 "test_callFuncRecharge", 200, "REAL", "productType",
//		 "productID", "assetID", "businessID", "assetName",
//		 "businessName");
		// callFuncBalanceQuery("DASS-UMS-01", "066666",
		// "UMS200908141705011230666666", "test_callFuncBalanceQuery");
		// callFuncDeduct("DASS-UMS-01", "066666",
		// "UMS200908141705011230666666",
		// "test_callFuncRecharge", 500, "VIRTUAL", "productType",
		// "productID", "assetID", "businessID", "assetName",
		// "businessName");
		// callFuncBalanceQuery("DASS-UMS-01", "066666",
		// "UMS200908141705011230666666", "test_callFuncBalanceQuery");
	}

	public FunctionResult callFuncPasswordVerify(String gatewayID,
			String uuid, String transactionID, String remark, String password) {
		String resultCode = "";
		Connection conn = null;
		try {
			conn = getConnection();
		} catch (Exception e) {
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		}
		CallableStatement cs;
		try {
			logger.info("-----entering function fuc_pwverify-----");

			// fuc_pwverify(
			// uu_id IN VARCHAR2,
			// pwd IN VARCHAR2,
			// tr_id IN VARCHAR2
			// ) return varchar2

			logger.info("-->fuc_pwverify(" + uuid + "," + password + ","
					+ transactionID + ")");

			cs = conn.prepareCall("{? = call fuc_pwverify(?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, uuid);
			cs.setString(3, password);
			cs.setString(4, transactionID);
			cs.execute();

			resultCode = cs.getString(1);

			logger.info("-->returnCode is: " + resultCode);
		} catch (Exception e) {
			logger.error("-->Error happend while execute fuc_pwverify.");
			logger.error(e.getMessage());
//			e.printStackTrace();
			logger.error(e.getCause());
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		} finally {
			logger.info("=====finished function fuc_pwverify=====");
			// if (conn != null) {
			// conn.close();
			// }
		}

		FunctionResult funcResult = new FunctionResult();
		funcResult.setResultCode(resultCode);
		funcResult.setResultDescription("DEFAULT");

		return funcResult;
	}

	public FunctionResult callFuncPasswordModify(String gatewayID,
			String uuid, String transactionID, String remark,
			String oldPassword, String newPassword) {
		String resultCode = "";

		Connection conn = null;
		try {
			conn = getConnection();
		} catch (Exception e) {
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		}
		CallableStatement cs;
		try {
			logger.info("-----entering function fuc_pwmodify-----");

			// fuc_pwmodify(
			// uu_id IN VARCHAR2,
			// old_pwd IN VARCHAR2,
			// new_pwd IN VARCHAR2,
			// tr_id IN VARCHAR2
			// ) return varchar2

			logger.info("-->fuc_pwmodify(" + uuid + "," + oldPassword
					+ "," + newPassword + "," + transactionID + ")");

			cs = conn.prepareCall("{? = call fuc_pwmodify(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, uuid);
			cs.setString(3, oldPassword);
			cs.setString(4, newPassword);
			cs.setString(5, transactionID);
			cs.execute();

			resultCode = cs.getString(1);

			logger.info("-->returnCode is: " + resultCode);
		} catch (Exception e) {
			logger.error("-->Error happend while execute fuc_pwmodify.");
			logger.error(e.getMessage());
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		} finally {
			logger.info("=====finished function fuc_pwmodify=====");
			// if (conn != null) {
			// conn.close();
			// }
		}

		FunctionResult funcResult = new FunctionResult();
		funcResult.setResultCode(resultCode);
		funcResult.setResultDescription("DEFAULT");

		return funcResult;
	}

	public FunctionResult callFuncRecharge(String gatewayID,
			String uuid, String transactionID, String remark, int chargeAmount,
			String accountType, String modeID) {
		String resultCode = "";
		String accountValue = "";

		Connection conn = null;
		try {
			conn = getConnection();
		} catch (Exception e) {
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		}
		CallableStatement cs;
		try {
			logger.info("-----entering function fuc_recharge-----");

			// fuc_recharge(
			// gw_id IN VARCHAR2,
			// uu_id IN VARCHAR2,
			// charge_amount IN NUMBER,
			// tr_id IN VARCHAR2,
			// account_type IN VARCHAR2,
			// mode_id IN VARCHAR2,
			// remark IN VARCHAR2,
			// account_value OUT VARCHAR2
			// )return varchar2

			logger.info("-->fuc_recharge(" + gatewayID + "," + uuid
					+ "," + chargeAmount + "," + transactionID + ","
					+ accountType + "," + modeID + "," + remark
					+ ", OutputAccountValue)");

			cs = conn.prepareCall("{? = call fuc_recharge(?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, gatewayID);
			cs.setString(3, uuid);
			cs.setInt(4, chargeAmount);
			cs.setString(5, transactionID);
			cs.setString(6, accountType);
			cs.setString(7, modeID);
			cs.setString(8, remark);
			cs.registerOutParameter(9, Types.VARCHAR);
			cs.execute();

			resultCode = cs.getString(1);
			accountValue = cs.getString(9);

			logger.info("-->returnCode is: " + resultCode
					+ ", accountValue is: " + accountValue);
		} catch (Exception e) {
			logger.error("-->Error happend while execute fuc_recharge.");
			logger.error(e.getMessage());
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		} finally {
			logger.info("=====finished function fuc_recharge=====");
			// if (conn != null) {
			// conn.close();
			// }
		}

		FunctionResult funcResult = new FunctionResult();
		funcResult.setResultCode(resultCode);
		funcResult.setResultDescription("DEFAULT");
		funcResult.setAccountValue(accountValue);

		return funcResult;
	}

	public FunctionResult callFuncDeduct(String gatewayID, String uuid,
			String transactionID, String remark, int billAmount,
			String accountType, String productType, String productID,
			String assetID, String businessID, String assetName,
			String businessName) {
		String resultCode = "";
		String accountValue = "";

		Connection conn = null;

		try {
			conn = getConnection();
		} catch (Exception e) {
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		}
		CallableStatement cs;
		try {
			logger.info("-----entering function fuc_deduct-----");

			// fuc_deduct(
			// gw_id IN VARCHAR2,
			// uu_id IN VARCHAR2,
			// bill_amount IN NUMBER,
			// tr_id IN VARCHAR2,
			// account_type IN VARCHAR2,
			// product_type IN VARCHAR2,
			// product_id IN VARCHAR2,
			// asset_id IN VARCHAR2,
			// business_id IN VARCHAR2,
			// asset_name IN VARCHAR2,
			// business_name IN VARCHAR2,
			// remark IN VARCHAR2,
			// account_value OUT VARCHAR2
			// )return varchar2

			logger.info("-->fuc_deduct(" + gatewayID + "," + uuid + ","
					+ billAmount + "," + transactionID + "," + accountType
					+ "," + productType + "," + productID + "," + assetID + ","
					+ businessID + "," + assetName + "," + businessName + ","
					+ remark + ", OutputAccountValue)");

			cs = conn
					.prepareCall("{? = call fuc_deduct(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, gatewayID);
			cs.setString(3, uuid);
			cs.setInt(4, billAmount);
			cs.setString(5, transactionID);
			cs.setString(6, accountType);
			cs.setString(7, productType);
			cs.setString(8, productID);
			cs.setString(9, assetID);
			cs.setString(10, businessID);
			cs.setString(11, assetName);
			cs.setString(12, businessName);
			cs.setString(13, remark);
			cs.registerOutParameter(14, Types.VARCHAR);
			cs.execute();

			resultCode = cs.getString(1);
			accountValue = cs.getString(14);

			logger.info("-->returnCode is: " + resultCode
					+ ", accountValue is: " + accountValue);
		} catch (Exception e) {
			logger.error("-->Error happend while execute fuc_recharge.");
			logger.error(e.getMessage());
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		} finally {
			logger.info("=====finished function fuc_deduct=====");
			// if (conn != null) {
			// conn.close();
			// }
		}

		FunctionResult funcResult = new FunctionResult();
		funcResult.setResultCode(resultCode);
		funcResult.setResultDescription("DEFAULT");
		funcResult.setAccountValue(accountValue);

		return funcResult;

	}

	public FunctionResult callFuncBalanceQuery(String gatewayID,
			String uuid, String transactionID, String remark) {

		String resultCode = "";
		String accountValue = "";

		Connection conn = null;
		try {
			conn = getConnection();
		} catch (Exception e) {
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		}
		CallableStatement cs;
		try {
			logger.info("-----entering fucntion fuc_balanceQuery-----");

			// FUC_BALANCEQUERY(
			// GW_ID IN VARCHAR2,
			// UU_ID IN VARCHAR2,
			// TR_ID IN VARCHAR2,
			// REMARK IN VARCHAR2,
			// ACCOUNT_VALUE OUT VARCHAR2
			// )RETURN VARCHAR2

			logger.info("-->fuc_balanceQuery(" + gatewayID + "," + uuid
					+ "," + transactionID + "," + remark
					+ ", OutputAccountValue)");

			cs = conn.prepareCall("{? = call fuc_balanceQuery(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, gatewayID);
			cs.setString(3, uuid);
			cs.setString(4, transactionID);
			cs.setString(5, remark);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();

			resultCode = cs.getString(1);
			accountValue = cs.getString(6);

			logger.info("-->returnCode is: " + resultCode
					+ ", accountValue is: " + accountValue);
		} catch (Exception e) {
			logger.error("-->Error happend while execute fuc_recharge.");
			logger.error(e.getMessage());
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		} finally {
			logger.info("=====finished fucntion fuc_balanceQuery=====");
		}

		FunctionResult funcResult = new FunctionResult();
		funcResult.setResultCode(resultCode);
		funcResult.setResultDescription("DEFAULT");
		funcResult.setAccountValue(accountValue);

		return funcResult;

	}

	public Connection getConnection() throws SQLException,
			java.lang.ClassNotFoundException {
//		String url = "jdbc:oracle:thin:@10.10.0.12:1521:dassorcl";
		Class.forName(driver);
//		String userName = "SMS";
//		String password = "SMS";
		Connection con = DriverManager.getConnection(url, userName, password);
		return con;
	}

	public FunctionResult callFuncBaseAccoBank(String gatewayID, String uuid,
			String transactionID, String chargeAccount, String remark) {
		// TODO Auto-generated method stub
		String resultCode = "";
		String accountValue = "";
		Connection conn = null;
		try {
			conn = getConnection();
		} catch (Exception e) {
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		}
		CallableStatement cs;
		try {
			logger.info("-----entering function fuc_paybasebybank-----");

			// fuc_paybasebybank(
			// uu_id IN VARCHAR2,
			// money IN VARCHAR2,
			// tr_id IN VARCHAR2
			// remark IN VARCHAR2
			// ) return varchar2

			logger.info("-->fuc_paybasebybank(" + gatewayID + "," + uuid
					+ "," + chargeAccount+ "," + transactionID + "," + remark
					+ ", OutputAccountValue)");

			cs = conn.prepareCall("{? = call fuc_paybasebybank(?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, gatewayID);
			cs.setString(3, uuid);
			cs.setString(4, chargeAccount);
			cs.setString(5, transactionID);
			cs.setString(6, remark);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.execute();

			resultCode = cs.getString(1);
			accountValue = cs.getString(7);
			logger.info("-->returnCode is: " + resultCode
					+ ", accountValue is: " + accountValue);
		} catch (Exception e) {
			logger.error("-->Error happend while execute fuc_paybasebybank.");
			logger.error(e.getMessage());
			logger.error(e.getCause());
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		} finally {
			logger.info("=====finished function fuc_paybasebybank=====");
		}

		FunctionResult funcResult = new FunctionResult();
		funcResult.setResultCode(resultCode);
		funcResult.setResultDescription("DEFAULT");
		funcResult.setAccountValue(accountValue);
		return funcResult;
	}

	public FunctionResult callFuncBaseQuery(String gatewayID, String uuid,
			String transactionID, String remark) {
		// TODO Auto-generated method stub
		String resultCode = "";
		String accountValue = "";
		Connection conn = null;
		try {
			conn = getConnection();
		} catch (Exception e) {
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		}
		CallableStatement cs;
		try {
			logger.info("-----entering function fuc_baseQuery-----");

			// fuc_baseQuery(
			// uu_id IN VARCHAR2,
			// pwd IN VARCHAR2,
			// tr_id IN VARCHAR2
			// ) return varchar2

			logger.info("-->fuc_baseQuery(" + gatewayID + "," + uuid
					+ "," + transactionID + "," + remark
					+ ", OutputAccountValue)");

			cs = conn.prepareCall("{? = call fuc_baseQuery(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, gatewayID);
			cs.setString(3, uuid);
			cs.setString(4, transactionID);
			cs.setString(5, remark);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();

			resultCode = cs.getString(1);
			accountValue = cs.getString(6);
			logger.info("-->returnCode is: " + resultCode
					+ ", accountValue is: " + accountValue);
		} catch (Exception e) {
			logger.error("-->Error happend while execute fuc_baseQuery.");
			logger.error(e.getMessage());
//			e.printStackTrace();
			logger.error(e.getCause());
			resultCode = ResultDefine.OPERATE_FAIL_UNI;
		} finally {
			logger.info("=====finished function fuc_baseQuery=====");
			// if (conn != null) {
			// conn.close();
			// }
		}

		FunctionResult funcResult = new FunctionResult();
		funcResult.setResultCode(resultCode);
		funcResult.setResultDescription("DEFAULT");
		funcResult.setAccountValue(accountValue);
		return funcResult;
	}

}
