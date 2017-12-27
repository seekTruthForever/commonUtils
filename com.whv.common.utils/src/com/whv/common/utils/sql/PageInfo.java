package com.whv.common.utils.sql;
/**
 * ��ҳ��Ϣ��
 * @author huawei
 *
 */
public class PageInfo {
	/** �ܼ�¼���� */
	private Integer totalCount;
	/** ��ҳ�� */
	private Integer totalPage;
	/** ÿҳ��������¼ */
	private Integer pageSize;
	/** ��ǰ�ڼ�ҳ */
	private Integer currPage;
	public PageInfo() {}
	public PageInfo(Integer pageSize,Integer currPage) {
		super();
		this.pageSize = pageSize;
		this.currPage = currPage;
	}
	public Integer getTotalCount() {
		return totalCount==null?0:totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		totalPage = 0;
		int ps = getPageSize();
		if(ps==0) return 0;
		int tc = getTotalCount();
		if(tc%ps==0) {
			totalPage = tc/ps;
		}else {
			totalPage = tc/ps + 1;
		}
		return totalPage;
	}
	
	public Integer getPageSize() {
		return pageSize==null?10:pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrPage() {
		return currPage==null?1:currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

}
