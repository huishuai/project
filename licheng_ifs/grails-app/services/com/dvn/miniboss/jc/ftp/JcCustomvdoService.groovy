package com.dvn.miniboss.jc.ftp

import com.miniboss.acct.bean.BsmpFtpFileBean

class JcCustomvdoService extends BaseFtpProcessService {

    boolean transactional = false

    public JcCustomvdoService() {
        //todo �޸�һ��
        this.type = "A"
        this.code = "200101"
    }

    void execute(BsmpFtpFileBean bean) {
        //todo �豸ͬ��   BOSS-->PORTAL
    }

}