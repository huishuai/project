package com.miniboss.bean.print

/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-5-28
 * Time: 10:36:02
 * To change this template use File | Settings | File Templates.
 */
/**
 * Ӫҵ��ҵ������[ת��]
 */
public class NetPrintExChangeStbIcBean implements Serializable {
     //������Ϣ
    NetPrintBaseBean baseBean

    //ת���û���Ϣ
    String exChangeOutCustomName
    String exChangeOutCustomCode
    
    List<NetPrintExChangeStbIcItemBean>  stbIcList
    
}
