package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-6-1
 * Time: 10:57:35
 * To change this template use File | Settings | File Templates.
 */
/**
 *营业厅业务受理单[初装机卡与服务订购] 
 */
public class NetPrintNewSetupAssembledBean implements Serializable{
    //基础信息
    NetPrintBaseBean baseBean
    //机卡信息
    List<NetPrintNewSetupStbBean> stbBeanList
     //订购关系
    List<NetPrintAuthItemBean> authList
}
