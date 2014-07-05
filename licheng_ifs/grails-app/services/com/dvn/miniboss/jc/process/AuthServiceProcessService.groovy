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
    //����������״̬
    public final static int BASE_SERVICE_STATUS_VALID = 1
    public final static int BASE_SERVICE_STATUS_INVALID = 3

    //�趨��ҳ��ѯ����ʱÿҳ��ѯ������
    public final static int ONE_PAGE_DATA_SIZE = 1000
    //�趨������ϵ�������ݵ�"updatedate"ƽ��ʱ��
    public final static int AUTHSERVICE_INCREMENT_SYNC_TIME_OFFSET = -20

    /**
     * ����������ϵͬ����15����һ�Σ���ȡ��ǰʱ��֮ǰ20���Ӹ��µĶ�����ϵ
     * @return
     * @throws Exception
     */
    public void getAuthServiceIncrementSyncStr(String localFile, String fileName) throws Exception {

        //todo������ͬ��ƫ��ʱ�����ͺ���ֵ
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
        //����������ϵͬ�����ݣ����ر���
        makeAuthServiceSyncDataFile(empowerments, localFile, fileName)
    }



    def orderOnce(BodyMessage requestBean) {
        CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(requestBean.getDevNo())
        if (!cmngStbcustomvdo) {
            log.error "cmngStbcustomvdo not exist[UUID]: " + requestBean.getDevNo()
            return StatusConstant.USER_INFO_ERROR
        }

        //1.�ͻ�
        CmngCustom cmngCustom = CmngCustom.get(cmngStbcustomvdo.dvbcustomidCmngCustom)
        if (!cmngCustom) {
            log.error "cmngCustom not exist[UUID]: " + requestBean.getDevNo()
            return StatusConstant.USER_INFO_ERROR
        }

        //2.�ͻ���ϸ��Ϣ
        CmngCustominfo cmngCustominfo = CmngCustominfo.get(cmngCustom.customidCmngCustominfo)
        if (!cmngCustominfo) {
            log.error "cmngCustominfo not exist[UUID]: " + requestBean.getDevNo()
            return StatusConstant.USER_INFO_ERROR
        }

        //3.��ȡ�û���Ϣ
        CmngUser cmngUser = CmngUser.get(cmngStbcustomvdo.useridCmngUser)
        if (!cmngUser) {
            log.error "cmngUser not exist[UUID]: " + requestBean.getDevNo()
            return StatusConstant.USER_INFO_ERROR
        }

        //�������������
        String isFixedPrice = AuthService.IS_FIXEDPRICE_FALSE
        String paymode = AuthService.PAYMODE_PAYBEFOREUSE
        Date startTime = new Date()
        //ƿװ������ϵ��������������ʵ�����������������Ƕ���

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

        //ƴװUserList
//        List<CmngUser> userList = new ArrayList<CmngUser>()
//        userList.add(cmngUser)

        //CBS�ӿ�Bean
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

        //��ֹ�û�����ĩ18��֮�����portal��ѯ�Ͷ���
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

        //1.�ͻ�
        CmngCustom cmngCustom = CmngCustom.get(cmngStbcustomvdo.dvbcustomidCmngCustom)
        if (!cmngCustom) {
            log.error "cmngCustom not exist[UUID]: " + requestBean.getDevNo()
            responseBean.errCode = StatusConstant.USER_INFO_ERROR
            return responseBean
        }

        //2.�ͻ���ϸ��Ϣ
        CmngCustominfo cmngCustominfo = CmngCustominfo.get(cmngCustom.customidCmngCustominfo)
        if (!cmngCustominfo) {
            log.error "cmngCustominfo not exist[UUID]: " + requestBean.getDevNo()
            responseBean.errCode = StatusConstant.USER_INFO_ERROR
            return responseBean
        }

        //3.��ȡ�û���Ϣ
        CmngUser cmngUser = CmngUser.get(cmngStbcustomvdo.useridCmngUser)
        if (!cmngUser) {
            log.error "cmngUser not exist[UUID]: " + requestBean.getDevNo()
            responseBean.errCode = StatusConstant.USER_INFO_ERROR
            return responseBean
        }

        //�������������
        String isFixedPrice = AuthService.IS_FIXEDPRICE_FALSE
        String paymode = AuthService.PAYMODE_PAYBEFOREUSE
        Date startTime = new Date()
        //������Ч,��ȡ���µ����ڿ�ʼʱ��,���ε����ֵû�У���Ϊ��ʱ��Ч
        if ("2".equals(requestBean.getOrderType())) {
            Calendar calendar = Calendar.getInstance()
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1, 0, 0, 0)

            Date nextMonthFirstDay = calendar.getTime()
            AcctBillingCycle acctBillingCycle = AcctBillingCycleService.getAcctBillingCycle(nextMonthFirstDay)
            startTime = acctBillingCycle.getCycleBeginDate()
            //���¶����Ľ���ʱ��
            responseBean.endTime = DateUtil.getDateDayStr(acctBillingCycle.getCycleEndDate())
        } else {
            //������Ч�Ķ�����ϵ
            AcctBillingCycle acctBillingCycle = AcctBillingCycleService.getAcctBillingCycle(startTime)
            //���¶����Ľ���ʱ��
            responseBean.endTime = DateUtil.getDateDayStr(acctBillingCycle.getCycleEndDate())
        }
        //��������Чʱ��
        responseBean.startTime = DateUtil.getDateDayStr(startTime)
        //ƿװ������ϵ��������������ʵ�����������������Ƕ���
        AuthService productAuthService = createProductAuthService(requestBean.getProductId(), 0, paymode, startTime, null, isFixedPrice, requestBean.getBillCycleCount(), AuthService.ISRENEW_TRUE)
        List<AuthService> authServiceList = new ArrayList<AuthService>()
        productAuthService.orderUserId = cmngUser.getId()
        productAuthService.typeCmngCustomtype = cmngCustom.typeCmngCustomtype
        productAuthService.areaid = cmngCustom.areaid
        productAuthService.classType = cmngStbcustomvdo.classType
        authServiceList.add(productAuthService)
//        cmngUser.authServices = authServiceList
//
//        ƴװUserList
//        List<CmngUser> userList = new ArrayList<CmngUser>()
//        userList.add(cmngUser)

        AuthOrderReqBean authOrderReqBean = new AuthOrderReqBean()
        authOrderReqBean.authServices = authServiceList
        authOrderReqBean.setOperatorUserId("ifs")
        authOrderReqBean.loginCustomId = cmngCustom.id
        authOrderReqBean.areaId = cmngCustom.areaid
        authOrderReqBean.netCodeId = cmngCustom.netid

//        //CBS�ӿ�Bean
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
     * ���нɷѶ���
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

            //1.�ͻ�
            CmngCustom cmngCustom = CmngCustom.get(cmngStbcustomvdo.dvbcustomidCmngCustom)
            if (!cmngCustom) {
                log.error "cmngCustom not exist[UUID]: " + request.getUuId()
                payRes.result = OrderPayRes.RESULT_FAILURE
                return payRes
            }

            //2.�ͻ���ϸ��Ϣ
            CmngCustominfo cmngCustominfo = CmngCustominfo.get(cmngCustom.customidCmngCustominfo)
            if (!cmngCustominfo) {
                log.error "cmngCustominfo not exist[UUID]: " + request.getUuId()
                payRes.result = OrderPayRes.RESULT_FAILURE
                return payRes
            }

            //3.��ȡ�û���Ϣ
            CmngUser cmngUser = CmngUser.get(cmngStbcustomvdo.useridCmngUser)
            if (!cmngUser) {
                log.error "cmngUser not exist[UUID]: " + request.getUuId()
                payRes.result = CommodityPlayResBean.RESULT_FAILURE
                return payRes
            }
            //�������������
            String isFixedPrice = AuthService.IS_FIXEDPRICE_FALSE
            String paymode = AuthService.PAYMODE_PAYBEFOREUSE

            //�������Ŀ�ʼʱ������ѿ�ʼʱ�䣬���϶������ڲ��Ƕ�����������ʼʱ�䣬Ҳ����˵����һ������ڵĶ�����ϵ
            int BillingCycleCount = Integer.parseInt(request.getBillingCycleCount())
            Date startTime = DateUtil.getPreDateByMonth(request.getBeginDate(), (0 - BillingCycleCount))
            String productId = request.getProductId();
            //�����Ʒ����������޷���ѯ���Ļ�����Ϊ�ǰ��β�Ʒ
//            Commodity commodity=Commodity.findById(productId)
//            if(!commodity){
//                productId=PublishAssetProcessService.getClickCommodity().getId()
//            }
//            long price =PublishAssetProcessService.getPrice(request.getResourceId())

            //ƴװ������ϵ��������������ʵ�����������������Ƕ���
            AuthService productAuthService = createProductAuthService(productId, 0, paymode, startTime, request.getBeginDate(), isFixedPrice, Integer.parseInt(request.getBillingCycleCount()), AuthService.ISRENEW_TRUE)
            productAuthService.setDeviceId(request.getResourceId())
            productAuthService.orderUserId = cmngUser.getId()
            productAuthService.typeCmngCustomtype = cmngCustom.typeCmngCustomtype
            productAuthService.areaid = cmngCustom.areaid
            productAuthService.classType = cmngStbcustomvdo.classType
            List<AuthService> authServiceList = new ArrayList<AuthService>()
            authServiceList.add(productAuthService)
//            cmngUser.authServices = authServiceList

            //ƴװUserList
//            List<CmngUser> userList = new ArrayList<CmngUser>()
//            userList.add(cmngUser)

            //ƴװ�����¼����ҵ�ͻ���һ����¼
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
            //CBS�ӿ�Bean
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

    //���н��п۷ѣ�ibossֻ�����¶�����ϵ��¼���ã�Ŀǰֻ�е���������ϵͬ��,���Ҳ�����������������ͬ���İ��¶�����ϵ��
    def bankCardOrder(CommodityPlayReqBean commodityPlayReqBean, CommodityPlayResBean commodityPlayResBean) {
        try {
            CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findBySmcode(commodityPlayReqBean.getUuId())
            if (!cmngStbcustomvdo) {
                log.error "cmngStbcustomvdo not exist[UUID]: " + commodityPlayReqBean.getUuId()
                commodityPlayResBean.result = CommodityPlayResBean.RESULT_FAILURE
                return commodityPlayResBean
            }

            //1.�ͻ�
            CmngCustom cmngCustom = CmngCustom.get(cmngStbcustomvdo.dvbcustomidCmngCustom)
            if (!cmngCustom) {
                log.error "cmngCustom not exist[UUID]: " + commodityPlayReqBean.getUuId()
                commodityPlayResBean.result = CommodityPlayResBean.RESULT_FAILURE
                return commodityPlayResBean
            }

            //2.�ͻ���ϸ��Ϣ
            CmngCustominfo cmngCustominfo = CmngCustominfo.get(cmngCustom.customidCmngCustominfo)
            if (!cmngCustominfo) {
                log.error "cmngCustominfo not exist[UUID]: " + commodityPlayReqBean.getUuId()
                commodityPlayResBean.result = CommodityPlayResBean.RESULT_FAILURE
                return commodityPlayResBean
            }

            //3.��ȡ�û���Ϣ
            CmngUser cmngUser = CmngUser.get(cmngStbcustomvdo.useridCmngUser)
            if (!cmngUser) {
                log.error "cmngUser not exist[UUID]: " + commodityPlayReqBean.getUuId()
                commodityPlayResBean.result = CommodityPlayResBean.RESULT_FAILURE
                return commodityPlayResBean
            }
            //�������������
            String isFixedPrice = AuthService.IS_FIXEDPRICE_FALSE
            String paymode = AuthService.PAYMODE_PAYBEFOREUSE

            //�������Ŀ�ʼʱ������ѿ�ʼʱ�䣬���϶������ڲ��Ƕ�����������ʼʱ�䣬Ҳ����˵����һ������ڵĶ�����ϵ
            //�����⣬���Ѻ��
            int BillingCycleCount = Integer.parseInt(commodityPlayReqBean.getBillingCycleCount())
            Date startTime = DateUtil.getPreDateByMonth(commodityPlayReqBean.getBeginDate(), (0 - BillingCycleCount))

            //ƴװ������ϵ��������������ʵ�����������������Ƕ���
            AuthService productAuthService = createProductAuthService(commodityPlayReqBean.getProductId(), 0, paymode, startTime, commodityPlayReqBean.getBeginDate(), isFixedPrice, Integer.parseInt(commodityPlayReqBean.getBillingCycleCount()), AuthService.ISRENEW_FALSE)

            productAuthService.orderUserId = cmngUser.getId()
            productAuthService.typeCmngCustomtype = cmngCustom.typeCmngCustomtype
            productAuthService.areaid = cmngCustom.areaid
            productAuthService.classType = cmngStbcustomvdo.classType
            List<AuthService> authServiceList = new ArrayList<AuthService>()
            authServiceList.add(productAuthService)
            
//            cmngUser.authServices = authServiceList
//
//            //ƴװUserList
//            List<CmngUser> userList = new ArrayList<CmngUser>()
//            userList.add(cmngUser)


            AuthSyncReq authSyncReq = new AuthSyncReq()
            authSyncReq.authServices = authServiceList
            authSyncReq.setOperatorUserId("ifs")
            authSyncReq.loginCustomId = cmngCustom.id
            authSyncReq.areaId = cmngCustom.areaid
            authSyncReq.netCodeId = cmngCustom.netid

            //CBS�ӿ�Bean
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

    //�˶�����Ŀǰδ��ͨ����Ҫ��������߼�
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

        //�޸�δ���
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
        productAuthService.billingCyclecount = billingCyclecount    //�Ʒ�������ֵ
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
     * ȫ��������ϵͬ����ÿ����1��ִ�У���ȡ������Ч״̬�Ķ�����ϵ
     * @param sql
     * @param conn
     * @param local
     * @param fileName
     */
    public void batchWriteAuthServiceToLocalFile(String sql, Connection conn, String localPath, String fileName) {
        //ÿ�δ����ݿ��л�ȡ�ļ�¼�������ͷ�ҳ������ͬ
        int fetchSize = 500;
        int batchWriteCount = ONE_PAGE_DATA_SIZE     //����д�ļ��ļ�¼����
        Statement stmt = null;
        ResultSet rs = null;

        //���û���UUid��ϵ�����ڴ������ӿ��ѯ�ٶȣ���ֹ�������ݲ�ѯ����
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
                //��ȡ��ͬ��������ϵ
                Empowerment empowerment = converEmpowermentByResultSet(rs);
                String syncData = makeAuthServiceSyncData(empowerment, userRefMap, uuidUserIdRefMap)
                syncDataBuffer.append(syncData)
                //ÿƴװbatchWriteCount�����ݺ����д�ļ�����
                if (index % batchWriteCount == 0) {
                    FileUtil fileUtil = new FileUtil()
                    fileUtil.writeSyncDataToFileFoot(localPath, fileName, syncDataBuffer.toString())
                    syncDataBuffer.delete(0, syncDataBuffer.length());
                }
                index++
            }
            //����¼��С�������ύ��¼��ʱ������ͬ�������ύ
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
                System.out.println("SQLErrorCode: �������" + sqle.getErrorCode());
                System.out.println("SQLErrorMessage:����������ַ��� " + sqle.toString());
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException sqle1) {
                System.out.println("SQLState: " + sqle1.getSQLState());
                System.out.println("SQLErrorCode: �������" + sqle1.getErrorCode());
                System.out.println("SQLErrorMessage:����������ַ��� " + sqle1.toString());
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

    //���ĳ���û��Ļ�����������ϵ--������

    public AuthService getBaseServiceByUserId(long userId) throws Exception {

        //todo����ȡ�û���ǰ������������ϵ
        List<AuthService> authServiceList = AuthService.findAllByOrderUserIdAndBillTypeId(userId, ChargeBillType.SERVICE_BASE)
        for (AuthService authService: authServiceList) {

            //��ȡ�û��������߼�
            //1.�����û�����������״̬����ͬʱ����(����ʹ���С��û���ͣ��Ƿ����ͣ)��������״̬�Ļ�����������ϵ�����ٴμ���
            //-----�����߼��п����Ƿ���Ҫ�ٶ�����״̬���в�ͬ�Ĵ���
            //2.����״̬������Ϊ�����ڻ�����������ϵ��ֱ�ӷ���Null����������ֹ��δ��Ч���û������˶���
            //3.�����ϲ�Ӧ�ô��ڵ�ǰ��Ч��������״̬Ϊ(������ֹ��δ��Ч���û������˶�)���û�����Ӧ��Ϊ(����ʹ���С��û���ͣ��Ƿ����ͣ)״̬֮һ
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
     * ��ȡĳ���ͻ������ж����û���Ӧ����Ч������--������
     * @param customId
     * @param customType
     * @return map
     */
    public Map<String, List<AuthService>> getAllBaseServiceForUserByCustomId(long customId, String customType) {
        Map<String, List<AuthService>> baseServiceMap = new HashMap<String, List<AuthService>>()
        //��õ�ǰ�ͻ������ж����û�����
        List<CmngUser> allUserList = cmngUserProcessService.getOrderUserByCustomId(customId, customType)
        for (CmngUser cmngUser: allUserList) {
            List<AuthService> authServiceList = AuthService.findAllByOrderUserIdAndBillTypeId(cmngUser.id, ChargeBillType.SERVICE_BASE)
            if (authServiceList != null && authServiceList.size() > 0)
                baseServiceMap.put(cmngUser.id + "", authServiceList)
        }
        return baseServiceMap
    }

    /**
     * ��ȡĳ���ͻ������ж����û���Ӧ����Ч������--������
     * @param customId
     * @param customType
     * @return map
     */
    public Map<String, AuthService> getValidBaseServiceForUserByCustomId(long customId, String customType) {
        Map<String, AuthService> baseServiceMap = new HashMap<String, AuthService>()
        //��õ�ǰ�ͻ������ж����û�����
        List<CmngUser> allUserList = cmngUserProcessService.getOrderUserByCustomId(customId, customType)
        for (CmngUser cmngUser: allUserList) {
            AuthService authService = getBaseServiceByUserId(cmngUser.id)
            if (authService != null)
                baseServiceMap.put(cmngUser.id + "", authService)
        }
        return baseServiceMap
    }

    /**
     * ȫ���û���Ϣͬ����ÿ����1��ִ�У���ȡ�����û���Ϣ
     * @return
     * @throws Exception
     */
    public void createCmngUserAllSyncFile(String localPath, String fileName) throws Exception {

        //���ȴ����ļ�дͷ��Ϣ
        StringBuffer IncrementStr = new StringBuffer("DVBCUSTOMID|STBID|ICID|STATUS|SMCODE|TYPE|SEX|ENDDATE|CUSTOMNM|ADDRESS|IDCARD|MAINTELE|STBTYPE\n")
        FileUtil fileUtil = new FileUtil()
        fileUtil.writeSyncDataToFileFoot(localPath, fileName, IncrementStr.toString())

        //��ȡ������ϵ��Ч�û�ID(�������󣬷�ҳ��ʽ��ȡ)
        Set validUserSet = getValidUser()
        //��ȡ����û�uuId
        Set freeUserSet = getFreeUser()

        //��ҳ���������ͬ������
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

        //���ȫ���û���Ϣ
        System.out.println("sql:" + sql);
        Connection conn1 = dataSource.getConnection()
        int page = 1;
        int pageNum = ONE_PAGE_DATA_SIZE;
        BasePage basePage = new BasePage();
        basePage.setPageNo(page);
        basePage.setPageSize(pageNum);
        JdbcUtils.calcCount(basePage, sql, conn1)
        conn1.close()
        //��ҳ��ѯȫ���û���Ϣ���б����ļ�д��
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
     * �����û���Ϣ�Ͷ�����ϵ��Ϣƴװȫ���û�ͬ������
     * @param results
     * @return
     */
    public void createCmngUserSyncData(List<Map> userInfoList, Set validUserSet, Set freeUserSet, String localPath, String fileName) {

        if (userInfoList == null || userInfoList.size() == 0)
            return
        StringBuffer IncrementStr = new StringBuffer()

        for (Map userInfoMap: userInfoList) {

            boolean isValid = validUserSet.contains(userInfoMap.get("ID"))
            //��ѯ��ǰ�û�������״̬
            int baseServiceStatus = BASE_SERVICE_STATUS_INVALID
            //��������״̬��δ��Ч״̬���������û�������Ч״̬
            //��Ҫ���˵��û��Ѿ�������ͣ��δ��Ч���������
            if (isValid)
                baseServiceStatus = BASE_SERVICE_STATUS_VALID
            //�����Ѿ�������ͣ���û���������״̬��Ч�������û�������ͣ��δ��Ч������Ϊ��Ч״̬
            if (CmngUser.STATUS_SERVICE_PAUSED.equals(userInfoMap.get("STATUS")))
                baseServiceStatus = BASE_SERVICE_STATUS_INVALID
            //������Ǹ��˿ͻ��Ļ����У�������Ϊ��Ч״̬(��ҵ�ͼ��ſͻ����û���Ե�ǰ���ܲ�����)
            if (!CmngCustom.CUSTOMTYPE_PERSONAL.equals(userInfoMap.get("CUSTOMTYPE")))
                baseServiceStatus = BASE_SERVICE_STATUS_INVALID

            //�жϵ�ǰ�û��Ƿ�������û�
            boolean isFreeUser = freeUserSet.contains(userInfoMap.get("SMCODE"))
            //��װ�û�ͬ����Ϣ
            CmngCustomInfoBean customInfoBean = new CmngCustomInfoBean(userInfoMap, baseServiceStatus, isFreeUser)
            if (customInfoBean.smCode != null && customInfoBean.smCode.trim().length() > 0)
                IncrementStr.append(customInfoBean.convertBeanToFtpLog())
        }

        FileUtil fileUtil = new FileUtil()
        fileUtil.writeSyncDataToFileFoot(localPath, fileName, IncrementStr.toString())
        IncrementStr.delete(0, IncrementStr.length());

    }

    //��ȡ������״̬������δ��Ч״̬���Ļ�����������ϵ���û�ID
    //���ã������û�����������û����û�������ͣ�û���Ӧ���û�ID
    //����������ͣ�û���Ҫ��ѭ���û�ʱ�����жϴ���

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

    //��ISCP��ȡ����û�uuID�б�

    public Set<String> getFreeUser() {

        Set<String> uuIdSet = new HashSet<String>()
        BaseBean responseBean = requestService.requestToIscpByStr("/loadFreeUUid.jsp", "", BaseBean.class)
        String uuIdArrStr = responseBean.getMessage()
        if (responseBean.result && uuIdArrStr != null) {
            //��װ����û�uuId
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
        //������ҳ�������
        int pageNum = ONE_PAGE_DATA_SIZE
        BasePage basePage = new BasePage()
        basePage.setPageSize(pageNum)
        //�����û���¼����
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
        //��ҳ��ʽ��ѯ����ƴװ�û���Ϣ�����������ڼ���
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
        //������ҳ�������
        int pageNum = ONE_PAGE_DATA_SIZE
        BasePage basePage = new BasePage()
        basePage.setPageSize(pageNum)
        //�����û���¼����
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
        //��ҳ��ʽ��ѯ����ƴװ�û���Ϣ�����������ڼ���
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
     * ת����ǰ������ϵΪ��Ӧ��ͬ������
     * @param authService
     * @return
     * @throws BaseException
     */
    public String makeAuthServiceSyncData(Empowerment empowerment, Map<String, List<String>> userRefMap, Map<String, String> uuIdUserIdRefMap) throws BaseException {
        StringBuffer incrementStr = new StringBuffer()
        //���ж�����ϵ���ݵĽӿ�����ƴװ
        long orderUserId = empowerment.userId
        List<String, String> userIdList = userRefMap.get(orderUserId + "")
        if (userIdList == null) {
            String uuId = uuIdUserIdRefMap.get(orderUserId + "")
            if (uuId != null && uuId.trim().length() > 0) {
                ProductOrderBean productOrderBean = new ProductOrderBean(empowerment, uuId)
                incrementStr.append(productOrderBean.convertBeanToFtpLog())
            }
        } else {
            //�����ϲ�Ӧ�ý�����ҵ�ͻ��Ķ�����ϵȫ��������ͬ������Ϊ��ҵ�ͻ�������˫����²�Ʒ����(vod������Ŀ����)
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
     * ���ݶ�����ϵ����ƴװͬ������,ͬʱд�����ļ�
     * @param results
     * @return
     */
    private void makeAuthServiceSyncDataFile(List<Empowerment> results, String localPath, String fileName) {
        //������ֱ�ӷ���
        if (results == null || results.size() == 0)
            return
        //���û���UUid��ϵ�����ڴ������ӿ��ѯ�ٶȣ���ֹ�������ݲ�ѯ����
        HashMap userRefMap = getAllCmngUserRef()
        HashMap uuidUserIdRefMap = getUUidUserIdRef()
        //ѭ��ͬ��������ϵ������д�����ļ�
        int index = 0
        FileUtil fileUtil = new FileUtil()
        StringBuffer syncDataBuffer = new StringBuffer()
        for (Empowerment empowerment: results) {
            //ƴװһ��������ϵͬ����Ϣ
            String anAuthServiceSyncStr = makeAuthServiceSyncData(empowerment, userRefMap, uuidUserIdRefMap)
            syncDataBuffer.append(anAuthServiceSyncStr)
            //ÿƴװbatchWriteCount�����ݺ����д�ļ�����
            if (index % ONE_PAGE_DATA_SIZE == 0) {
                fileUtil.writeSyncDataToFileFoot(localPath, fileName, syncDataBuffer.toString())
                syncDataBuffer.delete(0, syncDataBuffer.length());
            }
            index++
        }
        //����¼��С�������ύ��¼��ʱ������ͬ�������ύ
        if (syncDataBuffer.length() > 0) {
            fileUtil.writeSyncDataToFileFoot(localPath, fileName, syncDataBuffer.toString())
            syncDataBuffer.delete(0, syncDataBuffer.length());
        }

    }

    //��ĩ18��֮���ܽ����κΰ��²�Ʒ����(VOD���¶�����Portal��������ѯ������)
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
