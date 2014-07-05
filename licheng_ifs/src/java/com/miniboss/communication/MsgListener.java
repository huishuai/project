package com.miniboss.communication;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: XIE Feng
 * Date: 2009-12-3
 * Time: 11:33:14
 * To change this template use File | Settings | File Templates.
 */
public interface MsgListener {
    public void onMessage(Serializable msg);
}
