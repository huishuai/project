package com.dvn.miniboss.ca

/**
 * Created by IntelliJ IDEA.
 * User: XIE Feng
 * Date: 2009-11-17
 * Time: 17:28:22
 * To change this template use File | Settings | File Templates.
 */

public class CaCMDType implements Serializable{

    public static String Cmd_PairICC        = "PairICC";        //�������ָ��
    public static String Cmd_AddProduct     = "AddProduct";     //������Ȩָ��
    public static String Cmd_DelProduct     = "CancelProduct";  //����Ȩָ��
    public static String Cmd_UnPairICC      = "UnPairICC";      //�����ָ��
    public static String Cmd_OSD            = "SendOSD";  //�Ի����з���OSDָ��
    public static String Cmd_Mail           = "SendMail";  //�Ի����з���Mailָ��
    public static String Cmd_STBON         ="ReactiveICC"   //  ���������з���
    public static String Cmd_STBOFF        ="SuspendICC"     //�رջ�����

}