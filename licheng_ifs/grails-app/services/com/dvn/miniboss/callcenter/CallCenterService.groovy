package com.dvn.miniboss.callcenter

import com.miniboss.callcenter.AreaListBean
import com.miniboss.callcenter.NetListBean
import com.miniboss.callcenter.CustomerReq
import com.miniboss.callcenter.CustomerInfoListBean
import com.miniboss.callcenter.BalanceBean
import com.miniboss.callcenter.OperateLogListBean
import com.miniboss.callcenter.SystemLogListBean
import com.miniboss.callcenter.OperateActionListBean
import com.miniboss.callcenter.SystemActionListBean
import com.miniboss.callcenter.ConsumeReq
import com.miniboss.callcenter.AuthServiceListBean

import com.miniboss.callcenter.ConsumeInfoListBean
import com.miniboss.callcenter.ChangePasswordReq
import com.miniboss.callcenter.CommonResultBean
import com.miniboss.callcenter.ResendCAReq
import com.miniboss.callcenter.CustomerLogReq
import com.miniboss.callcenter.SystemLogReq
import com.miniboss.callcenter.AreaCode
import com.miniboss.callcenter.NetCode
import com.miniboss.callcenter.CustomerInfo
import com.miniboss.callcenter.User
import com.miniboss.callcenter.OperateLog
import com.miniboss.callcenter.OperateAction
import com.miniboss.callcenter.WorkOrderReq
import com.miniboss.callcenter.WorkOrderListBean
import com.dvn.miniboss.oldsms.CmngCustom
import com.dvn.miniboss.oldsms.CmngCustominfo
import com.dvn.miniboss.acct.CmngUser
import com.dvn.miniboss.oldsms.CmngStbcustomvdo
import com.dvn.miniboss.acct.AcctBalance
import com.dvn.miniboss.oldsms.AuthServicebill
import com.miniboss.callcenter.ConsumeInfo
import com.dvn.miniboss.oldsms.ChargePaybilldetail
import com.miniboss.callcenter.WorkOrder
import com.dvn.miniboss.ca.SendCaMessageService
import java.text.SimpleDateFormat
import com.miniboss.callcenter.ProductListBean
import com.miniboss.callcenter.Product
import com.miniboss.callcenter.SystemAction
import com.miniboss.callcenter.SystemLog
import com.miniboss.callcenter.CustomTypeListBeanRes
import com.miniboss.callcenter.CustomType
import com.miniboss.callcenter.BusinessTypeListBeanRes
import com.miniboss.callcenter.CustomListReq
import com.miniboss.callcenter.CustomLightListBeanRes

import com.miniboss.callcenter.ServiceListBeanRes
import com.miniboss.callcenter.TransDetailListBeanRes
import com.miniboss.callcenter.PartnerReq
import com.miniboss.callcenter.NotifyListBeanRes
import com.miniboss.callcenter.BusinessType
import com.miniboss.callcenter.CustomLight

import com.miniboss.callcenter.ServiceInfo
import com.miniboss.callcenter.TransDetail
import com.dvn.miniboss.oldsms.ChargeBillType
import com.dvn.miniboss.product.CaCmdCasHistory
import com.miniboss.callcenter.Notify
import com.miniboss.callcenter.CustomInfoRes
import com.miniboss.callcenter.CustomStbNum
import com.miniboss.callcenter.GroupCustomerReq
import com.dvn.miniboss.oldsms.CmngCustomtype
import com.dvn.miniboss.acct.CmngStbType
import com.miniboss.bean.workorder.WorkOrderExtendBean
import com.google.gson.Gson
import com.dvn.miniboss.acct.CmngUserRef
import com.dvn.miniboss.ca.CaCMDType
import com.dvn.miniboss.acct.AcctSpecialPayment
import com.dvn.miniboss.acct.DepositDetail
import com.miniboss.product.BalanceSnapshot
import com.miniboss.util.MessageUtil
import com.dvn.miniboss.serv.Empowerment
import com.dvn.miniboss.jc.process.SyncCbsDataProcessService
import com.miniboss.bean.authservice.RefreshEmpowermentReq
import com.dvn.miniboss.oldsms.AuthService
import com.miniboss.acct.utils.DateUtil
import com.miniboss.callcenter.ResendAreaControlCAReq
import com.dvn.miniboss.product.AreacontrolSpecialuserHistory
import com.dvn.miniboss.ca.CaTemplate

/**
 * Created by IntelliJ IDEA.
 * User: star
 * Date: 2010-8-3
 * Time: 18:45:29
 * To change this template use File | Settings | File Templates.
 */
class CallCenterService {
  static expose = ['cxf']

  boolean transactional = true
  def SendCaMessageService sendCaMessageService
  def SyncCbsDataProcessService syncCbsDataProcessService

  static final int PAGENUM = 15
  static final String ACCTINVOICELISTTYPE_ZZF = "zzf" //增值费
  static final String ACCTPAYMENTMETHOD_CARD = "5" //一卡通
  static final String ACCTPAYMENTMETHOD_BANK = "6" //充值卡
  static final String CMNGCUSTOM_PERSONAL = "1" //个人客户

  /**
   * 返回iBoss系统中所有地区对象的列表的
   * @param clientId
   * @return
   */
  public AreaListBean getAreaList(String clientId) {
    AreaListBean areaListBean = new AreaListBean()
    try {
      AreaCode areaCode = null
      List areaList = com.dvn.miniboss.system.AreaCode.list()
      for (com.dvn.miniboss.system.AreaCode area: areaList) {
        areaCode = new AreaCode()
        areaCode.setId(area.id)
        areaCode.setAreanm(area.areanm)
        areaCode.setParentid(area.parentid)
        areaCode.setAreatype(area.areatype)
        areaCode.setStatus(area.status)
        areaCode.setAreaNo(area.areaNo)
        areaCode.setCreatedate(area.createdate)
        areaCode.setDescription(area.description)
        areaListBean.areaList.add(areaCode)
      }
    } catch (Exception e) {
      e.printStackTrace()
      areaListBean.setErrCode("300")
    }
    return areaListBean
  }

  /**
   * 返回iBoss系统中所有网点对象的列表
   * @param clientId
   * @return
   */
  public NetListBean getNetList(String clientId) {
    NetListBean netListBean = new NetListBean()
    try {
      NetCode netCode = null
      List netCodeList = com.dvn.miniboss.system.NetCode.list()
      for (com.dvn.miniboss.system.NetCode net: netCodeList) {
        netCode = new NetCode()
        netCode.setId(net.id)
        netCode.setNetnm(net.netnm)
        netCode.setIsbase(net.isbase)
        netCode.setAddress(net.address)
        netCode.setZipcode(net.zipcode)
        netCode.setMaintele(net.maintele)
        netCode.setMainfax(net.mainfax)
        netCode.setStatus(net.status)
        netCode.setAreaCodeId(net.areaCode.id)
        netCode.setLocationid(net.locationid)
        netCode.setCreatedate(net.createdate)
        netCode.setDescription(net.description)
        netListBean.netList.add(netCode)
      }
    } catch (Exception e) {
      e.printStackTrace()
      netListBean.setErrorCode("300")
    }
    return netListBean
  }

  /**
   * 个人客户账户余额查询
   * @param clientId
   * @param customerId
   * @return BalanceBean
   */
  public BalanceBean getBalance(String clientId, long customerId) {
    BalanceBean balanceBean = new BalanceBean()
    try {
      if (customerId) {
        balanceBean.setBalance((int)computeBalance(customerId))
      } else {
        balanceBean.setErrorCode("200")
      }
    } catch (Exception e) {
      e.printStackTrace()
      balanceBean.setErrorCode("300")
    }
    return balanceBean
  }

    /**
     * 重发授权
     * @param clientId
     * @param req
     * @return
     */
    public CommonResultBean resendCA(String clientId, ResendCAReq req) {
        //todo：确认下之前逻辑是否有特殊处理
        log.info "------->resendCA begin"
        CommonResultBean commonResultBean = new CommonResultBean()
        try {
            if (req && req.getOperatorId()) {
                log.info "-------------->resendCA params [customId:" + req.getCustomerId() + ",serviceIdArray.size:" + req.getServiceIdArray()?.size() + "]"
                def cmngStbcustomvdos = new HashSet<com.dvn.miniboss.oldsms.CmngStbcustomvdo>()
                if (req.getCustomerId() != 0 && req.getCustomerId() > 0) {
                    com.dvn.miniboss.oldsms.CmngCustom custom = com.dvn.miniboss.oldsms.CmngCustom.get(req.getCustomerId())
                    if (custom) {
                        //设置订购用户类型(个人用户，用户组)
                        String userGroupType = CmngUser.GROUPTYPE_PERSON
                        if (!CmngCustom.CUSTOMTYPE_PERSONAL.equals(custom.getCustomType())) {
                            userGroupType = CmngUser.GROUPTYPE_COMPANY_USERTEAM
                        }
                        String getEmpListHql = "from Empowerment e where e.customId = '" + custom.id + "' and e.status = '" + Empowerment.STATUS_VALID + "'" +
                                " and groupType = '" + userGroupType + "' and e.maxEndDate Is Not Null and " +
                                " e.maxEndDate >= to_date('" + DateUtil.getDateFullStr(new Date()) + "','yyyy-mm-dd hh24:mi:ss') order by e.maxEndDate desc"
                        List<Empowerment> empowermentList = Empowerment.executeQuery(getEmpListHql)

                        for (com.dvn.miniboss.serv.Empowerment empowerment: empowermentList) {
                            long userId = empowerment.userId
                            String serviceId = empowerment.serviceId
                            //调用CBS服务刷新接口
                            RefreshEmpowermentReq refreshEmpowermentReq = new RefreshEmpowermentReq()
                            refreshEmpowermentReq.userId = userId
                            refreshEmpowermentReq.commodityId = serviceId
                            refreshEmpowermentReq.loginCustomId = custom.id
                            refreshEmpowermentReq.operatorUserId = req.operatorId
                            syncCbsDataProcessService.refreshEmpowermentToCBS(refreshEmpowermentReq)
                        }

                        //收集机卡 add by hmx 2011-07-07
                        for(com.dvn.miniboss.oldsms.CmngStbcustomvdo cmngStbcustomvdo : com.dvn.miniboss.oldsms.CmngStbcustomvdo.executeQuery("from CmngStbcustomvdo c where c.useridCmngUser in (select u.id from CmngUser u where u.status <> '" + com.dvn.miniboss.acct.CmngUser.STATUS_INVALID + "' and u.dvbCostomId=" + custom.getId() + ")")){
                            cmngStbcustomvdos.add(cmngStbcustomvdo)
                        }

                    } else {
                        log.warn "-------------->resendCA Error: Custom Not Found! [customId:" + req.customerId + "]"
                    }
                } else if (req.getServiceIdArray() && req.getServiceIdArray().size() > 0) {
                    for (long serviceId: req.getServiceIdArray()) {
                        AuthService authService = AuthService.get(serviceId)
                        if (authService == null) {
                            log.warn "-------------->resendCA Error: AuthService Not Found! [serviceId:" + authService.id + ",customId:" + authService.userid + "]"
                            continue
                        }
                        //调用CBS服务刷新接口
                        RefreshEmpowermentReq refreshEmpowermentReq = new RefreshEmpowermentReq()
                        refreshEmpowermentReq.userId = authService.orderUserId
                        refreshEmpowermentReq.commodityId = authService.commodityId
                        refreshEmpowermentReq.loginCustomId = authService.userid
                        refreshEmpowermentReq.operatorUserId = req.operatorId
                        syncCbsDataProcessService.refreshEmpowermentToCBS(refreshEmpowermentReq)

                        //收集机卡 add by hmx 2011-07-07
                        CmngCustom custom = CmngCustom.get(authService.getUserid())
                        if(null != custom){
                            String hql = "";
                            if(CmngCustom.CUSTOMTYPE_PERSONAL.equals(custom.getCustomType())) hql = "from CmngStbcustomvdo c where c.useridCmngUser in (select u.id from CmngUser u where u.status <> '" + com.dvn.miniboss.acct.CmngUser.STATUS_INVALID + "' and u.id=" + authService.getOrderUserId() + ")"
                            else hql = "from CmngStbcustomvdo c where c.useridCmngUser in (select u.id from CmngUser u where u.status <> '" + com.dvn.miniboss.acct.CmngUser.STATUS_INVALID + "' and u.id in (select r.childUserId from CmngUserRef r where r.fatherUserId = " + authService.getOrderUserId() + ") )"
                            for(com.dvn.miniboss.oldsms.CmngStbcustomvdo cmngStbcustomvdo : com.dvn.miniboss.oldsms.CmngStbcustomvdo.executeQuery(hql)){
                                cmngStbcustomvdos.add(cmngStbcustomvdo)
                            }
                        }
                    }

                } else {
                    log.warn "-------------->resendCA Error: Custom Not Found And ServiceIdArray is Empty! [customId:" + req.customerId + "]"
                }

                //重发分区指令 add by hmx 2011-07-07
                log.info "-------------->resendCA for Areacontrol [customId:" + req.getCustomerId() + "|serviceIdArray size:" + req.getServiceIdArray()?.size()  + "|cmngStbcustomvdos size:" + cmngStbcustomvdos.size() + "]"
                def isSpecial="1" ,areaId = "" ,netId = "" ,operatorId = ""
                for(com.dvn.miniboss.oldsms.CmngStbcustomvdo cmngStbcustomvdo : cmngStbcustomvdos){
                    def catemplateId = ""
                    //判断是否做过分区  add by hmx 2011-07-07
                    com.dvn.miniboss.product.AreacontrolSpecialuserHistory areacontrolHistory = com.dvn.miniboss.product.AreacontrolSpecialuserHistory.find("from AreacontrolSpecialuserHistory a where a.action.id in (" + com.dvn.miniboss.log.OperateAction.ID_VILLAGE_AREACONTROL + "," + com.dvn.miniboss.log.OperateAction.ID_CITY_AREACONTROL + ") and a.goodsNumber='" + cmngStbcustomvdo.getStbid() + ";" + cmngStbcustomvdo.getIcid() + "' order by a.createDate desc")
                    if(null != areacontrolHistory){
                        if(com.dvn.miniboss.log.OperateAction.ID_VILLAGE_AREACONTROL == areacontrolHistory.action.id) catemplateId = com.dvn.miniboss.ca.CaTemplate.VILLAGE
                        else if(com.dvn.miniboss.log.OperateAction.ID_CITY_AREACONTROL == areacontrolHistory.action.id) catemplateId = com.dvn.miniboss.ca.CaTemplate.CITY
                        areaId = areacontrolHistory.getAreaId()
                        netId = areacontrolHistory.getNetId()
                        operatorId = areacontrolHistory.getOperatorId()
                        //判断是否做过1003特殊用户包  add by hmx 2011-07-07
                        com.dvn.miniboss.product.AreacontrolSpecialuserHistory specialuserHistory = com.dvn.miniboss.product.AreacontrolSpecialuserHistory.find("from AreacontrolSpecialuserHistory a where a.action.id in (" + com.dvn.miniboss.log.OperateAction.ID_SPECIAL_USER + ") and a.goodsNumber='" + cmngStbcustomvdo.getStbid() + ";" + cmngStbcustomvdo.getIcid() + "' order by a.createDate desc")
                        if(null != specialuserHistory){
                            if(com.dvn.miniboss.log.OperateAction.ID_VILLAGE_AREACONTROL == areacontrolHistory.action.id) catemplateId = com.dvn.miniboss.ca.CaTemplate.VILLAGE_SPECIALUSER
                            else if(com.dvn.miniboss.log.OperateAction.ID_CITY_AREACONTROL == areacontrolHistory.action.id) catemplateId = com.dvn.miniboss.ca.CaTemplate.CITY_SPECIALUSER
                            if(specialuserHistory.getCreateDate().after(areacontrolHistory.getCreateDate())){
                                areaId = specialuserHistory.getAreaId()
                                netId = specialuserHistory.getNetId()
                                operatorId = specialuserHistory.getOperatorId()
                            }
                        }
                        log.info "-------------->resendCA for Areacontrol [catemplateId:" + catemplateId  + "]"
                        sendCaMessageService.sendCAByTemplateBatch("", cmngStbcustomvdo.getStbid(), cmngStbcustomvdo.getIcid(), catemplateId, new Date(), new Date(), areaId, operatorId, netId, isSpecial);
                    }
                }
            } else {
                commonResultBean.setErrorCode("200")
            }
        } catch (Exception e) {
            e.printStackTrace()
            commonResultBean.setErrorCode("300")
        }
        log.info "------->resendCA end"
        return commonResultBean
    }
    /**
     * 重发分区管理授权
     * @param clientId
     * @param req
     * @return
     */
    public CommonResultBean resendAreaControlCA(String clientId, ResendAreaControlCAReq req) {
        log.info "------->resendAreaControlCA begin"
        CommonResultBean commonResultBean = new CommonResultBean()
        if (req == null || req.areaControlId == null || req.customerId == null||req.customerId==0 || req.operatorId == null) {
            commonResultBean.setErrorCode("200")
            return commonResultBean
        }
        try {
            if (req && req.getOperatorId()) {
                log.info "-------------->resendAreaControlCA params [customId:" + req.getCustomerId() + "]"
                def cmngStbcustomvdos = new HashSet<com.dvn.miniboss.oldsms.CmngStbcustomvdo>()
                if (req.getCustomerId() != 0 && req.getCustomerId() > 0) {
                    com.dvn.miniboss.oldsms.CmngCustom custom = com.dvn.miniboss.oldsms.CmngCustom.get(req.getCustomerId())
                    if (custom) {
                        //收集机卡
                        for (com.dvn.miniboss.oldsms.CmngStbcustomvdo cmngStbcustomvdo: com.dvn.miniboss.oldsms.CmngStbcustomvdo.executeQuery("from CmngStbcustomvdo c where c.useridCmngUser in (select u.id from CmngUser u where u.status <> '" + com.dvn.miniboss.acct.CmngUser.STATUS_INVALID + "' and u.dvbCostomId=" + custom.getId() + ")")) {
                            cmngStbcustomvdos.add(cmngStbcustomvdo)
                        }

                    } else {
                        log.warn "-------------->resendAreaControlCA Error: Custom Not Found! [customId:" + req.customerId + "]"
                    }
                }
                else {
                    log.warn "-------------->resendAreaControlCA Error: Custom Not Found! [customId:" + req.customerId + "]"
                }

                //重发分区指令
                log.info "-------------->resendAreaControlCA for Areacontrol [customId:" + req.getCustomerId() + "|cmngStbcustomvdos size:" + cmngStbcustomvdos.size() + "]"
                def isSpecial = "1", areaId = "", netId = "", operatorId = ""
                for (com.dvn.miniboss.oldsms.CmngStbcustomvdo cmngStbcustomvdo: cmngStbcustomvdos) {
                    def catemplateId = ""
                    areaId = cmngStbcustomvdo.areaid
                    netId = cmngStbcustomvdo.netid
                    operatorId = req.operatorId
                    //判断是否做过分区

                    if (com.dvn.miniboss.log.OperateAction.ID_VILLAGE_AREACONTROL == Long.parseLong(req.areaControlId))
                        catemplateId = com.dvn.miniboss.ca.CaTemplate.VILLAGE
                    else if (com.dvn.miniboss.log.OperateAction.ID_CITY_AREACONTROL == Long.parseLong(req.areaControlId))
                        catemplateId = com.dvn.miniboss.ca.CaTemplate.CITY

                    //判断是否做过1003特殊用户包
                    com.dvn.miniboss.product.AreacontrolSpecialuserHistory specialuserHistory = com.dvn.miniboss.product.AreacontrolSpecialuserHistory.find("from AreacontrolSpecialuserHistory a where a.action.id in (" + com.dvn.miniboss.log.OperateAction.ID_SPECIAL_USER + ") and a.goodsNumber='" + cmngStbcustomvdo.getStbid() + ";" + cmngStbcustomvdo.getIcid() + "' order by a.createDate desc")
                    if (null != specialuserHistory) {
                        if (com.dvn.miniboss.log.OperateAction.ID_VILLAGE_AREACONTROL ==  Long.parseLong(req.areaControlId))
                            catemplateId = com.dvn.miniboss.ca.CaTemplate.VILLAGE_SPECIALUSER
                        else if (com.dvn.miniboss.log.OperateAction.ID_CITY_AREACONTROL ==  Long.parseLong(req.areaControlId))
                            catemplateId = com.dvn.miniboss.ca.CaTemplate.CITY_SPECIALUSER
                    }
                    log.info "-------------->resendAreaControlCA for Areacontrol [catemplateId:" + catemplateId + "]"
                    sendCaMessageService.sendCAByTemplateBatch("", cmngStbcustomvdo.getStbid(), cmngStbcustomvdo.getIcid(), catemplateId, new Date(), new Date(), areaId, operatorId, netId, isSpecial);
                    //生成历史记录
                    AreacontrolSpecialuserHistory history = new AreacontrolSpecialuserHistory()
                    history.customId = cmngStbcustomvdo.dvbcustomidCmngCustom
                    history.userId = cmngStbcustomvdo.useridCmngUser;
                    history.areaId = areaId
                    history.netId = netId
                    history.operatorId = req.operatorId
                    history.action = com.dvn.miniboss.log.OperateAction.get(Long.parseLong(req.areaControlId))
                    history.createDate = new Date()
                    history.goodsNumber = cmngStbcustomvdo.stbid + ";" + cmngStbcustomvdo.icid
                    if (!history.save()) {
                        history.errors.each {
                            log.info(it);
                        }
                    }

                }

            } else {
                commonResultBean.setErrorCode("200")
            }
        } catch (Exception e) {
            e.printStackTrace()
            commonResultBean.setErrorCode("300")
        }
        log.info "------->resendAreaControlCA end"
        return commonResultBean
    }
  /**
   * 配对
   * @param clientId
   * @param req
   * @return
   */
  public CommonResultBean partner(String clientId, PartnerReq req) {
    CommonResultBean commonResultBean = new CommonResultBean()
    log.info '------->partner begin'
    try {
      log.info '------->partner [pairArray.length:'+req.getPairArray().length + "]"
      if (req && req.getPairArray() && req.getPairArray().length > 0) {
        List<String[]> lists = new ArrayList<String[]>()
        String[] args = null
        for (String str: req.getPairArray()) {
          System.out.println( '==str='+str)
          log.info '------->str:'+str
          args = str.split("&")
          if (args && args.length == 4 && args[0] && args[1] && args[2] && args[3]) {
            lists.add(args)
          } else {
            commonResultBean.setErrorCode("200")
            return commonResultBean
          }
        }
        //配对
        for (String[] scao: lists) {
          log.info '------->partner doing [stbid:'+scao[0] +",icid:"+ scao[1] + "]"
          sendCaMessageService.bindByStbRealTime(scao[0], scao[1], new Date(), scao[2], scao[3])
        }
      } else {
        commonResultBean.setErrorCode("200")
      }
    } catch (Exception e) {
      e.printStackTrace()
      commonResultBean.setErrorCode("300")
    }
    return commonResultBean
  }

  /**
   * 工单查询
   * @param clientId
   * @param req
   * @return WorkOrderListBean
   */
  public WorkOrderListBean getWorkOrder(String clientId, WorkOrderReq req) {
    WorkOrderListBean workOrderListBean = new WorkOrderListBean()
    SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd")
    try {
      if (req) {
        StringBuffer hql = new StringBuffer("from WorkOrder w where 1=1");
        /**
         * 客户信息处理
         * 客户编号、客户名称、身份证号、UU号、装机地址、业务类型来查。
         */
        if (req.getCustomerId())
          hql.append(" and w.customId=" + req.getCustomerId())
        if (req.getCustomName())
          hql.append(" and w.customName like '%" + req.getCustomName() + "%'")
        if (req.getID() || req.getAddress()) {
          hql.append(" and w.customId in (select t.id from CmngCustom t where t.customidCmngCustominfo in (select c.id from CmngCustominfo c where 1=1")
          if (req.getID())
            hql.append(" and c.idcard='" + req.getID() + "'")
          if (req.getAddress())
            hql.append(" and c.address like '%" + req.getAddress() + "%'")
          hql.append("))")
        }

        if (req.getUuid())
          hql.append(" and w.customId in (select s.dvbcustomidCmngCustom from CmngStbcustomvdo s where s.smcode='" + req.getUuid() + "')")
        if (req.getCustomerType())
          hql.append(" and w.customId in (select c.id from CmngCustom c where c.customType='" + req.getCustomerType() + "')")

        /**
         * 地区处理
         * 如果地区ID存在，则以地区ID来查询；
         * 否则依次以地区对象、网点ID来查询
         */
        if (req.getAreaid())
          hql.append(" and w.area='" + req.getAreaid() + "'")
        else if (req.getArea())
          hql.append(" and w.area='" + req.getArea() + "'")
        else if (req.getNetid())
          hql.append(" and w.area in (select n.areaCode.id NetCode n where n.id='" + req.getNetid() + "')")

        if (req.getWorkOrderNo())
          hql.append(" and w.workOrderNo='" + req.getWorkOrderNo() + "'")
        if (req.getSendWorker())
          hql.append(" and w.sendWorker='" + req.getSendWorker() + "'")
        if (req.getSendDate())
          hql.append(" and w.sendDate='" + req.getSendDate() + "'")
        if (req.getReceiveWorker())
          hql.append(" and w.receiveWorker='" + req.getReceiveWorker() + "'")
        if (req.getReceiveDate())
          hql.append(" and w.receiveDate='" + req.getReceiveDate() + "'")
        if (req.getCreateDate())
          hql.append(" and to_char(w.createDate ,'yyyy-MM-dd')='" + simple.format(req.getCreateDate()) + "'")
        if (req.getStatus()){
          //挂起按工单状态查询，其他按任务状态查询
          if(com.dvn.miniboss.acct.WorkOrder.SUBSTATUS_DVNSTBINSTALL_PAUSE.equals(req.getStatus()))
           hql.append(" and w.status='" + com.dvn.miniboss.acct.WorkOrder.STATUS_PAUSE + "'")
          else
           hql.append(" and w.status<>'" + com.dvn.miniboss.acct.WorkOrder.STATUS_PAUSE + "' and w.subStatus='" + req.getStatus() + "'")
        }
        if (req.getProcessId())
          hql.append(" and w.processId='" + req.getProcessId() + "'")

        List<com.dvn.miniboss.acct.WorkOrder> wList = com.dvn.miniboss.acct.WorkOrder.executeQuery(hql.toString())
        WorkOrder workOrder = null
        for (com.dvn.miniboss.acct.WorkOrder w: wList) {
          workOrder = new WorkOrder()
          workOrder.setId(w.getId())
          workOrder.setWorkOrderNo(w.getWorkOrderNo())
          workOrder.setName(w.getName())
          workOrder.setTypeName(w.getTypeName())
          workOrder.setCustomId(w.getCustomId())
          workOrder.setCustomName(w.getCustomName())
          workOrder.setSendWorker(w.getSendWorker())
          workOrder.setSendDate(w.getSendDate())
          workOrder.setReceiveWorker(w.getReceiveWorker())
          workOrder.setReceiveDate(w.getReceiveDate())
          workOrder.setArea(w.getArea())
          workOrder.setType(w.getType())
          workOrder.setSubType(w.getSubType())
          workOrder.setCreateDate(w.getCreateDate())
          if (w.getStatus() && w.getSubStatus()) {
            workOrder.setStatus(w.getStatus())
            workOrder.setSubStatus(w.getSubStatus())
            WorkOrderExtendBean extendBean = new Gson().fromJson(w.getExtendsValue(), WorkOrderExtendBean.class)
            //撤单原因or挂起原因
            if (com.dvn.miniboss.acct.WorkOrder.STATUS_PAUSE.equals(w.getStatus())) {
              workOrder.setReasonForUp(extendBean.getHung())
            }else if (com.dvn.miniboss.acct.WorkOrder.SUBSTATUS_DVNSTBINSTALL_CANCEL.equals(w.getSubStatus())) {
              workOrder.setReasonForUp(extendBean.getReason())
            }
          }

          if (w.getTaskActivityName()) {
            if (w.getTaskActivityName() == "GetOrder")
              workOrder.setTaskName(MessageUtil.getMessage("call.workorder.taskName.getOrder"))
            else if (w.getTaskActivityName() == "Replay")
              workOrder.setTaskName(MessageUtil.getMessage("call.workorder.taskName.replay"))
          } else {
            workOrder.setTaskName(MessageUtil.getMessage("call.workorder.taskName.over"))
          }
          workOrder.setOperatorId(w.getOperatorId())
          //workOrder.setExtendsValue(w.getExtendsValue())
          workOrder.setLastUpdateTime(w.getLastUpdateTime())
          workOrder.setPreCompleteTime(w.getPreCompleteTime())
          workOrder.setDescription(w.getDescription())
          workOrderListBean.workOrderList.add(workOrder)
        }
      } else {
        workOrderListBean.setErrorCode("200")
      }
    } catch (Exception e) {
      e.printStackTrace()
      workOrderListBean.setErrorCode("300")
    }
    return workOrderListBean
  }

  /**
   * 返回iBoss系统中所有客户类别对象的列表
   * @param clientId
   * @return
   */
  public CustomTypeListBeanRes getCustomTypeList(String clientId) {
    CustomTypeListBeanRes customTypeListBean = new CustomTypeListBeanRes()
    try {
      List<com.dvn.miniboss.oldsms.CmngCustomtype> list = com.dvn.miniboss.oldsms.CmngCustomtype.list()
      CustomType customType = null
      for (com.dvn.miniboss.oldsms.CmngCustomtype t: list) {
        customType = new CustomType()
        customType.setId(t.getId())
        customType.setTypenm(t.getTypenm())
        customTypeListBean.customTypeList.add(customType)
      }
    } catch (Exception e) {
      e.printStackTrace()
      customTypeListBean.setErrorCode("300")
    }
    return customTypeListBean
  }

  /**
   * 返回iBoss系统中所有业务类型对象的列表
   * @param clientId
   * @return
   */
  public BusinessTypeListBeanRes getBusinessTypeList(String clientId) {
    BusinessTypeListBeanRes businessTypeListBean = new BusinessTypeListBeanRes()
    try {
      List<com.dvn.miniboss.product.BusinessType> list = com.dvn.miniboss.product.BusinessType.findAllByStatus("1")
      BusinessType businessType = null
      for (com.dvn.miniboss.product.BusinessType t: list) {
        businessType = new BusinessType()
        businessType.setId(t.getId())
        businessType.setTypenm(t.getName())
        businessTypeListBean.businessTypeList.add(businessType)
      }
    } catch (Exception e) {
      e.printStackTrace()
      businessTypeListBean.setErrorCode("300")
    }
    return businessTypeListBean
  }

  /**
   * 客户基本信息查询
   * @param clientId
   * @param req
   * @param currentPage
   * @return
   */
  public CustomLightListBeanRes getCustomList(String clientId, CustomListReq req ,String currentPage) {
    CustomLightListBeanRes customLightListBeanRes = new CustomLightListBeanRes()
    int page = 1
    try {
      if (req) {
        StringBuffer hql = new StringBuffer("from CmngCustom c where 1=1 ")
        
        if (req.getCustomNo())
          hql.append(" and c.customNo='" + req.getCustomNo() + "'")
        if (req.getType())
          hql.append(" and c.typeCmngCustomtype='" + req.getType() + "'")
        if (req.getCustomnm() || req.getMaintele() || req.getAddress()) {
          hql.append(" and c.customidCmngCustominfo in (select i.id from CmngCustominfo i where 1=1")
          if (req.getCustomnm())
            hql.append(" and i.customnm like '%" + req.getCustomnm() + "%'")
          if (req.getMaintele())
            hql.append(" and i.maintele = '" + req.getMaintele() + "'")
          if (req.getAddress())
            hql.append(" and i.address like '%" + req.getAddress() + "%'")
          hql.append(")")
        }
        if (req.getUuid() || req.getStbid() || req.getCardId()) {
          hql.append(" and c.id in ( select s.dvbcustomidCmngCustom from CmngStbcustomvdo s where 1=1")
          if (req.getUuid())
            hql.append(" and s.smcode='" + req.getUuid() + "'")
          if (req.getStbid())
            hql.append(" and s.stbid='" + req.getStbid() + "'")
          if (req.getCardId())
            hql.append(" and s.icid='" + req.getCardId() + "'")
          hql.append(")")
        }
        hql.append("order by c.id")
        if(currentPage){
          page = Long.valueOf(currentPage) > 0 ? Long.valueOf(currentPage) : 1
        }        
        List<CmngCustom> list = CmngCustom.executeQuery(hql.toString() ,[max: PAGENUM, offset: PAGENUM * (page - 1)])
        def records = CmngCustom.executeQuery("select count(c) " + hql.toString())
        def recordNum = 0
        if(records && records.size() == 1){
          recordNum = (long) records.get(0)
        }
        //List<CmngCustom> list = CmngCustom.executeQuery(hql.toString())
        if (list && list.size() > 0) {
          CustomLight customLight = null
          for (CmngCustom c: list) {
            CmngCustominfo info = CmngCustominfo.get(c.customidCmngCustominfo)
            customLight = new CustomLight()
            customLight.setDvbCostomId(String.valueOf(c.getId()))
            customLight.setCustomNo(c.getCustomNo())
            customLight.setCustomnm(info?.getCustomnm())
            customLight.setAddress(info?.getAddress())
            customLight.setMaintele(info?.getMaintele())
            customLight.setType(c.getTypeCmngCustomtype())
            customLight.setStatus(c.getStatusCmngCustomstatus())
            customLight.setIdcard(info?.getIdcard())
            customLight.setCustomType(c.getCustomType())
            customLight.setLease(c.getLease()) 
            customLightListBeanRes.customLightList.add(customLight)
          }
        }
        customLightListBeanRes.setTotalPage(String.valueOf(recordNum))
      } else {
        customLightListBeanRes.setErrorCode("200")
      }
    } catch (Exception e) {
      e.printStackTrace()
      customLightListBeanRes.setErrorCode("300")
    }
    return customLightListBeanRes
  }

  /**
   * 客户详细信息查询
   * @param clientId
   * @param customerId
   * @return
   */
  public CustomInfoRes getCustomInfo(String clientId, String customerId) {
    CustomInfoRes customInfoRes = new CustomInfoRes()
    try {
      if (customerId && customerId.trim().length() > 0) {
        CmngCustom custom = CmngCustom.get(Long.valueOf(customerId))
        if (custom) {
          CmngCustominfo info = CmngCustominfo.get(custom.getCustomidCmngCustominfo())
          customInfoRes.setCustomnm(info?.getCustomnm())
          customInfoRes.setCustomNo(custom.getCustomNo())
          customInfoRes.setCreateDate(custom.getCreateDate())
          customInfoRes.setMaintele(info?.getMaintele())
          customInfoRes.setUserType(custom.getCustomType())
          customInfoRes.setVoucher(MessageUtil.getMessage("call.custom.voucher"))
          customInfoRes.setIdcard(info?.getIdcard())
          List<CmngStbcustomvdo> list = CmngStbcustomvdo.findAllByDvbcustomidCmngCustom(custom.getId())
          if (list && list.size() > 0) {
            customInfoRes.setStbnum(String.valueOf(list.size()))
            CustomStbNum customStbNum = null
            Map<String, CmngUser> users = new HashMap<String, CmngUser>()
            List<CmngUser> uList = CmngUser.findAllByDvbCostomId(custom.getId())
            for (CmngUser u: uList) {
              users.put(String.valueOf(u.getId()), u)
            }
            if (CMNGCUSTOM_PERSONAL.equals(custom?.getCustomType())) {
              for (CmngStbcustomvdo cs: list) {
                CmngUser user = users.get(String.valueOf(cs.getUseridCmngUser()))
                customStbNum = new CustomStbNum()
                customStbNum.setUserId(String.valueOf(user?.getId()))
                customStbNum.setStatus(user?.getStatus())
                customStbNum.setStbnum(CmngStbType.get(cs.getClassType())?.getName() + cs.getStbid())
                customStbNum.setIccard(cs.getIcid())
                customStbNum.setDescription(user?.getDescription())
                customStbNum.setCreateDate(user?.getCreateDate())
                customStbNum.setGroupType(user?.getGroupType())
                customInfoRes.customStbNumList.add(customStbNum)
              }
            } else {
              for (CmngStbcustomvdo cs: list) {
                CmngUserRef r = CmngUserRef.findByChildUserId(cs.getUseridCmngUser())
                CmngUser user = users.get(String.valueOf(r?.getFatherUserId()))
                customStbNum = new CustomStbNum()
                customStbNum.setUserId(String.valueOf(user?.getId()))
                customStbNum.setStatus(user?.getStatus())
                customStbNum.setStbnum(CmngStbType.get(cs.getClassType())?.getName() + cs.getStbid())
                customStbNum.setIccard(cs.getIcid())
                customStbNum.setDescription(user?.getDescription())
                customStbNum.setCreateDate(user?.getCreateDate())
                customStbNum.setGroupType(user?.getGroupType())
                customInfoRes.customStbNumList.add(customStbNum)
              }
            }
          } else {
            customInfoRes.setStbnum("0")
          }
          CmngUser user = CmngUser.findByDvbCostomIdAndClassType(custom.getId(), "10001")
          customInfoRes.setMainDate(user?.getCreateDate())
          customInfoRes.setStatus(custom.getStatusCmngCustomstatus())
          if (custom.getTypeCmngCustomtype())
            customInfoRes.setType(CmngCustomtype.get(custom.getTypeCmngCustomtype())?.getTypenm())
          customInfoRes.setBalance(String.valueOf(computeBalance(custom.getId())))
          customInfoRes.setAddress(info?.getAddress())
          customInfoRes.setCustomType(custom.getCustomType())
        }
      } else {
        customInfoRes.setErrorCode("200")
      }
    } catch (Exception e) {
      e.printStackTrace()
      customInfoRes.setErrorCode("300")
    }
    return customInfoRes
  }

  /**
   * 服务订购详细信息查询
   * @param clientId
   * @param userId
   * @param businessTypeId
   * @return
   */
  public ServiceListBeanRes getServiceByUser(String clientId, String userId, String businessTypeId) {
    ServiceListBeanRes serviceListBeanRes = new ServiceListBeanRes()
    try {
      if (userId && businessTypeId) {
        List<com.dvn.miniboss.oldsms.AuthService> sList = com.dvn.miniboss.oldsms.AuthService.executeQuery("from AuthService a where a.orderUserId='" + userId + "' and a.businessType='" + businessTypeId + "'")
        ServiceInfo serviceInfo = null
        CmngStbcustomvdo cs = CmngStbcustomvdo.findByUseridCmngUser(Long.valueOf(userId))
        CmngUser user = CmngUser.get(Long.valueOf(userId))
        for (com.dvn.miniboss.oldsms.AuthService s: sList) {
          serviceInfo = new ServiceInfo()
          serviceInfo.setServiceId(String.valueOf(s.getId()))
          serviceInfo.setUserId(String.valueOf(s.getOrderUserId()))
          serviceInfo.setStbnum(cs?.getStbid())
          serviceInfo.setIccard(cs?.getIcid())
          serviceInfo.setProductname(s.getProductname())
          serviceInfo.setStartdate(s.getStartdate())
          serviceInfo.setEnddate(s.getEnddate())
          serviceInfo.setStatus(s.getStatus())
          serviceInfo.setUserStatus(user?.getStatus())
          serviceInfo.setPaymode(s.getPaymode())
          serviceListBeanRes.serviceInfoList.add(serviceInfo)
        }
      } else {
        serviceListBeanRes.setErrorCode("200")
      }
    } catch (Exception e) {
      e.printStackTrace()
      serviceListBeanRes.setErrorCode("300")
    }
    return serviceListBeanRes
  }

  /**
   * 授权/配对 服务订购信息列表查询 按类型、时间分组取最近的一条记录
   * @param clientId
   * @param customerId
   * @return
   */
  public ServiceListBeanRes getServiceByCustom(String clientId, String customerId) {
    ServiceListBeanRes serviceListBeanRes = new ServiceListBeanRes()
    try {
      if (customerId) {
        ServiceInfo serviceInfo = null
        String billTypeId = null
        String tag = CmngCustom.get(customerId)?.getCustomType()
        if (tag) {
          if (CMNGCUSTOM_PERSONAL.equals(tag)) {
            //hql = "select s.a from ( select row_number() over(partition by a.orderUserId,a.billTypeId order by a.enddate desc) lev, a from AuthService a where a.userid=" + customerId + " and a.freestartdate <= sysdate) s where s.lev=1"
            for(com.dvn.miniboss.acct.CmngUser u : com.dvn.miniboss.acct.CmngUser.findAllByDvbCostomId(customerId)){
              List<com.dvn.miniboss.oldsms.AuthService> sList = com.dvn.miniboss.oldsms.AuthService.executeQuery("from AuthService a where a.orderUserId=" + u.getId() + " and nvl(a.freestartdate ,a.startdate) <= sysdate order by a.billTypeId,a.startdate desc")
              billTypeId = "begin"
              for(com.dvn.miniboss.oldsms.AuthService s : sList){
                if (s.getBillTypeId() != billTypeId) {
                  CmngStbcustomvdo cs = CmngStbcustomvdo.findByUseridCmngUser(s.getOrderUserId())
                  serviceInfo = new ServiceInfo()
                  serviceInfo.setServiceId(String.valueOf(s.getId()))
                  serviceInfo.setUserId(String.valueOf(s.getOrderUserId()))                                    
                  serviceInfo.setStbnum(cs.getStbid())
                  serviceInfo.setIccard(cs.getIcid())
                  serviceInfo.setProductname(s.getProductname())
                  serviceInfo.setStartdate(s.getStartdate())
                  if (s.getEnddate() != null && s.getRealenddate() != null) {
                    if ((s.getEnddate().getTime() - s.getRealenddate().getTime()) > 0) serviceInfo.setEnddate(s.getEnddate())
                    else serviceInfo.setEnddate(s.getRealenddate())
                  } else {
                    if (s.getEnddate() == null && s.getRealenddate() != null) serviceInfo.setEnddate(s.getRealenddate())
                    else if (s.getRealenddate() == null && s.getEnddate() != null) serviceInfo.setEnddate(s.getEnddate())
                  }
                  //serviceInfo.setEnddate(s.getEnddate())
                  serviceInfo.setStatus(s.getStatus())
                  serviceInfo.setPaymode(s.getPaymode())
                  serviceListBeanRes.serviceInfoList.add(serviceInfo)
                  billTypeId = s.getBillTypeId()
                }
              }
            }

          } else {
            for(com.dvn.miniboss.acct.CmngUserRef r : CmngUserRef.findAllByDvbCostomId(customerId)){
              CmngStbcustomvdo cs = CmngStbcustomvdo.findByUseridCmngUser(r.getChildUserId())
              List<com.dvn.miniboss.oldsms.AuthService> sList = com.dvn.miniboss.oldsms.AuthService.executeQuery("from AuthService a where a.orderUserId=" + r.getFatherUserId() + " and nvl(a.freestartdate ,a.startdate) <= sysdate order by a.billTypeId,a.startdate desc")
              billTypeId = "begin"              
              for(com.dvn.miniboss.oldsms.AuthService s : sList){
                if (s.getBillTypeId() != billTypeId) {
                  serviceInfo = new ServiceInfo()
                  serviceInfo.setServiceId(String.valueOf(s.getId()))
                  serviceInfo.setUserId(String.valueOf(s.getOrderUserId()))
                  serviceInfo.setStbnum(cs.getStbid())
                  serviceInfo.setIccard(cs.getIcid())
                  serviceInfo.setProductname(s.getProductname())
                  serviceInfo.setStartdate(s.getStartdate())
                  if (s.getEnddate() != null && s.getRealenddate() != null) {
                    if ((s.getEnddate().getTime() - s.getRealenddate().getTime()) > 0) serviceInfo.setEnddate(s.getEnddate())
                    else serviceInfo.setEnddate(s.getRealenddate())
                  } else {
                    if (s.getEnddate() == null && s.getRealenddate() != null) serviceInfo.setEnddate(s.getRealenddate())
                    else if (s.getRealenddate() == null && s.getEnddate() != null) serviceInfo.setEnddate(s.getEnddate())
                  }
                  //serviceInfo.setEnddate(s.getEnddate())
                  serviceInfo.setStatus(s.getStatus())
                  serviceInfo.setPaymode(s.getPaymode())
                  serviceListBeanRes.serviceInfoList.add(serviceInfo)
                  billTypeId = s.getBillTypeId()
                }
              }
            }
          }
        } else {
          serviceListBeanRes.setErrorCode("200")
        }
        
      } else {
        serviceListBeanRes.setErrorCode("200")
      }
    } catch (Exception e) {
      e.printStackTrace()
      serviceListBeanRes.setErrorCode("300")
    }


    /*try {
      if (customerId) {
        List<com.dvn.miniboss.oldsms.AuthService> sList = com.dvn.miniboss.oldsms.AuthService.executeQuery("from AuthService s where s.userid=" + customerId + " order by s.orderUserId,s.billTypeId,s.enddate desc")
        ServiceInfo serviceInfo = null
        String billTypeId = "begin"
        String tag = CmngCustom.get(customerId)?.getCustomType()
        if (tag) {
          if (tag == "1") {
            for (com.dvn.miniboss.oldsms.AuthService s: sList) {
              if (s.getBillTypeId() != billTypeId) {
                CmngStbcustomvdo cs = CmngStbcustomvdo.findByUseridCmngUser(s.getOrderUserId())
                serviceInfo = new ServiceInfo()
                serviceInfo.setServiceId(String.valueOf(s.getId()))
                serviceInfo.setUserId(String.valueOf(s.getOrderUserId()))
                serviceInfo.setStbnum(cs.getStbid())
                serviceInfo.setIccard(cs.getIcid())
                serviceInfo.setProductname(s.getProductname())
                serviceInfo.setStartdate(s.getStartdate())
                serviceInfo.setEnddate(s.getEnddate())
                serviceInfo.setStatus(s.getStatus())
                serviceInfo.setPaymode(s.getPaymode())
                serviceListBeanRes.serviceInfoList.add(serviceInfo)
                billTypeId = s.getBillTypeId()
              }
            }
          } else {
            for (com.dvn.miniboss.oldsms.AuthService s: sList) {
              if (s.getBillTypeId() != billTypeId) {
                for (CmngUserRef u: CmngUserRef.findAllByFatherUserId(s.getOrderUserId())) {
                  CmngStbcustomvdo cs = CmngStbcustomvdo.findByUseridCmngUser(u.getChildUserId())
                  serviceInfo = new ServiceInfo()
                  serviceInfo.setServiceId(String.valueOf(s.getId()))
                  serviceInfo.setUserId(String.valueOf(s.getOrderUserId()))
                  serviceInfo.setStbnum(cs.getStbid())
                  serviceInfo.setIccard(cs.getIcid())
                  serviceInfo.setProductname(s.getProductname())
                  serviceInfo.setStartdate(s.getStartdate())
                  serviceInfo.setEnddate(s.getEnddate())
                  serviceInfo.setStatus(s.getStatus())
                  serviceInfo.setPaymode(s.getPaymode())
                  serviceListBeanRes.serviceInfoList.add(serviceInfo)
                }
                billTypeId = s.getBillTypeId()
              }
            }
          }
        } else {
          serviceListBeanRes.setErrorCode("200")
        }

      } else {
        serviceListBeanRes.setErrorCode("200")
      }
    } catch (Exception e) {
      serviceListBeanRes.setErrorCode("300")
    }*/
    if(serviceListBeanRes.serviceInfoList.size()==0){
      serviceListBeanRes.serviceInfoList = null
    }
    return serviceListBeanRes
  }

  /**
   * 按机顶盒编号查询服务
   * @param clientId
   * @param stbid
   * @return
   */
  public ServiceListBeanRes getServiceByStb(String clientId, String stbid) {
    ServiceListBeanRes serviceListBeanRes = new ServiceListBeanRes()
    try {
      if (stbid) {
        ServiceInfo serviceInfo = null
        CmngStbcustomvdo cs = CmngStbcustomvdo.findByStbid(stbid)
        if(cs){
          String tag = CmngCustom.get(cs.getDvbcustomidCmngCustom())?.getCustomType()
          String billTypeId = null
          if (CMNGCUSTOM_PERSONAL.equals(tag)) {
            List<com.dvn.miniboss.oldsms.AuthService> sList = com.dvn.miniboss.oldsms.AuthService.executeQuery("from AuthService a where a.orderUserId=" + cs.getUseridCmngUser() + " and nvl(a.freestartdate ,a.startdate) <= sysdate order by a.billTypeId,a.startdate desc")
            billTypeId = "begin"
            for(com.dvn.miniboss.oldsms.AuthService s : sList){
              if (s.getBillTypeId() != billTypeId) {
                serviceInfo = new ServiceInfo()
                serviceInfo.setServiceId(String.valueOf(s.getId()))
                serviceInfo.setUserId(String.valueOf(s.getOrderUserId()))
                serviceInfo.setStbnum(cs.getStbid())
                serviceInfo.setIccard(cs.getIcid())
                serviceInfo.setProductname(s.getProductname())
                serviceInfo.setStartdate(s.getStartdate())
                serviceInfo.setEnddate(s.getEnddate())
                serviceInfo.setStatus(s.getStatus())
                serviceInfo.setPaymode(s.getPaymode())
                serviceListBeanRes.serviceInfoList.add(serviceInfo)
                billTypeId = s.getBillTypeId()
              }
            }
          } else {
            CmngUserRef r = CmngUserRef.findByChildUserId(cs.getUseridCmngUser())
            List<com.dvn.miniboss.oldsms.AuthService> sList = com.dvn.miniboss.oldsms.AuthService.executeQuery("from AuthService a where a.orderUserId=" + r.getFatherUserId() + " and nvl(a.freestartdate ,a.startdate) <= sysdate order by a.billTypeId,a.startdate desc")
            billTypeId = "begin"
            for(com.dvn.miniboss.oldsms.AuthService s : sList){
              if (s.getBillTypeId() != billTypeId) {
                serviceInfo = new ServiceInfo()
                serviceInfo.setServiceId(String.valueOf(s.getId()))
                serviceInfo.setUserId(String.valueOf(s.getOrderUserId()))
                serviceInfo.setStbnum(cs.getStbid())
                serviceInfo.setIccard(cs.getIcid())
                serviceInfo.setProductname(s.getProductname())
                serviceInfo.setStartdate(s.getStartdate())
                serviceInfo.setEnddate(s.getEnddate())
                serviceInfo.setStatus(s.getStatus())
                serviceInfo.setPaymode(s.getPaymode())
                serviceListBeanRes.serviceInfoList.add(serviceInfo)
                billTypeId = s.getBillTypeId()
              }
            }
          }
        }
      } else {
        serviceListBeanRes.setErrorCode("200")
      }
    } catch (Exception e) {
      e.printStackTrace()
      serviceListBeanRes.setErrorCode("300")
    }
    return serviceListBeanRes
  }

  /**
   * 交易明细列表查询 取最近100条记录
   * 数字电视："id"="交易号" "cusotmerId"="客户编号" "productName"="缴费类型" "billType"="单据类型" "realprice"="交费金额" "createDate"="交费日期" "srvendDate"="缴费期限"
   * 增值业务："id"="交易号" "transName"="交易名称" "transContent"="交易内容" "happenType"="发生金额" "createDate"="交易时间"
   * @param clientId
   * @param customerId
   * @param businessTypeId
   * @return
   */
  public TransDetailListBeanRes getTransactionDetail(String clientId, String customerId, String businessTypeId) {
    TransDetailListBeanRes transDetailListBeanRes = new TransDetailListBeanRes()
    try {
      if (customerId && businessTypeId) {
        TransDetail transDetail = null
        Map<String, String> maps = new HashMap<String, String>()
        for (ChargeBillType t: ChargeBillType.findAll()) {
          maps.put(t.getId(), t.getTypename())
        }
        CmngCustom custom = CmngCustom.get(customerId)
        if(custom){
          /**
           * 数字电视 or 增值业务
           */
          if(com.dvn.miniboss.product.BusinessType.TYPE_SZDS.equals(businessTypeId)){
            //订购关系
            List<com.dvn.miniboss.oldsms.AuthService> services = com.dvn.miniboss.oldsms.AuthService.findAll("from AuthService b where b.userid=" + customerId + " and b.billTypeId not in (select c.id from ChargeBillType c where c.invoiceListType='" + ACCTINVOICELISTTYPE_ZZF + "' ) order by b.createdate desc", [max: 75])
            for (com.dvn.miniboss.oldsms.AuthService s: services) {
              transDetail = new TransDetail()
              transDetail.setId(String.valueOf(s.getId()))
              transDetail.setCusotmerId(custom.getCustomNo())
              transDetail.setProductName(s.getProductname())
              transDetail.setBillType(maps.get(s.getBillTypeId()))
              List<com.dvn.miniboss.acct.DepositDetail> details = com.dvn.miniboss.acct.DepositDetail.findAllByDepositRefId(s.getId())
              long temp = 0;
              for(com.dvn.miniboss.acct.DepositDetail d : details){
                temp += d.getDepositCount()
              }
              if(temp == 0){
                transDetail.setRealprice(s.getPricefix())
              }else{
                transDetail.setRealprice(temp)
              }
              transDetail.setCreateDate(s.getCreatedate())
              transDetail.setSrvstartDate(s.getStartdate())
              transDetail.setSrvendDate(s.getEnddate())
              //transDetail.setServiceDays()
              transDetailListBeanRes.transDetailList.add(transDetail)
            }
            //一次性订购关系
            List<com.dvn.miniboss.oldsms.AuthServiceForOnce> onces = com.dvn.miniboss.oldsms.AuthServiceForOnce.findAll("from AuthServiceForOnce b where b.userid=" + customerId + " and b.billTypeId not in (select c.id from ChargeBillType c where c.invoiceListType='" + ACCTINVOICELISTTYPE_ZZF + "' ) order by b.createdate desc", [max: 75])
            for(com.dvn.miniboss.oldsms.AuthServiceForOnce o : onces){
              transDetail = new TransDetail()
              transDetail.setId(String.valueOf(o.getId()))
              transDetail.setCusotmerId(custom.getCustomNo())
              transDetail.setProductName(o.getProductname())
              transDetail.setBillType(maps.get(o.getBillTypeId()))
              List<com.dvn.miniboss.acct.DepositDetail> details = com.dvn.miniboss.acct.DepositDetail.findAllByDepositRefId(o.getId())
              long temp = 0;
              for(com.dvn.miniboss.acct.DepositDetail d : details){
                temp += d.getDepositCount()
              }
              if(temp == 0){
                transDetail.setRealprice(o.getPricefix())
              }else{
                transDetail.setRealprice(temp)
              }
              transDetail.setRealprice(o.getPricefix())
              transDetail.setCreateDate(o.getCreatedate())
              transDetail.setSrvstartDate(o.getStartdate())
              transDetail.setSrvendDate(o.getEnddate())
              //transDetail.setServiceDays()
              transDetailListBeanRes.transDetailList.add(transDetail)
            }
            //营业厅缴费
            List<com.dvn.miniboss.acct.ChargePayBillDetailSettleCollect> details = com.dvn.miniboss.acct.ChargePayBillDetailSettleCollect.executeQuery("from ChargePayBillDetailSettleCollect d where d.chargePayBillId in (select c.id from ChargePaybill c where c.customid=" + customerId + ") and d.settleType='"+ com.dvn.miniboss.acct.AcctSettleType.TYPE_SAVING +"' order by d.createDate desc" ,[max: 25])
            for(com.dvn.miniboss.acct.ChargePayBillDetailSettleCollect d : details){
              transDetail = new TransDetail()
              transDetail.setId(String.valueOf(d.getId()))
              transDetail.setCusotmerId(custom.getCustomNo())
              //transDetail.setProductName()
              transDetail.setBillType(MessageUtil.getMessage("call.transdetail.saving"))
              transDetail.setRealprice(d.getAmount())
              transDetail.setCreateDate(d.getCreateDate())
              //transDetail.setSrvendDate()
              //transDetail.setSrvstartDate(b.getSrvstartdate())
              //transDetail.setServiceDays(b.getServicedays())
              transDetailListBeanRes.transDetailList.add(transDetail)
            }
            //充值卡
            details = com.dvn.miniboss.acct.ChargePayBillDetailSettleCollect.executeQuery("from ChargePayBillDetailSettleCollect d where d.chargePayBillId in (select c.id from ChargePaybill c where c.customid=" + customerId + " and c.transtype='"+ ACCTPAYMENTMETHOD_CARD + "') order by d.createDate desc" ,[max: 25])
            for(com.dvn.miniboss.acct.ChargePayBillDetailSettleCollect d : details){
              transDetail = new TransDetail()
              transDetail.setId(String.valueOf(d.getId()))
              transDetail.setCusotmerId(custom.getCustomNo())
              //transDetail.setProductName()
              transDetail.setBillType(MessageUtil.getMessage("call.transdetail.card"))
              transDetail.setRealprice(d.getAmount())
              transDetail.setCreateDate(d.getCreateDate())
              //transDetail.setSrvendDate()
              //transDetail.setSrvstartDate(b.getSrvstartdate())
              //transDetail.setServiceDays(b.getServicedays())
              transDetailListBeanRes.transDetailList.add(transDetail)
            }
            //一卡通
            details = com.dvn.miniboss.acct.ChargePayBillDetailSettleCollect.executeQuery("from ChargePayBillDetailSettleCollect d where d.chargePayBillId in (select c.id from ChargePaybill c where c.customid=" + customerId + " and c.transtype='"+ ACCTPAYMENTMETHOD_BANK + "') order by d.createDate desc" ,[max: 25])
            for(com.dvn.miniboss.acct.ChargePayBillDetailSettleCollect d : details){
              transDetail = new TransDetail()
              transDetail.setId(String.valueOf(d.getId()))
              transDetail.setCusotmerId(custom.getCustomNo())
              //transDetail.setProductName()
              transDetail.setBillType(MessageUtil.getMessage("call.transdetail.bank"))
              transDetail.setRealprice(d.getAmount())
              transDetail.setCreateDate(d.getCreateDate())
              //transDetail.setSrvendDate()
              //transDetail.setSrvstartDate(b.getSrvstartdate())
              //transDetail.setServiceDays(b.getServicedays())
              transDetailListBeanRes.transDetailList.add(transDetail)
            }

          }else if(com.dvn.miniboss.product.BusinessType.TYPE_ZZYW.equals(businessTypeId)){
            //订购关系   "id"="交易号" "transName"="交易名称" "transContent"="交易内容" "happenType"="发生金额" "createDate"="交易时间"
            List<com.dvn.miniboss.oldsms.AuthService> services = com.dvn.miniboss.oldsms.AuthService.findAll("from AuthService b where b.userid=" + customerId + " and b.billTypeId in (select c.id from ChargeBillType c where c.invoiceListType='" + ACCTINVOICELISTTYPE_ZZF + "' ) order by b.createdate desc", [max: 100])
            for (com.dvn.miniboss.oldsms.AuthService s: services) {
              transDetail = new TransDetail()
              transDetail.setId(String.valueOf(s.getId()))
              transDetail.setCusotmerId(custom.getCustomNo())
              transDetail.setProductName(s.getProductname()) 
              transDetail.setTransName(maps.get(s.getBillTypeId()))
              transDetail.setBillType(maps.get(s.getBillTypeId()))
              List<com.dvn.miniboss.acct.DepositDetail> details = com.dvn.miniboss.acct.DepositDetail.findAllByDepositRefId(s.getId())
              long temp = 0;
              for(com.dvn.miniboss.acct.DepositDetail d : details){
                temp += d.getDepositCount()
              }
              if(temp == 0){
                transDetail.setRealprice(s.getPricefix())
              }else{
                transDetail.setRealprice(temp)
              }
              transDetail.setCreateDate(s.getCreatedate())
              transDetail.setSrvstartDate(s.getStartdate())
              transDetail.setSrvendDate(s.getEnddate())
              //transDetail.setServiceDays()
              transDetailListBeanRes.transDetailList.add(transDetail)
            }
            //一次性订购关系
            List<com.dvn.miniboss.oldsms.AuthServiceForOnce> onces = com.dvn.miniboss.oldsms.AuthServiceForOnce.findAll("from AuthServiceForOnce b where b.userid=" + customerId + " and b.billTypeId in (select c.id from ChargeBillType c where c.invoiceListType='" + ACCTINVOICELISTTYPE_ZZF + "' ) order by b.createdate desc", [max: 100])
            for(com.dvn.miniboss.oldsms.AuthServiceForOnce o : onces){
              transDetail = new TransDetail()
              transDetail.setId(String.valueOf(o.getId()))
              transDetail.setCusotmerId(custom.getCustomNo())
              transDetail.setProductName(o.getProductname())
              transDetail.setTransName(maps.get(o.getBillTypeId()))
              transDetail.setBillType(maps.get(o.getBillTypeId()))
              List<com.dvn.miniboss.acct.DepositDetail> details = com.dvn.miniboss.acct.DepositDetail.findAllByDepositRefId(o.getId())
              long temp = 0;
              for(com.dvn.miniboss.acct.DepositDetail d : details){
                temp += d.getDepositCount()
              }
              if(temp == 0){
                transDetail.setRealprice(o.getPricefix())
              }else{
                transDetail.setRealprice(temp)
              }
              transDetail.setCreateDate(o.getCreatedate())
              transDetail.setSrvstartDate(o.getStartdate())
              transDetail.setSrvendDate(o.getEnddate())
              //transDetail.setServiceDays()
              transDetailListBeanRes.transDetailList.add(transDetail)
            }
          }
        }else{
          transDetailListBeanRes.setErrorCode("200")
        }

      } else {
        transDetailListBeanRes.setErrorCode("200")
      }
    } catch (Exception e) {
      e.printStackTrace()
      transDetailListBeanRes.setErrorCode("300")
    }
    return transDetailListBeanRes
  }

  /**
   * 公告查询
   * 限制只查询三个月内的公告信息
   * @param clientId
   * @param startDate
   * @param endDate
   * @return
   */
  public NotifyListBeanRes getNotify(String clientId, Date startDate, Date endDate) {
    NotifyListBeanRes notifyListBeanRes = new NotifyListBeanRes()
    try {
      if (startDate && endDate) {
        Notify notify = null
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd")
        Date now = new Date()
        long beforeTime = (now.getTime() / 1000) - 60 * 60 * 24 * 90
        Date before = new Date(beforeTime * 1000)
        StringBuffer hql = new StringBuffer("from CaCmdCasHistory c where c.issent = 'Y' and (c.cmdtype = '" + CaCMDType.Cmd_OSD + "' or c.cmdtype = '" + CaCMDType.Cmd_Mail + "')")
        if (startDate.after(before)) {
          hql.append(" and to_char(c.senddate,'yyyy-mm-dd') >= '" + simple.format(startDate) + "'")
        } else {
          hql.append(" and to_char(c.senddate,'yyyy-mm-dd') >= '" + simple.format(before) + "'")
        }
        if (endDate.before(now)) {
          hql.append(" and to_char(c.senddate,'yyyy-mm-dd') <= '" + simple.format(endDate) + "'")
        } else {
          hql.append(" and to_char(c.senddate,'yyyy-mm-dd') <= '" + simple.format(now) + "'")
        }
        List<CaCmdCasHistory> list = CaCmdCasHistory.executeQuery(hql.toString())
        //List<CaCmdCasHistory> list = CaCmdCasHistory.executeQuery("from CaCmdCasHistory c where c.errorflag=? and to_char(c.senddate,'yyyy-mm-dd')>=?" ,["正确" ,simple.format(before)])
        simple = new SimpleDateFormat("yyyyMMdd")
        for (CaCmdCasHistory h: list) {
          notify = new Notify()
          //notify.setTitle()
          notify.setContent(h.getMailcontent())
          notify.setSendDate(h.getSenddate())
          if (h.getProductenddate())
            notify.setEndDate(simple.parse(h.getProductenddate()))
          notify.setOperator(h.getOperatorname())
          if (h.getCmdtype() == CaCMDType.Cmd_OSD)
            notify.setType("OSD")
          else if (h.getCmdtype() == CaCMDType.Cmd_Mail)
            notify.setType("Mail")
          notifyListBeanRes.notifyList.add(notify)
        }
      } else {
        notifyListBeanRes.setErrorCode("200")
      }
    } catch (Exception e) {
      e.printStackTrace()
      notifyListBeanRes.setErrorCode("300")
    }
    return notifyListBeanRes
  }

  /**
   * 获取当前余额账本的所有余额：只获取余额，排除已累帐金额，押金明细无效
   * @param customId
   * @return
   */
  private long computeBalance(long customId){
    long balance = 0
    List<BalanceSnapshot> balanceSnapshots = new ArrayList()
    List<com.dvn.miniboss.acct.Account> aList = com.dvn.miniboss.acct.Account.findAllByDvbCostomId(customId)
    for (com.dvn.miniboss.acct.Account a: aList) {
      /*
      Map<Long, AcctBalance> acctBalanceMap = new HashMap()
      Map<Long, DepositDetail> depoMap = new HashMap()*/

      List<AcctBalance> abList = AcctBalance.findAllByAcctId(a.getId())
      List<AcctSpecialPayment> aspList = AcctSpecialPayment.findAllByAccountId(a.getId())
      List<DepositDetail> ddList = DepositDetail.findAllByAccountId(a.getId())
      for (AcctBalance acctBalance: abList) {
        //acctBalanceMap.put(acctBalance.id, acctBalance)
        BalanceSnapshot snapshot = new BalanceSnapshot()
        snapshot.balanceId = acctBalance.id
        snapshot.balance = acctBalance.balance
        snapshot.prePay = acctBalance.payBeforeUsePlus
        snapshot.afterPay = acctBalance.payAfterUsePlus
        snapshot.maxAfterPay = acctBalance.maxOverdraft
        //snapshot.name = acctBalance.name
        snapshot.isDefault = AcctBalance.ISDEFAUT_TRUE.equals(acctBalance.isDefaut)
        for (AcctSpecialPayment sp: aspList) {
          if (sp.acctBalanceId == acctBalance.id) {
            snapshot.billTypeSet.add(sp.billTypeId)
          }
        }
        for (DepositDetail detail: ddList) {
          if (detail.accountBalanceId == acctBalance.id && detail.depositBalance > 0) {
            snapshot.depositMap.put(detail.depositRefId, detail.depositBalance)
            //depoMap.put(detail.depositRefId, detail)
          }
        }
        balanceSnapshots.add(snapshot)
      }
      for(BalanceSnapshot b : balanceSnapshots){
        balance += b.getCanUseBalance()
      }
    }
    return balance
  }






  /** zhf
   * 返回iBoss系统中个人客户资料及用户列表
   * @param clientId
   * @param req
   * @return
   */
  public CustomerInfoListBean getCustomerInfo(String clientId, CustomerReq req) {
    CustomerInfoListBean customerInfoListBean = new CustomerInfoListBean()
    try {
      if (req) {
        StringBuffer hql = new StringBuffer("from CmngCustom c where c.customType='1'")
        List<String> customIds = new ArrayList<String>()
        if (req.getCustomerId() != 0) {
          customIds.add(String.valueOf(req.getCustomerId()))
        } else if (req.getUuid()) {
          List<CmngStbcustomvdo> list = CmngStbcustomvdo.findAllBySmcode(req.getUuid())
          for (CmngStbcustomvdo cs: list) {
            customIds.add(cs.getDvbcustomidCmngCustom()?.toString())
          }
        } else if (req.getStbId()) {
          List<CmngStbcustomvdo> list = CmngStbcustomvdo.findAllByStbid(req.getStbId())
          for (CmngStbcustomvdo cs: list) {
            customIds.add(cs.getDvbcustomidCmngCustom()?.toString())
          }
        }
        if (customIds && customIds.size() > 0) {
          StringBuffer sb = new StringBuffer()
          for (String id: customIds) {
            sb.append(id + ",")
          }
          String temp = sb.toString()
          hql.append(" and c.id in (" + temp.substring(0, temp.length() - 1) + ")")
        }
        if (req.getCustomerName() || req.getID()) {
          if (req.getCustomerName() && req.getID()) {
            hql.append(" and c.customidCmngCustominfo in (select i.id from CmngCustominfo i where i.customnm like '%" + req.getCustomerName() + "%' and i.idcard='" + req.getID() + "')")
          } else if (req.getCustomerName()) {
            hql.append(" and c.customidCmngCustominfo in (select i.id from CmngCustominfo i where i.customnm like '%" + req.getCustomerName() + "%')")
          } else if (req.getID()) {
            hql.append(" and c.customidCmngCustominfo in (select i.id from CmngCustominfo i where i.idcard='" + req.getID() + "')")
          }
        }
        List<CmngCustom> list = CmngCustom.executeQuery(hql.toString())
        if (list && list.size() > 0) {
          CustomerInfo customerInfo = null
          for (CmngCustom c: list) {
            CmngCustominfo custominfo = CmngCustominfo.get(c.customidCmngCustominfo)
            customerInfo = new CustomerInfo()
            customerInfo.setId(c.getId())
            customerInfo.setAreaid(c.areaid)
            customerInfo.setNetid(c.netid)
            customerInfo.setOpendate(c.opendate)
            customerInfo.setCustomNo(c.customNo)
            customerInfo.setStockid(c.stockid)
            customerInfo.setCustomType(c.customType)
            customerInfo.setBankAssign(c.bankAssign)
            customerInfo.setCreateDate(c.createDate)
            customerInfo.setType(c.typeCmngCustomtype)
            customerInfo.setStatus(c.statusCmngCustomstatus)
            customerInfo.setLease(c.lease)
            customerInfo.setCustomnm(custominfo.customnm)
            customerInfo.setSex(custominfo.sex)
            customerInfo.setBirthday(custominfo.birthday)
            customerInfo.setPhone(custominfo.phone)
            customerInfo.setAddress(custominfo.address)
            customerInfo.setZipcode(custominfo.zipcode)
            customerInfo.setMaintele(custominfo.maintele)
            customerInfo.setIdcard(custominfo.idcard)
            customerInfo.setEmail(custominfo.email)
            customerInfo.setDistrict(custominfo.district)
            customerInfo.setRemark(custominfo.remark)
            List userList = CmngUser.findAllByDvbCostomId(c.getId())
            for (CmngUser u: userList) {
              User user = new User()
              user.setId(u.id)
              user.setDvbCostomId(u.dvbCostomId)
              user.setUserTeamId(u.userTeamId)
              user.setName(u.name)
              user.setPassword(u.password)
              user.setStatus(u.status)
              user.setDescription(u.description)
              user.setCreateDate(u.createDate)
              user.setUpdateDate(u.updateDate)
              user.setPauseDate(u.pauseDate)
              user.setClassType(u.classType)
              user.setGroupType(u.groupType)
              user.setOperatorFlag(u.operatorFlag)
              CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findByUseridCmngUser(u.id)
              user.setUuid(cmngStbcustomvdo?.smcode)
              customerInfo.userList.add(user)
            }
            customerInfoListBean.customerList.add(customerInfo)
          }
        }
        return customerInfoListBean
      } else {
        customerInfoListBean.setErrorCode("200")
      }
    } catch (Exception e) {
      customerInfoListBean.setErrorCode("300")
    }
    return customerInfoListBean
  }

  /**
   * 返回iboss系统中集团客户资料
   * @param clientId
   * @param req
   * @return CustomerInfoListBean
   */
  public CustomerInfoListBean getCustomerInfo(String clientId, GroupCustomerReq req) {
    CustomerInfoListBean customerInfoListBean = new CustomerInfoListBean()
    try {
      if (req) {
        //先以集团客户id进行查询，如果id为空，则以集团客户名称进行查询
        if (req.getGroupId()) {
          CmngCustom custom = CmngCustom.get(Long.valueOf(req.getGroupId()))
          CmngCustominfo custominfo = CmngCustominfo.get(custom.customidCmngCustominfo)
          if (custom && custominfo) {
            CustomerInfo customerInfo = new CustomerInfo()
            customerInfo.setId(custom.getId())
            customerInfo.setAreaid(custom.areaid)
            customerInfo.setNetid(custom.netid)
            customerInfo.setOpendate(custom.opendate)
            customerInfo.setCustomNo(custom.customNo)
            customerInfo.setStockid(custom.stockid)
            customerInfo.setCustomType(custom.customType)
            customerInfo.setBankAssign(custom.bankAssign)
            customerInfo.setCreateDate(custom.createDate)
            customerInfo.setType(custom.typeCmngCustomtype)
            customerInfo.setStatus(custom.statusCmngCustomstatus)
            customerInfo.setLease(custom.lease)
            customerInfo.setCustomnm(custominfo.customnm)
            customerInfo.setSex(custominfo.sex)
            customerInfo.setBirthday(custominfo.birthday)
            customerInfo.setPhone(custominfo.phone)
            customerInfo.setAddress(custominfo.address)
            customerInfo.setZipcode(custominfo.zipcode)
            customerInfo.setMaintele(custominfo.maintele)
            customerInfo.setIdcard(custominfo.idcard)
            customerInfo.setEmail(custominfo.email)
            customerInfo.setDistrict(custominfo.district)
            customerInfo.setRemark(custominfo.remark)
            User user = null
            for (CmngUser u: CmngUser.findAllByDvbCostomId(req.getGroupId())) {
              user = new User()
              user.setId(u.id)
              user.setDvbCostomId(u.dvbCostomId)
              user.setUserTeamId(u.userTeamId)
              user.setName(u.name)
              user.setPassword(u.password)
              user.setStatus(u.status)
              user.setDescription(u.description)
              user.setCreateDate(u.createDate)
              user.setUpdateDate(u.updateDate)
              user.setPauseDate(u.pauseDate)
              user.setClassType(u.classType)
              user.setGroupType(u.groupType)
              user.setOperatorFlag(u.operatorFlag)
              user.setUuid(CmngStbcustomvdo.findByUseridCmngUser(u.id)?.smcode)
              customerInfo.userList.add(user)
            }
            customerInfoListBean.customerList.add(customerInfo)
          }
        } else if (req.getGroupName()) {
          List<CmngCustom> cList = CmngCustom.executeQuery("from CmngCustom c where c.customType='3' and c.customidCmngCustominfo in (select i.id from CmngCustominfo i where i.customnm like '%" + req.getGroupName() + "%')")
          CustomerInfo customerInfo = null
          CmngCustominfo custominfo = null
          User user = null
          for (CmngCustom custom: cList) {
            customerInfo = new CustomerInfo()
            custominfo = CmngCustominfo.get(custom.customidCmngCustominfo)
            customerInfo.setId(custom.getId())
            customerInfo.setAreaid(custom.areaid)
            customerInfo.setNetid(custom.netid)
            customerInfo.setOpendate(custom.opendate)
            customerInfo.setCustomNo(custom.customNo)
            customerInfo.setStockid(custom.stockid)
            customerInfo.setCustomType(custom.customType)
            customerInfo.setBankAssign(custom.bankAssign)
            customerInfo.setCreateDate(custom.createDate)
            customerInfo.setType(custom.typeCmngCustomtype)
            customerInfo.setStatus(custom.statusCmngCustomstatus)
            customerInfo.setLease(custom.lease)
            customerInfo.setCustomnm(custominfo.customnm)
            customerInfo.setSex(custominfo.sex)
            customerInfo.setBirthday(custominfo.birthday)
            customerInfo.setPhone(custominfo.phone)
            customerInfo.setAddress(custominfo.address)
            customerInfo.setZipcode(custominfo.zipcode)
            customerInfo.setMaintele(custominfo.maintele)
            customerInfo.setIdcard(custominfo.idcard)
            customerInfo.setEmail(custominfo.email)
            customerInfo.setDistrict(custominfo.district)
            customerInfo.setRemark(custominfo.remark)
            for (CmngUser u: CmngUser.findAllByDvbCostomId(custom.getId())) {
              user = new User()
              user.setId(u.id)
              user.setDvbCostomId(u.dvbCostomId)
              user.setUserTeamId(u.userTeamId)
              user.setName(u.name)
              user.setPassword(u.password)
              user.setStatus(u.status)
              user.setDescription(u.description)
              user.setCreateDate(u.createDate)
              user.setUpdateDate(u.updateDate)
              user.setPauseDate(u.pauseDate)
              user.setClassType(u.classType)
              user.setGroupType(u.groupType)
              user.setOperatorFlag(u.operatorFlag)
              user.setUuid(CmngStbcustomvdo.findByUseridCmngUser(u.id)?.smcode)
              customerInfo.userList.add(user)
            }
            customerInfoListBean.customerList.add(customerInfo)
          }
        } else {
          customerInfoListBean.setErrorCode("200")
        }
      } else {
        customerInfoListBean.setErrorCode("200")
      }
    } catch (Exception e) {
      customerInfoListBean.setErrorCode("300")
    }
    return customerInfoListBean
  }

  /**
   * 用户日志查询
   * @param clientId
   * @param req
   * @return OperateLogListBean
   */
  public OperateLogListBean getCustomerLog(String clientId, CustomerLogReq req) {
    OperateLogListBean operateActionListBean = new OperateLogListBean()
    SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd")
    try {
      if (req) {
        StringBuffer hql = new StringBuffer("from OperateLog o where 1=1")
        if (req.getCustomerId())
          hql.append(" and o.customId=" + req.getCustomerId())
        if (req.getOperateActionId())
          hql.append(" and o.action.id=" + req.getOperateActionId())
        if (req.getStartDate())
          hql.append(" and to_char(o.createDate ,'yyyy-MM-dd') >= '" + simple.format(req.getStartDate()) + "'")
        if (req.getEndDate())
          hql.append(" and to_char(o.createDate ,'yyyy-MM-dd') <= '" + simple.format(req.getEndDate()) + "'")
        List<com.dvn.miniboss.log.OperateLog> oList = com.dvn.miniboss.log.OperateLog.executeQuery(hql.toString())
        OperateLog ol = null
        for (com.dvn.miniboss.log.OperateLog o: oList) {
          ol = new OperateLog()
          ol.setId(o.getId())
          ol.setOperatorId(o.getOperatorId())
          ol.setCustomId(o.getCustomId())
          ol.setUserId(o.getUserId())
          ol.setOperateActionId(o.getAction()?.getId())
          ol.setDescription(o.getDescription())
          ol.setOperateDate(o.getOperateDate())
          ol.setCreateDate(o.getCreateDate())
          operateActionListBean.operateLogList.add(ol)
        }
      } else {
        operateActionListBean.setErrorCode("200")
      }
    } catch (Exception e) {
      operateActionListBean.setErrorCode("300")
    }
    return operateActionListBean
  }

  /**
   * 系统日志类型列表
   * @param clientId
   * @param req
   * @return SystemLogListBean
   */
  public SystemLogListBean getSystemLog(String clientId, SystemLogReq req) {
    SystemLogListBean systemLogListBean = new SystemLogListBean()
    SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd")
    try {
      if (req) {
        StringBuffer hql = new StringBuffer("from SysLog s where 1=1")
        if (req.getSystemActionId())
          hql.append(" and s.action.id=" + req.getSystemActionId())
        if (req.getStartDate())
          hql.append(" and to_char(s.createDate ,'yyyy-MM-dd') >= '" + simple.format(req.getStartDate()) + "'")
        if (req.getEndDate())
          hql.append(" and to_char(s.createDate ,'yyyy-MM-dd') <= '" + simple.format(req.getEndDate()) + "'")
        List<com.dvn.miniboss.log.SysLog> sList = com.dvn.miniboss.log.SysLog.executeQuery(hql.toString())
        SystemLog sys = null
        for (com.dvn.miniboss.log.SysLog s: sList) {
          sys = new SystemLog()
          sys.setId(s.getId())
          sys.setOperateActionId(s.getAction()?.getId())
          sys.setDescription(s.getDescription())
          sys.setOperateDate(s.getOperateDate())
          sys.setCreateDate(s.getCreateDate())
          systemLogListBean.systemLogList.add(sys)
        }
      } else {
        systemLogListBean.setErrorCode("200")
      }
    } catch (Exception e) {
      systemLogListBean.setErrorCode("300")
    }
    return systemLogListBean
  }

  /**
   * 用户订购服务查询
   * @param clientId
   * @param customerId
   * @return AuthServiceListBean
   */
  public AuthServiceListBean getSubscribeService(String clientId, long customerId) {
    AuthServiceListBean authServiceListBean = new AuthServiceListBean()
    try {
      if (customerId) {
        List<com.dvn.miniboss.oldsms.AuthService> authServiceList = com.dvn.miniboss.oldsms.AuthService.executeQuery("from AuthService a where a. and a.userid in (select u.id CmngUser u where u.dvbCostomId=" + customerId + ")")
        if (authServiceList && authServiceList.size() > 0) {
          com.miniboss.callcenter.AuthService authService = null
          for (com.dvn.miniboss.oldsms.AuthService authSer: authServiceList) {
            authService = new com.miniboss.callcenter.AuthService()
            authService.setProductid(authSer.getProductid())
            authService.setProductname(authSer.getProductname())
            authService.setIsgroup(authSer.getIsgroup())
            authService.setPaymode(authSer.getPaymode())
            authService.setCreatedate(authSer.getCreatedate())
            authService.setUpdatedate(authSer.getUpdatedate())
            authService.setStartdate(authSer.getStartdate())
            authService.setEnddate(authSer.getEnddate())
            authService.setPausedate(authSer.getPausedate())
            authService.setActivepausedate(authSer.getActivepausedate())
            authService.setResumedate(authSer.getResumedate())
            authService.setRealstartdate(authSer.getRealstartdate())
            authService.setRealenddate(authSer.getRealenddate())
            authService.setFormerstatus(authSer.getFormerstatus())
            authService.setStatus(authSer.getStatus())
            authService.setFreestartdate(authSer.getFreestartdate())
            authService.setFreeenddate(authSer.getFreeenddate())
            authService.setRefpricefix(authSer.getRefpricefix())
            authService.setPricefix(authSer.getPricefix())
            authService.setIsrenew(authSer.getIsrenew())
            authServiceListBean.authServiceList.add(authService)
          }
        }
      } else {
        authServiceListBean.setErrorCode("200")
      }
    } catch (Exception e) {
      authServiceListBean.setErrorCode("300")
    }
    return authServiceListBean
  }

  /**
   * 用户日志类型列表
   * @param clientId
   * @return OperateActionListBean
   */
  public OperateActionListBean getOperateAction(String clientId) {
    OperateActionListBean operateActionListBean = new OperateActionListBean()
    try {
      List<com.dvn.miniboss.log.OperateAction> oList = com.dvn.miniboss.log.OperateAction.list()
      OperateAction operateAction = null
      for (com.dvn.miniboss.log.OperateAction o: oList) {
        operateAction = new OperateAction()
        operateAction.setId(o.getId())
        operateAction.setName(o.getName())
        operateAction.setDescription(o.getDescription())
        operateAction.setCreateDate(o.getCreateDate())
        operateActionListBean.operateActionList.add(operateAction)
      }
    } catch (Exception e) {
      operateActionListBean.setErrorCode("300")
    }
    return operateActionListBean
  }

  /**
   * 系统日志类型列表
   * @param clientId
   * @return
   */
  public SystemActionListBean getSystemAction(String clientId) {
    SystemActionListBean systemActionListBean = new SystemActionListBean()
    try {
      List<com.dvn.miniboss.log.SysAction> sList = com.dvn.miniboss.log.SysAction.list()
      SystemAction systemAction = null
      if (sList && sList.size()) {
        for (com.dvn.miniboss.log.SysAction s: sList) {
          systemAction = new SystemAction()
          systemAction.setId(s.getId())
          systemAction.setName(s.getName())
          systemAction.setDescription(s.getDescription())
          systemAction.setCreateDate(s.getCreateDate())
          systemActionListBean.systemActionList.add(systemAction)
        }
      }
    } catch (Exception e) {
      systemActionListBean.setErrorCode("300")
    }

    return systemActionListBean
  }

  /**
   * 交易明细查询
   * @param clientId
   * @param req
   * @return ConsumeInfoListBean
   */
  public ConsumeInfoListBean getConsumeList(String clientId, ConsumeReq req) {
    ConsumeInfoListBean consumeInfoListBean = new ConsumeInfoListBean()
    try {
      if (req && req.getCustomerId()) {
        List<CmngUser> uList = CmngUser.findAllByDvbCostomId(req.getCustomerId())
        ConsumeInfo consumeInfo = null
        ChargePaybilldetail chargePaybilldetail = null
        AuthServicebill authServicebill = null
        for (CmngUser u: uList) {
          List<com.dvn.miniboss.oldsms.AuthService> aList = com.dvn.miniboss.oldsms.AuthService.findAllByOrderUserId(u.getId())
          for (com.dvn.miniboss.oldsms.AuthService a: aList) {
            authServicebill = AuthServicebill.find("from AuthServicebill a where a.serviceid=" + a.getId())
            if (authServicebill) {
              consumeInfo = new ConsumeInfo()
              consumeInfo.setId(authServicebill.getId())
              consumeInfo.setCusotmerId(req.getCustomerId())
              consumeInfo.setProductId(authServicebill.getProductId())
              consumeInfo.setProductName(authServicebill.getProductName())
              consumeInfo.setCreatedate(authServicebill.getCreatedate())
              consumeInfo.setSrvstartdate(authServicebill.getSrvstartdate())
              consumeInfo.setSrvenddate(authServicebill.getSrvenddate())
              consumeInfo.setServicedays(authServicebill.getServicedays())
              consumeInfo.setRealprice(authServicebill.getRealprice())
              chargePaybilldetail = ChargePaybilldetail.find("from ChargePaybilldetail c where c.isAdjustRecord='YES' and c.billid=" + authServicebill.getId())
              consumeInfo.setRefund(chargePaybilldetail?.getRealprice())
              consumeInfo.setRefunddate(chargePaybilldetail?.getTransdate())
              consumeInfoListBean.customerInfoList.add(consumeInfo)
            }
          }
        }
      } else {
        consumeInfoListBean.setErrorCode("200")
      }
    } catch (Exception e) {
      consumeInfoListBean.setErrorCode("300")
    }
    return consumeInfoListBean
  }

  /**
   * 产品信息查询
   * @param clientId
   * @return
   */
  public ProductListBean getProduct(String clientId) {
    ProductListBean productListBean = new ProductListBean()
    try {
      List<com.dvn.miniboss.product.ProductInstance> pList = com.dvn.miniboss.product.ProductInstance.list()
      if (pList && pList.size() > 0) {
        Product product = null
        for (com.dvn.miniboss.product.ProductInstance p: pList) {
          product = new Product()
          product.setName(p.getName())
          product.setCommodity(p.getCommodity()?.getName())
          product.setArea(com.dvn.miniboss.system.AreaCode.get(p.getAreaId())?.getAreanm())
          product.setStbType(p.getStbTypeId() == "10001" ? "主机" : "从机")
          product.setCustomType(com.dvn.miniboss.oldsms.CmngCustomtype.get(p.getCustomTypeId())?.getTypenm())
          product.setPrice(p.getProductCode()?.getProduct()?.getPrice())
          product.setBillingCycle(p.getProductCode()?.getProduct()?.getBillingCycle()?.getName())
          product.setDescription(p.getDescription())
          product.setCreateTime(p.getCreateTime())
          product.setStartTime(p.getStartTime())
          product.setEndTime(p.getEndTime())
          productListBean.productList.add(product)
        }
      }
    } catch (Exception e) {
      productListBean.setErrorCode("300")
    }
    return productListBean
  }
  /**
   * 派单
   * @param clientId
   * @param customName
   * @param sendWorker
   */
  /*public void sedWorkOrder(String clientId, long workOrderNo, String sendWorker) {

  }*/

  /**
   * 双向业务密码修改
   * @param clientId
   * @param req
   * @return
   */
  public CommonResultBean changePassword(String clientId, ChangePasswordReq req) {
    CommonResultBean commonResultBean = new CommonResultBean()
    try {
      if (req && req.getUuid() && req.getOldPassword() && req.getNewPassword()) {
        CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.find("from CmngStbcustomvdo where c.smcode='" + req.getUuid() + "'")
        CmngUser user = CmngUser.find("from CmngUser c where c.id=" + cmngStbcustomvdo.getUseridCmngUser() + " and c.password='" + req.getOldPassword() + "'")
        if (user) {
          user.setPassword(req.getNewPassword())
          user.save()
        }
      } else {
        commonResultBean.setErrorCode("200")
      }
    } catch (Exception e) {
      commonResultBean.setErrorCode("300")
    }
    return commonResultBean
  }

  public static void main(String[] args) {
    Date now = new Date()
    System.out.println(now)
    long afterTime = (now.getTime() / 1000) + 60 * 60 * 24 * 365
    Date afterYear = new Date()
    afterYear.setTime(afterTime * 1000)
  }
}