package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-28
 * Time: 10:36:02
 * To change this template use File | Settings | File Templates.
 */
/**
 * 营业厅业务受理单[转机]
 */
public class NetPrintExChangeStbIcBean implements Serializable {
     //基础信息
    NetPrintBaseBean baseBean

    //转出用户信息
    String exChangeOutCustomName
    String exChangeOutCustomCode
    
    List<NetPrintExChangeStbIcItemBean>  stbIcList
    
}
