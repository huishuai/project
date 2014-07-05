package com.dvn.miniboss.jc.webservice

import com.dvn.miniboss.oldsms.AuthService
import com.dvn.miniboss.oldsms.CmngCustom
import com.dvn.miniboss.oldsms.CmngStbcustomvdo
import com.dvn.miniboss.product.Commodity
import com.miniboss.product.PriceResult
import com.miniboss.bean.bankcard.*
import com.dvn.miniboss.jc.process.AuthServiceProcessService
import com.dvn.miniboss.product.BillingCycle
import com.dvn.miniboss.sync.PublishAsset
import com.dvn.miniboss.jc.process.PublishAssetProcessService
import com.dvn.miniboss.jc.process.SyncCbsDataProcessService
import com.miniboss.bean.auth.CommodityPricingRes

class BankCardService {
    static expose = ['cxf']
    def SyncCbsDataProcessService syncCbsDataProcessService
    def AuthServiceProcessService authServiceProcessService
    boolean transactional = false

    /**
     * 第三方批价接口
     * @param priceReqBean 产品批价请求
     * @return CommodityPriceResBean
     */
    public CommodityPriceResBean getCommodityPrice(CommodityPriceReqBean priceReqBean) {
        CommodityPriceResBean priceResBean = new CommodityPriceResBean()
        try {

            priceResBean.productId = priceReqBean.getProductId()

            CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(priceReqBean.getUuId())
            if (!cmngStbcustomvdo) {
                priceResBean.result = StringUtil.USER_NOT_EXIST
                return priceResBean
            }

            CmngCustom cmngCustom = CmngCustom.get(cmngStbcustomvdo.dvbcustomidCmngCustom)
            if (!cmngCustom) {
                priceResBean.result = StringUtil.USER_NOT_EXIST
                return priceResBean
            }

            AuthService authService = AuthServiceProcessService.createOnceBatchAuthService(priceReqBean.getProductId(), 
                    cmngCustom.getAreaid(), cmngCustom.getTypeCmngCustomtype(), cmngStbcustomvdo.getClassType(),
                    priceReqBean.getBeginDate(), priceReqBean.getBillingCycleCount(),cmngStbcustomvdo.useridCmngUser)

            List<AuthService> authServiceList = new ArrayList<AuthService>()
            authServiceList.add(authService)
            CommodityPricingRes commodityPricingRes = syncCbsDataProcessService.getBatchCommodityPrice(cmngCustom, authServiceList)
            if (commodityPricingRes.getServices() == null || (commodityPricingRes.getServices().size() == 0))
                throw new Exception("CBS Pricing Error!!!");
            PriceResult priceResult = commodityPricingRes.getServices().get(0).getPriceResult()
            priceResBean.result = StringUtil.SUCCESS
            priceResBean.totlePrice = priceResult.total

        } catch (Exception e) {
            priceResBean.result = StringUtil.ERROR
            priceResBean.totlePrice = 0
        }
        return priceResBean
    }

    /**
     * 一卡通点播、订购、续服务接口
     * @param commodityPlayReqBean
     * @return CommodityPlayResBean
     */
    public CommodityPlayResBean getCommodityPlay(CommodityPlayReqBean commodityPlayReqBean) {
        CommodityPlayResBean commodityPlayResBean = new CommodityPlayResBean()
        commodityPlayResBean = authServiceProcessService.bankCardOrder(commodityPlayReqBean, commodityPlayResBean)
        return commodityPlayResBean
    }


}
