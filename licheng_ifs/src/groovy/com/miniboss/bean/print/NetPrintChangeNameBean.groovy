package com.miniboss.bean.print

import com.dvn.miniboss.oldsms.CmngStbcustomvdo

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-27
 * Time: 16:56:38
 * To change this template use File | Settings | File Templates.
 */
/**
 * 营业厅业务受理单[更名过户]
 */
public class NetPrintChangeNameBean implements Serializable {
    //基础信息
    NetPrintBaseBean baseBean
    //机卡信息
    List<CmngStbcustomvdo> stbIcList
}
