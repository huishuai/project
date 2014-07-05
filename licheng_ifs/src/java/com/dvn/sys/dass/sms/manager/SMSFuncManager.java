/**
 * Create Time: 2009-2-9
 * Package Name: com.dvn.sys.dass.ucs.service
 * File Name: IBalanceManager.java
 */
package com.dvn.sys.dass.sms.manager;

import com.dvn.sys.dass.sms.common.tool.FunctionResult;


/**
 * @author wwz
 */
public interface SMSFuncManager {
	public FunctionResult callFuncPasswordVerify(String gatewayID,
			String uuid, String transactionID, String remark, String password);
	
	public FunctionResult callFuncPasswordModify(String gatewayID,
			String uuid, String transactionID, String remark,
			String oldPassword, String newPassword);
	
	public FunctionResult callFuncRecharge(String gatewayID,
			String uuid, String transactionID, String remark, int chargeAmount,
			String accountType, String modeID);
	
	public FunctionResult callFuncDeduct(String gatewayID, String uuid,
			String transactionID, String remark, int billAmount,
			String accountType, String productType, String productID,
			String assetID, String businessID, String assetName,
			String businessName);
	
	public FunctionResult callFuncBalanceQuery(String gatewayID,
			String uuid, String transactionID, String remark);
	
	public FunctionResult callFuncBaseQuery(String gatewayID,
			String uuid, String transactionID, String remark);
	
	public FunctionResult callFuncBaseAccoBank(String gatewayID,
			String uuid, String transactionID,String chargeAccount, String remark);
}
