package com.miniboss.bean.authservice

import com.dvn.miniboss.oldsms.AuthService
import com.miniboss.bean.BaseRequestBean

/**
 * Created by IntelliJ IDEA.
 * User: xgz
 * Date: 2010-3-16
 * Time: 11:19:36
 * To change this template use File | Settings | File Templates.
 */

public class AuthServReqBean extends BaseRequestBean{
    String operId;
    List<Long> services = new ArrayList<Long>();
    long customId;
}