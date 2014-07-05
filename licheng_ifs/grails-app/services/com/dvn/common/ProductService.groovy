package com.dvn.common
 
import com.dvn.miniboss.oldsms.AuthService
import com.miniboss.product.PriceResult
import com.miniboss.bean.auth.CommodityPricingReq
import com.miniboss.bean.auth.CommodityPricingRes
import com.dvn.miniboss.oldsms.CmngCustom

/**
 * Copyright lr.
 * User: admin
 * Date: 2009-7-13
 * Time: 9:48:42
 * 已作废，移到SyncCbsDataProcessService中
 */
public class ProductService {
    def com.dvn.common.RequestService requestService

    /**
     *  获取商品的价格
     */
    PriceResult getPriceResultResponseBean(AuthService authService,CmngCustom cmngCustom) {
        CommodityPricingReq commodityPricingReq = new CommodityPricingReq()
        commodityPricingReq.custom = cmngCustom
        List<AuthService> list = new ArrayList<AuthService>();
        list.add(authService)
        commodityPricingReq.services = list

        CommodityPricingRes pricingRes = (CommodityPricingRes) requestService.requestToCbs('/authService/batchPrice', commodityPricingReq, CommodityPricingRes.class)
        if (!pricingRes.getResult()) {
            throw new Exception(pricingRes.getMessage());
        }
        if(pricingRes.getServices()==null||(pricingRes.getServices().size()==0))
            throw new Exception("CBS Pricing Error!!!");
        return pricingRes.getServices().get(0).getPriceResult();
    }

}