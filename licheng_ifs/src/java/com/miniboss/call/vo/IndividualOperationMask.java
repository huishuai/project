package com.miniboss.call.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Map;

/**
 * <p>Title: SMS 查询信息选择</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 * @author sandish
 * @version 4.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualOperationMask", namespace = "http://vo.call.sms.dvnchina.com")
public class IndividualOperationMask
{
    public static final String YES="1";
    public static final String NO="0";
    private String isCustomerInfo;         /* 是否返回用户基本信息，’0’= 否，’1’=是 */
    private String isStbOrderInfo;         /* 是否返回机顶盒购买交易信息，’0’= 否，’1’=是 */
    private String isStbBillInfo;          /* 是否返回机顶盒购买帐单信息，’0’= 否，’1’=是 */
    private String isStbTakeInfo;          /* 是否返回机顶盒过户信息，’0’= 否，’1’=是 */
    private String isStbReturnInfo;        /* 是否返回机顶盒退货信息，’0’= 否，’1’=是 */
    private String isStbChangeInfo;        /* 是否返回机顶盒换机信息，’0’= 否，’1’=是 */
    private String isStbSignUpInfo;        /* 是否返回机顶盒开户信息，’0’= 否，’1’=是 */
    private String isStbSignOffInfo;       /* 是否返回机顶盒消户信息，’0’= 否，’1’=是 */
    private String isStbAddressMoveInfo;   /* 是否返回机顶盒移机信息，’0’= 否，’1’=是 */
    private String isSubscriptionInfo;     /* 是否返回收视服务信息，’0’= 否，’1’=是 */
    private String isSubscriptionBillInfo; /* 是否返回收视服务帐单信息，’0’= 否，’1’=是 */
    private String isVodInfo;              /* 是否返VOD 服务信息，’0’= 否，’1’=是 */
    private String isVodBillInfo;          /* 是否返回VOD 帐单信息，’0’= 否，’1’=是 */
    private String isPpvInfo;              /* 是否返回PPV 服务信息，’0’= 否，’1’=是 */
    private String isPpvBillInfo;          /* 是否返回PPV 帐单信息，’0’= 否，’1’=是 */
    private String isSpecialBillInfo;      /* 是否返回特殊交费帐单信息，’0’= 否，’1’=是 */
    private String isSpecialRefundInfo;    /* 是否返回特殊退款帐单信息，’0’= 否，’1’=是 */
    private String isPayInfo;              /* 是否返回交费交易信息，’0’= 否，’1’=是 */
    private String isRefundInfo;           /* 是否返回退款交易信息，’0’= 否，’1’=是 */
    private String isDepositInfo;          /* 是否返回存款交易信息，’0’= 否，’1’=是 */
    private String isWithdrawInfo;         /* 是否返回取款交易信息，’0’= 否，’1’=是 */
    private String isUnpaidSheetInfo;      /* 是否返回未交费单据信息，’0’= 否，’1’=是 */
    private String isBalanceInfo;          /* 是否返回内部帐户余额信息，’0’= 否，’1’=是 */
    private String isTeamSubscriptionInfo; /* 是否返回集团组的收视信息，’0’= 否，’1’=是 */
    private String isSubscribeProductInfo; /* 是否返回可订购收视产品信息，’0’= 否，’1’=是 */

    public String getIsCustomerInfo() {
        return isCustomerInfo;
    }

    public void setIsCustomerInfo(String customerInfo) {
        isCustomerInfo = customerInfo;
    }

    public String getIsStbOrderInfo() {
        return isStbOrderInfo;
    }

    public void setIsStbOrderInfo(String stbOrderInfo) {
        isStbOrderInfo = stbOrderInfo;
    }

    public String getIsStbBillInfo() {
        return isStbBillInfo;
    }

    public void setIsStbBillInfo(String stbBillInfo) {
        isStbBillInfo = stbBillInfo;
    }

    public String getIsStbTakeInfo() {
        return isStbTakeInfo;
    }

    public void setIsStbTakeInfo(String stbTakeInfo) {
        isStbTakeInfo = stbTakeInfo;
    }

    public String getIsStbReturnInfo() {
        return isStbReturnInfo;
    }

    public void setIsStbReturnInfo(String stbReturnInfo) {
        isStbReturnInfo = stbReturnInfo;
    }

    public String getIsStbChangeInfo() {
        return isStbChangeInfo;
    }

    public void setIsStbChangeInfo(String stbChangeInfo) {
        isStbChangeInfo = stbChangeInfo;
    }

    public String getIsStbSignUpInfo() {
        return isStbSignUpInfo;
    }

    public void setIsStbSignUpInfo(String stbSignUpInfo) {
        isStbSignUpInfo = stbSignUpInfo;
    }

    public String getIsStbSignOffInfo() {
        return isStbSignOffInfo;
    }

    public void setIsStbSignOffInfo(String stbSignOffInfo) {
        isStbSignOffInfo = stbSignOffInfo;
    }

    public String getIsStbAddressMoveInfo() {
        return isStbAddressMoveInfo;
    }

    public void setIsStbAddressMoveInfo(String stbAddressMoveInfo) {
        isStbAddressMoveInfo = stbAddressMoveInfo;
    }

    public String getIsSubscriptionInfo() {
        return isSubscriptionInfo;
    }

    public void setIsSubscriptionInfo(String subscriptionInfo) {
        isSubscriptionInfo = subscriptionInfo;
    }

    public String getIsSubscriptionBillInfo() {
        return isSubscriptionBillInfo;
    }

    public void setIsSubscriptionBillInfo(String subscriptionBillInfo) {
        isSubscriptionBillInfo = subscriptionBillInfo;
    }

    public String getIsVodInfo() {
        return isVodInfo;
    }

    public void setIsVodInfo(String vodInfo) {
        isVodInfo = vodInfo;
    }

    public String getIsVodBillInfo() {
        return isVodBillInfo;
    }

    public void setIsVodBillInfo(String vodBillInfo) {
        isVodBillInfo = vodBillInfo;
    }

    public String getIsPpvInfo() {
        return isPpvInfo;
    }

    public void setIsPpvInfo(String ppvInfo) {
        isPpvInfo = ppvInfo;
    }

    public String getIsPpvBillInfo() {
        return isPpvBillInfo;
    }

    public void setIsPpvBillInfo(String ppvBillInfo) {
        isPpvBillInfo = ppvBillInfo;
    }

    public String getIsSpecialBillInfo() {
        return isSpecialBillInfo;
    }

    public void setIsSpecialBillInfo(String specialBillInfo) {
        isSpecialBillInfo = specialBillInfo;
    }

    public String getIsSpecialRefundInfo() {
        return isSpecialRefundInfo;
    }

    public void setIsSpecialRefundInfo(String specialRefundInfo) {
        isSpecialRefundInfo = specialRefundInfo;
    }

    public String getIsPayInfo() {
        return isPayInfo;
    }

    public void setIsPayInfo(String payInfo) {
        isPayInfo = payInfo;
    }

    public String getIsRefundInfo() {
        return isRefundInfo;
    }

    public void setIsRefundInfo(String refundInfo) {
        isRefundInfo = refundInfo;
    }

    public String getIsDepositInfo() {
        return isDepositInfo;
    }

    public void setIsDepositInfo(String depositInfo) {
        isDepositInfo = depositInfo;
    }

    public String getIsWithdrawInfo() {
        return isWithdrawInfo;
    }

    public void setIsWithdrawInfo(String withdrawInfo) {
        isWithdrawInfo = withdrawInfo;
    }

    public String getIsUnpaidSheetInfo() {
        return isUnpaidSheetInfo;
    }

    public void setIsUnpaidSheetInfo(String unpaidSheetInfo) {
        isUnpaidSheetInfo = unpaidSheetInfo;
    }

    public String getIsBalanceInfo() {
        return isBalanceInfo;
    }

    public void setIsBalanceInfo(String balanceInfo) {
        isBalanceInfo = balanceInfo;
    }

    public String getIsTeamSubscriptionInfo() {
        return isTeamSubscriptionInfo;
    }

    public void setIsTeamSubscriptionInfo(String teamSubscriptionInfo) {
        isTeamSubscriptionInfo = teamSubscriptionInfo;
    }

    public String getIsSubscribeProductInfo() {
        return isSubscribeProductInfo;
    }

    public void setIsSubscribeProductInfo(String subscribeProductInfo) {
        isSubscribeProductInfo = subscribeProductInfo;
    }
}