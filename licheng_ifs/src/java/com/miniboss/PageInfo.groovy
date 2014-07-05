package com.miniboss

//针对jbpm的分页处理
public class PageInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int total; // 总记录数
    private int pageCount; // 总页数
    private int pageSize; // 每页记录条数
    private int curPage = 1; // 当前页码
    private int firstResult // 当前页码
    def obj;

    private List data = Collections.emptyList(); // 分页数据

    /**
     * @param curPage
     *          当前页
     * @param pageSize
     *          每页记录条数
     * @param total
     *          总记录数
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
