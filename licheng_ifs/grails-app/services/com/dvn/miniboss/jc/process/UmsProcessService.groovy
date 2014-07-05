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
     * 第三方充值接口
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

            //创建付款记录
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
            //获取余额账本信息
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
                //增加余额存款日志
                //todo 在crm段统一做吧，不然主键会有问题
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
        //iboss实时扣费delete-已作废


        return response
    }

    /**
     * UMS基本收视费缴费接口，只处理个人客户
     * 基本包订购时间从订购时间下一天开始生效
     * @param request
     * @return
     */
    def baseAccoBank(SMSBaseAccoBankAPIRequest request) {
        SMSBaseAccoBankAPIResponse response = new SMSBaseAccoBankAPIResponse()
        try {
            //禁止用户于月末18点之后进行portal查询和订购
            boolean canProcess = authServiceProcessService.canProcessBaseQueryAndOrder(new Date())
            if(!canProcess){
                throw new BaseException(StringUtil.PROCESS_ERROR,
                        MessageUtil.getMessage("BaseQueryAndBaseAccoBank.MustBefore.8PM.ForLastDayOfMonth"))
            }

            //获得客户信息，用户集合(主从机排序,排除无效用户)
            CmngUser cmngUserReq = cmngUserProcessService.getUserIDByUuId(request.getUUID())
            String hql = "from CmngUser c where c.dvbCostomId=" + cmngUserReq.getDvbCostomId() + " and c.status != " + CmngUser.STATUS_INVALID  + " order by c.classType asc"
            List<CmngUser> allUserList = CmngUser.executeQuery(hql)
            CmngCustom cmngCustom = CmngCustom.findById(cmngUserReq.getDvbCostomId())
            if (cmngCustom == null || !CmngCustom.CUSTOMTYPE_PERSONAL.equals(cmngCustom.customType))
                throw new BaseException(StringUtil.PROCESS_ERROR, MessageUtil.getMessage("BaseAccoBank.NotSupport.CompanyCustom"))

            //进行当前用户是否可以使用Portal方式进行基本包交费处理，逻辑同查询接口
            //获得所有用户的最大基本包服务集合
            Map<String, Empowerment> userEmpowermentMap = getEmpowermentMapByUser(allUserList)
            //获取所属客户对应的每个有效用户的基本包产品(包月，一年，三年)
            List<AuthService> authServiceList = createBatchAuthService(cmngCustom, allUserList, userEmpowermentMap)
            //对有效用户的各种基本包进行批价处理
            Map<String, BaseServiceDataBean> baseServiceDataBeanMap = createPayingData(cmngCustom,authServiceList)
            //计算当前客户是否可以进行Portal基本包订购
            String canOrder = canOrderByPortal(allUserList, baseServiceDataBeanMap)
            //不能通过Portal进行基本包缴费的，抛出操作失败异常
            if (BaseServiceDataBean.ISORNOT_NOT.equals(canOrder))
                throw new BaseException(ExceptionUtil.getUmsExceptionCode(null), ExceptionUtil.getUmsExceptionMessage(null))

            //获得请求计费周期量值
            int billingCountPara = Integer.parseInt(request.getBillingCount())
            //拼装iBoss基本包产品订购请求包，请求iBoss产品订购接口(如果计费周期不等于36，当前产品为包月基本包产品，否则为三年基本包产品)
            String baseServiceProduct = ConfigurationHolder.config.ums.baseQuery.oneMonthProductId
            if (billingCountPara == BaseServiceDataBean.UMS_THREEYEAR_BILLINGCYCLECOUNT)
                baseServiceProduct = ConfigurationHolder.config.ums.baseQuery.threeYearsProductId
            Commodity commodity = Commodity.findById(baseServiceProduct)
            List<AuthService> auths = new ArrayList()
            //用户状态报停	 +  	*		 = 不定购新基本包
            //用户状态正常 + 订购关系有欠费、报停，用户主动退订的 = 补残月:(计费周期加1),查询和缴费时：服务开始时间-按当天走
            //用户状态正常 + 其他的订购关系  = 不补残月,查询和缴费时:服务开始时间-都取订购关系中最大的那个截止时间+1天,刨除（服务终止，用户主动退订）订购关系)
            for (CmngUser cmngUser: allUserList) {

                //如果当前用户不能从Portal订购，继续下一个用户
                //如果用户报停，不能订购
                if (CmngUser.STATUS_SERVICE_PAUSED.equals(cmngUser.status)) {
                    logger.info("The User Status is 'STATUS_SERVICE_PAUSED', Can Not Order BaseService By Portal, UserId:" + cmngUser.id)
                    continue
                }

                //获得用户批价用续服务基本包的开始时间和计费周期长度（根据用户有效基本包状态进行判断）
                int currUserBillingCountTemp = billingCountPara
                Empowerment empowerment = userEmpowermentMap.get(cmngUser.id + "")
                Date startDate = EmpowermentUtil.getEmpowermentNextOrderDateForPortal(empowerment)
                //如果不是月初，计费周期量值需要+1，增加残月量
                if (DateUtil.getDayOfMonth(startDate) > 1) {
                    logger.info("The BillingCycleCount must add 1,Because The nextOrderDate not beginning Date of a month:" + DateUtil.getDateBasicStr(startDate))
                    currUserBillingCountTemp = currUserBillingCountTemp + 1
                }

                //拼装新基本包订购关系
                AuthService anAuthService = createProductAuthService(commodity.id, AuthService.PAYMODE_PAYBEFOREUSE,
                        DateUtil.getDateBasicStr(startDate), null, AuthService.IS_FIXEDPRICE_FALSE,
                        currUserBillingCountTemp, 999999999L)
                anAuthService.typeCmngCustomtype = cmngCustom.typeCmngCustomtype
                anAuthService.netid = cmngCustom.netid
                anAuthService.classType = cmngUser.classType
                anAuthService.orderUserId = cmngUser.id
                auths.add(anAuthService)

            }

            //拼装付款记录,如果余额支付不产生付款记录
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
            //CBS接口Bean
            AuthOrderReqBean authOrderReqBean = new AuthOrderReqBean()
            authOrderReqBean.setLoginCustomId(cmngCustom.id)
            authOrderReqBean.setOperatorUserId("ifs")
            authOrderReqBean.setPayment(acctPayment)
            authOrderReqBean.setAuthServices(auths)
            authOrderReqBean.areaId = cmngCustom.areaid
            authOrderReqBean.netCodeId = cmngCustom.netid

            //请求CBS系统，进行客户开户及产品订购动作
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
            log.error "ums baseAccoBank error：" + e.getMessage()
            e.printStackTrace()
        }
        return response
    }


    //创建基本包订购关系

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
        //包月和包年(base80)续服务，三年包(base120)不续服务
        if (billingCyclecount >= 36) {
            //需要判断普通三年包和残月三年包
            productAuthService.isrenew = AuthService.ISRENEW_FALSE
        } else {
            productAuthService.isrenew = AuthService.ISRENEW_TRUE
        }
        productAuthService.isFixedPrice = isFixedPrice
        productAuthService.pricefix = pricefix
        productAuthService.refpricefix = pricefix
        productAuthService.billingCyclecount = billingCyclecount    //计费周期量值
        return productAuthService
    }

    /**
     * UMS基本收视费查询接口，只处理个人客户
     * @param request
     * @return
     */
    def baseQuery(SMSBaseQueryAPIRequest request) {
        SMSBaseQueryAPIResponse response = new SMSBaseQueryAPIResponse()
        try {
            //禁止用户于月末18点之后进行portal查询和订购
            boolean canProcess = authServiceProcessService.canProcessBaseQueryAndOrder(new Date())
            if(!canProcess){
                throw new BaseException(StringUtil.PROCESS_ERROR,
                        MessageUtil.getMessage("BaseQueryAndBaseAccoBank.MustBefore.8PM.ForLastDayOfMonth"))
            }

            //客户信息
            CmngUser cmngUser = cmngUserProcessService.getUserIDByUuId(request.getUUID())
            CmngCustom cmngCustom = CmngCustom.findById(cmngUser.getDvbCostomId())
            if (cmngCustom == null || !CmngCustom.CUSTOMTYPE_PERSONAL.equals(cmngCustom.customType))
                throw new BaseException(StringUtil.PROCESS_ERROR, MessageUtil.getMessage("BaseQuery.NotSupport.CompanyCustom"))

            //用户集合(主从机排序,排除无效用户)
            String hql = "from CmngUser c where c.dvbCostomId=" + cmngUser.getDvbCostomId() + " and c.status != " + cmngUser.STATUS_INVALID  + " order by c.classType asc"
            List<CmngUser> allUserList = CmngUser.executeQuery(hql)

            //获得所有用户的最大基本包服务集合
            Map<String, Empowerment> userEmpowermentMap = getEmpowermentMapByUser(allUserList)

            //获取所属客户对应的每个有效用户的基本包产品(包月，一年，三年)
            List<AuthService> authServiceList = createBatchAuthService(cmngCustom, allUserList, userEmpowermentMap)
            //对有效用户的各种基本包进行批价处理
            Map<String, BaseServiceDataBean> baseServiceDataBeanMap = createPayingData(cmngCustom,authServiceList)

            //拼装“基本服务数据”
            //“基本服务数据”格式：（type=1,status=1,sumnopaid=2300,enddate=20100531;*）
            //终止、退订状态的订购关系返回状态为0（退订），主动暂停状态的订购关系返回状态为2（主动暂停）
            StringBuffer baseServiceData = new StringBuffer()
            Map<String, Long> queryPriceMap = new HashMap<String, Long>()
            int index = 1
            for (CmngUser userBrother: allUserList) {

                //获得生效基本包状态和基本服务的最大终止时间
                Empowerment empowerment = userEmpowermentMap.get(userBrother.id + "")
                AuthService authServicePara = getValidStatusAndMaxEndDate(userBrother, empowerment)

                //拼装某个用户的“基本服务数据”
                BaseServiceDataBean baseServiceDataBean = baseServiceDataBeanMap.get(userBrother.id + "")
                if (baseServiceDataBean == null) {
                    baseServiceDataBean = new BaseServiceDataBean()
                    baseServiceDataBeanMap.put(userBrother.id + "", baseServiceDataBean)
                }
                baseServiceDataBean.setType(userBrother.classType)
                baseServiceDataBean.setSumNoPaid("0")
                baseServiceDataBean.setStatus(authServicePara.status)
                baseServiceDataBean.setEndDate(DateUtil.getDateDayStr(authServicePara.enddate))

                //最后一个用户的信息不需要分号分隔
                baseServiceData.append(baseServiceDataBean.convertBeanToString())
                if (index < allUserList.size())
                    baseServiceData.append(BaseServiceDataBean.sign_semicolon)
                index++

                //进行批价结果累加计算
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

            //获取CBS中余额快照对象的可用余额
            AccountBean accountInfoBean = syncCbsDataProcessService.getAccountInfoByCustomId(cmngUser.dvbCostomId)
            AccountSnapshot acctoutSnapshot = accountInfoBean.getAccountSnapshot()
            long canUserBalance = acctoutSnapshot.getCanUseBalanceTotal()
            //计算当前客户是否可以进行Portal基本包订购
            String canOrder = canOrderByPortal(allUserList, baseServiceDataBeanMap)
            //拼装响应信息
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
            log.error "ums baseQuery error：" + e.getMessage()
            e.printStackTrace()
        }
        return response
    }

    //获得所有用户的所有服务集合
    private Map<String, Empowerment> getEmpowermentMapByUser(List<CmngUser> allUserList) {
        Map<String, Empowerment> userEmpowermentMap = new HashMap<String, Empowerment>()
        for (CmngUser user: allUserList) {
            Empowerment empowerment = getEmpowermentByEndDateMax(user.id)
            userEmpowermentMap.put(user.id + "", empowerment)
        }
        return userEmpowermentMap
    }


    //获得某个用户结束时间最大的基本服务，个人用户订购新服务的起始时间基于服务的endDate
    private Empowerment getEmpowermentByEndDateMax(long userId) {
        String getEmpListHql = "from Empowerment e where e.userId = '" + userId + "' and e.status = '" + Empowerment.STATUS_VALID + "'" +
                " and chargeBillType = '" + ChargeBillType.SERVICE_BASE + "' and e.endDate Is Not Null order by e.endDate desc"

        List empowermentList = Empowerment.executeQuery(getEmpListHql)
        if(empowermentList == null || empowermentList.size() == 0)
            return null
        Empowerment empowerment = empowermentList.get(0)
        if(empowerment == null || empowerment.endDate == null)
            return null
        //如果服务结束时间在当前时间之前说明服务已经过期返回空
        Date now = new Date()
        Date endDate = DateUtil.getNextDay(empowerment.endDate)
        if(endDate.before(now))
            return null

        return empowerment
    }


    //Portal基本包查询接口：交费限制根据”Isornot“属性返回
    //所有条件独立判断

    public String canOrderByPortal(List<CmngUser> allUserList, Map<String, BaseServiceDataBean> userBaseServiceDataMap) {
        //获取主机用户
        CmngUser masterUser = null
        for (CmngUser cmngUser: allUserList) {
            //主机判断
            if (CmngStbType.MASTER.equals(cmngUser.classType)) {
                masterUser = cmngUser
                break
            }
        }
        //主机不存在，不允许Portal缴费
        if(masterUser == null){
            logger.info("MasterSTB CmngUser is Null! Can Not Order BaseService By Portal!")
            return BaseServiceDataBean.ISORNOT_NOT
        }
        //主机报停，不允许Portal缴费
        if (CmngUser.STATUS_SERVICE_PAUSED.equals(masterUser.status)) {
            logger.info("MasterSTB CmngUser.STATUS_SERVICE_PAUSED, Can Not Order BaseService By Portal, UUID:" + masterUser.id)
            return BaseServiceDataBean.ISORNOT_NOT
        }
        //主机基本包批价金额小于23元的，不允许Portal缴费
        BaseServiceDataBean baseServiceDataBean = userBaseServiceDataMap.get(masterUser.id + "")
        if (baseServiceDataBean != null && baseServiceDataBean.getOneMonthPrice() < BaseServiceDataBean.UMS_ALLOW_MIN_PRICE) {
            logger.info("MasterSTB BaseService Price less 23 YUAN, Can Not Order BaseService By Portal, UUID:" + masterUser.id)
            return BaseServiceDataBean.ISORNOT_NOT
        }
        return BaseServiceDataBean.ISORNOT_YES
    }

    //获取当前某个用户有效基本包状态和服务最大结束时间
    //返回portal的服务状态类型三种：1-正常，2-主动暂停，4-终止（包括：3-欠费暂停）
    private AuthService getValidStatusAndMaxEndDate(CmngUser cmngUser, Empowerment empowerment) {
        AuthService authServicePara = new AuthService()
        //获得服务的结束时间
        authServicePara.enddate = EmpowermentUtil.getEmpowermentEndDateForPortal(empowerment)

        //对于服务不存在或者服务endDate不存在情况的处理
        if (empowerment == null || empowerment.endDate == null) {
            authServicePara.status = AuthService.STATUS_STOPPED
            logger.info("There is nothing BaseService, Status:" + authServicePara.status + " EndDate:" + authServicePara.enddate)
            return authServicePara
        }

        //对于用户主动报停基本包情况处理
        //如果当前用户状态为主动报停，那么最大结束时间订购关系的状态只有可能是：预订购，用户主动退订，用户报停
        if (CmngUser.STATUS_SERVICE_PAUSED.equals(cmngUser.status)) {
            authServicePara.status = AuthService.STATUS_USER_PAUSE
            logger.info("The User Status has 'STATUS_USER_PAUSE', Status:" + authServicePara.status +
                    " EndDate:" + authServicePara.enddate + " ServiceId:" + empowerment.id)
            return authServicePara
        }

        //针对各种最终服务状态进行，当前生效订购关系和服务终止时间分析获取
        if (Empowerment.STATUS_VALID.equals(empowerment.status)) {
            authServicePara.status = AuthService.STATUS_RUNNING
        } else {
            authServicePara.status = AuthService.STATUS_STOPPED
        }
        logger.info("BaseService Status:" + authServicePara.status + " EndDate:" + authServicePara.enddate + " empowermentId:" + empowerment?.id)
        return authServicePara
    }

    /**
     * 计算所有用户的各种基本包缴费金额(包月，包年，三年)
     * @param authServiceList 订购关系集合
     * @param allUserList 用户集合
     * @param userDataMap 用户和各种基本包缴费金额
     * @return
     */
    private Map<String, BaseServiceDataBean> createPayingData(CmngCustom cmngCustom,ArrayList<AuthService> authServiceList) {

        Map<String, BaseServiceDataBean> priceResultMap = new HashMap<String, BaseServiceDataBean>()
        if (authServiceList.size() == 0) {
            return priceResultMap
        }

        //保存三种计费周期量值的两种基本包产品的价格(1个月包月产品，12个月包月产品，36个月三年包产品)
        //拼装响应数据例子:<Paying>count=1,paying=2300;count=12,paying=27600;count=36,paying=80000;</Paying>
        //获取批量批价结果
        CommodityPricingRes responseBatchCommodityPrice = syncCbsDataProcessService.getBatchCommodityPrice(cmngCustom,authServiceList)

        //循环累加有效用户的批价数据
        for (AuthService auth: responseBatchCommodityPrice.services) {
            //获得用户信息容器中基本包数据对象，保存用户各种基本包缴费金额
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
     * UMS基本收视费查询接口中需要进行批价的订购关系集合
     * @param allUserBaseServiceMap 基本包集合(每个用户对应一个有效基本包)
     * @param cmngCustom 客户
     * @param authServiceList
     */
    private List<AuthService> createBatchAuthService(CmngCustom cmngCustom, List<CmngUser> allUserList, Map<String, Empowerment> userEmpowermentMap) {
        List<AuthService> authServiceList = new ArrayList<AuthService>()
        //获得本接口将要订购的基本包产品ID（单月基本包、三年基本包、其他基本包）
        String oneMonthProductId = ConfigurationHolder.config.ums.baseQuery.oneMonthProductId
        String oneYearsProductId = ConfigurationHolder.config.ums.baseQuery.oneYearsProductId
        String threeYearsProductId = ConfigurationHolder.config.ums.baseQuery.threeYearsProductId

        //拼装所有用户的批价用基本包(包月的：1和12个计费周期，三年包：1个计费周期)
        for (CmngUser anUser: allUserList) {

            //如果用户报停，不能订购
            if (CmngUser.STATUS_SERVICE_PAUSED.equals(anUser.status)) {
                logger.info("The User Status is 'STATUS_SERVICE_PAUSED', Can Not Order BaseService By Portal, UserId:" + anUser.id)
                continue
            }

            //获得用户批价用续服务基本包的开始时间和计费周期长度（根据用户有效基本包状态进行判断）
            Empowerment empowerment = userEmpowermentMap.get(anUser.id + "")
            Date nextOrderDate = EmpowermentUtil.getEmpowermentNextOrderDateForPortal(empowerment)
            //如果不是月初，计费周期量值需要+1，增加残月量
            int plusCount = 0
            if (DateUtil.getDayOfMonth(nextOrderDate) > 1) {
                logger.info("The BillingCycleCount must add 1,Because next order date not the beginning of a month:" + DateUtil.getDateBasicStr(nextOrderDate))
                plusCount = 1
            }

            // 组装批价订购关系(包月的-1和12个计费周期，三年包-36个计费周期-三年包也是计费类型也是按月)，
            // 批价服务开始时间是以当前开始,使用userId作为当前批价结果标识位
            // 拼装包月1个计费周期批价产品
            String oneMonthKey = anUser.id + BaseServiceDataBean.UMS_MONTH_BILLINGCYCLECOUNT_KEY
            AuthService anAuthService = createCommodityBatchAuthService(oneMonthKey,
                    oneMonthProductId, cmngCustom, anUser, nextOrderDate,
                    plusCount + BaseServiceDataBean.UMS_MONTH_BILLINGCYCLECOUNT)
            authServiceList.add(anAuthService)
            //拼装包月12计费周期批价产品
            String oneYearKey = anUser.id + BaseServiceDataBean.UMS_ONEYEAR_BILLINGCYCLECOUNT_KEY
            anAuthService = createCommodityBatchAuthService(oneYearKey,
                    oneYearsProductId, cmngCustom, anUser, nextOrderDate,
                    plusCount + BaseServiceDataBean.UMS_ONEYEAR_BILLINGCYCLECOUNT)
            authServiceList.add(anAuthService)
            //拼装三年包36个计费周期批价产品
            String threeYearKey = anUser.id + BaseServiceDataBean.UMS_THREEYEAR_BILLINGCYCLECOUNT_KEY
            anAuthService = createCommodityBatchAuthService(threeYearKey,
                    threeYearsProductId, cmngCustom, anUser, nextOrderDate,
                    plusCount + BaseServiceDataBean.UMS_THREEYEAR_BILLINGCYCLECOUNT)
            authServiceList.add(anAuthService)
        }
        return authServiceList
    }


    /**
     * 拼装批价用订购关系
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
        //包月和包年不续服务，三年包续服务
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
     * sms密码验证
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
     * sms密码修改
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
            //xml格式错误
            responseObj.returnCode = StringUtil.XML_FORMAT_ERROR
        } else {
            try {
                responseObj = this."${method}"(requestObj)
            } catch (Exception e) {
                responseObj = reqponse.newInstance()
                //处理失败
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