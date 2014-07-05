package com.miniboss.acct.bean;

import com.miniboss.acct.utils.DateUtil;
import com.miniboss.acct.utils.IdCreator;

import java.util.Date;

/**
 * bsmp的ftp处理的时候文件地址及其参数bean
 */
public class BsmpFtpFileBean {
    //文件名
    private String name;
    //接口单元编码
    private String code;
    //文件类型
    private String type;
    //文件序号，调用next方法会自动加一
    private int index = 1;
    //文件名【含后缀】
    private String fileName;
    //远程根地址
    private String remoteRootPath;

    //远程文件地址
    private String remoteFilePath;
    //远程文件夹地址
    private String remoteFileDir;

    //远程错误文件地址
    private String remoteErrorFilePath;
    //远程错误文件夹地址
    private String remoteErrorFileDir;

    //远程备份文件地址
    private String remoteBackFilePath;
    //远程备份文件夹地址
    private String remoteBackFileDir;

    //md5名【含后缀】
    private String md5Name;
    //本地地址
    private String localRootPath;
    //本地文件地址
    private String localFilePath;
    //本地文件夹地址
    private String localFileDir;

    //本地错误文件地址
    private String localErrorFilePath;
    //本地错误文件夹地址
    private String localErrorFileDir;

    //本地备份文件地址
    private String localBackFilePath;
    //本地备份文件夹地址
    private String localBackFileDir;

    private String fileSuffer;
    private String fileDir;
    private static final String SP = "/";
    private boolean increment;
    private boolean hasIndex = true;
    private String userDefineSuffix = null; //用户定义文件后缀

    /**
     * dat后缀
     */
    public static final String SPARATOR_DAT = ".DAT";

    /**
     * md5后缀
     */
    public static final String MD5 = ".MD5";
    public static final String FILE_STOR_PATH = "receive/";//数据存放目录
    public static final String FILE_ERROR_PATH = "error/";//数据存放目录
    public static final String FILE_BACK_PATH = "back/";//数据存放目录

    public BsmpFtpFileBean(String type, String code, String remoteRootPath) {
        this.type = type;
        this.code = code;
        this.remoteRootPath = remoteRootPath;
    }

    public BsmpFtpFileBean(String type, String code, String remoteRootPath, String localRootPath) {
        this.type = type;
        this.code = code;
        this.remoteRootPath = remoteRootPath;
        this.localRootPath = localRootPath;
    }

    public void load() {
        if (this.fileDir == null)
            this.fileDir = DateUtil.getDateDayStr(new Date());
        if (this.fileSuffer == null)
            this.fileSuffer = this.type + this.code + this.fileDir;
        if (!this.hasIndex) {
            this.name = this.fileSuffer;
        } else {
            if (!increment) {
                this.name = this.fileSuffer + IdCreator.formatIndex(this.index);
            } else {
                this.name = this.fileSuffer + IdCreator.formatIndex(this.type, this.fileDir);
            }
        }
        //如果有用户自定义文件后缀名，不使用用默认的.DAT
        if (userDefineSuffix != null) {
            this.fileName = this.name + userDefineSuffix;
        } else {
            this.fileName = this.name + SPARATOR_DAT;
        }
        this.md5Name = this.name + MD5;
        if (!remoteRootPath.endsWith(SP)) {
            remoteRootPath += SP;
        }
        this.remoteFileDir = this.remoteRootPath + FILE_STOR_PATH + fileDir;
        this.remoteFilePath = this.remoteFileDir + SP + this.fileName;

        this.remoteErrorFileDir = this.remoteRootPath + FILE_ERROR_PATH + fileDir;
        this.remoteErrorFilePath = this.remoteErrorFileDir + SP + this.fileName;

        this.remoteBackFileDir = this.remoteRootPath + FILE_BACK_PATH + fileDir;
        this.remoteBackFilePath = this.remoteBackFileDir + SP + this.fileName;

        if (localRootPath != null) {
            if (!localRootPath.endsWith(SP))
                localRootPath += SP;

            this.localFileDir = this.localRootPath + FILE_STOR_PATH + fileDir;
            this.localFilePath = this.localFileDir + SP + this.fileName;

            this.localErrorFileDir = this.localRootPath + FILE_ERROR_PATH + fileDir;
            this.localErrorFilePath = this.localErrorFileDir + SP + this.fileName;

            this.localBackFileDir = this.localRootPath + FILE_BACK_PATH + fileDir;
            this.localBackFilePath = this.localBackFileDir + SP + this.fileName;

        }
    }

    public String getType() {
        return type;
    }

    public void next() {
        index = index + 1;
        load();
    }

    public int getIndex() {
        return index;
    }


    public String getName() {
        return name;
    }


    public String getFileName() {
        return fileName;
    }

    public String getMd5Name() {
        return md5Name;
    }

    public String getRemoteFilePath() {
        return remoteFilePath;
    }


    public String getLocalFilePath() {
        return localFilePath;
    }

    public String getCode() {
        return code;
    }

    public String getRemoteRootPath() {
        return remoteRootPath;
    }

    public String getRemoteFileDir() {
        return remoteFileDir;
    }

    public String getLocalRootPath() {
        return localRootPath;
    }

    public String getLocalFileDir() {
        return localFileDir;
    }

    public String getFileSuffer() {
        return fileSuffer;
    }

    public String getFileDir() {
        return fileDir;
    }

    public String getRemoteErrorFilePath() {
        return remoteErrorFilePath;
    }

    public String getRemoteErrorFileDir() {
        return remoteErrorFileDir;
    }

    public String getRemoteBackFilePath() {
        return remoteBackFilePath;
    }

    public String getRemoteBackFileDir() {
        return remoteBackFileDir;
    }

    public String getLocalErrorFilePath() {
        return localErrorFilePath;
    }

    public String getLocalErrorFileDir() {
        return localErrorFileDir;
    }

    public String getLocalBackFilePath() {
        return localBackFilePath;
    }

    public String getLocalBackFileDir() {
        return localBackFileDir;
    }

    public void setHasIndex(boolean hasIndex) {
        this.hasIndex = hasIndex;
    }

    public boolean isIncrement() {
        return increment;
    }

    public void setIncrement(boolean increment) {
        this.increment = increment;
    }

    public String getUserDefineSuffix() {
        return userDefineSuffix;
    }

    public void setUserDefineSuffix(String userDefineSuffix) {
        this.userDefineSuffix = userDefineSuffix;
    }
}
