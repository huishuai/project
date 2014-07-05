package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-27
 * Time: 9:40:44
 * To change this template use File | Settings | File Templates.
 */
/**
 * 营业厅业务受理单[网内迁址-机卡信息]
 */
public class NetPrintMoveAddressStbBean implements Serializable {
    //机顶盒号
    String stdId
    //卡号
    String icardId
    //主/副/租
    String stbTypeName
    //初装费
    long feeNewSetup
}
