package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-27
 * Time: 9:34:15
 * To change this template use File | Settings | File Templates.
 */
/**
 * Ӫҵ��ҵ������[����Ǩַ]
 */
public class NetPrintMoveAddressBean implements Serializable {
    //������Ϣ
    NetPrintBaseBean baseBean
    //ԭ��ַ
    String sourceAddress
    //������Ϣ
    List<NetPrintMoveAddressStbBean> stbBeanList
    //������Ϣ
    List<NetPrintMoveAddressAuthBean> authBeanList
    //�˻����
    long accountBalance
}
