package com.bmsp.message;

import com.daocren.server.communication.sync.SyncMessage;

import java.io.Serializable;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-13
 * Time: 17:54:54
 * 消息类基类
 */
public class BaseMessage implements Serializable, SyncMessage {

    private int mark;            //消息头标识
    private int length;          //消息总长度
    private int commandId;      //消息体标识
    private byte[] body;         //消息体
    private String sessionId;    //消息体的SessionID

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getLength() {
        return body.length + 12;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }


    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public String getSyncMessageId() {
        return getSessionId();
    }
}
