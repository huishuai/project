package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-28
 * Time: 10:15:18
 * To change this template use File | Settings | File Templates.
 */
/**
 * 营业厅业务受理单[销户-机卡]
 */
public class NetPrintCancelCustomStbBean implements Serializable{
    //业务类型
    String buzType
    //销户类型
    String cancelType
    //机顶盒号
    String stdId
    //卡号
    String icardId
    //主/副/租
    String stbTypeName
    //销户原因
    String cancelReason
    
}
