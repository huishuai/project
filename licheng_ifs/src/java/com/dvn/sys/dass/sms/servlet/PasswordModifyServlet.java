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
import com.dvn.sys.dass.sms.po.pwdmod.request.SMSPwdModAPIRequest;
import com.dvn.sys.dass.sms.po.pwdmod.response.SMSPwdModAPIResponse;
/**
 */
public class PasswordModifyServlet extends BaseServlet {

	private static final Logger logger = Logger
			.getLogger(PasswordModifyServlet.class);

	private static ApplicationContext ctx = null;
	
	private String encoding;

	private String returnCode;

	private SMSPwdModAPIRequest requestAPI;

	private SMSPwdModAPIResponse responseAPI;

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
		logger.error("###PasswordModifyServlet###");
		if (checkServiceStatus()) {
			logger.debug("Server status is OK!");
			String requestContent = getRequestContent(request, encoding);
			logger.debug(LogPrinter.printLogForRequest("Account",
					requestContent));

			putRequestAPI(requestContent);
			String transactionId = requestAPI.getTransactionID();
			if (returnCode.equals(ResultDefine.SUCCESS_UNI)) {
				execute(transactionId);
			}
		}

		if (null == responseAPI) {
			responseAPI = new SMSPwdModAPIResponse();
		}
		responseAPI.setReturnCode(returnCode);

		byte[] responseContent = putResponseAPI(encoding);
		logger.debug(LogPrinter.printLogForResponse("PwdMod", Tool
				.getStringByBytes(responseContent, encoding)));

		response(response, responseContent, encoding, responseContent.length,
				null);
	}

	private void execute(String transactionId) {

		String uuid = requestAPI.getUUID();
		String remark = requestAPI.getRemark();
		String oldpassword = requestAPI.getOldPassword();
		String newpassword = requestAPI.getNewPassword();
		
		SMSFuncManager smsFuncMana = (SMSFuncManager)getBean("SMSFuncManager");
		FunctionResult PwdModRes = smsFuncMana.callFuncPasswordModify("SMS-FEP",
				uuid, transactionId, remark, oldpassword, newpassword);
		responseAPI = new SMSPwdModAPIResponse();
		if(!"100".equals(PwdModRes.getResultCode()) && !"101".equals(PwdModRes.getResultCode())){
			returnCode = ResultDefine.transResultCode(PwdModRes.getResultCode());
		}
		responseAPI.setTransactionID(transactionId);
	}


	private void putRequestAPI(String requestContent) {
		try {
			requestAPI = (SMSPwdModAPIRequest) getRequest(requestContent,
					com.dvn.sys.dass.sms.po.pwdmod.request.ObjectFactory.class);
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
			temp = putResponse(responseAPI, com.dvn.sys.dass.sms.po.pwdmod.response.ObjectFactory.class, encoding);
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