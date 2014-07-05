package com.miniboss.acct.utils;

public class PageUtil {


    // 一页显示的记录数
    public static final int ONEPAGE_RECORD_SIZE = 10000;
    // 总页数
    int pageCount = 1;
    // 起始行数
    int startIndex = 1;
    // 结束行数
    int endIndex = 1;

//    int firstPageNo = 1;
//    int prePageNo = 1;
//    int nextPageNo = 1;
//    int lastPageNo = 1;


    /**
     * 初始化构造函数
     *
     * @param currPageNo 当前页码
     * @param rowCount   记录总数
     */
    public PageUtil(int currPageNo, int rowCount) {

        //计算总页数
        if (rowCount % ONEPAGE_RECORD_SIZE == 0) {
            this.pageCount = rowCount / ONEPAGE_RECORD_SIZE;
        } else {
            this.pageCount = rowCount / ONEPAGE_RECORD_SIZE + 1;
        }
        //计算开始结束记录索引
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
        //计算总页数
        int pageCount;
        if (rowCount % ONEPAGE_RECORD_SIZE == 0) {
            pageCount = rowCount / ONEPAGE_RECORD_SIZE;
        } else {
            pageCount = rowCount / ONEPAGE_RECORD_SIZE + 1;
        }
        return pageCount;
    }

    public static int getStartIndex(int currPageNo) {
        //开始记录索引
        return ONEPAGE_RECORD_SIZE * (currPageNo - 1);
    }

    public static int getEndIndex(int startIndex, int recordCount) {
        //结束记录索引
        int endIndex = startIndex + ONEPAGE_RECORD_SIZE - 1;
        if (endIndex > recordCount)
            return recordCount;
        return endIndex;
    }

}
