package com.miniboss.sync.ftp

import com.dvn.miniboss.acct.CmngUser
import com.dvn.miniboss.oldsms.CmngCustom
import com.dvn.miniboss.oldsms.CmngCustominfo
import com.dvn.miniboss.oldsms.CmngStbcustomvdo
import com.miniboss.exception.BaseException
import com.miniboss.acct.utils.DateUtil

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-3
 * Time: 17:01:34
 * To change this template use File | Settings | File Templates.
 */
class CmngCustomInfoBean implements FtpBean {

    String dvbCustomId            //�ͻ����
    String stbId                //�����к�
    String icId                 //IC����
    String stbStatus            //������״̬
    String smCode               //UU��
    String customType           //�ͻ����ͣ�1-���˿ͻ���2-��ҵ�ͻ���3-���ſͻ�
    String sex                  //�Ա�
    Date endDate                //��ѽ�������
    String customNM            //�ͻ�����
    String address              //�ͻ���ַ
    String idCard               //���֤��
    String maintele             //��ϵ�绰
    String stbtype           //����������

    //��ʼ����ǰBean
    public CmngCustomInfoBean(Map userInfeMap, int baseServiceStatus, boolean isFreeUser) throws Exception {
        if (userInfeMap == null || baseServiceStatus == null)
            throw new BaseException("Request Paramater Is Null!", "UserInfeMap or b aseServiceStatus is Null")
        this.dvbCustomId = (String)userInfeMap.get("CUSTOMNO")
        this.customType = (String)userInfeMap.get("TYPE")
        //��Ҫ��������ڽ������ڣ�����iscpͬ������û������ж�
        //����û���"2011-12-31"��������"2010-07-01"
        this.endDate = DateUtil.getDateByBasicStr("2010-07-01")
        if(isFreeUser)
            this.endDate = DateUtil.getDateByBasicStr("2011-12-31")
        this.stbId = userInfeMap.get("STBID")
        this.icId = userInfeMap.get("ICID")
        this.smCode = userInfeMap.get("SMCODE")
        this.stbStatus = baseServiceStatus
        this.sex = userInfeMap.get("SEX")
        this.customNM = userInfeMap.get("CUSTOMNM")
        this.address = userInfeMap.get("ADDRESS")
        this.idCard = userInfeMap.get("IDCARD")
        this.maintele = userInfeMap.get("MAINTELE")
        this.stbtype = userInfeMap.get("RESOURCE_MODEL_ID")
    }


    def String convertBeanToFtpLog() {
        //˳���Խӿ��ĵ�Ϊ����
        StringBuffer result = new StringBuffer()
        result.append(FtpBeanUtil.convertStringToLog(this.dvbCustomId)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.stbId)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.icId)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.stbStatus)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.smCode)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.customType)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.sex)).append(SPLIT)
        result.append(FtpBeanUtil.convertDateToLog(this.endDate)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.customNM)).append(SPLIT)
        //��ַ�ֶ��п��ܻ��лس�������Ҫ�����˵��������ڵ����ļ��л���ֲ������ķ���
        String address = this.address;
        if (address != null && address.length() > 0) {
            address = address.replaceAll("\n", "");
            address = address.replaceAll("\r", "");
        }
        result.append(FtpBeanUtil.convertStringToLog(this.address)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.idCard)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.maintele)).append(SPLIT)
        result.append(FtpBeanUtil.convertStringToLog(this.stbtype))
        result.append(LINE_END);
        return result.toString();
    }

    def void convertFtpLogToBean(String ftpLog) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
