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
     * ��ĳ���ͻ����в�Ʒ����
     * @param customId �ͻ�ID
     * @return ResponseAccountInfoBean �˻���Ϣ
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
     * ��ȡĳ���ͻ����˻���Ϣ����������˱���Ϣ
     * @param customId �ͻ�ID
     * @return ResponseAccountInfoBean �˻���Ϣ
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
     * ������Ʒ������Ϣͬ��
     * @param authServices ������ϵ����
     * @return ResponseBatchCommodityPrice ���۽������
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
//     * ������Ʒ������Ϣͬ��
//     * @param authService ��ǰ��Ʒ������ϵ
//     * @return PriceResult ���۽��
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
     * ��ĳ���û���������Ʒ�����ط���Ȩ
     * @param customId �ͻ�ID
     * @return ResponseAccountInfoBean �˻���Ϣ
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
