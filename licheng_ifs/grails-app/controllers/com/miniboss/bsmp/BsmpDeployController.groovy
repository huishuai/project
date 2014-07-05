package com.miniboss.bsmp

import com.dvn.miniboss.jc.webservice.JcBsmpProcessService
import com.dvn.miniboss.jc.ftp.JcPublishAssetService
import com.dvn.miniboss.jc.ftp.JcASAllService
import com.dvn.miniboss.jc.ftp.JcASIncrementService
import com.dvn.miniboss.jc.ftp.JcUmsUserInfoService
import com.dvn.miniboss.jc.ftp.JcUmsPointService

import com.dvn.miniboss.jc.process.BsmpIbossProcessService
import com.dvn.miniboss.jc.process.BsmpIbossResoureProcessService
import com.dvn.miniboss.jc.webservice.BankCardService
import com.miniboss.bean.bankcard.CommodityPlayReqBean
import com.miniboss.util.HttpUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.miniboss.bean.bankcard.CommodityPlayResBean
import com.miniboss.bean.bankcard.CommodityPriceReqBean
import com.miniboss.bean.bankcard.CommodityPriceResBean
import com.dvn.miniboss.jc.socket.JcSocketService
import com.bmsp.message.BodyMessage
import com.dvn.miniboss.callcenter.CallCenterService
import com.miniboss.callcenter.CommonResultBean
import com.miniboss.callcenter.ResendCAReq

class BsmpDeployController {
  def JcBsmpProcessService jcBsmpProcessService
  def JcPublishAssetService jcPublishAssetService
  def JcASAllService jcASAllService
  def JcASIncrementService jcASIncrementService
  def JcUmsUserInfoService jcUmsUserInfoService
  def JcUmsPointService jcUmsPointService
  def BsmpIbossProcessService bsmpIbossProcessService
  def BsmpIbossResoureProcessService bsmpIbossResoureProcessService
  def BankCardService bankCardService
  def JcSocketService jcSocketService
  def CallCenterService callCenterService

  def addCompany = {
    String id = params.id
    println id
    if (id) {
      println id
      jcBsmpProcessService.addCooperater(id)
    }
  }
  def editCompany = {
    String id = params.id
    if (id) {
      jcBsmpProcessService.editCooperater(id)
    }
  }
  def deleteCompany = {
    String id = params.id
    if (id) {
      jcBsmpProcessService.removeCooperater(id)
    }
  }
  def deployCommodity = {
    String id = params.id
    if (id) {
      jcBsmpProcessService.deployPorductPkgInfo(id)
    }
  }

  def syncPublishAsset = {
    jcPublishAssetService.run()
    render(contentType: "text/xml", text: "------syncPublishAsset End--------")
  }

  def syncAllAuthService = {
    jcASAllService.run()
    render(contentType: "text/xml", text: "------syncAllAuthService End--------")
  }

  def syncIncrementAuthService = {
    jcASIncrementService.run()
    render(contentType: "text/xml", text: "------syncIncrementAuthService End--------")
  }

  def syncUmsUserInfo = {
    jcUmsUserInfoService.run()
    render(contentType: "text/xml", text: "------syncUmsUserInfo End--------")
  }
  def syncUmsPoint = {
    jcUmsPointService.run()
    render(contentType: "text/xml", text: "------syncUmsPoint End--------")
  }
  def syncBsmpIboss = {
    bsmpIbossProcessService.dataSync()
    render(contentType: "text/xml", text: "------syncBsmpIboss End--------")
  }
  def syncBsmpIbossResource = {
    bsmpIbossResoureProcessService.resoureDataSync();
    render(contentType: "text/xml", text: "------syncBsmpIbossResource End--------")
  }

    //IFS接口 3.11-第三方批价接口描述 JMeter测试用入口
    def getCommodityPrice = {
        String str = HttpUtil.getPost(request)
        log.info str
        //将请求数据拼装成Bean
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        CommodityPriceReqBean requestBean = gson.fromJson(str, CommodityPriceReqBean.class)
        CommodityPriceResBean responseBean = bankCardService.getCommodityPrice(requestBean)

        log.info "result:" + responseBean.result
        log.info "productId:" + responseBean.productId
        log.info "totlePrice:" + responseBean.totlePrice
        render(contentType: "text/xml", text: "------getCommodityPrice End--------")
    }

    //IFS接口 3.12-第三方订购关系同步接口 JMeter测试用入口
    def getCommodityPlay = {

        String str = HttpUtil.getPost(request)
        log.info str
        //将请求数据拼装成Bean
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        CommodityPlayReqBean commodityPlayReqBean = gson.fromJson(str, CommodityPlayReqBean.class)
        CommodityPlayResBean responseBean = bankCardService.getCommodityPlay(commodityPlayReqBean)

        log.info "result:" + responseBean.result
        log.info "message:" + responseBean.message
        render(contentType: "text/xml", text: "------getCommodityPlay End--------")
    }

    //IFS接口 3.1 VOD按次点播描述 JMeter测试用入口
    def orderOnce = {

        String str = HttpUtil.getPost(request)
        log.info str
        //将请求数据拼装成Bean
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        BodyMessage requestBean = gson.fromJson(str, BodyMessage.class)
        BodyMessage responseBean = jcSocketService.orderOnce(requestBean)

        log.info "result:" + responseBean.result
        log.info "sessionId:" + responseBean.sessionId
        render(contentType: "text/xml", text: "------orderOnce End--------")
    }

    //IFS接口JMeter测试用入口 3.2 VOD包月订购
    def monthDeal = {

        String str = HttpUtil.getPost(request)
        log.info str
        //将请求数据拼装成Bean
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        BodyMessage requestBean = gson.fromJson(str, BodyMessage.class)
        BodyMessage responseBean = jcSocketService.monthDeal(requestBean)

        log.info "errCode:" + responseBean.errCode
        log.info "sessionId:" + responseBean.sessionId
        render(contentType: "text/xml", text: "------orderOnce End--------")
    }

    //IFS接口,CaCenter的重发授权接口
    def resendCA = {

        String str = HttpUtil.getPost(request)
        log.info str
        //将请求数据拼装成Bean
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        ResendCAReq requestBean = gson.fromJson(str, ResendCAReq.class)
        CommonResultBean responseBean = callCenterService.resendCA("testSessionID",requestBean)

        log.info "errCode:" + responseBean.errorCode
        render(contentType: "text/xml", text: "------orderOnce End--------")
    }






}
