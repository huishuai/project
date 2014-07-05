package com.miniboss.acct.bean;

/**
 * Created by IntelliJ IDEA.
 * User: AngelWing
 * Date: 2009-11-18
 * Time: 9:55:18
 * To change this template use File | Settings | File Templates.
 */
public class KeyBean {
    long index;
    long offset;

    public long getTotal() {
        return index + offset;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }
}