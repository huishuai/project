package com.dvn.miniboss.jc.ftp

import com.dvn.miniboss.jc.process.PublishAssetProcessService
import com.miniboss.acct.bean.BsmpFtpFileBean
import com.miniboss.acct.utils.FileUtil
import com.miniboss.acct.utils.FtpOperate
import com.miniboss.acct.utils.Md5Util
import com.miniboss.sync.ftp.SyncConstant
import org.apache.log4j.Logger

class JcPublishAssetService extends BaseFtpProcessService {

    boolean transactional = false
    private final static Logger logger = Logger.getLogger(JcPublishAssetService.class)
    def PublishAssetProcessService publishAssetProcessService

    public JcPublishAssetService() {
        this.type = "A"
        this.code = "02004"
    }

    void execute(BsmpFtpFileBean bean) {
        //��Դͬ�� BSMP-->BOSS
        //��ȡftp�������ļ�
        //������������Ŀ¼������ftp�ļ�
        //��������ftp�ļ�
        //ͬ��Bean���

        logger.debug(">>>>>>>>>>>>>>>>>>>>>PublishAsset Sync��Begin<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        FtpOperate ftpOperate = null
        try {
            ftpOperate = this.ftpClient.createFtpClient();
            ftpOperate.getFtpClient().enterLocalPassiveMode();
            if (ftpOperate.checkConnected()) {
                //ӰƬý����Ϣϵ�����ļ�����
                boolean flag = downloadFileByType(ftpOperate, bean.remoteFileDir, bean.localFileDir, bean.fileSuffer, bean.remoteErrorFileDir);
                if (!flag) {
                    logger.error("DownLoad Error! RemoteFilePath:" + bean.remoteFilePath);
                } else {
                    logger.info("DownLoad Success! RemoteFilePath:" + bean.remoteFilePath);
                    //��ñ�������Ŀ¼�е������ļ�
                    List<File> FileList = FileUtil.getFileListFromDir(bean.localFileDir)
                    for (File file: FileList) {
                        if (file.getName().startsWith(bean.fileSuffer) && file.getName().indexOf(SyncConstant.SPARATOR_DAT) > 0) {
                            //��ͬ��ý����Ϣ���
                            publishAssetProcessService.insertPublishAssetToDB(file)
                            String fileName = file.getName();
                            if (file.isFile()) {
                                file.delete() 
                            }
                            String validFileName = fileName.substring(0, fileName.toUpperCase().indexOf(SyncConstant.SPARATOR_DAT)) + SyncConstant.MD5;
                            ftpOperate.moveFile(bean.remoteFileDir, fileName, bean.remoteBackFileDir, fileName);
                            ftpOperate.moveFile(bean.remoteFileDir, validFileName, bean.remoteBackFileDir, validFileName);
                        }
                    }

                }

            } else {
                logger.error("FTP Not Connection��Please Check Net��");
            }
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>>>>>>>>>>PublishAsset Sync��Exception<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            e.printStackTrace();
        } finally {
            try {
                if (ftpOperate != null)
                    ftpOperate.close();
            } catch (Exception e) {
                logger.error("Close FtpClient Error��");
            }
        }
        logger.debug(">>>>>>>>>>>>>>>>>>>>>PublishAsset Sync��End<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>ӰƬý����Ϣͬ������������<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * ֱ������FTP�ļ�,�Ȼ��Ҫ���ص��ļ����ټ���ļ��Ƿ�����������ļ��ϴ�����������
     *
     * @param ftpOperate FTP�ͻ���
     * @param pathName �������ϵ�·����
     * @param localPath �����ļ���·����
     * @param type �ļ����ͱ���
     * @return boolean �Ƿ����سɹ�
     */
    public boolean downloadFileByType(FtpOperate ftpOperate, String pathName, String localPath, String type, String remoteErrPath) {

        try {
            if (!FileUtil.mkDir(localPath)) {
                return false
            }
            if (!ftpOperate.checkConnected()) {
                return false
            }
            List<String> list = checkFile(ftpOperate, pathName, type);
            if (list.size() > 0) {
                for (String file: list) {
                    try {
                        boolean isvalid = validMd5(ftpOperate, pathName, localPath, file);
                        String validFileName = file.substring(0, file.toUpperCase().indexOf(SyncConstant.SPARATOR_DAT)) + SyncConstant.MD5;

                        if (isvalid) {
                            if (downloadFile(file, ftpOperate, pathName, localPath)) {
                                logger.debug(">>>>>>>>>>>>>>>>>>>>>> DownLoad File Success:" + pathName + File.separator + file);
                            } else {
                                logger.error(">>>>>>>>>>>>>>>>>>>>>> DownLoad File failed:" + pathName + File.separator + file);
//                                ftpOperate.moveFile(pathName, file, remoteErrPath, file);
//                                ftpOperate.moveFile(pathName, validFileName, remoteErrPath, validFileName);
                            }
                        } else {
                            logger.error(">>>>>>>>>>>>>>> ftp file md5 valid failed!");
//                            ftpOperate.moveFile(pathName, file, remoteErrPath, file);
//                            ftpOperate.moveFile(pathName, validFileName, remoteErrPath, validFileName);
                        }

                    } catch (Exception e) {
                        logger.error(file + " DownLoad Error!", e);
                        File localFile = new File(localPath + File.separator + file);
                        if (localFile.exists()) {
                            localFile.delete();
                            logger.debug("Clean File Error!");
                        }
                    }
                }
            } else {
                logger.info("Not Download File Path��" + pathName + "    File Need FileBeforeStuff��" + type);
//                System.out.println("û��Ҫ���ص��ļ�·����" + pathName + "    �ļ���Ҫ�����ǰ׺��ʽ��" + type);
            }

        } catch (Exception e) {
            logger.error("Can Not Download File!", e);
            return false
        }
        return true
    }


    private List<String> checkFile(FtpOperate ftpOperate, String pathName, String type) throws IOException {
        List<String> _fileList = new ArrayList<String>();
        String[] list = ftpOperate.getFileList(pathName);
        if (list != null && list.length > 0) {
            List<String> fileList = new ArrayList<String>();
            for (String file: list) {
                if (file.startsWith(type)) {
                    fileList.add(file);
                }
            }
            for (String fileName: fileList) {
                if (fileName.endsWith(SyncConstant.SPARATOR_DAT)) {
                    String md5 = fileName.substring(0, fileName.toUpperCase().indexOf(SyncConstant.SPARATOR_DAT)) + SyncConstant.MD5;
                    for (String _fileName: fileList) {
                        if (_fileName.equals(md5)) {
                            _fileList.add(fileName);
                        }
                    }
                }
            }
        }
        return _fileList;
    }

    /**
     * ����MD5�ļ����Ա�MD5��Ϣ�鿴�ļ��Ƿ���
     * @param ftpOperate
     * @param remoteReceivePath
     * @param localReceivePath
     * @param fileName
     * @return
     */
    private boolean validMd5(FtpOperate ftpOperate, String remoteReceivePath, String localReceivePath, String fileName) {

        BufferedReader validReader = null
        try {
            String validFileName = fileName.substring(0, fileName.toUpperCase().indexOf(SyncConstant.SPARATOR_DAT)) + SyncConstant.MD5;
            if (!ftpOperate.downloadFile(localReceivePath, remoteReceivePath, validFileName)) {
                logger.error("-------- download from ftp md5 file error ");
                return false;
            }
            validReader = new BufferedReader(new FileReader(localReceivePath + "/" + validFileName));
            String validLine
            while ((validLine = validReader.readLine()) != null) {
                String validKey = fileName.substring(0, fileName.toUpperCase().indexOf(SyncConstant.SPARATOR_DAT)) +
                        ftpOperate.getFileSize(remoteReceivePath, fileName) + SyncConstant.SPARATOR_N;
                if (validLine.trim().equals(Md5Util.byte2hex(Md5Util.encryptMD5(validKey.getBytes())))) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (validReader != null) {
                validReader.close()
            }
        }
    }

    private boolean downloadFile(String fileName, FtpOperate ftpOperate, String remotePath, String localPath) throws IOException {
        boolean success = false;
        println remotePath + "/" + fileName
        println localPath + "/" + fileName
        println ftpOperate.checkConnected()
        ftpOperate.getFtpClient().enterLocalPassiveMode();
        success = ftpOperate.downloadFile(localPath, remotePath, fileName);
        return success;
    }


}