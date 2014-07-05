package com.dvn.miniboss.jc.process

import com.bmsp.message.BodyMessage
import com.bmsp.util.StatusConstant
import com.dvn.common.RequestService
import com.dvn.miniboss.product.Commodity
import com.dvn.miniboss.serv.Empowerment
import com.miniboss.acct.utils.DateUtil
import com.miniboss.acct.utils.FileUtil
import com.miniboss.acct.utils.JdbcUtils
import com.miniboss.bean.auth.AuthOrderReqBean
import com.miniboss.bean.auth.AuthSyncReq
import com.miniboss.bean.authservice.AuthServReqBean
import com.miniboss.bean.bankcard.CommodityPlayReqBean
import com.miniboss.bean.bankcard.CommodityPlayResBean
import com.miniboss.exception.BaseException
import com.miniboss.sync.ftp.CmngCustomInfoBean
import com.miniboss.sync.ftp.ProductOrderBean
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import javax.sql.DataSource
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.hibernate.SessionFactory
import com.dvn.miniboss.acct.*
import com.dvn.miniboss.oldsms.*
import com.miniboss.bean.*
import com.miniboss.bean.ums.BaseServiceDataBean

class AuthServiceProcessService {
    def RequestService requestService
    def String cbs = ConfigurationHolder.config.cbs.url
    def CmngStbcustomvdoProcessService cmngStbcustomvdoProcessService
    def CmngUserProcessService cmngUserProcessService
    def SessionFactory sessionFactory
    def DataSource dataSource


    boolean transactional = false
    //基本包服务状态
    public final static int BASE_SERVICE_STATUS_VALID = 1
    public final static int BASE_SERVICE_STATUS_INVALID = 3

    //设定分页查询数据时每页查询数据量
    public final static int ONE_PAGE_DATA_SIZE = 1000
    //设定订购关系增量数据的"updatedate"平移时间
    public final static int AUTHSERVICE_INCREMENT_SYNC_TIME_OFFSET = -20

    /**
     * 增量订购关系同步，15分钟一次，获取当前时间之前20分钟更新的订购关系
     * @return
     * @throws Exception
     */
    public void getAuthServiceIncrementSyncStr(String localFile, String fileName) throws Exception {

        //todo：更改同步偏移时间类型和量值
        int beginDateOffset = AUTHSERVICE_INCREMENT_SYNC_TIME_OFFSET
        Date endDate = new Date()
        Date beginDate = DateUtil.getPosDate(endDate, beginDateOffset, Calendar.MINUTE)
        def criteria=Empowerment.createCriteria()
        List<Empowerment> empowerments = criteria {
            eq("status", Empowerment.STATUS_VALID)
            eq("chargeBillType", ChargeBillType.SERVICE_VOD_MONTH)
            eq("groupType", CmngUser.GROUPTYPE_PERSON)
            eq("packageType", Commodity.PACKAGETYPE_NORMAL)
            isNotNull("minBeginDate")
            isNotNull("maxEndDate")
            between('updateDate', beginDate, endDate)
        }


//        def c = AuthService.createCriteria()
//        List<AuthService> results = c {
//            between('updatedate', beginDate, endDate)
//            eq("billTypeId", ChargeBillType.SERVICE_VOD_MONTH)
//            not {
//                eq("status", AuthService.STATUS_INVALID)
//                eq("status", AuthService.STATUS_STB_INSTALL)
//            }
//        }
        //增量订购关系同步数据，本地保存
        makeAuthServiceSyncDataFile(empowerments, localFile, fileName)
    }



    def orderOnce(BodyMessage requestBean) {
        CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(requestBean.getDevNo())
        if (!cmngStbcustomvdo) {
            log.error "cmngStbcustomvdo not exist[UUID]: " + requestBean.getDevNo()
            return StatusConstant.USER_INFO_ERROR
        }

        //1.客户
        CmngCustom cmngCustom = CmngCustom.get(cmngStbcustomvdo.dvbcustomidCmngCustom)
        if (!cmngCustom) {
            log.error "cmngCustom not exist[UUID]: " + requestBean.getDevNo()
            return StatusConstant.USER_INFO_ERROR
        }

        //2.客户详细信息
        CmngCustominfo cmngCustominfo = CmngCustominfo.get(cmngCustom.customidCmngCustominfo)
        if (!cmngCustominfo) {
            log.error "cmngCustominfo not exist[UUID]: " + requestBean.getDevNo()
            return StatusConstant.USER_INFO_ERROR
        }

        //3.获取用户信息
        CmngUser cmngUser = CmngUser.get(cmngStbcustomvdo.useridCmngUser)
        if (!cmngUser) {
            log.error "cmngUser not exist[UUID]: " + requestBean.getDevNo()
            return StatusConstant.USER_INFO_ERROR
        }

        //订购所需的数据
        String isFixedPrice = AuthService.IS_FIXEDPRICE_FALSE
        String paymode = AuthService.PAYMODE_PAYBEFOREUSE
        Date startTime = new Date()
        //瓶装订购关系，基本包订购其实就是续服务，其他就是订购

//        String commodityId = PublishAssetProcessService.getClickCommodity().getId()
//        if (commodityId == null) {
//            log.error "publishAsset not exist[AssetId]: " + requestBean.assetId
//            return StatusConstant.PublishAsset_INFO_ERROR
//        }
//        long price=PublishAssetProcessService.getPrice(requestBean.getAssetId())
        AuthService productAuthService = createProductAuthService(requestBean.getSpId(), 0, paymode, startTime, null, isFixedPrice, requestBean.getBillCycleCount(), AuthService.ISRENEW_TRUE)
        productAuthService.setDeviceId(requestBean.getAssetId())
        productAuthService.orderUserId = cmngUser.getId()
        productAuthService.typeCmngCustomtype = cmngCustom.typeCmngCustomtype
        productAuthService.areaid = cmngCustom.areaid
        productAuthService.classType = cmngStbcustomvdo.classType
        List<AuthService> authServiceList = new ArrayList<AuthService>()
        authServiceList.add(productAuthService)
//        cmngUser.authServices = authServiceList

        //拼装UserList
//        List<CmngUser> userList = new ArrayList<CmngUser>()
//        userList.add(cmngUser)

        //CBS接口Bean
        AuthOrderReqBean authOrderReqBean = new AuthOrderReqBean()
        authOrderReqBean.authServices = authServiceList
        authOrderReqBean.setOperatorUserId("ifs")
        authOrderReqBean.loginCustomId = cmngCustom.id
        authOrderReqBean.areaId = cmngCustom.areaid
        authOrderReqBean.netCodeId = cmngCustom.netid

//        CustomAcctSubmitBean acctSubmitBean = new CustomAcctSubmitBean()
//        acctSubmitBean.setCustom(cmngCustom)
//        acctSubmitBean.setCustomInfo(cmngCustominfo)
//        acctSubmitBean.setUsers(userList)
//        acctSubmitBean.setOperatorId("ifs")
//        acctSubmitBean.setOperatorUserId("ifs")
//        acctSubmitBean.setLoginCustomId(cmngCustom.id)
        String url = '/account/orderProduct';
        String errorCode = orderToCbs(authOrderReqBean, url)
        return errorCode
    }

    def BodyMessage orderMonth(BodyMessage requestBean, BodyMessage responseBean) {

        //禁止用户于月末18点之后进行portal查询和订购
        boolean canProcess = canProcessBaseQueryAndOrder(new Date())
        if (!canProcess) {
            log.error "BaseQueryAndBaseAccoBank Must Before 8PM For LastDayOfMonth![UUID]: " + requestBean.getDevNo()
            responseBean.errCode = StatusConstant.USER_INFO_ERROR
            return responseBean
        }

        CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(requestBean.getDevNo())
        if (!cmngStbcustomvdo) {
            log.error "cmngStbcustomvdo not exist[UUID]: " + requestBean.getDevNo()
            responseBean.errCode = StatusConstant.USER_INFO_ERROR
            return responseBean
        }

        //1.客户
        CmngCustom cmngCustom = CmngCustom.get(cmngStbcustomvdo.dvbcustomidCmngCustom)
        if (!cmngCustom) {
            log.error "cmngCustom not exist[UUID]: " + requestBean.getDevNo()
            responseBean.errCode = StatusConstant.USER_INFO_ERROR
            return responseBean
        }

        //2.客户详细信息
        CmngCustominfo cmngCustominfo = CmngCustominfo.get(cmngCustom.customidCmngCustominfo)
        if (!cmngCustominfo) {
            log.error "cmngCustominfo not exist[UUID]: " + requestBean.getDevNo()
            responseBean.errCode = StatusConstant.USER_INFO_ERROR
            return responseBean
        }

        //3.获取用户信息
        CmngUser cmngUser = CmngUser.get(cmngStbcustomvdo.useridCmngUser)
        if (!cmngUser) {
            log.error "cmngUser not exist[UUID]: " + requestBean.getDevNo()
            responseBean.errCode = StatusConstant.USER_INFO_ERROR
            return responseBean
        }

        //订购所需的数据
        String isFixedPrice = AuthService.IS_FIXEDPRICE_FALSE
        String paymode = AuthService.PAYMODE_PAYBEFOREUSE
        Date startTime = new Date()
        //下月生效,获取下月的账期开始时间,按次的这个值没有，即为当时生效
        if ("2".equals(requestBean.getOrderType())) {
            Calendar calendar = Calendar.getInstance()
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1, 0, 0, 0)

            Date nextMonthFirstDay = calendar.getTime()
            AcctBillingCycle acctBillingCycle = AcctBillingCycleService.getAcctBillingCycle(nextMonthFirstDay)
            startTime = acctBillingCycle.getCycleBeginDate()
            //包月订购的结束时间
            responseBean.endTime = DateUtil.getDateDayStr(acctBillingCycle.getCycleEndDate())
        } else {
            //当月生效的订购关系
            AcctBillingCycle acctBillingCycle = AcctBillingCycleService.getAcctBillingCycle(startTime)
            //包月订购的结束时间
            responseBean.endTime = DateUtil.getDateDayStr(acctBillingCycle.getCycleEndDate())
        }
        //订购的生效时间
        responseBean.startTime = DateUtil.getDateDayStr(startTime)
        //瓶装订购关系，基本包订购其实就是续服务，其他就是订购
        AuthService productAuthService = createProductAuthService(requestBean.getProductId(), 0, paymode, startTime, null, isFixedPrice, requestBean.getBillCycleCount(), AuthService.ISRENEW_TRUE)
        List<AuthService> authServiceList = new ArrayList<AuthService>()
        productAuthService.orderUserId = cmngUser.getId()
        productAuthService.typeCmngCustomtype = cmngCustom.typeCmngCustomtype
        productAuthService.areaid = cmngCustom.areaid
        productAuthService.classType = cmngStbcustomvdo.classType
        authServiceList.add(productAuthService)
//        cmngUser.authServices = authServiceList
//
//        拼装UserList
//        List<CmngUser> userList = new ArrayList<CmngUser>()
//        userList.add(cmngUser)

        AuthOrderReqBean authOrderReqBean = new AuthOrderReqBean()
        authOrderReqBean.authServices = authServiceList
        authOrderReqBean.setOperatorUserId("ifs")
        authOrderReqBean.loginCustomId = cmngCustom.id
        authOrderReqBean.areaId = cmngCustom.areaid
        authOrderReqBean.netCodeId = cmngCustom.netid

//        //CBS接口Bean
//        CustomAcctSubmitBean acctSubmitBean = new CustomAcctSubmitBean()
//        acctSubmitBean.setCustom(cmngCustom)
//        acctSubmitBean.setCustomInfo(cmngCustominfo)
//        acctSubmitBean.setUsers(userList)
//        acctSubmitBean.setLoginCustomId(cmngCustom.id)
//        acctSubmitBean.setOperatorUserId("ifs")
//        acctSubmitBean.setOperatorId("ifs")
        String url = '/account/orderProduct';
        String errorCode = orderToCbs(authOrderReqBean, url)

        responseBean.errCode = errorCode
        return responseBean
    }
    /**
     * 银行缴费订购
     * @param request
     * @param methodId
     * @return
     */
    def orderPay(OrderPayReq request, String methodId) {
        OrderPayRes payRes = new OrderPayRes()
        try {
            CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(request.getUuId())
            if (!cmngStbcustomvdo) {
                log.error "cmngStbcustomvdo not exist[UUID]: " + request.getUuId()
                payRes.result = OrderPayRes.RESULT_FAILURE
                return payRes
            }

            //1.客户
            CmngCustom cmngCustom = CmngCustom.get(cmngStbcustomvdo.dvbcustomidCmngCustom)
            if (!cmngCustom) {
                log.error "cmngCustom not exist[UUID]: " + request.getUuId()
                payRes.result = OrderPayRes.RESULT_FAILURE
                return payRes
            }

            //2.客户详细信息
            CmngCustominfo cmngCustominfo = CmngCustominfo.get(cmngCustom.customidCmngCustominfo)
            if (!cmngCustominfo) {
                log.error "cmngCustominfo not exist[UUID]: " + request.getUuId()
                payRes.result = OrderPayRes.RESULT_FAILURE
                return payRes
            }

            //3.获取用户信息
            CmngUser cmngUser = CmngUser.get(cmngStbcustomvdo.useridCmngUser)
            if (!cmngUser) {
                log.error "cmngUser not exist[UUID]: " + request.getUuId()
                payRes.result = CommodityPlayResBean.RESULT_FAILURE
                return payRes
            }
            //订购所需的数据
            String isFixedPrice = AuthService.IS_FIXEDPRICE_FALSE
            String paymode = AuthService.PAYMODE_PAYBEFOREUSE

            //传递来的开始时间是免费开始时间，加上订购周期才是订购的真正开始时间，也就是说这是一个免费期的订购关系
            int BillingCycleCount = Integer.parseInt(request.getBillingCycleCount())
            Date startTime = DateUtil.getPreDateByMonth(request.getBeginDate(), (0 - BillingCycleCount))
            String productId = request.getProductId();
            //如果产品在我们这边无法查询到的话就认为是按次产品
//            Commodity commodity=Commodity.findById(productId)
//            if(!commodity){
//                productId=PublishAssetProcessService.getClickCommodity().getId()
//            }
//            long price =PublishAssetProcessService.getPrice(request.getResourceId())

            //拼装订购关系，基本包订购其实就是续服务，其他就是订购
            AuthService productAuthService = createProductAuthService(productId, 0, paymode, startTime, request.getBeginDate(), isFixedPrice, Integer.parseInt(request.getBillingCycleCount()), AuthService.ISRENEW_TRUE)
            productAuthService.setDeviceId(request.getResourceId())
            productAuthService.orderUserId = cmngUser.getId()
            productAuthService.typeCmngCustomtype = cmngCustom.typeCmngCustomtype
            productAuthService.areaid = cmngCustom.areaid
            productAuthService.classType = cmngStbcustomvdo.classType
            List<AuthService> authServiceList = new ArrayList<AuthService>()
            authServiceList.add(productAuthService)
//            cmngUser.authServices = authServiceList

            //拼装UserList
//            List<CmngUser> userList = new ArrayList<CmngUser>()
//            userList.add(cmngUser)

            //拼装付款记录，企业客户就一个记录
            AcctPayment acctPayment = new AcctPayment()
            acctPayment.setPaymentMethodId(methodId)
            acctPayment.setAmount(request.pay)
            acctPayment.setStatus(AcctPayment.STATE_VALID)
            acctPayment.setNetCodeId(cmngCustom.getNetid())
            acctPayment.setBankBillId(request.getTransId())


            AuthOrderReqBean authOrderReqBean = new AuthOrderReqBean()
            authOrderReqBean.authServices = authServiceList
            authOrderReqBean.setOperatorUserId("ifs")
            authOrderReqBean.loginCustomId = cmngCustom.id
            authOrderReqBean.areaId = cmngCustom.areaid
            authOrderReqBean.netCodeId = cmngCustom.netid
            authOrderReqBean.payment = acctPayment
            //CBS接口Bean
//            CustomAcctSubmitBean acctSubmitBean = new CustomAcctSubmitBean()
//            acctSubmitBean.setCustom(cmngCustom)
//            acctSubmitBean.setCustomInfo(cmngCustominfo)
//            acctSubmitBean.setUsers(userList)
//            acctSubmitBean.setLoginCustomId(cmngCustom.id)
//            acctSubmitBean.setOperatorUserId("ifs")
//            acctSubmitBean.setOperatorId("ifs")
            String url = "/account/orderProduct";
            String errorCode = orderToCbs(authOrderReqBean, url)
            if (StatusConstant.BILLING_SUCCESS.equals(errorCode)) {
                payRes.result = OrderPayRes.RESULT_SUCCESS
            } else {
                payRes.result = OrderPayRes.RESULT_FAILURE
            }
        } catch (Exception e) {
            payRes.result = OrderPayRes.RESULT_FAILURE
        }
        return payRes
    }

    //银行进行扣费，iboss只保留下定购关系记录备用，目前只有单条订购关系同步,并且不续服务（用来控制误同步的包月定购关系）
    def bankCardOrder(CommodityPlayReqBean commodityPlayReqBean, CommodityPlayResBean commodityPlayResBean) {
        try {
            CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(commodityPlayReqBean.getUuId())
            if (!cmngStbcustomvdo) {
                log.error "cmngStbcustomvdo not exist[UUID]: " + commodityPlayReqBean.getUuId()
                commodityPlayResBean.result = CommodityPlayResBean.RESULT_FAILURE
                return commodityPlayResBean
            }

            //1.客户
            CmngCustom cmngCustom = CmngCustom.get(cmngStbcustomvdo.dvbcustomidCmngCustom)
            if (!cmngCustom) {
                log.error "cmngCustom not exist[UUID]: " + commodityPlayReqBean.getUuId()
                commodityPlayResBean.result = CommodityPlayResBean.RESULT_FAILURE
                return commodityPlayResBean
            }

            //2.客户详细信息
            CmngCustominfo cmngCustominfo = CmngCustominfo.get(cmngCustom.customidCmngCustominfo)
            if (!cmngCustominfo) {
                log.error "cmngCustominfo not exist[UUID]: " + commodityPlayReqBean.getUuId()
                commodityPlayResBean.result = CommodityPlayResBean.RESULT_FAILURE
                return commodityPlayResBean
            }

            //3.获取用户信息
            CmngUser cmngUser = CmngUser.get(cmngStbcustomvdo.useridCmngUser)
            if (!cmngUser) {
                log.error "cmngUser not exist[UUID]: " + commodityPlayReqBean.getUuId()
                commodityPlayResBean.result = CommodityPlayResBean.RESULT_FAILURE
                return commodityPlayResBean
            }
            //订购所需的数据
            String isFixedPrice = AuthService.IS_FIXEDPRICE_FALSE
            String paymode = AuthService.PAYMODE_PAYBEFOREUSE

            //传递来的开始时间是免费开始时间，加上订购周期才是订购的真正开始时间，也就是说这是一个免费期的订购关系
            //有问题，会扣押金
            int BillingCycleCount = Integer.parseInt(commodityPlayReqBean.getBillingCycleCount())
            Date startTime = DateUtil.getPreDateByMonth(commodityPlayReqBean.getBeginDate(), (0 - BillingCycleCount))

            //拼装订购关系，基本包订购其实就是续服务，其他就是订购
            AuthService productAuthService = createProductAuthService(commodityPlayReqBean.getProductId(), 0, paymode, startTime, commodityPlayReqBean.getBeginDate(), isFixedPrice, Integer.parseInt(commodityPlayReqBean.getBillingCycleCount()), AuthService.ISRENEW_FALSE)

            productAuthService.orderUserId = cmngUser.getId()
            productAuthService.typeCmngCustomtype = cmngCustom.typeCmngCustomtype
            productAuthService.areaid = cmngCustom.areaid
            productAuthService.classType = cmngStbcustomvdo.classType
            List<AuthService> authServiceList = new ArrayList<AuthService>()
            authServiceList.add(productAuthService)
            
//            cmngUser.authServices = authServiceList
//
//            //拼装UserList
//            List<CmngUser> userList = new ArrayList<CmngUser>()
//            userList.add(cmngUser)


            AuthSyncReq authSyncReq = new AuthSyncReq()
            authSyncReq.authServices = authServiceList
            authSyncReq.setOperatorUserId("ifs")
            authSyncReq.loginCustomId = cmngCustom.id
            authSyncReq.areaId = cmngCustom.areaid
            authSyncReq.netCodeId = cmngCustom.netid

            //CBS接口Bean
//            CustomAcctSubmitBean acctSubmitBean = new CustomAcctSubmitBean()
//            acctSubmitBean.setCustom(cmngCustom)
//            acctSubmitBean.setCustomInfo(cmngCustominfo)
//            acctSubmitBean.setUsers(userList)
//            acctSubmitBean.setLoginCustomId(cmngCustom.id)
//            acctSubmitBean.setOperatorUserId("ifs")
//            acctSubmitBean.setOperatorId("ifs")
            String url = "/authService/syncAuthService";
            String errorCode = orderToCbs(authSyncReq, url)
            if (StatusConstant.BILLING_SUCCESS.equals(errorCode)) {
                commodityPlayResBean.result = CommodityPlayResBean.RESULT_SUCCESS
            } else {
                commodityPlayResBean.result = CommodityPlayResBean.RESULT_FAILURE
            }
        } catch (Exception e) {
            commodityPlayResBean.result = CommodityPlayResBean.RESULT_FAILURE
        }
        return commodityPlayResBean
    }

    //退订功能目前未开通，需要整理代码逻辑
    def disOrderMonth(BodyMessage requestBean, BodyMessage responseBean) {
        CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(requestBean.getDevNo())
        if (!cmngStbcustomvdo) {
            log.error "cmngStbcustomvdo not exist[UUID]: " + requestBean.getDevNo()
            responseBean.errCode = StatusConstant.USER_INFO_ERROR
            return responseBean
        }

        Date now = new Date()
        String hql = "form AuthService a where a.orderUserId=? and a.commodityId=? and a.realstartdate<? and a.realenddate>?"
        List paras = new ArrayList()
        paras.add(cmngStbcustomvdo.getUseridCmngUser())
        paras.add(requestBean.getProductId())
        paras.add(now)
        paras.add(now)
        AuthService authService = AuthService.find(hql, paras)

        //修改未完成
//        String hql = "form AuthService a where a.orderUserId=" + cmngStbcustomvdo.getUseridCmngUser()
//            + " and a.commodityId=" + requestBean.getProductId()
//            + " and a.realstartdate < to_date('" + DateUtil.getDateFullStr(now) + "','yyyy-mm-dd hh24:mi:ss') "
//            + " and a.realenddate > to_date('" + DateUtil.getDateFullStr(now) + "','yyyy-mm-dd hh24:mi:ss')"
//        AuthService authService = AuthService.find(hql)

        if (!authService) {
            log.error "authService not exist[UUID]: " + requestBean.getDevNo()
            responseBean.errCode = StatusConstant.NOT_ORDER
            return responseBean
        }
        List<Long> services = new ArrayList<Long>();
        services.add(authService.id)


        AuthServReqBean authServReqBean = new AuthServReqBean()
        authServReqBean.setLoginCustomId(cmngStbcustomvdo.dvbcustomidCmngCustom)
        authServReqBean.setOperId("ifs")
        authServReqBean.setOperatorUserId("ifs")
        authServReqBean.setCustomId(cmngStbcustomvdo.dvbcustomidCmngCustom)
        authServReqBean.setServices(services)

        String errorCode = disOrderToCbs(authServReqBean)
        responseBean.errCode = errorCode
        return responseBean
    }

    def static AuthService createProductAuthService(String commodityId, long price, String paymode, Date startdate, Date freestartdate, String isFixedPrice, int billingCyclecount, String isrenew) {
        AuthService productAuthService = new AuthService()
        productAuthService.commodityId = commodityId
        productAuthService.paymode = paymode
        productAuthService.startdate = startdate
        productAuthService.freestartdate = freestartdate
        productAuthService.pricefix = price
        productAuthService.refpricefix = price
        productAuthService.isFixedPrice = isFixedPrice
        productAuthService.isrenew = isrenew
        productAuthService.billingCyclecount = billingCyclecount    //计费周期量值
        return productAuthService
    }

    public static AuthService createOnceBatchAuthService(String commodityId, String area, String customType, String stbType, Date startdate, String billingCyclecount, long orderUserId) {
        AuthService authService = new AuthService()
        authService.orderUserId = orderUserId
        authService.commodityId = commodityId
        authService.areaid = area
        authService.typeCmngCustomtype = customType
        authService.classType = stbType
        authService.startdate = startdate
        authService.billingCyclecount = Long.parseLong(billingCyclecount)
        authService.isFixedPrice = AuthService.IS_FIXEDPRICE_FALSE
        return authService
    }

    public String disOrderToCbs(AuthServReqBean bean) {
        try {
            String url = cbs + '/authService/cancelService';
            BaseBean responseBean = (BaseBean) requestService.requestToCbs(url, bean, BaseBean.class)
            if (responseBean.getResult()) {
                return StatusConstant.BILLING_SUCCESS;
            }
        } catch (Exception e) {
            log.error "request cbs error" + e.getMessage()
        }
        return StatusConstant.DISORDER_PROCESS_ERROR;
    }

    public String orderToCbs(def bean, String url) {
        try {
            CommonRespBean responseBean = (CommonRespBean) requestService.requestToCbs(url, bean, CommonRespBean.class)
            if (responseBean.getResult()) {
                return StatusConstant.BILLING_SUCCESS;
            } else {
                if ("AcctBalance.NotEngough".equals(responseBean.code) || "AcctBalance.NeedMoreMoney".equals(responseBean.code)) {
                    return StatusConstant.BILLING_NOT_ENOUGH_MONEY;
                } else if ("AuthService.OrderTimeMatched".equals(responseBean.code)) {
                    return StatusConstant.BILLING_USER_HAVE_ORDER;
                } else if ("RequestService.request.responseFormatError".equals(responseBean.code)) {
                    return StatusConstant.CONNECT_ERROE;
                } else if ("Commodity.NotFoundById".equals(responseBean.code)) {
                    return StatusConstant.PRODUCT_NOT_EXIST;
                }
            }
        } catch (Exception e) {
            log.error "request cbs error" + e.getMessage()
        }
        return StatusConstant.ORDER_PROCESS_ERROR;
    }

    /**
     * 全量订购关系同步，每个月1号执行，获取所有有效状态的订购关系
     * @param sql
     * @param conn
     * @param local
     * @param fileName
     */
    public void batchWriteAuthServiceToLocalFile(String sql, Connection conn, String localPath, String fileName) {
        //每次从数据库中获取的记录数量，和分页数据相同
        int fetchSize = 500;
        int batchWriteCount = ONE_PAGE_DATA_SIZE     //批量写文件的记录数量
        Statement stmt = null;
        ResultSet rs = null;

        //将用户和UUid关系做成内存容器加快查询速度，防止大量数据查询动作
        HashMap userRefMap = getAllCmngUserRef()
        HashMap uuidUserIdRefMap = getUUidUserIdRef()

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            stmt.setFetchSize(fetchSize);
            stmt.setEscapeProcessing(false);
            stmt.setFetchDirection(ResultSet.FETCH_FORWARD);
            rs = stmt.executeQuery(sql);

            int index = 0
            StringBuffer syncDataBuffer = new StringBuffer()
            while (rs.next()) {
                //获取待同步订购关系
                Empowerment empowerment = converEmpowermentByResultSet(rs);
                String syncData = makeAuthServiceSyncData(empowerment, userRefMap, uuidUserIdRefMap)
                syncDataBuffer.append(syncData)
                //每拼装batchWriteCount条数据后进行写文件操作
                if (index % batchWriteCount == 0) {
                    FileUtil fileUtil = new FileUtil()
                    fileUtil.writeSyncDataToFileFoot(localPath, fileName, syncDataBuffer.toString())
                    syncDataBuffer.delete(0, syncDataBuffer.length());
                }
                index++
            }
            //当记录数小于批量提交记录数时，进行同步数据提交
            if (syncDataBuffer.length() > 0) {
                FileUtil fileUtil = new FileUtil()
                fileUtil.writeSyncDataToFileFoot(localPath, fileName, syncDataBuffer.toString())
                syncDataBuffer.delete(0, syncDataBuffer.length());
            }


        } catch (Exception e) {
            System.out.println("error: " + e);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException sqle) {
                System.out.println("SQLState: " + sqle.getSQLState());
                System.out.println("SQLErrorCode: 错误代码" + sqle.getErrorCode());
                System.out.println("SQLErrorMessage:错误情况的字符串 " + sqle.toString());
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException sqle1) {
                System.out.println("SQLState: " + sqle1.getSQLState());
                System.out.println("SQLErrorCode: 错误代码" + sqle1.getErrorCode());
                System.out.println("SQLErrorMessage:错误情况的字符串 " + sqle1.toString());
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle2) {
                System.out.println(sqle2.toString());
                System.out.println(sqle2.getSQLState());
                System.out.println(sqle2.getErrorCode());
            }
            if (userRefMap != null)
                userRefMap.clear()
            if (uuidUserIdRefMap != null)
                uuidUserIdRefMap.clear()

        }

    }

    public Empowerment converEmpowermentByResultSet(ResultSet rs) throws Exception {
        Empowerment empowerment = new Empowerment()
        empowerment.setId(rs.getLong("ID"));
        empowerment.setServiceId(rs.getString("SERVICE_ID"));
        empowerment.setCustomId(rs.getLong("CUSTOM_ID"));
        empowerment.setUserId(rs.getLong("USER_ID"));
        if (rs.getTimestamp("BEGIN_DATE") != null)
            empowerment.setBeginDate(new java.util.Date(rs.getTimestamp("BEGIN_DATE").getTime()));
        if (rs.getTimestamp("END_DATE") != null)
            empowerment.setEndDate(new java.util.Date(rs.getTimestamp("END_DATE").getTime()));
        if (rs.getTimestamp("UPDATE_DATE") != null)
            empowerment.setUpdateDate(new java.util.Date(rs.getTimestamp("UPDATE_DATE").getTime()));
        if (rs.getTimestamp("OPERATE_DATE") != null)
            empowerment.setOperateDate(new java.util.Date(rs.getTimestamp("OPERATE_DATE").getTime()));
        if (rs.getTimestamp("CREATE_DATE") != null)
            empowerment.setCreateDate(new java.util.Date(rs.getTimestamp("CREATE_DATE").getTime()));
        empowerment.setMaxEndDate(new java.util.Date(rs.getTimestamp("MAX_END_DATE").getTime()));
        empowerment.setMinBeginDate(new java.util.Date(rs.getTimestamp("MIN_BEGIN_DATE").getTime()));
        empowerment.setStatus(rs.getString("STATUS"));
        empowerment.setGroupType(rs.getString("GROUP_TYPE"));
        empowerment.setPackageType(rs.getString("PACKAGE_TYPE"));
        empowerment.setChargeBillType(rs.getString("CHARGE_BILL_TYPE"));
        empowerment.setBusinessType(rs.getString("BUSINESS_TYPE"));
        return empowerment;
    }

    //获得某个用户的基本包订购关系--已作废

    public AuthService getBaseServiceByUserId(long userId) throws Exception {

        //todo：获取用户当前基本包订购关系
        List<AuthService> authServiceList = AuthService.findAllByOrderUserIdAndBillTypeId(userId, ChargeBillType.SERVICE_BASE)
        for (AuthService authService: authServiceList) {

            //获取用户基本包逻辑
            //1.假设用户的以下三种状态不能同时存在(正常使用中、用户暂停、欠费暂停)；后两种状态的基本包订购关系可以再次激活
            //-----后续逻辑中考虑是否需要再对三种状态进行不同的处理
            //2.其他状态可以认为不存在基本包订购关系，直接返回Null。（服务终止、未生效、用户主动退订）
            //3.理论上不应该存在当前生效基本包的状态为(服务终止、未生效、用户主动退订)的用户，而应该为(正常使用中、用户暂停、欠费暂停)状态之一
            if (AuthService.STATUS_RUNNING.equals(authService.status)) {
                return authService
            } else if (AuthService.STATUS_USER_PAUSE.equals(authService.status)) {
                return authService
            } else if (AuthService.STATUS_ARREAR_PAUSE.equals(authService.status)) {
                return authService
            }
        }
        return null
    }

    /**
     * 获取某个客户下所有订购用户对应的有效基本包--已作废
     * @param customId
     * @param customType
     * @return map
     */
    public Map<String, List<AuthService>> getAllBaseServiceForUserByCustomId(long customId, String customType) {
        Map<String, List<AuthService>> baseServiceMap = new HashMap<String, List<AuthService>>()
        //获得当前客户下所有订购用户集合
        List<CmngUser> allUserList = cmngUserProcessService.getOrderUserByCustomId(customId, customType)
        for (CmngUser cmngUser: allUserList) {
            List<AuthService> authServiceList = AuthService.findAllByOrderUserIdAndBillTypeId(cmngUser.id, ChargeBillType.SERVICE_BASE)
            if (authServiceList != null && authServiceList.size() > 0)
                baseServiceMap.put(cmngUser.id + "", authServiceList)
        }
        return baseServiceMap
    }

    /**
     * 获取某个客户下所有订购用户对应的有效基本包--已作废
     * @param customId
     * @param customType
     * @return map
     */
    public Map<String, AuthService> getValidBaseServiceForUserByCustomId(long customId, String customType) {
        Map<String, AuthService> baseServiceMap = new HashMap<String, AuthService>()
        //获得当前客户下所有订购用户集合
        List<CmngUser> allUserList = cmngUserProcessService.getOrderUserByCustomId(customId, customType)
        for (CmngUser cmngUser: allUserList) {
            AuthService authService = getBaseServiceByUserId(cmngUser.id)
            if (authService != null)
                baseServiceMap.put(cmngUser.id + "", authService)
        }
        return baseServiceMap
    }

    /**
     * 全量用户信息同步，每个月1号执行，获取所有用户信息
     * @return
     * @throws Exception
     */
    public void createCmngUserAllSyncFile(String localPath, String fileName) throws Exception {

        //优先创建文件写头信息
        StringBuffer IncrementStr = new StringBuffer("DVBCUSTOMID|STBID|ICID|STATUS|SMCODE|TYPE|SEX|ENDDATE|CUSTOMNM|ADDRESS|IDCARD|MAINTELE|STBTYPE\n")
        FileUtil fileUtil = new FileUtil()
        fileUtil.writeSyncDataToFileFoot(localPath, fileName, IncrementStr.toString())

        //获取订购关系有效用户ID(数据量大，翻页方式获取)
        Set validUserSet = getValidUser()
        //获取免费用户uuId
        Set freeUserSet = getFreeUser()

        //翻页处理大批量同步数据
        String sql = "SELECT cc.CUSTOMNO, "
        sql += '       cu.ID, '
        sql += '       cu.STATUS, '
        sql += '       cc.CUSTOMTYPE, '
        sql += '       cc.TYPE, '
        sql += '       cs.STBID,'
        sql += '       cs.ICID,'
        sql += '       cs.SMCODE,'
        sql += '       ci.SEX, '
        sql += '       ci.CUSTOMNM, '
        sql += '       ci.ADDRESS, '
        sql += '       ci.IDCARD, '
        sql += '       ci.MAINTELE,'
        sql += '       wr.RESOURCE_MODEL_ID '
        sql += '  FROM ((((CMNG_CUSTOM cc LEFT JOIN CMNG_CUSTOMINFO ci ON'
        sql += '        cc.CUSTOMID = ci.CUSTOMID) LEFT JOIN CMNG_USER cu ON'
        sql += '        cc.DVBCUSTOMID = cu.DVB_COSTOM_ID) LEFT JOIN CMNG_STBCUSTOMVDO cs ON'
        sql += '        cu.ID = cs.USERID) LEFT JOIN WARE_RESOURCE WR ON CS.STBID = WR.GOODSNO) '
        sql += ' WHERE  cu.GROUP_TYPE = \'' + CmngUser.GROUPTYPE_PERSON + '\' '
        sql += ' AND  cu.STATUS != \'' + CmngUser.STATUS_INVALID + '\' '
        sql += ' AND  cc.STATUS != \'' + CmngCustom.STATUS_CANCEL + '\' '
        sql += ' AND  cc.STATUS != \'' + CmngCustom.STATUS_CANCEL_INSTALLERROR + '\' '
        System.out.println("sql:" + sql);

        //获得全量用户信息
        System.out.println("sql:" + sql);
        Connection conn1 = dataSource.getConnection()
        int page = 1;
        int pageNum = ONE_PAGE_DATA_SIZE;
        BasePage basePage = new BasePage();
        basePage.setPageNo(page);
        basePage.setPageSize(pageNum);
        JdbcUtils.calcCount(basePage, sql, conn1)
        conn1.close()
        //分页查询全量用户信息进行本地文件写入
        int count = basePage.getPageCount()
        System.out.println("count:" + count);
        for (int i = 0; i < count; i++) {
            Connection conn = dataSource.getConnection()
            basePage.setPageNo(i + 1);
            basePage.setFetchTotal(false)
            List<Map> userInfoList = JdbcUtils.getResultList(sql, basePage, conn);
            createCmngUserSyncData(userInfoList, validUserSet, freeUserSet, localPath, fileName)
        }
    }

    /**
     * 根据用户信息和订购关系信息拼装全量用户同步数据
     * @param results
     * @return
     */
    public void createCmngUserSyncData(List<Map> userInfoList, Set validUserSet, Set freeUserSet, String localPath, String fileName) {

        if (userInfoList == null || userInfoList.size() == 0)
            return
        StringBuffer IncrementStr = new StringBuffer()

        for (Map userInfoMap: userInfoList) {

            boolean isValid = validUserSet.contains(userInfoMap.get("ID"))
            //查询当前用户基本包状态
            int baseServiceStatus = BASE_SERVICE_STATUS_INVALID
            //对于正常状态和未生效状态基本包设置基本包有效状态
            //需要过滤掉用户已经主动报停的未生效基本包情况
            if (isValid)
                baseServiceStatus = BASE_SERVICE_STATUS_VALID
            //对于已经主动报停的用户，基本包状态无效，处理用户主动报停的未生效基本包为无效状态
            if (CmngUser.STATUS_SERVICE_PAUSED.equals(userInfoMap.get("STATUS")))
                baseServiceStatus = BASE_SERVICE_STATUS_INVALID
            //如果不是个人客户的机顶盒，基本包为无效状态(企业和集团客户的用户针对当前功能不开放)
            if (!CmngCustom.CUSTOMTYPE_PERSONAL.equals(userInfoMap.get("CUSTOMTYPE")))
                baseServiceStatus = BASE_SERVICE_STATUS_INVALID

            //判断当前用户是否免费期用户
            boolean isFreeUser = freeUserSet.contains(userInfoMap.get("SMCODE"))
            //组装用户同步信息
            CmngCustomInfoBean customInfoBean = new CmngCustomInfoBean(userInfoMap, baseServiceStatus, isFreeUser)
            if (customInfoBean.smCode != null && customInfoBean.smCode.trim().length() > 0)
                IncrementStr.append(customInfoBean.convertBeanToFtpLog())
        }

        FileUtil fileUtil = new FileUtil()
        fileUtil.writeSyncDataToFileFoot(localPath, fileName, IncrementStr.toString())
        IncrementStr.delete(0, IncrementStr.length());

    }

    //获取“正常状态”，“未生效状态”的基本包订购关系的用户ID
    //会获得：正常用户，开户免费用户，用户主动报停用户对应的用户ID
    //对于主动报停用户需要在循环用户时进行判断处理

    public Set<Long> getValidUser() {

        Set validUserSet = new HashSet()
//        String sql = "select a.ORDERUSERID from AUTH_SERVICE a where (a.status = '" + AuthService.STATUS_RUNNING + "' or a.status = '" + AuthService.STATUS_INVALID + "') and a.billTypeId = '" + ChargeBillType.SERVICE_BASE + "' ";
        String sql = "select a.USER_ID from empowerment a where (a.STATUS = '" + Empowerment.STATUS_VALID + "') and a.CHARGE_BILL_TYPE = '" + ChargeBillType.SERVICE_BASE + "' and a.GROUP_TYPE = '" + CmngUser.GROUPTYPE_PERSON + "' and a.MAX_END_DATE Is Not Null and a.MAX_END_DATE >= to_date('" + DateUtil.getDateStr(new Date()) + "', 'yyyy-mm-dd hh24:mi:ss')"

        System.out.println("sql:" + sql);
        System.out.println("------GetValidUser-------StartTime:" + new Date());
        Connection conn1 = dataSource.getConnection()
        int pageNum = ONE_PAGE_DATA_SIZE;
        BasePage basePage = new BasePage();
        basePage.setPageSize(pageNum);
        JdbcUtils.calcCount(basePage, sql, conn1)
        basePage.setFetchTotal(false)
        conn1.close()

        int recordCount = 0
        int count = basePage.getPageCount()
        System.out.println("count:" + count);
        for (int i = 0; i < count; i++) {
            Connection conn = dataSource.getConnection()
            basePage.setPageNo(i + 1);
            List<Map> validUserDBList = JdbcUtils.getResultList(sql, basePage, conn);
            for (Map map: validUserDBList) {
//                validUserSet.add(map.get("ORDERUSERID"))
                validUserSet.add(map.get("USER_ID"))
            }
            recordCount += validUserDBList.size()
        }
        System.out.println("recordCount:" + recordCount);
        System.out.println("------GetValidUser-------EndTime:" + new Date());
        return validUserSet

    }

    //从ISCP获取免费用户uuID列表

    public Set<String> getFreeUser() {

        Set<String> uuIdSet = new HashSet<String>()
        BaseBean responseBean = requestService.requestToIscpByStr("/loadFreeUUid.jsp", "", BaseBean.class)
        String uuIdArrStr = responseBean.getMessage()
        if (responseBean.result && uuIdArrStr != null) {
            //组装免费用户uuId
            String[] uuIdArrs = uuIdArrStr.split(",")
            for (String uuId: uuIdArrs) {
                if (uuId != null && uuId.trim().length() > 0)
                    uuIdSet.add(uuId)
            }
        }
        return uuIdSet

    }

    public static void main(String[] args) {
        AuthServiceProcessService service = new AuthServiceProcessService()
        HashSet user = service.getFreeUser()
        System.out.println("size:" + user.size());

        }


    public Map<String, List<String>> getAllCmngUserRef() {

        Map<String, List<String>> userRefMap = new HashMap<String, List<String>>()
        String sql = "select c.FATHER_USER_ID,c.CHILD_USER_ID from CMNG_USER_REF c where c.STATUS = '" + CmngUserRef.STATUS_VALID + "' ";
        System.out.println("------getAllCmngUserRef-------StartTime:" + new Date());
        //创建翻页计算对象
        int pageNum = ONE_PAGE_DATA_SIZE
        BasePage basePage = new BasePage()
        basePage.setPageSize(pageNum)
        //计算用户记录数量
        Connection conn1 = null
        try {
            conn1 = dataSource.getConnection()
            JdbcUtils.calcCount(basePage, sql, conn1)
            basePage.setFetchTotal(false)
        } catch (Exception ex) {
            ex.printStackTrace()
        } finally {
            if (conn1 != null) {
                conn1.close()
            }
        }
        //分页方式查询数据拼装用户信息到容器中用于计算
        int count = basePage.getPageCount()
        System.out.println("PageCount:" + count);
        for (int i = 0; i < count; i++) {
            Connection conn = dataSource.getConnection()
            basePage.setPageNo(i + 1);
            List<Map> validUserDBList = JdbcUtils.getResultList(sql, basePage, conn);
            for (Map map: validUserDBList) {
                Object fatherUserId = map.get("FATHER_USER_ID")
                Object childUserId = map.get("CHILD_USER_ID")
                List<String> childUserList = userRefMap.get(fatherUserId + "")
                if (childUserList == null) {
                    childUserList = new ArrayList<String>()
                    userRefMap.put(fatherUserId + "", childUserList)
                }
                childUserList.add(childUserId + "")
            }
        }
        System.out.println("SaveCount:" + userRefMap.size());
        System.out.println("------getAllCmngUserRef-------StartTime:" + new Date());
        return userRefMap

    }


    public Map<String, String> getUUidUserIdRef() {

        Map<String, String> userIduuIdRefMap = new HashMap<String, String>()
        String sql = "select T.USERID,T.SMCODE from CMNG_STBCUSTOMVDO T ";
        System.out.println("------getUUidUserIdRef-------EndTime:" + new Date());
        //创建翻页计算对象
        int pageNum = ONE_PAGE_DATA_SIZE
        BasePage basePage = new BasePage()
        basePage.setPageSize(pageNum)
        //计算用户记录数量
        Connection conn1 = null
        try {
            conn1 = dataSource.getConnection()
            JdbcUtils.calcCount(basePage, sql, conn1)
            basePage.setFetchTotal(false)
        } catch (Exception ex) {
            ex.printStackTrace()
        } finally {
            if (conn1 != null) {
                conn1.close()
            }
        }
        //分页方式查询数据拼装用户信息到容器中用于计算
        int count = basePage.getPageCount()
        System.out.println("PageCount:" + count);
        for (int i = 0; i < count; i++) {
            Connection conn = dataSource.getConnection()
            basePage.setPageNo(i + 1);
            List<Map> validUserDBList = JdbcUtils.getResultList(sql, basePage, conn);
            for (Map map: validUserDBList) {
                Object userId = map.get("USERID")
                String smCode = (String) map.get("SMCODE")
                if (smCode != null && smCode.length() > 0)
                    userIduuIdRefMap.put(userId + "", smCode + "")
            }
        }
        System.out.println("SaveCount:" + userIduuIdRefMap.size());
        System.out.println("------getUUidUserIdRef-------EndTime:" + new Date());
        return userIduuIdRefMap

    }

    /**
     * 转换当前订购关系为对应的同步数据
     * @param authService
     * @return
     * @throws BaseException
     */
    public String makeAuthServiceSyncData(Empowerment empowerment, Map<String, List<String>> userRefMap, Map<String, String> uuIdUserIdRefMap) throws BaseException {
        StringBuffer incrementStr = new StringBuffer()
        //进行订购关系内容的接口数据拼装
        long orderUserId = empowerment.userId
        List<String, String> userIdList = userRefMap.get(orderUserId + "")
        if (userIdList == null) {
            String uuId = uuIdUserIdRefMap.get(orderUserId + "")
            if (uuId != null && uuId.trim().length() > 0) {
                ProductOrderBean productOrderBean = new ProductOrderBean(empowerment, uuId)
                incrementStr.append(productOrderBean.convertBeanToFtpLog())
            }
        } else {
            //理论上不应该进行企业客户的订购关系全量和增量同步，因为企业客户不进行双向包月产品订购(vod包月账目类型)
            for (String childUserId: userIdList) {
                String uuId = uuIdUserIdRefMap.get(childUserId + "")
                if (uuId != null && uuId.trim().length() > 0) {
                    ProductOrderBean productOrderBean = new ProductOrderBean(empowerment, uuId)
                    incrementStr.append(productOrderBean.convertBeanToFtpLog())
                }
            }
        }
        return incrementStr.toString()
    }

    /**
     * 根据订购关系集合拼装同步数据,同时写本地文件
     * @param results
     * @return
     */
    private void makeAuthServiceSyncDataFile(List<Empowerment> results, String localPath, String fileName) {
        //空数据直接返回
        if (results == null || results.size() == 0)
            return
        //将用户和UUid关系做成内存容器加快查询速度，防止大量数据查询动作
        HashMap userRefMap = getAllCmngUserRef()
        HashMap uuidUserIdRefMap = getUUidUserIdRef()
        //循环同步订购关系，批量写本地文件
        int index = 0
        FileUtil fileUtil = new FileUtil()
        StringBuffer syncDataBuffer = new StringBuffer()
        for (Empowerment empowerment: results) {
            //拼装一条定购关系同步信息
            String anAuthServiceSyncStr = makeAuthServiceSyncData(empowerment, userRefMap, uuidUserIdRefMap)
            syncDataBuffer.append(anAuthServiceSyncStr)
            //每拼装batchWriteCount条数据后进行写文件操作
            if (index % ONE_PAGE_DATA_SIZE == 0) {
                fileUtil.writeSyncDataToFileFoot(localPath, fileName, syncDataBuffer.toString())
                syncDataBuffer.delete(0, syncDataBuffer.length());
            }
            index++
        }
        //当记录数小于批量提交记录数时，进行同步数据提交
        if (syncDataBuffer.length() > 0) {
            fileUtil.writeSyncDataToFileFoot(localPath, fileName, syncDataBuffer.toString())
            syncDataBuffer.delete(0, syncDataBuffer.length());
        }

    }

    //月末18点之后不能进行任何包月产品订购(VOD包月订购，Portal基本包查询订购等)
    public boolean canProcessBaseQueryAndOrder(Date date) throws Exception{
        Date lastDayOfCurrMonth = DateUtil.getLastDayOfMonth(new Date())
        int dayOfCurrMonth = DateUtil.getDayOfMonth(lastDayOfCurrMonth)

        int dayOfMonth = DateUtil.getDayOfMonth(date)
        int hour = DateUtil.getHour(date)

        if (dayOfCurrMonth == dayOfMonth && hour >= BaseServiceDataBean.ALLOW_BASE_QUERYORDER_HOUR) {
            return false
        }
        return true
    }


}
