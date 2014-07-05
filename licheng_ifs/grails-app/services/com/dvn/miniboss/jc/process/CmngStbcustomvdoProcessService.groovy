package com.dvn.miniboss.jc.process

import com.dvn.miniboss.oldsms.CmngStbcustomvdo
import com.dvn.miniboss.acct.CmngUserRef

class CmngStbcustomvdoProcessService {

    boolean transactional = false

    public String getUUidByPersonCustom(long userId) {

        CmngStbcustomvdo cmngStbcustomvdo = CmngStbcustomvdo.findByUseridCmngUser(userId)
        if (cmngStbcustomvdo != null) {
            return cmngStbcustomvdo.smcode
        }
        return null
    }

    public List<String> getUUidByCompanyAndGroupCustom(long userId) {

        List<String> uuIdList = new ArrayList<String>()
        List <CmngUserRef>cmngUserRefList = CmngUserRef.findAllByFatherUserId(userId)
        for (CmngUserRef cmngUserRef: cmngUserRefList) {
            long childUserId = cmngUserRef.childUserId
            String uuId = getUUidByPersonCustom(childUserId)
            uuIdList.add(uuId)
        }
        return uuIdList
    }
}
