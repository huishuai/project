package com.miniboss.call.vo;

/**
 * <p>Title: SMS</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author not attributable
 * @version 4.0
 */
import com.dvn.miniboss.oldsms.CmngCustominfo;
import com.miniboss.call.req.IndivSelfLookUpReq;
import com.miniboss.call.tool.SMSError;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndivSelfInfo", namespace = "http://vo.call.sms.dvnchina.com")
public class IndivSelfInfo
{
    private SMSError topError;                        /* 返回错误信息 */
    private IndivSelfLookUpReq lookUpRequset;         /* 返回请求信息 */
    private CmngCustominfo customerInfo;                  /* 用户基本信息 */
    private StbOrderInfo stbOrderInfos;             /* 机顶盒购买交易信息列表 */
    private StbBillInfo stbBillInfos;               /* 机顶盒购买帐单信息列表 */
    private StbTakeInfo stbTakeInfos;               /* 机顶盒过户信息列表 */
    private StbReturnInfo stbReturnInfos;           /* 机顶盒退货信息列表 */
    private StbChangeInfo stbChangeInfos;           /* 换机信息列表 */
    private StbSignUpInfo stbSignUpInfos;           /* 机顶盒开户信息列表 */
    private StbSignOffInfo stbSignOffInfos;         /* 机顶盒消户信息列表 */
    private StbAddrMoveInfo stbAddrMoveInfos;       /* 机顶盒移机信息列表 */
    private SubscriptionInfo subscriptionInfos;     /* 收视服务信息列表 */
    private SubscripBillInfo subscripBillInfos;     /* 收视服务帐单信息列表 */
    private VodInfo vodInfos;                       /* VOD 服务信息列表 */
    private VodBillInfo vodBillInfos;               /* VOD 帐单信息列表 */
    private PpvInfo ppvInfos;                       /* PPV 服务信息列表 */
    private PpvBillInfo ppvBillInfos;               /* PPV 帐单信息列表 */
    private SpecialBillInfo specialBillInfos;       /* 特殊交费帐单信息列表 */
    private SpecialRefundInfo specialRefundInfos;   /* 特殊退款帐单信息列表 */
    private PayInfo payInfos;                       /* 交费交易信息列表 */
    private RefundInfo refundInfos;                 /* 退款交易信息列表 */
    private DepositInfo depositInfos;               /* 存款交易信息列表 */
    private WithdrawInfo withdrawInfos;             /* 取款交易信息信息列表 */
    private UnpaidSheetInfo unpaidSheetInfos;       /* 未交费单据信息列表 */
    private BalanceInfo balanceInfos;               /* 内部帐户余额信息列表 */
    private TeamSubscripInfo teamSubscripInfos;     /* 集团组的收视信息列表 */
    private SubscribeProductInfo subscribeProductInfos; /* 可订购收视产品信息列表 */

    public SMSError getTopError() {
        return topError;
    }

    public void setTopError(SMSError topError) {
        this.topError = topError;
    }

    public IndivSelfLookUpReq getLookUpRequset() {
        return lookUpRequset;
    }

    public void setLookUpRequset(IndivSelfLookUpReq lookUpRequset) {
        this.lookUpRequset = lookUpRequset;
    }

    public CmngCustominfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CmngCustominfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public StbOrderInfo getStbOrderInfos() {
        return stbOrderInfos;
    }

    public void setStbOrderInfos(StbOrderInfo stbOrderInfos) {
        this.stbOrderInfos = stbOrderInfos;
    }

    public StbBillInfo getStbBillInfos() {
        return stbBillInfos;
    }

    public void setStbBillInfos(StbBillInfo stbBillInfos) {
        this.stbBillInfos = stbBillInfos;
    }

    public StbTakeInfo getStbTakeInfos() {
        return stbTakeInfos;
    }

    public void setStbTakeInfos(StbTakeInfo stbTakeInfos) {
        this.stbTakeInfos = stbTakeInfos;
    }

    public StbReturnInfo getStbReturnInfos() {
        return stbReturnInfos;
    }

    public void setStbReturnInfos(StbReturnInfo stbReturnInfos) {
        this.stbReturnInfos = stbReturnInfos;
    }

    public StbChangeInfo getStbChangeInfos() {
        return stbChangeInfos;
    }

    public void setStbChangeInfos(StbChangeInfo stbChangeInfos) {
        this.stbChangeInfos = stbChangeInfos;
    }

    public StbSignUpInfo getStbSignUpInfos() {
        return stbSignUpInfos;
    }

    public void setStbSignUpInfos(StbSignUpInfo stbSignUpInfos) {
        this.stbSignUpInfos = stbSignUpInfos;
    }

    public StbSignOffInfo getStbSignOffInfos() {
        return stbSignOffInfos;
    }

    public void setStbSignOffInfos(StbSignOffInfo stbSignOffInfos) {
        this.stbSignOffInfos = stbSignOffInfos;
    }

    public StbAddrMoveInfo getStbAddrMoveInfos() {
        return stbAddrMoveInfos;
    }

    public void setStbAddrMoveInfos(StbAddrMoveInfo stbAddrMoveInfos) {
        this.stbAddrMoveInfos = stbAddrMoveInfos;
    }

    public SubscriptionInfo getSubscriptionInfos() {
        return subscriptionInfos;
    }

    public void setSubscriptionInfos(SubscriptionInfo subscriptionInfos) {
        this.subscriptionInfos = subscriptionInfos;
    }

    public SubscripBillInfo getSubscripBillInfos() {
        return subscripBillInfos;
    }

    public void setSubscripBillInfos(SubscripBillInfo subscripBillInfos) {
        this.subscripBillInfos = subscripBillInfos;
    }

    public VodInfo getVodInfos() {
        return vodInfos;
    }

    public void setVodInfos(VodInfo vodInfos) {
        this.vodInfos = vodInfos;
    }

    public VodBillInfo getVodBillInfos() {
        return vodBillInfos;
    }

    public void setVodBillInfos(VodBillInfo vodBillInfos) {
        this.vodBillInfos = vodBillInfos;
    }

    public PpvInfo getPpvInfos() {
        return ppvInfos;
    }

    public void setPpvInfos(PpvInfo ppvInfos) {
        this.ppvInfos = ppvInfos;
    }

    public PpvBillInfo getPpvBillInfos() {
        return ppvBillInfos;
    }

    public void setPpvBillInfos(PpvBillInfo ppvBillInfos) {
        this.ppvBillInfos = ppvBillInfos;
    }

    public SpecialBillInfo getSpecialBillInfos() {
        return specialBillInfos;
    }

    public void setSpecialBillInfos(SpecialBillInfo specialBillInfos) {
        this.specialBillInfos = specialBillInfos;
    }

    public SpecialRefundInfo getSpecialRefundInfos() {
        return specialRefundInfos;
    }

    public void setSpecialRefundInfos(SpecialRefundInfo specialRefundInfos) {
        this.specialRefundInfos = specialRefundInfos;
    }

    public PayInfo getPayInfos() {
        return payInfos;
    }

    public void setPayInfos(PayInfo payInfos) {
        this.payInfos = payInfos;
    }

    public RefundInfo getRefundInfos() {
        return refundInfos;
    }

    public void setRefundInfos(RefundInfo refundInfos) {
        this.refundInfos = refundInfos;
    }

    public DepositInfo getDepositInfos() {
        return depositInfos;
    }

    public void setDepositInfos(DepositInfo depositInfos) {
        this.depositInfos = depositInfos;
    }

    public WithdrawInfo getWithdrawInfos() {
        return withdrawInfos;
    }

    public void setWithdrawInfos(WithdrawInfo withdrawInfos) {
        this.withdrawInfos = withdrawInfos;
    }

    public UnpaidSheetInfo getUnpaidSheetInfos() {
        return unpaidSheetInfos;
    }

    public void setUnpaidSheetInfos(UnpaidSheetInfo unpaidSheetInfos) {
        this.unpaidSheetInfos = unpaidSheetInfos;
    }

    public BalanceInfo getBalanceInfos() {
        return balanceInfos;
    }

    public void setBalanceInfos(BalanceInfo balanceInfos) {
        this.balanceInfos = balanceInfos;
    }

    public TeamSubscripInfo getTeamSubscripInfos() {
        return teamSubscripInfos;
    }

    public void setTeamSubscripInfos(TeamSubscripInfo teamSubscripInfos) {
        this.teamSubscripInfos = teamSubscripInfos;
    }

    public SubscribeProductInfo getSubscribeProductInfos() {
        return subscribeProductInfos;
    }

    public void setSubscribeProductInfos(SubscribeProductInfo subscribeProductInfos) {
        this.subscribeProductInfos = subscribeProductInfos;
    }
}