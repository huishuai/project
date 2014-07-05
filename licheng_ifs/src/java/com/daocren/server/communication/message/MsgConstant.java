package com.daocren.server.communication.message;

/**
 * 消息常量
 * @author Daocren
 */
public final class MsgConstant {

    /**
     * session 中存储toolmgr的key
   */
    public static final String KEY_TMGR = "toolmgr";

    /**
     * session 中存储窗口大小的key
     */
    public static final String KEY_WINDOWSIZE = "windowSize";

    /**
     * session中链接类型
     */
    public static final String KEY_CON_TYPE = "connection.remoteQname";

    /**
     * session断开重连后是否重发
     */
    public static final String KEY_RESEND = "reSend";

    /**
     * 责任链的key
     */
    public static final String KEY_FILTERS = "command.chain";

    /**
     * 连接是否为双向
     */
    public static final String KEY_BIDI = "is.bidirect";

    /**
     * 链接器名称键值
     */
    public static final String KEY_CONNECTOR_NAME = "connector.name";
}
