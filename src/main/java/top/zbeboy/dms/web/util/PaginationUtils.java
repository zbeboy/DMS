package top.zbeboy.dms.web.util;

public class PaginationUtils {
    private int totalPages;// 总页数
    private int totalDatas;// 数据总数
    private int pageNum;// 当前页
    private int pageSize;// 每页大小
    private int displayedPages;// 显示按钮数
    private String searchParams;// 搜索参数

    public int getTotalPages() {
        if (totalDatas % pageSize == 0) {
            totalPages = totalDatas / pageSize;
        } else {
            totalPages = totalDatas / pageSize + 1;
        }
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalDatas() {
        return totalDatas;
    }

    public void setTotalDatas(int totalDatas) {
        this.totalDatas = totalDatas;
    }

    public int getPageNum() {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getDisplayedPages() {
        if (totalPages > 3 || totalPages == 1) {
            displayedPages = 3;
        } else {
            displayedPages = totalPages;
        }
        return displayedPages;
    }

    public void setDisplayedPages(int displayedPages) {
        this.displayedPages = displayedPages;
    }

    public String getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }
}
