package com.miniboss.bean.print
/**
 * Created by IntelliJ IDEA.
 * User: tb
 * Date: 2010-3-19
 * Time: 13:20:41
 * To change this template use File | Settings | File Templates.
 */
/**
 * 营业厅业务受理单[销户]
 */
public class NetPrintCancelCustomBean implements Serializable{
    //基础信息
    NetPrintBaseBean baseBean
    //机卡信息
    List<NetPrintCancelCustomStbBean> stbBeanList
}