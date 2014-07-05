package com.bmsp.message;

import com.daocren.server.communication.sync.SyncMessage;

/**
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-12
 * Time: 15:07:02
 * 消息体
 */
public class BodyMessage implements SyncMessage {

    private String sessionId; //序列号
    private String type;    //请求类型
    private String devNo;  //机顶盒号码
    private String productId;  //产品包ID
    private String orderType; //订购类型 1 – 当月生效 2-下月生效
    private String operType;  //操作类型 1-订购 2-退订
    private String servId; //针对PORTAL系统是由BOSS系统传输到PORTAL系统的用户标识

    private String startTime;  //订购生效时间
    private String endTime;    //订购失效时间
    private String errCode;   //错误代码 定退的错误代码 待定
    private String transType; //交易类型
    private String identityType; //ID类型 1-为机顶盒号码 2-业务号码（IC号码）3-TVN号码
    private int billCycleCount = 1; //订购周期

    private String assetId; //影片ID
    private String spId;   //提供商编号
    private String result;  //鉴权结果,按次点播错误代码  0000正常 0001 余额不足,无权定购

    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
    private static final String SESSION_ID_L = "<session_id>";
    private static final String SESSION_IN_R = "</session_id>";
    private static final String TYPE_L = "<type>";
    private static final String TYPE_R = "</type>";
    private static final String PROD_ID_L = "<prod_id>";
    private static final String PROD_ID_R = "</prod_id>";
    private static final String DEV_NO_L = "<dev_no>";
    private static final String DEV_NO_R = "</dev_no>";
    private static final String ORDER_TYPE_L = "<order_type>";
    private static final String ORDER_TYPE_R = "</order_type>";
    private static final String RWSPONSE_L = "<response>";
    private static final String RWSPONSE_R = "</response>";
    private static final String ERR_CODE_L = "<err_code>";
    private static final String ERR_CODE_R = "</err_code>";
    private static final String TRANS_TYPE_L = "<trans_type>";
    private static final String TRANS_TYPE_R = "</trans_type>";
    private static final String START_DATE_L = "<start_date>";
    private static final String START_DATE_R = "</start_date>";
    private static final String START_TIME_L = "<start_time>";
    private static final String START_TIME_R = "</start_time>";
    private static final String END_DATE_L = "<end_date>";
    private static final String END_DATE_R = "</end_date>";
    private static final String ASSET_ID_L = "<asset_id>";
    private static final String ASSET_ID_R = "</asset_id>";
    private static final String SP_ID_L = "<sp_id>";
    private static final String SP_ID_R = "</sp_id>";
    private static final String RESULT_L = "<result>";
    private static final String RESULT_R = "</result>";
    private static final String IDENTITY_L = "<identity_type>";
    private static final String IDENTITY_R = "</identity_type>";
    private static final String SERVID_L = "<serv_id>";
    private static final String SERVID_R = "</serv_id>";
    private static final String OPERTYPE_L = "<oper_type>";
    private static final String OPERTYPE_R = "</oper_type>";
    private static final String BILLCYCLECOUNT_L = "</billCycleCount>";
    private static final String BILLCYCLECOUNT_R = "</billCycleCount>";

    public String getOperType() {
        if(operType==null) return "";
        else return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getServId() {
        if(servId==null) return "";
        else return servId;
    }

    public void setServId(String servId) {
        this.servId = servId;
    }

    public String getIdentityType() {
        if(identityType==null) return "";
        else return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getOrderXml(){
       StringBuffer buffer = new StringBuffer();
        buffer.append(XML_HEADER);
        buffer.append(SESSION_ID_L);
        buffer.append(sessionId);
        buffer.append(SESSION_IN_R);
        buffer.append(RESULT_L);
        buffer.append(IDENTITY_L);
        buffer.append(identityType);
        buffer.append(IDENTITY_R);
        buffer.append(DEV_NO_L);
        buffer.append(devNo);
        buffer.append(DEV_NO_R);
        buffer.append(OPERTYPE_L);
        buffer.append(operType);
        buffer.append(OPERTYPE_R);
        buffer.append(PROD_ID_L);
        buffer.append(productId);
        buffer.append(PROD_ID_R);
        buffer.append(ORDER_TYPE_L);
        buffer.append(orderType);
        buffer.append(ORDER_TYPE_R);
        buffer.append(RESULT_R);
        return buffer.toString();
    }

    public String getOrderWithCycleCountXml(){
       StringBuffer buffer = new StringBuffer();
        buffer.append(XML_HEADER);
        buffer.append(SESSION_ID_L);
        buffer.append(sessionId);
        buffer.append(SESSION_IN_R);
        buffer.append(RESULT_L);
        buffer.append(IDENTITY_L);
        buffer.append(identityType);
        buffer.append(IDENTITY_R);
        buffer.append(DEV_NO_L);
        buffer.append(devNo);
        buffer.append(DEV_NO_R);
        buffer.append(OPERTYPE_L);
        buffer.append(operType);
        buffer.append(OPERTYPE_R);
        buffer.append(PROD_ID_L);
        buffer.append(productId);
        buffer.append(PROD_ID_R);
        buffer.append(ORDER_TYPE_L);
        buffer.append(orderType);
        buffer.append(ORDER_TYPE_R);
        buffer.append(BILLCYCLECOUNT_L);
        buffer.append(billCycleCount);
        buffer.append(BILLCYCLECOUNT_R);
        buffer.append(RESULT_R);
        return buffer.toString();
    }

    public String getOrderOnceXml() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(XML_HEADER);
        buffer.append(SESSION_ID_L);
        buffer.append(sessionId);
        buffer.append(SESSION_IN_R);
        buffer.append(TYPE_L);
        buffer.append(type);
        buffer.append(TYPE_R);
        buffer.append(PROD_ID_L);
        buffer.append(productId);
        buffer.append(PROD_ID_R);
        buffer.append(DEV_NO_L);
        buffer.append(devNo);
        buffer.append(DEV_NO_R);
        buffer.append(ORDER_TYPE_L);
        buffer.append(orderType);
        buffer.append(ORDER_TYPE_R);
        return buffer.toString();
    }

    public String getOrderResponseXml() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(XML_HEADER);
        buffer.append(SESSION_ID_L);
        buffer.append(sessionId);
        buffer.append(SESSION_IN_R);
        buffer.append(RWSPONSE_L);
        buffer.append(ERR_CODE_L);
        buffer.append(errCode);
        buffer.append(ERR_CODE_R);
        buffer.append(TRANS_TYPE_L);
        buffer.append(transType);
        buffer.append(TRANS_TYPE_R);
        buffer.append(DEV_NO_L);
        buffer.append(devNo);
        buffer.append(DEV_NO_R);
        buffer.append(PROD_ID_L);
        buffer.append(productId);
        buffer.append(PROD_ID_R);
        buffer.append(START_DATE_L);
        buffer.append(startTime);
        buffer.append(START_DATE_R);
        buffer.append(END_DATE_L);
        buffer.append(endTime);
        buffer.append(END_DATE_R);
        buffer.append(RWSPONSE_R);
        return buffer.toString();
    }

    public String getKeepLiveXml() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(XML_HEADER);
        buffer.append(SESSION_ID_L);
        buffer.append(sessionId);
        buffer.append(SESSION_IN_R);
        return buffer.toString();
    }

    public String getBillingXml() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(XML_HEADER);
        buffer.append(SESSION_ID_L);
        buffer.append(sessionId);
        buffer.append(SESSION_IN_R);
        buffer.append(TYPE_L);
        buffer.append(type);
        buffer.append(TYPE_R);
        buffer.append(PROD_ID_L);
        buffer.append(productId);
        buffer.append(PROD_ID_R);
        buffer.append(RESULT_L);
        buffer.append(result);
        buffer.append(RESULT_R);
        return buffer.toString();
    }

    public String getOrderEveryTimeXml() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(XML_HEADER);
        buffer.append(SESSION_ID_L);
        buffer.append(sessionId);
        buffer.append(SESSION_IN_R);
        buffer.append(TYPE_L);
        buffer.append(type);
        buffer.append(TYPE_R);
        buffer.append(ASSET_ID_L);
        buffer.append(assetId);
        buffer.append(ASSET_ID_R);
        buffer.append(SP_ID_L);
        buffer.append(spId);
        buffer.append(SP_ID_R);
        buffer.append(START_TIME_L);
        buffer.append(startTime);
        buffer.append(START_TIME_R);
        buffer.append(DEV_NO_L);
        buffer.append(devNo);
        buffer.append(DEV_NO_R);
        return buffer.toString();
    }

    public String getSessionId() {
        if (sessionId == null) return "";
        else return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getType() {
        if(type==null) return "";
        else return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevNo() {
        if(devNo==null) return "";
        else return devNo;
    }

    public void setDevNo(String devNo) {
        this.devNo = devNo;
    }

    public String getProductId() {
        if(productId==null) return "";
        else return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderType() {
        if(orderType==null) return "";
        else return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStartTime() {
        if(startTime==null) return "";
        else return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        if(endTime==null) return "";
        else return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getErrCode() {
        if(errCode==null) return "";
        else return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getTransType() {
       if(transType==null) return "";
        else  return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getAssetId() {
        if(assetId==null) return "";
        else return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getSpId() {
        if(spId==null) return "";
        else return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getResult() {
        if(result==null) return "";
        else return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSyncMessageId() {
         return getSessionId();
    }

    public int getBillCycleCount() {
         return billCycleCount;
    }

    public void setBillCycleCount(int billCycleCount) {
        this.billCycleCount = billCycleCount;
    }
}
