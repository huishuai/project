package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-27
 * Time: 9:34:15
 * To change this template use File | Settings | File Templates.
 */
/**
 * 营业厅业务受理单[网内迁址]
 */
public class NetPrintMoveAddressBean implements Serializable {
    //基础信息
    NetPrintBaseBean baseBean
    //原地址
    String sourceAddress
    //机卡信息
    List<NetPrintMoveAddressStbBean> stbBeanList
    //服务信息
    List<NetPrintMoveAddressAuthBean> authBeanList
    //账户余额
    long accountBalance
}
