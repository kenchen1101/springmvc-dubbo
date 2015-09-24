package cn.rpc.mongo.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * 
 * @author Vincent.wang
 *
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -3223536164570808481L;

    /** 每页显示记录数(默认为10) */
    private int pageSize;

    /** 当前页数(默认为1) */
    private int currentPage;

    /** 总页数 */
    private long totalPage;

    /** 总记录数 */
    private long totalCount;

    /** 当前页对应的记录列表 */
    private List<T> resultList;

    /**
     * 计算总页数
     * 
     * @param totalCount
     *            总记录数
     * @param pageSize
     *            每页显示记录数
     * @return int 总页数
     */
    public static long computeTotalPage(final long totalCount, final int pageSize) {
        return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
    }

    /**
     * 计算开始页
     * 
     * @param currentPage
     *            当前页数
     * @param pageSize
     *            每页显示记录数
     * @return int 开始页
     */
    public static int computeStartPage(final int currentPage, final int pageSize) {
        return pageSize * (currentPage - 1);
    }

    /**
     * 计算结束页
     * 
     * @param currentPage
     *            当前页数
     * @param pageSize
     *            每页显示记录数
     * @return int 结束页
     */
    public static int computeEndPage(final int currentPage, final int pageSize) {
        return pageSize * currentPage;
    }

    public int getPageSize() {
        return pageSize <= 0 ? 10 : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage <= 0 ? 1 : currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

}
