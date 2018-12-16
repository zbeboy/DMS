package top.zbeboy.dms.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BootstrapTableUtils<T> {
    /*
   返回的数据
    */
    private List<T> rows = null;

    private int total = 0;

    /*
    请求数据
     */
    private String sortName = null;

    private String sortOrder = null;

    private int pageSize = 0;

    private int pageNumber = 0;

    /*
   额外搜索参数
    */
    private String extraSearch = null;

    /*
   object extraSearch
    */
    private JSONObject search = null;

    public static <T> BootstrapTableUtils<T> of() {
        return new BootstrapTableUtils<>();
    }

    public BootstrapTableUtils() {
    }

    public BootstrapTableUtils(HttpServletRequest request) {
        String sortNameParam = request.getParameter("sortName");
        String sortOrderParam = request.getParameter("sortOrder");
        String pageSizeParam = request.getParameter("pageSize");
        String pageNumberParam = request.getParameter("pageNumber");
        String extraSearchParam = request.getParameter("extraSearch");

        if (NumberUtils.isDigits(pageSizeParam)) {
            this.pageSize = NumberUtils.toInt(pageSizeParam);
        }

        if (NumberUtils.isDigits(pageNumberParam)) {
            this.pageNumber = NumberUtils.toInt(pageNumberParam);
        }

        if (StringUtils.isNotBlank(sortNameParam)) {
            this.sortName = sortNameParam;
        }

        if (StringUtils.isNotBlank(sortOrderParam)) {
            this.sortOrder = sortOrderParam;
        }

        if (StringUtils.isNotBlank(extraSearchParam)) {
            this.extraSearch = extraSearchParam;
            this.search = JSON.parseObject(extraSearchParam);
        }
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getExtraSearch() {
        return extraSearch;
    }

    public void setExtraSearch(String extraSearch) {
        this.extraSearch = extraSearch;
    }

    public JSONObject getSearch() {
        return search;
    }

    public void setSearch(JSONObject search) {
        this.search = search;
    }
}
