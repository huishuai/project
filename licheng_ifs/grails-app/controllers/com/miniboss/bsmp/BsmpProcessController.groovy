package com.miniboss.bsmp

import com.dvn.miniboss.jc.webservice.BankCardService
import com.google.gson.Gson
import com.miniboss.bean.bankcard.CommodityPriceReqBean
import com.miniboss.bean.bankcard.CommodityPriceResBean
import com.miniboss.util.HttpUtil
import grails.converters.JSON
import com.dvn.miniboss.acct.AcctPaymentMethod
import com.dvn.miniboss.jc.process.AuthServiceProcessService
import com.miniboss.bean.OrderPayReq
import com.miniboss.bean.OrderPayRes
import com.google.gson.GsonBuilder

class BsmpProcessController {
    def BankCardService bankCardService
    def AuthServiceProcessService authServiceProcessService

    def batchPrice = {
        String str = HttpUtil.getPost(request)
        log.info str
        //将请求数据拼装成Bean
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        CommodityPriceReqBean requestBean = gson.fromJson(str, CommodityPriceReqBean.class)
        CommodityPriceResBean responseBean = bankCardService.getCommodityPrice(requestBean)
        JSON json = new JSON(responseBean)
        println json.toString()
        render(contentType: "text/xml", text: "${json}")
    }
    /**
     * 银行缴费订购
     */
    def bankOrderPay = {
        String str = HttpUtil.getPost(request)
        log.info str
        //将请求数据拼装成Bean
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        OrderPayReq requestBean = gson.fromJson(str, OrderPayReq.class)
        OrderPayRes responseBean = authServiceProcessService.orderPay(requestBean,AcctPaymentMethod.PAYMETHOD_BANK)
        JSON json = new JSON(responseBean)
        println json.toString()
        render(contentType: "text/xml", text: "${json}")
    }
}
