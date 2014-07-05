package com.dvn.miniboss.jc.process

import com.dvn.common.RequestService
import com.dvn.miniboss.oldsms.AuthService
import com.miniboss.exception.BaseException
import com.miniboss.product.PriceResult
import com.miniboss.bean.*
import com.miniboss.exception.ExceptionUtil
import org.apache.log4j.Logger
import com.miniboss.bean.auth.AuthOrderReqBean
import com.miniboss.bean.custom.AccountBean
import com.miniboss.bean.auth.CommodityPricingReq
import com.dvn.miniboss.oldsms.CmngCustom
import com.miniboss.bean.auth.CommodityPricingRes
import com.miniboss.bean.custom.CustomAccountReqBean
import com.miniboss.bean.authservice.RefreshEmpowermentReq

class SyncCbsDataProcessService {

    boolean transactional = false
    def RequestService requestService
    private static final Logger logger = Logger.getLogger(SyncCbsDataProcessService.class)

    /**
     * 对某个客户进行产品订购
     * @param customId 客户ID
     * @return ResponseAccountInfoBean 账户信息
     */
    def CommonRespBean orderProductToCBS(AuthOrderReqBean bean) throws BaseException {
        String url = '/account/orderProduct';
        CommonRespBean responseBean = (CommonRespBean) requestService.requestToCbs(url, bean, CommonRespBean.class)
        if (!responseBean.getResult()){
            logger.error("CbsErrorCode:"+responseBean.code+"----CbsErrorMessage:"+responseBean.message)
            String umsCode = ExceptionUtil.getUmsExceptionCode(responseBean.code)
            String umsMessage = ExceptionUtil.getUmsExceptionMessage(responseBean.code)
            throw new BaseException(umsCode, umsMessage);
        }
        return responseBean;
    }

    /**
     * 获取某个客户的账户信息和所有余额账本信息
     * @param customId 客户ID
     * @return ResponseAccountInfoBean 账户信息
     */
    def AccountBean getAccountInfoByCustomId(long customId) throws BaseException {
        CustomAccountReqBean accountReqBean = new CustomAccountReqBean();
        accountReqBean.setLoginCustomId(customId);
        String url = '/account/getCustomAccountInfo'
        AccountBean responseBean = (AccountBean) requestService.requestToCbs(url, accountReqBean, AccountBean.class)
        if (!responseBean.getResult()){
            logger.error("CbsErrorCode:"+responseBean.code+"----CbsErrorMessage:"+responseBean.message)
            String umsCode = ExceptionUtil.getUmsExceptionCode(responseBean.code)
            String umsMessage = ExceptionUtil.getUmsExceptionMessage(responseBean.code)
            throw new BaseException(umsCode, umsMessage);
        }
        return responseBean;
    }

    /**
     * 批量商品批价信息同步
     * @param authServices 订购关系集合
     * @return ResponseBatchCommodityPrice 批价结果集合
     */
    def CommodityPricingRes getBatchCommodityPrice(CmngCustom custom,List<AuthService> authServices) throws BaseException {
        if (authServices == null || authServices.size() == 0)
            return null
        CommodityPricingReq pricingReq=new CommodityPricingReq();
        pricingReq.custom=custom
        pricingReq.services=authServices
        pricingReq.loginCustomId = custom.id
        pricingReq.operatorUserId = "ifs"

        String url = '/authService/batchPrice'
        CommodityPricingRes responseBean = (CommodityPricingRes) requestService.requestToCbs(url, pricingReq, CommodityPricingRes.class)
        if (!responseBean.getResult()){
            logger.error("CbsErrorCode:"+responseBean.code+"----CbsErrorMessage:"+responseBean.message)
            String umsCode = ExceptionUtil.getUmsExceptionCode(responseBean.code)
            String umsMessage = ExceptionUtil.getUmsExceptionMessage(responseBean.code)
            throw new BaseException(umsCode, umsMessage);
        }
        return responseBean
    }

//    /**
//     * 单个商品批价信息同步
//     * @param authService 当前商品订购关系
//     * @return PriceResult 批价结果
//     */
//    PriceResult getPriceResultResponseBean(AuthService authService) throws BaseException{
//        String url = '/authService/batchPrice'
//        PriceResultResponseBean responseBean = (PriceResultResponseBean) requestService.requestToCbs(url, authService, PriceResultResponseBean.class)
//        if (!responseBean.getResult()){
//            logger.error("CbsErrorCode:"+responseBean.code+"----CbsErrorMessage:"+responseBean.message)
//            String umsCode = ExceptionUtil.getUmsExceptionCode(responseBean.code)
//            String umsMessage = ExceptionUtil.getUmsExceptionMessage(responseBean.code)
//            throw new BaseException(umsCode, umsMessage);
//        }
//        return responseBean.getPriceResult();
//    }

    /**
     * 对某个用户订购的商品进行重发授权
     * @param customId 客户ID
     * @return ResponseAccountInfoBean 账户信息
     */
    def BaseBean refreshEmpowermentToCBS(RefreshEmpowermentReq bean) throws BaseException {
        String url = '/authService/refreshEmpowerment';
        BaseBean responseBean = (BaseBean) requestService.requestToCbs(url, bean, BaseBean.class)
        if (!responseBean.getResult()){
            logger.error("CbsErrorCode:"+responseBean.code+"----CbsErrorMessage:"+responseBean.message)
            String umsCode = ExceptionUtil.getUmsExceptionCode(responseBean.code)
            String umsMessage = ExceptionUtil.getUmsExceptionMessage(responseBean.code)
            throw new BaseException(umsCode, umsMessage);
        }
        return responseBean;
    }




}
