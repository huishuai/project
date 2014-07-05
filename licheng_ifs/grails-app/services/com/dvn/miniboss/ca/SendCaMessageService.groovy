package com.dvn.miniboss.ca

import com.dvn.miniboss.oldsms.AuthService
import com.dvn.miniboss.product.BusinessType
import com.dvn.miniboss.product.Commodity
import com.dvn.miniboss.product.Resource
import com.dvn.miniboss.product.ResourceType
import com.dvn.miniboss.system.NetCode
import com.google.gson.Gson
import com.miniboss.acct.utils.DateUtil
import com.miniboss.exception.BaseException

class SendCaMessageService {
    public static final String QUENETYPE_RT = "rt"
    public static final String QUENETYPE_BAT = "bat"
    public static boolean isLongAuth = true
    public static Date longAuthEndDate = DateUtil.getDateByBasicStr("2030-01-01")
    private static ThreadLocal<Boolean> delaySend = new ThreadLocal<Boolean>()
    private static ThreadLocal<List<CaRequestBean>> delaySendList = new ThreadLocal<List<CaRequestBean>>()

    def sendDelays() {
        delaySend.remove()
        def list = delaySendList.get()
        for (CaRequestBean ca: list) {
            sendMessage(ca)
        }
        delaySendList.remove()
    }

    def setDelaySend() {
        delaySend.set(new Boolean(true))
    }

    def clear() {
        delaySend.remove()
        delaySendList.remove()
    }

    /**
     * 发送CA指令到队列
     *
     * @param caRequestBean
     * @return void
     * @throws Exception
     */
    def sendMessage(CaRequestBean caRequestBean) {
        def isDelaySend = delaySend.get()
        if (isDelaySend && isDelaySend.booleanValue()) {
            def list = delaySendList.get()
            if (list == null) {
                list = new ArrayList<CaRequestBean>()
                delaySendList.set(list)
            }
            list.add(caRequestBean)
            return
        }

        if (isLongAuth && caRequestBean.cmdType.equals(CaCMDType.Cmd_AddProduct)) {
            caRequestBean.endDate = longAuthEndDate
        }
        try {
            if (caRequestBean.type.equals(QUENETYPE_RT)) {
                sendJMSMessage("dvnchina.ca.rtquene", caRequestBean)
            }
            else if (caRequestBean.type.equals(QUENETYPE_BAT)) {
                sendJMSMessage("dvnchina.ca.batquene", caRequestBean)
            }
            else {
                log.error("it is not rt or bat ca command!")
//                Gson gson = new Gson()
//                log.error(gson.toJson(caRequestBean))
//                throw new BaseException("SendCaMessageService.noSendType", "")
            }
        } catch (Exception e) {
            log.error(e.message + '\r\n' + new Gson().toJson(caRequestBean))
//            Gson gson = new Gson()
//            log.error(gson.toJson(caRequestBean))
//            throw new BaseException("SendCaMessageService.sendError", e)
        }
    }


    def authByStb(String stbId, String cardId, String commodityId, Date startDate, Date endDate, String areaId, String operatorId, boolean isBatch) {
        Commodity commodity = Commodity.findById(commodityId)
        if (!commodity.businessType.id.equals(BusinessType.TYPE_SZDS)) {
            return
        }
        def caRequestBean = new CaRequestBean()
        caRequestBean.cardId = cardId
        caRequestBean.stbId = stbId
        caRequestBean.productId = commodity.id
        caRequestBean.productName = commodity.name
        caRequestBean.startDate = startDate
        caRequestBean.endDate = endDate
        caRequestBean.areaId = areaId
        caRequestBean.operatorId = operatorId
        if (isBatch) {
            caRequestBean.type = QUENETYPE_BAT
        } else {
            caRequestBean.type = QUENETYPE_RT
        }
        caRequestBean.cmdType = CaCMDType.Cmd_AddProduct
        if (isLongAuth) {
            caRequestBean.endDate = longAuthEndDate
        }
        sendMessage(caRequestBean)
    }

    def authByUser(AuthService auth, Date startDate, Date endDate, String operatorId, boolean isBatch) {
        if (!auth.businessType.equals(BusinessType.TYPE_SZDS)) {
            return
        }
        def caRequestBean = new CaRequestBean()
        caRequestBean.customId = auth.userid
        caRequestBean.userId = auth.orderUserId
        caRequestBean.serviceId = auth.id
        caRequestBean.productId = auth.commodityId
        caRequestBean.productName = auth.productname
        caRequestBean.areaId = auth.areaid
        caRequestBean.startDate = startDate
        caRequestBean.endDate = endDate
        caRequestBean.operatorId = operatorId
        if (isBatch) {
            caRequestBean.type = QUENETYPE_BAT
        } else {
            caRequestBean.type = QUENETYPE_RT
        }
        caRequestBean.cmdType = CaCMDType.Cmd_AddProduct
        if (isLongAuth) {
            caRequestBean.endDate = longAuthEndDate
        }
        sendMessage(caRequestBean)
    }

    def cancelauthByStb(String stbId, String cardId, String productId, String productName, Date endDate, String areaId, String operatorId) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.stbId = stbId
        caRequestBean.cardId = cardId
        caRequestBean.productId = productId
        caRequestBean.productName = productName
        caRequestBean.startDate = new Date()
        caRequestBean.endDate = endDate
        caRequestBean.areaId = areaId
        caRequestBean.operatorId = operatorId
        caRequestBean.type = QUENETYPE_RT
        caRequestBean.cmdType = CaCMDType.Cmd_DelProduct

        sendMessage(caRequestBean)
    }

    def cancelauthByStb(Resource resource, Commodity commodity, Date endDate, String operatorId, boolean isBatch) {
        if (!commodity.businessType.equals(BusinessType.TYPE_SZDS)) {
            return
        }
        def caRequestBean = new CaRequestBean()
        if (resource.getResourceType().id.equals(ResourceType.TYPE_IC_CARD)) {
            caRequestBean.cardId = resource.goodsNo
            caRequestBean.stbId = resource.pairObjectId
        } else if (resource.getResourceType().id.equals(ResourceType.TYPE_SETTOPBOX)) {
            caRequestBean.stbId = resource.goodsNo
            caRequestBean.cardId = resource.pairObjectId
        } else {
            return
        }
        caRequestBean.productId = commodity.id
        caRequestBean.productName = commodity.name
        caRequestBean.startDate = new Date()
        caRequestBean.endDate = endDate
        caRequestBean.areaId = NetCode.findById(resource.locationId).areaCode
        caRequestBean.operatorId = operatorId
        if (isBatch) {
            caRequestBean.type = QUENETYPE_BAT
        } else {
            caRequestBean.type = QUENETYPE_RT
        }
        caRequestBean.cmdType = CaCMDType.Cmd_DelProduct
        sendMessage(caRequestBean)
    }

    def cancelauthByUser(AuthService auth, Date endDate, String operatorId, boolean isBatch) {
        if (!auth.businessType.equals(BusinessType.TYPE_SZDS)) {
            return
        }
        def caRequestBean = new CaRequestBean()
        caRequestBean.customId = auth.userid
        caRequestBean.userId = auth.orderUserId
        caRequestBean.serviceId = auth.id
        caRequestBean.productId = auth.commodityId
        caRequestBean.productName = auth.productname
        caRequestBean.areaId = auth.areaid
        caRequestBean.startDate = new Date()
        caRequestBean.endDate = endDate
        caRequestBean.operatorId = operatorId
        if (isBatch) {
            caRequestBean.type = QUENETYPE_BAT
        } else {
            caRequestBean.type = QUENETYPE_RT
        }
        caRequestBean.cmdType = CaCMDType.Cmd_DelProduct
        sendMessage(caRequestBean)
    }

    /**
     * 机卡配对 (实时接口调用 stbId)
     * @param stbId
     * @param cardId
     * @param startDate
     * @param areaId
     * @param operatorId
     * @return void
     */
    def bindByStbRealTime(String stbId, String cardId, Date startDate, String areaId, String operatorId) {
        bindByStb(stbId, cardId, startDate, areaId, operatorId, QUENETYPE_RT)
    }

    /**
     * 机卡配对 (批处理接口调用 stbId)
     * @param stbId
     * @param cardId
     * @param startDate
     * @param areaId
     * @param operatorId
     * @return
     */
    def bindByStbBatch(String stbId, String cardId, Date startDate, String areaId, String operatorId) {
        bindByStb(stbId, cardId, startDate, areaId, operatorId, QUENETYPE_BAT)
    }

    def bindByStb(String stbId, String cardId, Date startDate, String areaId, String operatorId, String queneType) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.stbId = stbId
        caRequestBean.cardId = cardId
        caRequestBean.startDate = startDate
        caRequestBean.areaId = areaId
        caRequestBean.operatorId = operatorId
        caRequestBean.type = queneType
        caRequestBean.cmdType = CaCMDType.Cmd_PairICC

        sendMessage(caRequestBean)
    }

    /**
     * 机卡配对 (实时接口调用 userId)
     * @param userId
     * @param startDate
     * @param operatorId
     * @param queneType
     * @return void
     */
    def bindByUserRealTime(String customId, String userId, Date startDate, String operatorId) {
        bindByUser(customId, userId, startDate, operatorId, QUENETYPE_RT)
    }

    /**
     * 机卡配对 (实时接口调用 userId)
     * @param userId
     * @param startDate
     * @param operatorId
     * @param queneType
     * @return void
     */
    def bindByUserBatch(String customId, String userId, Date startDate, String operatorId) {
        bindByUser(customId, userId, startDate, operatorId, QUENETYPE_BAT)
    }

    def bindByUser(String customId, String userId, Date startDate, String operatorId, String queneType) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.userId = userId
        caRequestBean.startDate = startDate
        caRequestBean.operatorId = operatorId
        caRequestBean.type = queneType
        caRequestBean.cmdType = CaCMDType.Cmd_PairICC

        sendMessage(caRequestBean)
    }

    /**
     * 机卡解配对 (实时接口调用 stbId)
     * @param stbId
     * @param cardId
     * @param startDate
     * @param areaId
     * @param operatorId
     * @return void
     */
    def unbindByStbRealTime(String stbId, String cardId, Date startDate, String areaId, String operatorId) {
        unbindByStb(stbId, cardId, startDate, areaId, operatorId, QUENETYPE_RT)
    }

    /**
     * 机卡解配对 (实时接口调用 stbId)
     * @param stbId
     * @param cardId
     * @param startDate
     * @param areaId
     * @param operatorId
     * @return void
     */
    def unbindByStbBatch(String stbId, String cardId, Date startDate, String areaId, String operatorId) {
        unbindByStb(stbId, cardId, startDate, areaId, operatorId, QUENETYPE_BAT)
    }

    def unbindByStb(String stbId, String cardId, Date startDate, String areaId, String operatorId, String queneType) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.stbId = stbId
        caRequestBean.cardId = cardId
        caRequestBean.startDate = startDate
        caRequestBean.areaId = areaId
        caRequestBean.operatorId = operatorId
        caRequestBean.type = queneType
        caRequestBean.cmdType = CaCMDType.Cmd_UnPairICC

        sendMessage(caRequestBean)
    }

    /**
     * 机卡解配对 (实时接口调用 userId)
     * @param userId
     * @param startDate
     * @param operatorId
     * @return void
     */
    def unbindByUserRealTime(String customId, String userId, Date startDate, String operatorId) {
        unbindByUser(customId, userId, startDate, operatorId, QUENETYPE_RT)
    }

    /**
     * 机卡解配对 (批处理接口调用 userId)
     * @param userId
     * @param startDate
     * @param operatorId
     * @return void
     */
    def unbindByUserBatch(String customId, String userId, Date startDate, String operatorId) {
        unbindByUser(customId, userId, startDate, operatorId, QUENETYPE_BAT)
    }

    def unbindByUser(String customId, String userId, Date startDate, String operatorId, String queneType) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.userId = userId
        caRequestBean.startDate = startDate
        caRequestBean.operatorId = operatorId
        caRequestBean.type = queneType
        caRequestBean.cmdType = CaCMDType.Cmd_UnPairICC

        sendMessage(caRequestBean)

    }

    /**
     * 开启机顶盒 (实时接口调用 stbId)
     * @param stbId
     * @param cardId
     * @param areaId
     * @param operatorId
     * @return void
     */
    def stbONByStbRealTime(String stbId, String cardId, String areaId, String operatorId) {
        stbONByStb(stbId, cardId, areaId, operatorId, QUENETYPE_RT)
    }

    /**
     * 开启机顶盒 (批处理接口调用 stbId)
     * @param stbId
     * @param cardId
     * @param areaId
     * @param operatorId
     * @return void
     */
    def stbONByStbBatch(String stbId, String cardId, String areaId, String operatorId) {
        stbONByStb(stbId, cardId, areaId, operatorId, QUENETYPE_BAT)
    }

    def stbONByStb(String stbId, String cardId, String areaId, String operatorId, String queneType) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.stbId = stbId
        caRequestBean.cardId = cardId
        caRequestBean.areaId = areaId
        caRequestBean.operatorId = operatorId
        caRequestBean.type = queneType
        caRequestBean.cmdType = CaCMDType.Cmd_STBON

        sendMessage(caRequestBean)
    }

    /**
     * 开启机顶盒 (实时接口调用 userId)
     * @param userId
     * @param operatorId
     * @return void
     */
    def stbONByUserRealTime(String customId, String userId, String operatorId) {
        stbONByUser(customId, userId, operatorId, QUENETYPE_RT)
    }

    /**
     * 开启机顶盒 (批处理接口调用 userId)
     * @param userId
     * @param operatorId
     * @return void
     */
    def stbONByUserBatch(String customId, String userId, String operatorId) {
        stbONByUser(customId, userId, operatorId, QUENETYPE_BAT)
    }

    def stbONByUser(String customId, String userId, String operatorId, String queneType) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.userId = userId
        caRequestBean.operatorId = operatorId
        caRequestBean.type = queneType
        caRequestBean.cmdType = CaCMDType.Cmd_STBON

        sendMessage(caRequestBean)
    }

    /**
     * 关闭机顶盒 (实时接口调用 stbId)
     * @param stbId
     * @param cardId
     * @param areaId
     * @param operatorId
     * @return void
     */
    def stbOFFByStbRealTime(String stbId, String cardId, String areaId, String operatorId) {
        stbOFFByStb(stbId, cardId, areaId, operatorId, QUENETYPE_RT)
    }

    /**
     * 关闭机顶盒 (批处理接口调用 stbId)
     * @param stbId
     * @param cardId
     * @param areaId
     * @param operatorId
     * @return void
     */
    def stbOFFByStbBatch(String stbId, String cardId, String areaId, String operatorId) {
        stbOFFByStb(stbId, cardId, areaId, operatorId, QUENETYPE_BAT)
    }

    def stbOFFByStb(String stbId, String cardId, String areaId, String operatorId, String queneType) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.stbId = stbId
        caRequestBean.cardId = cardId
        caRequestBean.areaId = areaId
        caRequestBean.operatorId = operatorId
        caRequestBean.type = queneType
        caRequestBean.cmdType = CaCMDType.Cmd_STBOFF

        sendMessage(caRequestBean)
    }

    /**
     * 关闭机顶盒 (实时接口调用 userId)
     * @param userId
     * @param operatorId
     * @return
     */
    def stbOFFByUserRealTime(String customId, String userId, String operatorId) {
        stbOFFByUser(customId, userId, operatorId, QUENETYPE_RT)
    }

    /**
     * 关闭机顶盒 (批处理接口调用 userId)
     * @param userId
     * @param operatorId
     * @return
     */
    def stbOFFByUserBatch(String customId, String userId, String operatorId) {
        stbOFFByUser(customId, userId, operatorId, QUENETYPE_BAT)
    }

    def stbOFFByUser(String customId, String userId, String operatorId, String queneType) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.userId = userId
        caRequestBean.operatorId = operatorId
        caRequestBean.type = queneType
        caRequestBean.cmdType = CaCMDType.Cmd_STBOFF

        sendMessage(caRequestBean)
    }

    /**
     * 发送OSD (实时接口调用 stbId)
     * @param stbId
     * @param cardId
     * @param startDate
     * @param areaId
     * @param message
     * @param operatorId
     * @return void
     */
    def sendOsdByStbRealTime(String stbId, String cardId, Date startDate, String areaId, String message,
                             String operatorId) {
        sendOsdByStb(stbId, cardId, startDate, areaId, message, operatorId, QUENETYPE_RT)
    }

    /**
     * 发送OSD (批处理接口调用 stbId)
     * @param stbId
     * @param cardId
     * @param startDate
     * @param areaId
     * @param message
     * @param operatorId
     * @return void
     */
    def sendOsdByStbBatch(String stbId, String cardId, Date startDate, String areaId, String message,
                          String operatorId) {
        sendOsdByStb(stbId, cardId, startDate, areaId, message, operatorId, QUENETYPE_BAT)
    }

    def sendOsdByStb(String stbId, String cardId, Date startDate, String areaId, String message,
                     String operatorId, String queneType) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.stbId = stbId
        caRequestBean.cardId = cardId
        caRequestBean.startDate = startDate
        caRequestBean.areaId = areaId
        caRequestBean.message = message
        caRequestBean.operatorId = operatorId
        caRequestBean.type = queneType
        caRequestBean.cmdType = CaCMDType.Cmd_OSD

        sendMessage(caRequestBean)
    }

    /**
     * 发送OSD (实时接口调用 userId)
     * @param userId
     * @param startDate
     * @param message
     * @param operatorId
     * @return void
     */
    def sendOsdByUserRealTime(String customId, String userId, Date startDate, String message, String operatorId) {
        sendOsdByUser(customId, userId, startDate, message, operatorId, QUENETYPE_RT)
    }

    /**
     * 发送OSD (批处理接口调用 userId)
     * @param userId
     * @param startDate
     * @param message
     * @param operatorId
     * @return void
     */
    def sendOsdByUserBatch(String customId, String userId, Date startDate, String message, String operatorId) {
        sendOsdByUser(customId, userId, startDate, message, operatorId, QUENETYPE_BAT)
    }

    def sendOsdByUser(String customId, String userId, Date startDate, String message, String operatorId, String queneType) {
        def caRequestBean = new CaRequestBean()
        caRequestBean.userId = userId
        caRequestBean.startDate = startDate
        caRequestBean.message = message
        caRequestBean.operatorId = operatorId
        caRequestBean.type = queneType
        caRequestBean.cmdType = CaCMDType.Cmd_OSD

        sendMessage(caRequestBean)

    }

    /**
     * 按模板发送CA指令
     *
     * @param customId
     * @param stbId
     * @param cardId
     * @param templateId
     * @param startDate
     * @param endDate
     * @param areaId
     * @param operatorId
     * @param netId
     * @param isSpecial
     * @param queneType
     * @return
     * @throws BaseException
     */
    def sendCAByTemplate(String customId, String stbId, String cardId, String templateId,
                         Date startDate, Date endDate, String areaId, String operatorId, String netId, String isSpecial, String queneType) throws BaseException {
        def caRequestBean = new CaRequestBean()
        caRequestBean.customId = customId
        caRequestBean.stbId = stbId
        caRequestBean.cardId = cardId
        caRequestBean.templateId = templateId
        caRequestBean.startDate = startDate
        caRequestBean.endDate = endDate
        caRequestBean.areaId = areaId
        caRequestBean.operatorId = operatorId
        caRequestBean.netId = netId
        caRequestBean.isSpecial = isSpecial
        caRequestBean.type = queneType

        sendMessage(caRequestBean)
    }

    def sendCAByTemplateBatch(String customId, String stbId, String cardId, String templateId,
                              Date startDate, Date endDate, String areaId, String operatorId, String netId, String isSpecial) throws BaseException {
        sendCAByTemplate(customId, stbId, cardId, templateId, startDate, endDate, areaId, operatorId, netId, isSpecial, QUENETYPE_BAT)
    }


}