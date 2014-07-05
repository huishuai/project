package com.miniboss.sync.ftp

import com.miniboss.exception.BaseException

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-6-30
 * Time: 16:07:57
 * To change this template use File | Settings | File Templates.
 */

class PublishAssetBean implements FtpBean {

    String publishAssetId    //���ݱ���-------------------publishAsset.id
    String providerId        //�����ṩ��id(�û�tvn���)----providerId
    String assetType         //�������-------------------AssetCatalog.id
    String assetName         //��������-------------------publishAsset.assetName
    long assetPrice          //���ݼ۸�-------------------publishAsset.price

    public String convertBeanToFtpLog() throws Exception {
        return null
    }

    public void convertFtpLogToBean(String ftpLog) throws BaseException {
        if (ftpLog == null)
            throw new BaseException("Request Paramater Is Null!", "ftpLog is Null!")
        //����FtpLog
        String[] parameter = ftpLog.split(this.SPARATOR_LINE_REGEX)
        if (parameter.length == 5) {
            this.publishAssetId = parameter[0] //���ݱ���
            this.providerId = parameter[1]//�����ṩ��id(�û�tvn���)
            this.assetType = parameter[2] //�������
            this.assetName = parameter[3]; //��������
            this.assetPrice = Long.parseLong(parameter[4]) //���ݼ۸�
        }
    }

}