/**
 * Create Time: 2009-5-20
 * Package Name : com.dvn.dass.sys.dass.rgs.servlet
 * File Name: RegisterServlet.java
 */
package com.dvn.sys.dass.sms.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.chaos.jaxb.base.servlet.BaseServlet;
import cn.chaos.jaxb.base.tool.LogPrinter;
import cn.chaos.jaxb.base.tool.Tool;

import com.dvn.sys.dass.sms.common.tool.FunctionResult;
import com.dvn.sys.dass.sms.common.tool.ResultDefine;
import com.dvn.sys.dass.sms.manager.SMSFuncManager;
import com.dvn.sys.dass.sms.po.basequery.request.SMSBaseQueryAPIRequest;
import com.dvn.sys.dass.sms.po.basequery.response.SMSBaseQueryAPIResponse;
/**
 */
public class BaseQueryServlet extends BaseServlet {

	private static final Logger logger = Logger
			.getLogger(BaseQueryServlet.class);

	private static ApplicationContext ctx = null;
	
	private String encoding;

	private String returnCode;

	private SMSBaseQueryAPIRequest requestAPI;

	private SMSBaseQueryAPIResponse responseAPI;
	
	public Object getBean(String name) {
		if (ctx == null) {
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServletContext());
		}
		return ctx.getBean(name);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		super.init(config);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		returnCode = ResultDefine.SUCCESS_UNI;
		responseAPI = null;

		String ip = request.getRemoteAddr();
		logger.debug("-------> Visit IP: " + ip);
		logger.error("###BaseQueryServlet###");
		if (checkServiceStatus()) {
			logger.debug("Server status is OK!");
			String requestContent = getRequestContent(request, encoding);
			logger.debug(LogPrinter.printLogForRequest("BaseQuery",
					requestContent));

			putRequestAPI(requestContent);
			String transactionId = requestAPI.getTransactionID();
			if (returnCode.equals(ResultDefine.SUCCESS_UNI)) {
				execute(transactionId);
			}
		}

		if (null == responseAPI) {
			responseAPI = new SMSBaseQueryAPIResponse();
		}
		responseAPI.setReturnCode(returnCode);

		byte[] responseContent = putResponseAPI(encoding);
		logger.debug(LogPrinter.printLogForResponse("BaseQuery", Tool
				.getStringByBytes(responseContent, encoding)));

		response(response, responseContent, encoding, responseContent.length,
				null);
	}

	private void execute(String transactionId) {

		try {
			String uuid = requestAPI.getUUID();
			String remark = requestAPI.getRemark();
			
			SMSFuncManager smsFuncMana = (SMSFuncManager)getBean("SMSFuncManager");
			responseAPI = new SMSBaseQueryAPIResponse();
			FunctionResult baseQueryRes = smsFuncMana.callFuncBaseQuery("SMS-FEP", uuid, transactionId, remark);
			if(!"100".equals(baseQueryRes.getResultCode()) && !"101".equals(baseQueryRes.getResultCode())){
				returnCode = ResultDefine.transResultCode(baseQueryRes.getResultCode());
			}else{
				String[] smsRetuArr =  baseQueryRes.getAccountValue().replace("|", "#").split("#");
				responseAPI.setBaseAccount(smsRetuArr[0].substring(smsRetuArr[0].indexOf("=")+1));
				responseAPI.setServiceData(smsRetuArr[1]);
				responseAPI.setPaying(smsRetuArr[2].substring(smsRetuArr[2].indexOf("=")+1));
				responseAPI.setIsornot(smsRetuArr[3].substring(smsRetuArr[3].indexOf("=")+1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception:"+e.getMessage());
			returnCode = ResultDefine.OPERATE_FAIL_UNI;
		}
	}


	private void putRequestAPI(String requestContent) {
		try {
			requestAPI = (SMSBaseQueryAPIRequest) getRequest(requestContent,
					com.dvn.sys.dass.sms.po.basequery.request.ObjectFactory.class);
		} catch (IOException e) {
			logger.error("putRequestAPI IOException: " + e.getMessage());
			returnCode = ResultDefine.REQUEST_XML_FORMAT_ERROR_UNI;
		} catch (JAXBException e) {
			logger.error("putRequestAPI JAXBException: " + e.getMessage());
			returnCode = ResultDefine.REQUEST_XML_FORMAT_ERROR_UNI;
		} catch (NullPointerException e) {
			logger.error("putRequestAPI NullPointerException: "
					+ e.getMessage());
			returnCode = ResultDefine.REQUEST_XML_FORMAT_ERROR_UNI;
		} catch (ClassCastException e) {
			logger.error("putRequestAPI ClassCastException: " + e.getMessage());
			returnCode = ResultDefine.REQUEST_XML_FORMAT_ERROR_UNI;
		}
	}

	private byte[] putResponseAPI(String encoding) {
		byte[] temp = null;
		try {
			temp = putResponse(responseAPI, com.dvn.sys.dass.sms.po.basequery.response.ObjectFactory.class, encoding);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
}