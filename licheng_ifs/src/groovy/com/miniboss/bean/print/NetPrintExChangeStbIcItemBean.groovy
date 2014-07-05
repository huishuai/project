package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-28
 * Time: 10:36:28
 * To change this template use File | Settings | File Templates.
 */
/**
 * 营业厅业务受理单[转机-机卡信息]
 */
public class NetPrintExChangeStbIcItemBean implements Serializable {
    
     //机顶盒号
    String stdId
    //卡号
    String icardId
    //主/副/租
    String stbTypeName
    //初装费
    long feeNewSetup

}
