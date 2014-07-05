package com.dvn.miniboss.jc.socket

import com.bmsp.impl.IProcess
import com.bmsp.message.BodyMessage
import com.bmsp.util.StatusConstant
import com.dvn.miniboss.jc.process.AuthServiceProcessService
import com.google.gson.Gson

class JcSocketService implements IProcess {
    def AuthServiceProcessService authServiceProcessService
    boolean transactional = false

    BodyMessage orderOnce(BodyMessage requestBean) {
        log.info new Gson().toJson(requestBean)
        BodyMessage bodyMessage = new BodyMessage()
        bodyMessage.sessionId = requestBean.sessionId
        String code;
        try {
            code = authServiceProcessService.orderOnce(requestBean)
        } catch (Exception e) {
            code = StatusConstant.ORDER_PROCESS_ERROR
        }
        bodyMessage.result = code
        bodyMessage.type = "4"
        return bodyMessage;
    }

    BodyMessage monthDeal(BodyMessage requestBean) {
        log.info new Gson().toJson(requestBean)
        BodyMessage bodyMessage = new BodyMessage()
        bodyMessage.sessionId = requestBean.sessionId
        if ("1".equals(requestBean.getOperType())) {
            bodyMessage = authServiceProcessService.orderMonth(requestBean, bodyMessage)
        } else {
            bodyMessage = authServiceProcessService.disOrderMonth(requestBean,bodyMessage)
        }
        return bodyMessage;
    }

}
