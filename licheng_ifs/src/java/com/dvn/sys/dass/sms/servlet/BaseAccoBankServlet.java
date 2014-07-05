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
import com.dvn.sys.dass.sms.po.baseaccount.request.SMSBaseAccoBankAPIRequest;
import com.dvn.sys.dass.sms.po.baseaccount.response.SMSBaseAccoBankAPIResponse;
/**
 */
public class BaseAccoBankServlet extends BaseServlet {

	private static final Logger logger = Logger
			.getLogger(BaseAccoBankServlet.class);

	private static ApplicationContext ctx = null;
	
	private String encoding;

	private String returnCode;

	private SMSBaseAccoBankAPIRequest requestAPI;

	private SMSBaseAccoBankAPIResponse responseAPI;
	
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
		logger.error("###BaseAccoBankServlet###");
		if (checkServiceStatus()) {
			logger.debug("Server status is OK!");
			String requestContent = getRequestContent(request, encoding);
			logger.debug(LogPrinter.printLogForRequest("BaseAccoBank",
					requestContent));

			putRequestAPI(requestContent);
			String transactionId = requestAPI.getTransactionID();
			if (returnCode.equals(ResultDefine.SUCCESS_UNI)) {
				execute(transactionId);
			}
		}

		if (null == responseAPI) {
			responseAPI = new SMSBaseAccoBankAPIResponse();
		}
		responseAPI.setReturnCode(returnCode);

		byte[] responseContent = putResponseAPI(encoding);
		logger.debug(LogPrinter.printLogForResponse("BaseAccoBank", Tool
				.getStringByBytes(responseContent, encoding)));

		response(response, responseContent, encoding, responseContent.length,
				null);
	}

	private void execute(String transactionId) {

		try {
			String uuid = requestAPI.getUUID();
			String remark = requestAPI.getRemark();
			String chargeAccount = requestAPI.getChargeAccount();
			
			SMSFuncManager smsFuncMana = (SMSFuncManager)getBean("SMSFuncManager");
			responseAPI = new SMSBaseAccoBankAPIResponse();
			FunctionResult BaseAccoBankRes = smsFuncMana.callFuncBaseAccoBank("SMS-FEP", uuid, transactionId, chargeAccount, remark);
			if(!"100".equals(BaseAccoBankRes.getResultCode()) && !"101".equals(BaseAccoBankRes.getResultCode())){
				returnCode = ResultDefine.transResultCode(BaseAccoBankRes.getResultCode());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception:"+e.getMessage());
			returnCode = ResultDefine.OPERATE_FAIL_UNI;
		}
	}


	private void putRequestAPI(String requestContent) {
		try {
			requestAPI = (SMSBaseAccoBankAPIRequest) getRequest(requestContent,
					com.dvn.sys.dass.sms.po.baseaccount.request.ObjectFactory.class);
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
			temp = putResponse(responseAPI, com.dvn.sys.dass.sms.po.baseaccount.response.ObjectFactory.class, encoding);
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