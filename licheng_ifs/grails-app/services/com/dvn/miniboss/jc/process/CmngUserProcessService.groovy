package com.dvn.miniboss.jc.process

import com.dvn.miniboss.acct.CmngUser
import com.dvn.miniboss.oldsms.CmngCustom
import com.dvn.miniboss.oldsms.CmngCustominfo
import com.dvn.miniboss.oldsms.CmngStbcustomvdo
import com.miniboss.sync.ftp.CmngCustomInfoBean
import com.dvn.miniboss.oldsms.AuthService
import com.dvn.miniboss.oldsms.ChargeBillType
import com.dvn.miniboss.acct.CmngStbType
import com.miniboss.exception.BaseException
import com.dvn.sys.dass.sms.StringUtil
import com.miniboss.util.MessageUtil

class CmngUserProcessService {

    boolean transactional = false

    //����uuId��ȡ�û�����
    public CmngUser getUserIDByUuId(String uuId) throws BaseException{
        CmngUser cmngUser
        try {
            CmngStbcustomvdo stbcustomvdo = CmngStbcustomvdo.findBySmcode(uuId)
            long userId = stbcustomvdo.getUseridCmngUser()
            cmngUser = CmngUser.findById(userId)
            if(cmngUser == null)
                throw new BaseException(StringUtil.UUID_NOT_EXIST,MessageUtil.getMessage("UUID.Not.Exist"))
        } catch (Exception ex) {
            throw new BaseException(StringUtil.UUID_NOT_EXIST,MessageUtil.getMessage("UUID.Not.Exist"))
        }
        return cmngUser
    }


    //����uuId��ȡ�û�����
    public CmngUser getMasterUserByCustomId(long customId) throws Exception{

        List <CmngUser>userList = CmngUser.findAllByDvbCostomId(customId)
        for (com.dvn.miniboss.acct.CmngUser cmngUser: userList) {
            if(CmngStbType.MASTER.equals(cmngUser.classType))
                return cmngUser
        }
        return null
    }

    /**
     * ���ĳ���ͻ��Ķ����û�
     * @param customId
     * @param customType
     * @return
     */
    public List<CmngUser> getOrderUserByCustomId(long customId, String customType){
        //Ĭ���û�������Ϊ�����û�
        String userType = CmngUser.GROUPTYPE_PERSON
        if(CmngCustom.CUSTOMTYPE_COMPANY.equals(customType)){
            userType = CmngUser.GROUPTYPE_COMPANY_USERTEAM
        }else if(CmngCustom.CUSTOMTYPE_COMPANY.equals(customType)){
            userType = CmngUser.GROUPTYPE_GROUP_USERTEAM
        }
        //��õ�ǰ�ͻ������ж����û�����
        List<CmngUser> allUserList = CmngUser.findAllByDvbCostomIdAndGroupType(customId,userType)
        return allUserList
    }


}
