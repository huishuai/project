package com.bmsp.impl;

import com.bmsp.message.BodyMessage;
import com.miniboss.acct.bean.OrderOnceRequestBean;
import com.miniboss.acct.bean.OrderOnceResponseBean;
import com.miniboss.acct.bean.OrderRequestBean;
import com.miniboss.acct.bean.OrderResponseBean;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-6-29
 * Time: 9:18:53
 * To change this template use File | Settings | File Templates.
 */
public interface IProcess {
    public BodyMessage orderOnce(BodyMessage requestBean);

    public BodyMessage monthDeal(BodyMessage requestBean); 
}
