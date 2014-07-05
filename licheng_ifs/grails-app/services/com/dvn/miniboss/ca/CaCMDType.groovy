package com.dvn.miniboss.ca

/**
 * Created by IntelliJ IDEA.
 * User: XIE Feng
 * Date: 2009-11-17
 * Time: 17:28:22
 * To change this template use File | Settings | File Templates.
 */

public class CaCMDType implements Serializable{

    public static String Cmd_PairICC        = "PairICC";        //机卡配对指令
    public static String Cmd_AddProduct     = "AddProduct";     //增加授权指令
    public static String Cmd_DelProduct     = "CancelProduct";  //减授权指令
    public static String Cmd_UnPairICC      = "UnPairICC";      //解配对指令
    public static String Cmd_OSD            = "SendOSD";  //对机顶盒发送OSD指令
    public static String Cmd_Mail           = "SendMail";  //对机顶盒发送Mail指令
    public static String Cmd_STBON         ="ReactiveICC"   //  开启机顶盒服务
    public static String Cmd_STBOFF        ="SuspendICC"     //关闭机顶盒

}