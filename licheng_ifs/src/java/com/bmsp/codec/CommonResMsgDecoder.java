package com.bmsp.codec;

import com.bmsp.BsmpConstant;
import com.daocren.server.communication.codec.CommonDecoder;

/**
 * Copyright lr.
 * User: admin
 * Date: 2008-8-14
 * Time: 14:05:10
 */
public class CommonResMsgDecoder extends CommonDecoder {
    public boolean cmdMatch(long cmdType) {
        return (cmdType == BsmpConstant.BUSINESSINFO_MSG_RES
                || cmdType == BsmpConstant.BUSINESSINFOREF_MSG_RES);
    }

}
