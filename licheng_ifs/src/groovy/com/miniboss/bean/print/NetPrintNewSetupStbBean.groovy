package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-27
 * Time: 14:59:21
 * To change this template use File | Settings | File Templates.
 */
public class NetPrintNewSetupStbBean implements Serializable{
    //机顶盒号
    String stdId
    //卡号
    String icardId
    //主/副/租
    String stbTypeName
    //初装费
    long feeNewSetup
    // 机顶盒费(分)
    long feeStb
    //其他费
    long feeOthers
    //合计
    long total
}
