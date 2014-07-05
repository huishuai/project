package com.miniboss

//���jbpm�ķ�ҳ����
public class PageInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int total; // �ܼ�¼��
    private int pageCount; // ��ҳ��
    private int pageSize; // ÿҳ��¼����
    private int curPage = 1; // ��ǰҳ��
    private int firstResult // ��ǰҳ��
    def obj;

    private List data = Collections.emptyList(); // ��ҳ����

    /**
     * @param curPage
     *          ��ǰҳ
     * @param pageSize
     *          ÿҳ��¼����
     * @param total
     *          �ܼ�¼��
     */
    public PageInfo() {

    }

    public PageInfo(def obj, int curPage, int pageSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.total = obj.count();
        if (total != 0 && pageSize != 0) {
            this.pageCount = (total + pageSize - 1) / pageSize;
        }
        this.firstResult = (curPage - 1) * pageSize
        this.data = obj.page(firstResult, pageSize).list()
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
