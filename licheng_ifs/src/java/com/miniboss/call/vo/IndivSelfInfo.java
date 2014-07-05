package com.miniboss.call.vo;

/**
 * <p>Title: SMS</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
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
    private SMSError topError;                        /* ���ش�����Ϣ */
    private IndivSelfLookUpReq lookUpRequset;         /* ����������Ϣ */
    private CmngCustominfo customerInfo;                  /* �û�������Ϣ */
    private StbOrderInfo stbOrderInfos;             /* �����й�������Ϣ�б� */
    private StbBillInfo stbBillInfos;               /* �����й����ʵ���Ϣ�б� */
    private StbTakeInfo stbTakeInfos;               /* �����й�����Ϣ�б� */
    private StbReturnInfo stbReturnInfos;           /* �������˻���Ϣ�б� */
    private StbChangeInfo stbChangeInfos;           /* ������Ϣ�б� */
    private StbSignUpInfo stbSignUpInfos;           /* �����п�����Ϣ�б� */
    private StbSignOffInfo stbSignOffInfos;         /* ������������Ϣ�б� */
    private StbAddrMoveInfo stbAddrMoveInfos;       /* �������ƻ���Ϣ�б� */
    private SubscriptionInfo subscriptionInfos;     /* ���ӷ�����Ϣ�б� */
    private SubscripBillInfo subscripBillInfos;     /* ���ӷ����ʵ���Ϣ�б� */
    private VodInfo vodInfos;                       /* VOD ������Ϣ�б� */
    private VodBillInfo vodBillInfos;               /* VOD �ʵ���Ϣ�б� */
    private PpvInfo ppvInfos;                       /* PPV ������Ϣ�б� */
    private PpvBillInfo ppvBillInfos;               /* PPV �ʵ���Ϣ�б� */
    private SpecialBillInfo specialBillInfos;       /* ���⽻���ʵ���Ϣ�б� */
    private SpecialRefundInfo specialRefundInfos;   /* �����˿��ʵ���Ϣ�б� */
    private PayInfo payInfos;                       /* ���ѽ�����Ϣ�б� */
    private RefundInfo refundInfos;                 /* �˿����Ϣ�б� */
    private DepositInfo depositInfos;               /* ������Ϣ�б� */
    private WithdrawInfo withdrawInfos;             /* ȡ�����Ϣ��Ϣ�б� */
    private UnpaidSheetInfo unpaidSheetInfos;       /* δ���ѵ�����Ϣ�б� */
    private BalanceInfo balanceInfos;               /* �ڲ��ʻ������Ϣ�б� */
    private TeamSubscripInfo teamSubscripInfos;     /* �������������Ϣ�б� */
    private SubscribeProductInfo subscribeProductInfos; /* �ɶ������Ӳ�Ʒ��Ϣ�б� */

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