package com.miniboss.bean.print

import com.dvn.miniboss.oldsms.CmngStbcustomvdo

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-6-3
 * Time: 10:54:16
 * To change this template use File | Settings | File Templates.
 */
public class PrintWorkOrderDvbStbInstallBean implements Serializable {
    //派工单母版
    PrintWorkOrderBean baseBean

    //机卡信息
    List<CmngStbcustomvdo> stbBeanList

}
