package com.dvn.miniboss.jc.ftp

import com.miniboss.acct.bean.BsmpFtpFileBean

class JcCustomvdoService extends BaseFtpProcessService {

    boolean transactional = false

    public JcCustomvdoService() {
        //todo 修改一下
        this.type = "A"
        this.code = "200101"
    }

    void execute(BsmpFtpFileBean bean) {
        //todo 设备同步   BOSS-->PORTAL
    }

}