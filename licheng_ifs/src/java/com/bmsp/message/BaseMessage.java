package com.bmsp.message;

import com.daocren.server.communication.sync.SyncMessage;

import java.io.Serializable;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-13
 * Time: 17:54:54
 * ��Ϣ�����
 */
public class BaseMessage implements Serializable, SyncMessage {

    private int mark;            //��Ϣͷ��ʶ
    private int length;          //��Ϣ�ܳ���
    private int commandId;      //��Ϣ���ʶ
    private byte[] body;         //��Ϣ��
    private String sessionId;    //��Ϣ���SessionID

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
