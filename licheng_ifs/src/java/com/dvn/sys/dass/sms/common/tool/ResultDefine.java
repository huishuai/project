package com.dvn.sys.dass.sms.common.tool;

public final class ResultDefine {
	public static final String SUCCESS_UNI = "0";
	public static final String DESC_SUCCESS_UNI = "Operate success!";

	public static final String ACCOUNT_NOT_ENOUGH_UNI = "1";
	public static final String DESC_ACCOUNT_NOT_ENOUGH_UNI = "User account is not enough!";

	public static final String UUID_NOT_FOUND_UNI = "-100";
	public static final String DESC_UUID_NOT_FOUND_UNI = "UUID is not found!";

	public static final String GROUP_NOT_CHARGE_UNI = "-103";
	public static final String DESC_GROUP_NOT_CHARGE_UNI = "Group charge is not allow!";

	public static final String CARD_PASSWORD_INVALID_UNI = "-200";
	public static final String DESC_CARD_PASSWORD_INVALID_UNI = "Card password invalid!";

	public static final String CARD_CANCELED_UNI = "-201";
	public static final String DESC_CARD_CANCELED_UNI = "The recharge card have cancelled!";

	public static final String OPERATE_FAIL_UNI = "-300";
	public static final String DESC_OPERATE_FAIL_UNI = "Operate fail!";

	public static final String SYSTEM_BUSY_UNI = "-400";
	public static final String DESC_SYSTEM_BUSY_UNI = "System is busy now!";

	public static final String SERVICE_STOPPING_UNI = "-401";
	public static final String DESC_SERVICE_STOPPING_UNI = "Service is stopping now!";

	public static final String IP_INVALID_UNI = "-410";
	public static final String DESC_IP_INVALID_UNI = "IP Access forbidden!";

	public static final String REQUEST_XML_FORMAT_ERROR_UNI = "-500";
	public static final String DESC_REQUEST_XML_FORMAT_ERROR_UNI = "Request format error!";

	public static final String SMS_NO_RESPONDED_UNI = "-600";
	public static final String DESC_SMS_NO_RESPONDED_UNI = "SMS server is not responded!";

	public static final String getResultDescription(String resultCode) {
		if (resultCode.equals(SUCCESS_UNI)) {
			return DESC_SUCCESS_UNI;
		} else if (resultCode.equals(ACCOUNT_NOT_ENOUGH_UNI)) {
			return DESC_ACCOUNT_NOT_ENOUGH_UNI;
		} else if (resultCode.equals(UUID_NOT_FOUND_UNI)) {
			return DESC_UUID_NOT_FOUND_UNI;
		} else if (resultCode.equals(GROUP_NOT_CHARGE_UNI)) {
			return DESC_GROUP_NOT_CHARGE_UNI;
		} else if (resultCode.equals(CARD_PASSWORD_INVALID_UNI)) {
			return DESC_CARD_PASSWORD_INVALID_UNI;
		} else if (resultCode.equals(CARD_CANCELED_UNI)) {
			return DESC_CARD_CANCELED_UNI;
		} else if (resultCode.equals(OPERATE_FAIL_UNI)) {
			return DESC_OPERATE_FAIL_UNI;
		} else if (resultCode.equals(SYSTEM_BUSY_UNI)) {
			return DESC_SYSTEM_BUSY_UNI;
		} else if (resultCode.equals(REQUEST_XML_FORMAT_ERROR_UNI)) {
			return DESC_REQUEST_XML_FORMAT_ERROR_UNI;
		} else if (resultCode.equals(SMS_NO_RESPONDED_UNI)) {
			return DESC_SMS_NO_RESPONDED_UNI;
		} else if (resultCode.equals(IP_INVALID_UNI)) {
			return DESC_IP_INVALID_UNI;
		} else {
			return "Unknown error code!";
		}
	}
	
	public static final String getResultDescriptionKey(String resultCode) {
		if (resultCode.equals(SUCCESS_UNI)) {
			return "DESC_SUCCESS_UNI";
		} else if (resultCode.equals(ACCOUNT_NOT_ENOUGH_UNI)) {
			return "DESC_ACCOUNT_NOT_ENOUGH_UNI";
		} else if (resultCode.equals(UUID_NOT_FOUND_UNI)) {
			return "DESC_UUID_NOT_FOUND_UNI";
		} else if (resultCode.equals(GROUP_NOT_CHARGE_UNI)) {
			return "DESC_GROUP_NOT_CHARGE_UNI";
		} else if (resultCode.equals(CARD_PASSWORD_INVALID_UNI)) {
			return "DESC_CARD_PASSWORD_INVALID_UNI";
		} else if (resultCode.equals(CARD_CANCELED_UNI)) {
			return "DESC_CARD_CANCELED_UNI";
		} else if (resultCode.equals(OPERATE_FAIL_UNI)) {
			return "DESC_OPERATE_FAIL_UNI";
		} else if (resultCode.equals(SYSTEM_BUSY_UNI)) {
			return "DESC_SYSTEM_BUSY_UNI";
		} else if (resultCode.equals(REQUEST_XML_FORMAT_ERROR_UNI)) {
			return "DESC_REQUEST_XML_FORMAT_ERROR_UNI";
		} else if (resultCode.equals(SMS_NO_RESPONDED_UNI)) {
			return "DESC_SMS_NO_RESPONDED_UNI";
		} else if (resultCode.equals(IP_INVALID_UNI)) {
			return "DESC_IP_INVALID_UNI";
		} else {
			return "Unknown error code!";
		}
	}
	
	public static final String transResultCode(String resultCode){
		if("100".equals(resultCode)|| "101".equals(resultCode)){
			return SUCCESS_UNI;
		}else if("201".equals(resultCode) || "202".equals(resultCode)){
			return UUID_NOT_FOUND_UNI;
		}else if("301".equals(resultCode) || "302".equals(resultCode) || "303".equals(resultCode)){
			return ACCOUNT_NOT_ENOUGH_UNI;
		}else if("900".equals(resultCode) || "901".equals(resultCode)){
			return SYSTEM_BUSY_UNI;
		}else{
			return OPERATE_FAIL_UNI;
		}
	}
}
