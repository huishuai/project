package com.miniboss.acct.utils;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-18
 * Time: 15:12:32
 * 时间创建实体Bean,以时间加上自增型字段组成，某一时间段内字段不断增加，在下一时间段内还原
 */
public class Creator {
    private int seqNumber;
    private String timeStampStr;

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getTimeStampStr() {
        return timeStampStr;
    }

    public void setTimeStampStr(String timeStampStr) {
        this.timeStampStr = timeStampStr;
    }
}
