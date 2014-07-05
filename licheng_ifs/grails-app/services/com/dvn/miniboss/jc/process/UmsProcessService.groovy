package com.dvn.miniboss.jc.process

import com.dvn.common.RequestService
import com.dvn.miniboss.oldsms.AuthService
import com.dvn.miniboss.oldsms.CmngCustom

import com.dvn.miniboss.oldsms.CmngStbcustomvdo
import com.dvn.miniboss.product.Commodity
import com.dvn.miniboss.serv.Empowerment
import com.dvn.sys.dass.sms.JaxbUtil
import com.dvn.sys.dass.sms.StringUtil
import com.dvn.sys.dass.sms.po.account.request.SMSAccountAPIRequest
import com.dvn.sys.dass.sms.po.account.response.SMSAccountAPIResponse
import com.dvn.sys.dass.sms.po.balance.request.SMSBalanceAPIRequest
import com.dvn.sys.dass.sms.po.balance.response.SMSBalanceAPIResponse
import com.dvn.sys.dass.sms.po.baseaccount.request.SMSBaseAccoBankAPIRequest
import com.dvn.sys.dass.sms.po.baseaccount.response.SMSBaseAccoBankAPIResponse
import com.dvn.sys.dass.sms.po.basequery.request.SMSBaseQueryAPIRequest
import com.dvn.sys.dass.sms.po.basequery.response.SMSBaseQueryAPIResponse
import com.dvn.sys.dass.sms.po.pwdmod.request.SMSPwdModAPIRequest
import com.dvn.sys.dass.sms.po.pwdmod.response.SMSPwdModAPIResponse
import com.dvn.sys.dass.sms.po.pwdval.request.SMSPwdValAPIRequest
import com.dvn.sys.dass.sms.po.pwdval.response.SMSPwdValAPIResponse
import com.dvn.sys.dass.sms.po.recharge.request.SMSRechargeAPIRequest
import com.dvn.sys.dass.sms.po.recharge.response.SMSRechargeAPIResponse
import com.miniboss.acct.utils.DateUtil
import com.miniboss.acct.utils.Md5Util
import com.miniboss.bean.ums.BaseServiceDataBean
import com.miniboss.exception.BaseException
import com.miniboss.exception.ExceptionUtil
import com.miniboss.product.AccountSnapshot
import com.miniboss.util.MessageUtil
import org.apache.log4j.Logger
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.dvn.miniboss.acct.*
import com.miniboss.bean.*
import com.miniboss.bean.auth.AuthOrderReqBean
import com.miniboss.bean.custom.AccountBean
import com.miniboss.bean.custom.CustomAccountReqBean
import com.miniboss.bean.custom.CustomSyncReqBean
import com.miniboss.bean.auth.CommodityPricingRes
import com.miniboss.exception.DomainSaveException
import com.miniboss.bean.custom.DepositReqBean
import com.dvn.miniboss.oldsms.ChargeBillType
import com.miniboss.util.EmpowermentUtil

class UmsProcessService {
    def RequestService requestService
    def CmngUserProcessService cmngUserProcessService
    def SyncCbsDataProcessService syncCbsDataProcessService
    def AuthServiceProcessService authServiceProcessService

    boolean transactional = false
    public final static Logger logger = Logger.getLogger(UmsProcessService.class)

    /**
     * ��������ֵ�ӿ�
     * @param request
     * @return
     */
    def recharge(SMSRechargeAPIRequest request) {
        SMSRechargeAPIResponse response = new SMSRechargeAPIResponse();
        try {
            CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(request.getUUID())
            CmngCustom cmngCustom = CmngCustom.get(cmngStbcustomvdo.dvbcustomidCmngCustom)
            Account account = Account.findByDvbCostomId(cmngCustom.getId())
            //todo request.accountType

            DepositReqBean depositRequestBean = new DepositReqBean()
            depositRequestBean.setLoginCustomId(cmngCustom.getId())
            depositRequestBean.setAcctId(account.id)
            depositRequestBean.setOperatorUserId("ifs")

            //���������¼
            AcctPayment acctPayment = new AcctPayment()
            acctPayment.setCustomId(cmngCustom.getId())
            acctPayment.setAcctId(account.id)
            acctPayment.amount = (com.miniboss.util.StringUtil.str2BigDecimal(request.chargeAccount)).toLong()
            acctPayment.setBankBillId(request.getTransactionID())
            acctPayment.setPaymentMethodId(request.modelID)
            acctPayment.setNetCodeId(cmngCustom.netid)

            depositRequestBean.setAcctPayament(acctPayment)

            AccountBean responseBean = (AccountBean) requestService.requestToCbs('/account/deposit', depositRequestBean, AccountBean.class)

            boolean result = responseBean.getResult()
            //��ȡ����˱���Ϣ
            try {
//                ProductCommandBean productCommandBean = new ProductCommandBean()
//                productCommandBean.setCustomId(depositRequestBean.customId)
//                ResponseAccountInfoBean responseAccountInfoBean = (ResponseAccountInfoBean) requestService.requestToCbs('/account/getCustomAccountInfo', productCommandBean, ResponseAccountInfoBean.class)
                AccountSnapshot accountSnapshot = responseBean.getAccountSnapshot()
                response.balance = "REAL=" + accountSnapshot.getCanUseBalanceTotal() + ";SUMNOPAID=0|VIRTUAL=0;SUMNOPAID=0"
            } catch (Exception e) {
                log.error "get balance error" + e.getMessage()
            }

            if (result) {
                //�����������־
                //todo ��crm��ͳһ���ɣ���Ȼ������������
                response.returnCode = StringUtil.SUCCESS

            } else {
                response.returnCode = StringUtil.PROCESS_ERROR
            }
        } catch (Exception e2) {
            log.error "recharge balance error" + e.getMessage()
            response.returnCode = StringUtil.PROCESS_ERROR
        }
        return response
    }

    def account(SMSAccountAPIRequest request) {
        SMSAccountAPIResponse response = new SMSAccountAPIResponse()
        //ibossʵʱ�۷�delete-������


        return response
    }

    /**
     * UMS�������ӷѽɷѽӿڣ�ֻ������˿ͻ�
     * ����������ʱ��Ӷ���ʱ����һ�쿪ʼ��Ч
     * @param request
     * @return
     */
    def baseAccoBank(SMSBaseAccoBankAPIRequest request) {
        SMSBaseAccoBankAPIResponse response = new SMSBaseAccoBankAPIResponse()
        try {
            //��ֹ�û�����ĩ18��֮�����portal��ѯ�Ͷ���
            boolean canProcess = authServiceProcessService.canProcessBaseQueryAndOrder(new Date())
            if(!canProcess){
                throw new BaseException(StringUtil.PROCESS_ERROR,
                        MessageUtil.getMessage("BaseQueryAndBaseAccoBank.MustBefore.8PM.ForLastDayOfMonth"))
            }

            //��ÿͻ���Ϣ���û�����(���ӻ�����,�ų���Ч�û�)
            CmngUser cmngUserReq = cmngUserProcessService.getUserIDByUuId(request.getUUID())
            String hql = "from CmngUser c where c.dvbCostomId=" + cmngUserReq.getDvbCostomId() + " and c.status != " + CmngUser.STATUS_INVALID  + " order by c.classType asc"
            List<CmngUser> allUserList = CmngUser.executeQuery(hql)
            CmngCustom cmngCustom = CmngCustom.findById(cmngUserReq.getDvbCostomId())
            if (cmngCustom == null || !CmngCustom.CUSTOMTYPE_PERSONAL.equals(cmngCustom.customType))
                throw new BaseException(StringUtil.PROCESS_ERROR, MessageUtil.getMessage("BaseAccoBank.NotSupport.CompanyCustom"))

            //���е�ǰ�û��Ƿ����ʹ��Portal��ʽ���л��������Ѵ����߼�ͬ��ѯ�ӿ�
            //��������û��������������񼯺�
            Map<String, Empowerment> userEmpowermentMap = getEmpowermentMapByUser(allUserList)
            //��ȡ�����ͻ���Ӧ��ÿ����Ч�û��Ļ�������Ʒ(���£�һ�꣬����)
            List<AuthService> authServiceList = createBatchAuthService(cmngCustom, allUserList, userEmpowermentMap)
            //����Ч�û��ĸ��ֻ������������۴���
            Map<String, BaseServiceDataBean> baseServiceDataBeanMap = createPayingData(cmngCustom,authServiceList)
            //���㵱ǰ�ͻ��Ƿ���Խ���Portal����������
            String canOrder = canOrderByPortal(allUserList, baseServiceDataBeanMap)
            //����ͨ��Portal���л������ɷѵģ��׳�����ʧ���쳣
            if (BaseServiceDataBean.ISORNOT_NOT.equals(canOrder))
                throw new BaseException(ExceptionUtil.getUmsExceptionCode(null), ExceptionUtil.getUmsExceptionMessage(null))

            //�������Ʒ�������ֵ
            int billingCountPara = Integer.parseInt(request.getBillingCount())
            //ƴװiBoss��������Ʒ���������������iBoss��Ʒ�����ӿ�(����Ʒ����ڲ�����36����ǰ��ƷΪ���»�������Ʒ������Ϊ�����������Ʒ)
            String baseServiceProduct = ConfigurationHolder.config.ums.baseQuery.oneMonthProductId
            if (billingCountPara == BaseServiceDataBean.UMS_THREEYEAR_BILLINGCYCLECOUNT)
                baseServiceProduct = ConfigurationHolder.config.ums.baseQuery.threeYearsProductId
            Commodity commodity = Commodity.findById(baseServiceProduct)
            List<AuthService> auths = new ArrayList()
            //�û�״̬��ͣ	 +  	*		 = �������»�����
            //�û�״̬���� + ������ϵ��Ƿ�ѡ���ͣ���û������˶��� = ������:(�Ʒ����ڼ�1),��ѯ�ͽɷ�ʱ������ʼʱ��-��������
            //�û�״̬���� + �����Ķ�����ϵ  = ��������,��ѯ�ͽɷ�ʱ:����ʼʱ��-��ȡ������ϵ�������Ǹ���ֹʱ��+1��,�ٳ���������ֹ���û������˶���������ϵ)
            for (CmngUser cmngUser: allUserList) {

                //�����ǰ�û����ܴ�Portal������������һ���û�
                //����û���ͣ�����ܶ���
                if (CmngUser.STATUS_SERVICE_PAUSED.equals(cmngUser.status)) {
                    logger.info("The User Status is 'STATUS_SERVICE_PAUSED', Can Not Order BaseService By Portal, UserId:" + cmngUser.id)
                    continue
                }

                //����û�������������������Ŀ�ʼʱ��ͼƷ����ڳ��ȣ������û���Ч������״̬�����жϣ�
                int currUserBillingCountTemp = billingCountPara
                Empowerment empowerment = userEmpowermentMap.get(cmngUser.id + "")
                Date startDate = EmpowermentUtil.getEmpowermentNextOrderDateForPortal(empowerment)
                //��������³����Ʒ�������ֵ��Ҫ+1�����Ӳ�����
                if (DateUtil.getDayOfMonth(startDate) > 1) {
                    logger.info("The BillingCycleCount must add 1,Because The nextOrderDate not beginning Date of a month:" + DateUtil.getDateBasicStr(startDate))
                    currUserBillingCountTemp = currUserBillingCountTemp + 1
                }

                //ƴװ�»�����������ϵ
                AuthService anAuthService = createProductAuthService(commodity.id, AuthService.PAYMODE_PAYBEFOREUSE,
                        DateUtil.getDateBasicStr(startDate), null, AuthService.IS_FIXEDPRICE_FALSE,
                        currUserBillingCountTemp, 999999999L)
                anAuthService.typeCmngCustomtype = cmngCustom.typeCmngCustomtype
                anAuthService.netid = cmngCustom.netid
                anAuthService.classType = cmngUser.classType
                anAuthService.orderUserId = cmngUser.id
                auths.add(anAuthService)

            }

            //ƴװ�����¼,������֧�������������¼
            AcctPayment acctPayment = null
            if (!(AcctPaymentMethod.PAYMETHOD_BALANCE.equals(request.getModelID()))) {
                acctPayment = new AcctPayment()
                acctPayment.setPaymentMethodId(request.getModelID())
                String chargeAccount = request.getChargeAccount()
                if (chargeAccount != null && chargeAccount.trim().length() > 0)
                    acctPayment.setAmount(Long.parseLong(chargeAccount))
                acctPayment.setStatus(AcctPayment.STATE_VALID)
                acctPayment.setBankBillId(request.getTransactionID())
                acctPayment.setNetCodeId(request.getNetId())
            }
            //CBS�ӿ�Bean
            AuthOrderReqBean authOrderReqBean = new AuthOrderReqBean()
            authOrderReqBean.setLoginCustomId(cmngCustom.id)
            authOrderReqBean.setOperatorUserId("ifs")
            authOrderReqBean.setPayment(acctPayment)
            authOrderReqBean.setAuthServices(auths)
            authOrderReqBean.areaId = cmngCustom.areaid
            authOrderReqBean.netCodeId = cmngCustom.netid

            //����CBSϵͳ�����пͻ���������Ʒ��������
            syncCbsDataProcessService.orderProductToCBS(authOrderReqBean)
            response.returnCode = BaseServiceDataBean.SUCCESS_RETURN_CODE
            response.returnMsg = BaseServiceDataBean.SUCCESS_RETURN_MESSAGE

        } catch (BaseException e) {
            if (e instanceof BaseException) {
                response.returnCode = ((BaseException) e).getCode()
                response.returnMsg = (((BaseException) e).getErrorMessage())
            } else {
                response.returnCode = StringUtil.PROCESS_ERROR
                response.returnMsg = MessageUtil.getMessage("RequestService.request.error")
            }
            log.error "ums baseAccoBank error��" + e.getMessage()
            e.printStackTrace()
        }
        return response
    }


    //����������������ϵ

    private AuthService createProductAuthService(String commodityId, String paymode, String startdate, String freestartdate,
                                                 String isFixedPrice, long billingCyclecount, long pricefix) {
        AuthService productAuthService = new AuthService()
        productAuthService.commodityId = commodityId
        productAuthService.paymode = paymode
        Date startDate = DateUtil.getDateByBasicStr(startdate)
        productAuthService.startdate = startDate
        if (freestartdate) {
            Date freestartDate = DateUtil.getDateByBasicStr(freestartdate)
            productAuthService.freestartdate = freestartDate
        }
        //���ºͰ���(base80)�����������(base120)��������
        if (billingCyclecount >= 36) {
            //��Ҫ�ж���ͨ������Ͳ��������
            productAuthService.isrenew = AuthService.ISRENEW_FALSE
        } else {
            productAuthService.isrenew = AuthService.ISRENEW_TRUE
        }
        productAuthService.isFixedPrice = isFixedPrice
        productAuthService.pricefix = pricefix
        productAuthService.refpricefix = pricefix
        productAuthService.billingCyclecount = billingCyclecount    //�Ʒ�������ֵ
        return productAuthService
    }

    /**
     * UMS�������ӷѲ�ѯ�ӿڣ�ֻ������˿ͻ�
     * @param request
     * @return
     */
    def baseQuery(SMSBaseQueryAPIRequest request) {
        SMSBaseQueryAPIResponse response = new SMSBaseQueryAPIResponse()
        try {
            //��ֹ�û�����ĩ18��֮�����portal��ѯ�Ͷ���
            boolean canProcess = authServiceProcessService.canProcessBaseQueryAndOrder(new Date())
            if(!canProcess){
                throw new BaseException(StringUtil.PROCESS_ERROR,
                        MessageUtil.getMessage("BaseQueryAndBaseAccoBank.MustBefore.8PM.ForLastDayOfMonth"))
            }

            //�ͻ���Ϣ
            CmngUser cmngUser = cmngUserProcessService.getUserIDByUuId(request.getUUID())
            CmngCustom cmngCustom = CmngCustom.findById(cmngUser.getDvbCostomId())
            if (cmngCustom == null || !CmngCustom.CUSTOMTYPE_PERSONAL.equals(cmngCustom.customType))
                throw new BaseException(StringUtil.PROCESS_ERROR, MessageUtil.getMessage("BaseQuery.NotSupport.CompanyCustom"))

            //�û�����(���ӻ�����,�ų���Ч�û�)
            String hql = "from CmngUser c where c.dvbCostomId=" + cmngUser.getDvbCostomId() + " and c.status != " + cmngUser.STATUS_INVALID  + " order by c.classType asc"
            List<CmngUser> allUserList = CmngUser.executeQuery(hql)

            //��������û��������������񼯺�
            Map<String, Empowerment> userEmpowermentMap = getEmpowermentMapByUser(allUserList)

            //��ȡ�����ͻ���Ӧ��ÿ����Ч�û��Ļ�������Ʒ(���£�һ�꣬����)
            List<AuthService> authServiceList = createBatchAuthService(cmngCustom, allUserList, userEmpowermentMap)
            //����Ч�û��ĸ��ֻ������������۴���
            Map<String, BaseServiceDataBean> baseServiceDataBeanMap = createPayingData(cmngCustom,authServiceList)

            //ƴװ�������������ݡ�
            //�������������ݡ���ʽ����type=1,status=1,sumnopaid=2300,enddate=20100531;*��
            //��ֹ���˶�״̬�Ķ�����ϵ����״̬Ϊ0���˶�����������ͣ״̬�Ķ�����ϵ����״̬Ϊ2��������ͣ��
            StringBuffer baseServiceData = new StringBuffer()
            Map<String, Long> queryPriceMap = new HashMap<String, Long>()
            int index = 1
            for (CmngUser userBrother: allUserList) {

                //�����Ч������״̬�ͻ�������������ֹʱ��
                Empowerment empowerment = userEmpowermentMap.get(userBrother.id + "")
                AuthService authServicePara = getValidStatusAndMaxEndDate(userBrother, empowerment)

                //ƴװĳ���û��ġ������������ݡ�
                BaseServiceDataBean baseServiceDataBean = baseServiceDataBeanMap.get(userBrother.id + "")
                if (baseServiceDataBean == null) {
                    baseServiceDataBean = new BaseServiceDataBean()
                    baseServiceDataBeanMap.put(userBrother.id + "", baseServiceDataBean)
                }
                baseServiceDataBean.setType(userBrother.classType)
                baseServiceDataBean.setSumNoPaid("0")
                baseServiceDataBean.setStatus(authServicePara.status)
                baseServiceDataBean.setEndDate(DateUtil.getDateDayStr(authServicePara.enddate))

                //���һ���û�����Ϣ����Ҫ�ֺŷָ�
                baseServiceData.append(baseServiceDataBean.convertBeanToString())
                if (index < allUserList.size())
                    baseServiceData.append(BaseServiceDataBean.sign_semicolon)
                index++

                //�������۽���ۼӼ���
                if (baseServiceDataBean.canIaddUpPrice) {
                    Long priceOld = queryPriceMap.get(BaseServiceDataBean.UMS_MONTH_BILLINGCYCLECOUNT_KEY)
                    if (priceOld == null)
                        priceOld = 0
                    long priceNew = priceOld + baseServiceDataBean.oneMonthPrice
                    queryPriceMap.put(BaseServiceDataBean.UMS_MONTH_BILLINGCYCLECOUNT_KEY, priceNew)

                    priceOld = queryPriceMap.get(BaseServiceDataBean.UMS_ONEYEAR_BILLINGCYCLECOUNT_KEY)
                    if (priceOld == null)
                        priceOld = 0
                    priceNew = priceOld + baseServiceDataBean.oneYearPrice
                    queryPriceMap.put(BaseServiceDataBean.UMS_ONEYEAR_BILLINGCYCLECOUNT_KEY, priceNew)

                    priceOld = queryPriceMap.get(BaseServiceDataBean.UMS_THREEYEAR_BILLINGCYCLECOUNT_KEY)
                    if (priceOld == null)
                        priceOld = 0
                    priceNew = priceOld + baseServiceDataBean.threeYearPrice
                    queryPriceMap.put(BaseServiceDataBean.UMS_THREEYEAR_BILLINGCYCLECOUNT_KEY, priceNew)
                }
            }

            //��ȡCBS�������ն���Ŀ������
            AccountBean accountInfoBean = syncCbsDataProcessService.getAccountInfoByCustomId(cmngUser.dvbCostomId)
            AccountSnapshot acctoutSnapshot = accountInfoBean.getAccountSnapshot()
            long canUserBalance = acctoutSnapshot.getCanUseBalanceTotal()
            //���㵱ǰ�ͻ��Ƿ���Խ���Portal����������
            String canOrder = canOrderByPortal(allUserList, baseServiceDataBeanMap)
            //ƴװ��Ӧ��Ϣ
            response.baseAccount = canUserBalance
            response.paying = BaseServiceDataBean.getPayingStr(queryPriceMap)
            response.serviceData = baseServiceData.toString()
            response.isornot = canOrder
            response.returnCode = BaseServiceDataBean.SUCCESS_RETURN_CODE
            response.returnMsg = BaseServiceDataBean.SUCCESS_RETURN_MESSAGE

        } catch (Exception e) {
            if (e instanceof BaseException) {
                response.returnCode = ((BaseException) e).getCode()
                response.returnMsg = (((BaseException) e).getErrorMessage())
            } else {
                response.returnCode = StringUtil.PROCESS_ERROR
                response.returnMsg = MessageUtil.getMessage("RequestService.request.error")
            }
            log.error "ums baseQuery error��" + e.getMessage()
            e.printStackTrace()
        }
        return response
    }

    //��������û������з��񼯺�
    private Map<String, Empowerment> getEmpowermentMapByUser(List<CmngUser> allUserList) {
        Map<String, Empowerment> userEmpowermentMap = new HashMap<String, Empowerment>()
        for (CmngUser user: allUserList) {
            Empowerment empowerment = getEmpowermentByEndDateMax(user.id)
            userEmpowermentMap.put(user.id + "", empowerment)
        }
        return userEmpowermentMap
    }


    //���ĳ���û�����ʱ�����Ļ������񣬸����û������·������ʼʱ����ڷ����endDate
    private Empowerment getEmpowermentByEndDateMax(long userId) {
        String getEmpListHql = "from Empowerment e where e.userId = '" + userId + "' and e.status = '" + Empowerment.STATUS_VALID + "'" +
                " and chargeBillType = '" + ChargeBillType.SERVICE_BASE + "' and e.endDate Is Not Null order by e.endDate desc"

        List empowermentList = Empowerment.executeQuery(getEmpListHql)
        if(empowermentList == null || empowermentList.size() == 0)
            return null
        Empowerment empowerment = empowermentList.get(0)
        if(empowerment == null || empowerment.endDate == null)
            return null
        //����������ʱ���ڵ�ǰʱ��֮ǰ˵�������Ѿ����ڷ��ؿ�
        Date now = new Date()
        Date endDate = DateUtil.getNextDay(empowerment.endDate)
        if(endDate.before(now))
            return null

        return empowerment
    }


    //Portal��������ѯ�ӿڣ��������Ƹ��ݡ�Isornot�����Է���
    //�������������ж�

    public String canOrderByPortal(List<CmngUser> allUserList, Map<String, BaseServiceDataBean> userBaseServiceDataMap) {
        //��ȡ�����û�
        CmngUser masterUser = null
        for (CmngUser cmngUser: allUserList) {
            //�����ж�
            if (CmngStbType.MASTER.equals(cmngUser.classType)) {
                masterUser = cmngUser
                break
            }
        }
        //���������ڣ�������Portal�ɷ�
        if(masterUser == null){
            logger.info("MasterSTB CmngUser is Null! Can Not Order BaseService By Portal!")
            return BaseServiceDataBean.ISORNOT_NOT
        }
        //������ͣ��������Portal�ɷ�
        if (CmngUser.STATUS_SERVICE_PAUSED.equals(masterUser.status)) {
            logger.info("MasterSTB CmngUser.STATUS_SERVICE_PAUSED, Can Not Order BaseService By Portal, UUID:" + masterUser.id)
            return BaseServiceDataBean.ISORNOT_NOT
        }
        //�������������۽��С��23Ԫ�ģ�������Portal�ɷ�
        BaseServiceDataBean baseServiceDataBean = userBaseServiceDataMap.get(masterUser.id + "")
        if (baseServiceDataBean != null && baseServiceDataBean.getOneMonthPrice() < BaseServiceDataBean.UMS_ALLOW_MIN_PRICE) {
            logger.info("MasterSTB BaseService Price less 23 YUAN, Can Not Order BaseService By Portal, UUID:" + masterUser.id)
            return BaseServiceDataBean.ISORNOT_NOT
        }
        return BaseServiceDataBean.ISORNOT_YES
    }

    //��ȡ��ǰĳ���û���Ч������״̬�ͷ���������ʱ��
    //����portal�ķ���״̬�������֣�1-������2-������ͣ��4-��ֹ��������3-Ƿ����ͣ��
    private AuthService getValidStatusAndMaxEndDate(CmngUser cmngUser, Empowerment empowerment) {
        AuthService authServicePara = new AuthService()
        //��÷���Ľ���ʱ��
        authServicePara.enddate = EmpowermentUtil.getEmpowermentEndDateForPortal(empowerment)

        //���ڷ��񲻴��ڻ��߷���endDate����������Ĵ���
        if (empowerment == null || empowerment.endDate == null) {
            authServicePara.status = AuthService.STATUS_STOPPED
            logger.info("There is nothing BaseService, Status:" + authServicePara.status + " EndDate:" + authServicePara.enddate)
            return authServicePara
        }

        //�����û�������ͣ�������������
        //�����ǰ�û�״̬Ϊ������ͣ����ô������ʱ�䶩����ϵ��״ֻ̬�п����ǣ�Ԥ�������û������˶����û���ͣ
        if (CmngUser.STATUS_SERVICE_PAUSED.equals(cmngUser.status)) {
            authServicePara.status = AuthService.STATUS_USER_PAUSE
            logger.info("The User Status has 'STATUS_USER_PAUSE', Status:" + authServicePara.status +
                    " EndDate:" + authServicePara.enddate + " ServiceId:" + empowerment.id)
            return authServicePara
        }

        //��Ը������շ���״̬���У���ǰ��Ч������ϵ�ͷ�����ֹʱ�������ȡ
        if (Empowerment.STATUS_VALID.equals(empowerment.status)) {
            authServicePara.status = AuthService.STATUS_RUNNING
        } else {
            authServicePara.status = AuthService.STATUS_STOPPED
        }
        logger.info("BaseService Status:" + authServicePara.status + " EndDate:" + authServicePara.enddate + " empowermentId:" + empowerment?.id)
        return authServicePara
    }

    /**
     * ���������û��ĸ��ֻ������ɷѽ��(���£����꣬����)
     * @param authServiceList ������ϵ����
     * @param allUserList �û�����
     * @param userDataMap �û��͸��ֻ������ɷѽ��
     * @return
     */
    private Map<String, BaseServiceDataBean> createPayingData(CmngCustom cmngCustom,ArrayList<AuthService> authServiceList) {

        Map<String, BaseServiceDataBean> priceResultMap = new HashMap<String, BaseServiceDataBean>()
        if (authServiceList.size() == 0) {
            return priceResultMap
        }

        //�������ּƷ�������ֵ�����ֻ�������Ʒ�ļ۸�(1���°��²�Ʒ��12���°��²�Ʒ��36�����������Ʒ)
        //ƴװ��Ӧ��������:<Paying>count=1,paying=2300;count=12,paying=27600;count=36,paying=80000;</Paying>
        //��ȡ�������۽��
        CommodityPricingRes responseBatchCommodityPrice = syncCbsDataProcessService.getBatchCommodityPrice(cmngCustom,authServiceList)

        //ѭ���ۼ���Ч�û�����������
        for (AuthService auth: responseBatchCommodityPrice.services) {
            //����û���Ϣ�����л��������ݶ��󣬱����û����ֻ������ɷѽ��
            BaseServiceDataBean baseServiceDataBean = priceResultMap.get(auth.orderUserId + "")
            if (baseServiceDataBean == null) {
                baseServiceDataBean = new BaseServiceDataBean()
                priceResultMap.put(auth.orderUserId + "", baseServiceDataBean)
            }
            baseServiceDataBean.makeBaseServiceDataBean(auth)
            baseServiceDataBean.canIaddUpPrice = true
        }
        return priceResultMap
    }

    /**
     * UMS�������ӷѲ�ѯ�ӿ�����Ҫ�������۵Ķ�����ϵ����
     * @param allUserBaseServiceMap ����������(ÿ���û���Ӧһ����Ч������)
     * @param cmngCustom �ͻ�
     * @param authServiceList
     */
    private List<AuthService> createBatchAuthService(CmngCustom cmngCustom, List<CmngUser> allUserList, Map<String, Empowerment> userEmpowermentMap) {
        List<AuthService> authServiceList = new ArrayList<AuthService>()
        //��ñ��ӿڽ�Ҫ�����Ļ�������ƷID�����»������������������������������
        String oneMonthProductId = ConfigurationHolder.config.ums.baseQuery.oneMonthProductId
        String oneYearsProductId = ConfigurationHolder.config.ums.baseQuery.oneYearsProductId
        String threeYearsProductId = ConfigurationHolder.config.ums.baseQuery.threeYearsProductId

        //ƴװ�����û��������û�����(���µģ�1��12���Ʒ����ڣ��������1���Ʒ�����)
        for (CmngUser anUser: allUserList) {

            //����û���ͣ�����ܶ���
            if (CmngUser.STATUS_SERVICE_PAUSED.equals(anUser.status)) {
                logger.info("The User Status is 'STATUS_SERVICE_PAUSED', Can Not Order BaseService By Portal, UserId:" + anUser.id)
                continue
            }

            //����û�������������������Ŀ�ʼʱ��ͼƷ����ڳ��ȣ������û���Ч������״̬�����жϣ�
            Empowerment empowerment = userEmpowermentMap.get(anUser.id + "")
            Date nextOrderDate = EmpowermentUtil.getEmpowermentNextOrderDateForPortal(empowerment)
            //��������³����Ʒ�������ֵ��Ҫ+1�����Ӳ�����
            int plusCount = 0
            if (DateUtil.getDayOfMonth(nextOrderDate) > 1) {
                logger.info("The BillingCycleCount must add 1,Because next order date not the beginning of a month:" + DateUtil.getDateBasicStr(nextOrderDate))
                plusCount = 1
            }

            // ��װ���۶�����ϵ(���µ�-1��12���Ʒ����ڣ������-36���Ʒ�����-�����Ҳ�ǼƷ�����Ҳ�ǰ���)��
            // ���۷���ʼʱ�����Ե�ǰ��ʼ,ʹ��userId��Ϊ��ǰ���۽����ʶλ
            // ƴװ����1���Ʒ��������۲�Ʒ
            String oneMonthKey = anUser.id + BaseServiceDataBean.UMS_MONTH_BILLINGCYCLECOUNT_KEY
            AuthService anAuthService = createCommodityBatchAuthService(oneMonthKey,
                    oneMonthProductId, cmngCustom, anUser, nextOrderDate,
                    plusCount + BaseServiceDataBean.UMS_MONTH_BILLINGCYCLECOUNT)
            authServiceList.add(anAuthService)
            //ƴװ����12�Ʒ��������۲�Ʒ
            String oneYearKey = anUser.id + BaseServiceDataBean.UMS_ONEYEAR_BILLINGCYCLECOUNT_KEY
            anAuthService = createCommodityBatchAuthService(oneYearKey,
                    oneYearsProductId, cmngCustom, anUser, nextOrderDate,
                    plusCount + BaseServiceDataBean.UMS_ONEYEAR_BILLINGCYCLECOUNT)
            authServiceList.add(anAuthService)
            //ƴװ�����36���Ʒ��������۲�Ʒ
            String threeYearKey = anUser.id + BaseServiceDataBean.UMS_THREEYEAR_BILLINGCYCLECOUNT_KEY
            anAuthService = createCommodityBatchAuthService(threeYearKey,
                    threeYearsProductId, cmngCustom, anUser, nextOrderDate,
                    plusCount + BaseServiceDataBean.UMS_THREEYEAR_BILLINGCYCLECOUNT)
            authServiceList.add(anAuthService)
        }
        return authServiceList
    }


    /**
     * ƴװ�����ö�����ϵ
     */
    private AuthService createCommodityBatchAuthService(String serviceId, String commodityId, CmngCustom custom,
                                                        CmngUser cmngUser, Date startdate, long billingCycleCount) {
        AuthService authService = new AuthService()
        authService.id = Long.parseLong(serviceId)
        authService.commodityId = commodityId
        authService.userid = custom.id
        authService.areaid = custom.areaid
        authService.typeCmngCustomtype = custom.typeCmngCustomtype
        authService.classType = cmngUser.classType
        authService.orderUserId = cmngUser.id
        authService.startdate = startdate
        authService.billingCyclecount = billingCycleCount
        //���ºͰ��겻�����������������
        if (billingCycleCount == 36) {
            authService.isrenew = AuthService.ISRENEW_TRUE
        } else {
            authService.isrenew = AuthService.ISRENEW_FALSE
        }
        authService.isFixedPrice = AuthService.IS_FIXEDPRICE_FALSE
        return authService
    }

    def balance(SMSBalanceAPIRequest request) {
        SMSBalanceAPIResponse response = new SMSBalanceAPIResponse()
        try {
            CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(request.getUUID())
            if (!cmngStbcustomvdo) {
                response.returnCode = StringUtil.UUID_NOT_EXIST
            } else {
                CustomAccountReqBean bean = new CustomAccountReqBean()
                bean.loginCustomId = cmngStbcustomvdo.getDvbcustomidCmngCustom()
                String url = '/account/getCustomAccountInfo';
                AccountBean responseBean = (AccountBean) requestService.requestToCbs(url, bean, AccountBean.class)
                response.balance = "REAL=" + responseBean.accountSnapshot.getCanUseBalanceTotal() + ";SUMNOPAID=0|VIRTUAL=0;SUMNOPAID=0"
                response.returnCode = StringUtil.SUCCESS
            }
        } catch (Exception e) {
            log.error "ums balance error" + e.getMessage()
            response.returnCode = StringUtil.PROCESS_ERROR
        }
        return response
    }
    /**
     * sms������֤
     * @param request
     * @return
     */
    def passwordValid(SMSPwdValAPIRequest request) {
        SMSPwdValAPIResponse response = new SMSPwdValAPIResponse()
        try {
            CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(request.getUUID())
            if (!cmngStbcustomvdo) {
                response.returnCode = StringUtil.UUID_NOT_EXIST
            } else {
                CmngUser cmngUser = CmngUser.get(cmngStbcustomvdo.useridCmngUser)
                if (!cmngUser) {
                    response.returnCode = StringUtil.UUID_NOT_EXIST
                } else {
                    if (cmngUser.getPassword() == null || (cmngUser.getPassword() != null && cmngUser.getPassword().equals(Md5Util.getMD5(request.getPassword())))) {
                        response.returnCode = StringUtil.SUCCESS
                    } else {
                        response.returnCode = StringUtil.PROCESS_ERROR
                    }
                }
            }
        } catch (Exception e) {
            log.error "ums passwordValid error" + e.getMessage()
            response.returnCode = StringUtil.PROCESS_ERROR
        }
        return response
    }
    /**
     * sms�����޸�
     * @param request
     * @return
     */
    def passwordModify(SMSPwdModAPIRequest request) {
        SMSPwdModAPIResponse response = new SMSPwdModAPIResponse()
        CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(request.getUUID())
        CmngUser cmngUser = CmngUser.findById(cmngStbcustomvdo.useridCmngUser)
        if (cmngUser.getPassword() == null || (cmngUser.getPassword() != null && cmngUser.getPassword().equals(Md5Util.getMD5(request.getOldPassword())))) {
            cmngUser.password = Md5Util.getMD5(request.getNewPassword())
            cmngUser.execAction=ExecAction.UPDATE
            cmngUser.updateDate=new Date()
            CmngUser.withTransaction {
                if (!cmngUser.save())
                    throw new DomainSaveException("Save CmngUser Error!", cmngUser)
            }
            CustomSyncReqBean syncReqBean=new CustomSyncReqBean()
            List<CmngUser> cmngUsers=new ArrayList<CmngUser>();
            cmngUsers.add(cmngUser)
            syncReqBean.cmngUsers=cmngUsers
            syncReqBean.setOperatorUserId("ifs")
            syncReqBean.loginCustomId = cmngUser.dvbCostomId
            syncReqBean.areaId = cmngStbcustomvdo.areaid
            syncReqBean.netCodeId = cmngStbcustomvdo.netid

            String url = '/account/syncCmngCustomAndInfo';
            BaseBean responseBean = (BaseBean) requestService.requestToCbs(url, syncReqBean, BaseBean.class)
            if (responseBean.result) {
                response.returnCode = StringUtil.SUCCESS
            } else {
                response.returnCode = StringUtil.PROCESS_ERROR
            }
        } else {
            response.returnCode = StringUtil.PROCESS_ERROR
        }
        return response
    }

    public String process(String method, String requestStr, Class request, Class reqponse) {
        Object requestObj = null
        if (requestStr != null && requestStr.length() > 0) {
            try {
                requestObj = JaxbUtil.getRequest(requestStr, request)
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        Object responseObj = null
        if (requestObj == null) {
            responseObj = reqponse.newInstance()
            //xml��ʽ����
            responseObj.returnCode = StringUtil.XML_FORMAT_ERROR
        } else {
            try {
                responseObj = this."${method}"(requestObj)
            } catch (Exception e) {
                responseObj = reqponse.newInstance()
                //����ʧ��
                responseObj.returnCode = StringUtil.PROCESS_ERROR
                responseObj.returnMsg = MessageUtil.getMessage("RequestService.request.error")
                e.printStackTrace()
            }
        }
        try {
            responseObj.transactionID = requestObj.transactionID
        } catch (Exception ex) {
//            ex.printStackTrace()
        }
        byte[] bytes = JaxbUtil.putResponse(responseObj, reqponse, "UTF-8")
        String responseStr = new String(bytes, "UTF-8")
        return responseStr;
    }
}