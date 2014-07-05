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

    String publishAssetId    //内容编码-------------------publishAsset.id
    String providerId        //内容提供商id(用户tvn编号)----providerId
    String assetType         //内容类别-------------------AssetCatalog.id
    String assetName         //内容名称-------------------publishAsset.assetName
    long assetPrice          //内容价格-------------------publishAsset.price

    public String convertBeanToFtpLog() throws Exception {
        return null
    }

    public void convertFtpLogToBean(String ftpLog) throws BaseException {
        if (ftpLog == null)
            throw new BaseException("Request Paramater Is Null!", "ftpLog is Null!")
        //解析FtpLog
        String[] parameter = ftpLog.split(this.SPARATOR_LINE_REGEX)
        if (parameter.length == 5) {
            this.publishAssetId = parameter[0] //内容编码
            this.providerId = parameter[1]//内容提供商id(用户tvn编号)
            this.assetType = parameter[2] //内容类别
            this.assetName = parameter[3]; //内容名称
            this.assetPrice = Long.parseLong(parameter[4]) //内容价格
        }
    }

}