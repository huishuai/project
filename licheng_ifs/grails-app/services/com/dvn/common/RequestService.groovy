package com.dvn.common

import com.google.gson.Gson
import com.miniboss.bean.BaseBean
import com.miniboss.util.HttpSendResult
import com.miniboss.util.HttpUtil
import com.miniboss.util.MessageUtil
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.google.gson.GsonBuilder

class RequestService {

    boolean transactional = false
    def String cbs = ConfigurationHolder.config.cbs.url
    def String iscpUrl = ConfigurationHolder.config.iscp.url

    def BaseBean requestToCbs(String resUrl, Object data, Class responseClass) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        log.info("request:" + cbs+resUrl)
        String json = gson.toJson(data)
        log.info("send json:" + json)
        HttpSendResult httpSendResult = HttpUtil.executePost(cbs+resUrl, json)
        BaseBean responseBean = (BaseBean) responseClass.newInstance()
        if (httpSendResult.getStatusCode() == 200) {
              log.info("recive json:" + httpSendResult.getResponse())
            try {
                responseBean = (BaseBean) gson.fromJson(httpSendResult.getResponse(), responseClass)
            } catch (Exception e1) {
                responseBean.message = MessageUtil.getMessage("RequestService.request.responseFormatError");
                responseBean.code = "RequestService.request.responseFormatError";
                log.error(responseBean.message + ":" + httpSendResult.getResponse())
            }
        } else {
            responseBean.message = MessageUtil.getMessage("RequestService.request.neterror") + httpSendResult.getStatusCode();
            log.error(MessageUtil.getMessage("RequestService.request.neterror") + httpSendResult.getStatusCode())
        }
        return responseBean
    }


    def BaseBean requestToIscpByStr(String resUrl, String reqStr, Class responseClass) {
        log.info("request:" + iscpUrl + resUrl)
        log.info("request Str:" + reqStr)
        HttpSendResult httpSendResult = HttpUtil.executePost(iscpUrl + resUrl, reqStr)
        BaseBean responseBean = (BaseBean) responseClass.newInstance()
        responseBean.result = false
        if (httpSendResult.getStatusCode() == 200) {
            log.info("recive Str:" + httpSendResult.getResponse())
            try {
                responseBean.message = httpSendResult.getResponse()
                responseBean.result = true
            } catch (Exception e1) {
                e1.printStackTrace()
                log.error(responseBean.message + ":" + e1.getMessage())
            }
        } else {
            responseBean.message = MessageUtil.getMessage("RequestService.request.neterror") + httpSendResult.getStatusCode();
            log.error(MessageUtil.getMessage("RequestService.request.neterror") + httpSendResult.getStatusCode())
        }
        return responseBean
    }
}
