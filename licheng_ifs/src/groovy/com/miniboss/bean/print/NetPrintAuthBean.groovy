package com.miniboss.bean.print
/**
 * Created by IntelliJ IDEA.
 * User: tb
 * Date: 2010-3-15
 * Time: 14:48:36
 * To change this template use File | Settings | File Templates.
 */

/**
 * 营业厅业务受理单[服务订购]
 */
public class NetPrintAuthBean implements Serializable {

    //基础信息
    NetPrintBaseBean baseBean
    //订购关系
    List<NetPrintAuthItemBean> authList

}