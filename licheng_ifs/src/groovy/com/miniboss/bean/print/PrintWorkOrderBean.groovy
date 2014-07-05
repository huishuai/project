package com.miniboss.bean.print
/**
 * Created by IntelliJ IDEA.
 * User: my
 * Date: 2010-4-29
 * Time: 15:28:22
 * To change this template use File | Settings | File Templates.
 */

/**
 * 派工单母版
 */
public class PrintWorkOrderBean implements Serializable{
    //派工单单编号
    String billCode
  
    //------客户资料-----------------
    //客户编号
    String customNo
    //客户姓名
    String customnm
    //住宅电话
    String phone
    //移动电话
    String mobilephone
     //装机地址
    String addressconn
    //端口数
    String ports
    //业务类型
    String buzType
    //用户类型
    String customType

    //------服务资料-----------------
    //下单时间
    java.util.Date timeCreate
    //派工时间
    java.util.Date timeAssign
    //安装时间
    java.util.Date timeSetup
    //施工人
    String setupOperator
  
    //------稽查资料-----------------
    //回单时间
    java.util.Date timeReturn
    //稽查时间
    java.util.Date timeCheck

    //备注
     String remark
    
    //子页面
    String localTemplate
    //页面记录数
    int pageSize = 10
    //总页数
    int pageCount = 1
    
  

}