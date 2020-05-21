package com.cjl.common.util.result;

import java.util.List;

/**
 * @author: 小崔
 * @create: 2020-05-15 17:53
 * @Description:
 */
public class PageModel<T> {
    //当前页
    private Integer pageIndex;

    //默认的每页的数据
    private Integer pageSize;

    //总页数
    private Integer pageNums;

    //总数据条数
    private Integer total;

    private Integer start;

    //集合数据
    private List<T> data;

    private Object statistical;

    public Integer getPageIndex() {
	return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
	this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
	return pageSize;
    }

    public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
    }

    public Integer getPageNums() {
	return pageNums!=null?calculatePageNums():null;
    }

    public void setPageNums(Integer pageNums) {
	this.pageNums = pageNums;
    }

    public Integer getTotal() {
	return total;
    }

    public void setTotal(Integer total) {
	this.total = total;
    }

    public List<T> getData() {
	return data;
    }

    public void setData(List<T> data) {
	this.data = data;
    }

    public int calculatePageNums() {
	if(total==null) {
	    return 0;
	} else {
	    return total < pageSize ? 1 : total % pageSize > 0 ? (total / pageSize) + 1 : (total / pageSize);
	}
    }

    public Integer getStart() {
	if(this.pageIndex==null || this.pageSize==null ){
	    return start;
	}

	this.start = (this.pageIndex - 1) * this.pageSize.intValue();
	return start;
    }

    public Object getStatistical() {
	return statistical;
    }

    public void setStatistical(Object statistical) {
	this.statistical = statistical;
    }

    public void init(){
	if(null  == getPageSize() || 1 > getPageSize()){
	    setPageSize(10);
	}
	if(null == getPageIndex() || 1 > getPageIndex()){
	    setPageIndex(1);
	}
    }
}
