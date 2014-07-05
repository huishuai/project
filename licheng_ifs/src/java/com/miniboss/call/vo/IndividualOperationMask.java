package com.miniboss.call.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Map;

/**
 * <p>Title: SMS ��ѯ��Ϣѡ��</p>
 * <p>Description: ���ֵ����û�����ϵͳ</p>
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
    private String isCustomerInfo;         /* �Ƿ񷵻��û�������Ϣ����0��= �񣬡�1��=�� */
    private String isStbOrderInfo;         /* �Ƿ񷵻ػ����й�������Ϣ����0��= �񣬡�1��=�� */
    private String isStbBillInfo;          /* �Ƿ񷵻ػ����й����ʵ���Ϣ����0��= �񣬡�1��=�� */
    private String isStbTakeInfo;          /* �Ƿ񷵻ػ����й�����Ϣ����0��= �񣬡�1��=�� */
    private String isStbReturnInfo;        /* �Ƿ񷵻ػ������˻���Ϣ����0��= �񣬡�1��=�� */
    private String isStbChangeInfo;        /* �Ƿ񷵻ػ����л�����Ϣ����0��= �񣬡�1��=�� */
    private String isStbSignUpInfo;        /* �Ƿ񷵻ػ����п�����Ϣ����0��= �񣬡�1��=�� */
    private String isStbSignOffInfo;       /* �Ƿ񷵻ػ�����������Ϣ����0��= �񣬡�1��=�� */
    private String isStbAddressMoveInfo;   /* �Ƿ񷵻ػ������ƻ���Ϣ����0��= �񣬡�1��=�� */
    private String isSubscriptionInfo;     /* �Ƿ񷵻����ӷ�����Ϣ����0��= �񣬡�1��=�� */
    private String isSubscriptionBillInfo; /* �Ƿ񷵻����ӷ����ʵ���Ϣ����0��= �񣬡�1��=�� */
    private String isVodInfo;              /* �Ƿ�VOD ������Ϣ����0��= �񣬡�1��=�� */
    private String isVodBillInfo;          /* �Ƿ񷵻�VOD �ʵ���Ϣ����0��= �񣬡�1��=�� */
    private String isPpvInfo;              /* �Ƿ񷵻�PPV ������Ϣ����0��= �񣬡�1��=�� */
    private String isPpvBillInfo;          /* �Ƿ񷵻�PPV �ʵ���Ϣ����0��= �񣬡�1��=�� */
    private String isSpecialBillInfo;      /* �Ƿ񷵻����⽻���ʵ���Ϣ����0��= �񣬡�1��=�� */
    private String isSpecialRefundInfo;    /* �Ƿ񷵻������˿��ʵ���Ϣ����0��= �񣬡�1��=�� */
    private String isPayInfo;              /* �Ƿ񷵻ؽ��ѽ�����Ϣ����0��= �񣬡�1��=�� */
    private String isRefundInfo;           /* �Ƿ񷵻��˿����Ϣ����0��= �񣬡�1��=�� */
    private String isDepositInfo;          /* �Ƿ񷵻ش�����Ϣ����0��= �񣬡�1��=�� */
    private String isWithdrawInfo;         /* �Ƿ񷵻�ȡ�����Ϣ����0��= �񣬡�1��=�� */
    private String isUnpaidSheetInfo;      /* �Ƿ񷵻�δ���ѵ�����Ϣ����0��= �񣬡�1��=�� */
    private String isBalanceInfo;          /* �Ƿ񷵻��ڲ��ʻ������Ϣ����0��= �񣬡�1��=�� */
    private String isTeamSubscriptionInfo; /* �Ƿ񷵻ؼ������������Ϣ����0��= �񣬡�1��=�� */
    private String isSubscribeProductInfo; /* �Ƿ񷵻ؿɶ������Ӳ�Ʒ��Ϣ����0��= �񣬡�1��=�� */

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