package cn.itcast.bos.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * luopa 在 2017/3/16 创建.
 * 分页显示
 */
public class PageBean {
    private Integer currentPage;//当前页数
    private Integer pageSize;//每页显示数据
    private List rows;//集合数据
    private Integer total;//总记录数
    private DetachedCriteria detachedCriteria;//离线查询

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }
}
