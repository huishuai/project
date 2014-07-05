package com.miniboss.acct.utils;

public class PageUtil {


    // һҳ��ʾ�ļ�¼��
    public static final int ONEPAGE_RECORD_SIZE = 10000;
    // ��ҳ��
    int pageCount = 1;
    // ��ʼ����
    int startIndex = 1;
    // ��������
    int endIndex = 1;

//    int firstPageNo = 1;
//    int prePageNo = 1;
//    int nextPageNo = 1;
//    int lastPageNo = 1;


    /**
     * ��ʼ�����캯��
     *
     * @param currPageNo ��ǰҳ��
     * @param rowCount   ��¼����
     */
    public PageUtil(int currPageNo, int rowCount) {

        //������ҳ��
        if (rowCount % ONEPAGE_RECORD_SIZE == 0) {
            this.pageCount = rowCount / ONEPAGE_RECORD_SIZE;
        } else {
            this.pageCount = rowCount / ONEPAGE_RECORD_SIZE + 1;
        }
        //���㿪ʼ������¼����
        this.startIndex = ONEPAGE_RECORD_SIZE * (currPageNo - 1);
        this.endIndex = this.startIndex + ONEPAGE_RECORD_SIZE - 1;

//        this.lastPageNo = this.pageCount;
//        if (this.currPageNo > 1) this.prePageNo = this.currPageNo - 1;
//        if (this.currPageNo == this.lastPageNo) {
//            this.nextPageNo = this.lastPageNo;
//        } else {
//            this.nextPageNo = this.currPageNo + 1;
//        }
    }

    public static int getPageCount(int rowCount) {
        if (rowCount <= 0)
            return 0;
        //������ҳ��
        int pageCount;
        if (rowCount % ONEPAGE_RECORD_SIZE == 0) {
            pageCount = rowCount / ONEPAGE_RECORD_SIZE;
        } else {
            pageCount = rowCount / ONEPAGE_RECORD_SIZE + 1;
        }
        return pageCount;
    }

    public static int getStartIndex(int currPageNo) {
        //��ʼ��¼����
        return ONEPAGE_RECORD_SIZE * (currPageNo - 1);
    }

    public static int getEndIndex(int startIndex, int recordCount) {
        //������¼����
        int endIndex = startIndex + ONEPAGE_RECORD_SIZE - 1;
        if (endIndex > recordCount)
            return recordCount;
        return endIndex;
    }

}
