package com.ssm.utils;

/**数据库分页工具类*/
public class PageUtil {
	private String pageSize = "10";//每页显示记录条数
    private String currentlyPage;//当前页
    private String total;//总数
    private int totalPage;//总页数 = total/pageSize
    private String json;//每页显示的数据
    
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getCurrentlyPage() {
		return currentlyPage;
	}
	public void setCurrentlyPage(String currentlyPage) {
		this.currentlyPage = currentlyPage;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage,String total,String pageSize) {
		if(total!=null && !"".equals(total)){
			Integer totalNum = Integer.parseInt(total);
			Integer pageSizeNum = Integer.parseInt(pageSize);
			totalPage = totalNum % pageSizeNum == 0 ? totalNum / pageSizeNum : totalNum / pageSizeNum + 1;
		}
		this.totalPage = totalPage;
	}
}