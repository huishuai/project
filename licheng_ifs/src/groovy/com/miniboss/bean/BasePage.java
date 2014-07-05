package com.miniboss.bean;

/**
 * Created by IntelliJ IDEA.
 * User: tb
 * Date: 2009-12-28
 * Time: 17:10:56
 * To change this template use File | Settings | File Templates.
 */

/**
 * ��ҳ����
 */
public class BasePage {
    static int DEFAULT_PAGE_SIZE = 10;
    //�ܼ�¼��
    public int recordCount = 0;
    //ҳ��
    public int pageNo = 1;
    //ҳ��С
    public int pageSize = DEFAULT_PAGE_SIZE;
    //�����Ƿ����ܼ�¼��
    private boolean fetchTotal = true;

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * �õ��ܹ�ҳ��
     * @return �ܹ�ҳ��
     */
    public int getPageCount() {
        return ((getRecordCount() - 1) / getPageSize()) + 1;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFetchTotal() {
        return fetchTotal;
    }

    public void setFetchTotal(boolean fetchTotal) {
        this.fetchTotal = fetchTotal;
    }

    public static int getRecordStart(BasePage p) {
        if (p == null) return 1;
        else return p.getPageSize() * (p.getPageNo() - 1) + 1;
    }

    public static int getRecordTo(BasePage p) {
        if (p == null) return Integer.MAX_VALUE;
        else return p.getPageSize() * p.getPageNo();
    }

    /**
     * �õ��ܹ�ҳ��
     * @param count
     * @param pageSize
     * @return �ܹ�ҳ��
     */
    public static int getPageCount(int count, int pageSize) {
        return ((count - 1) / pageSize) + 1;
    }

    public static void main(String[] args) {

        int onePageRecordSize = 3;
        int count = 0;
        int pageSize = BasePage.getPageCount(count, onePageRecordSize);
        System.out.println("pageSize:" + pageSize);

        for (int pageNo = 0; pageNo < pageSize; pageNo++) {

            int startIndex = pageNo * onePageRecordSize;
            if ((startIndex + onePageRecordSize) > count) {
                onePageRecordSize = (count - startIndex);
            }
            System.out.println("startIndex:" + startIndex);
            System.out.println("onePageRecordSize:" + onePageRecordSize);
        }

    }

}
