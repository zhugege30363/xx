package page;

import java.io.Serializable;

public class Pager implements Serializable{
	private Long pageSize=5L;
	private Long currentPage=1L;
	private Long totalSize;
	private Long totalPage;
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public Long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}
	public Long getTotalPage() {
		if (totalSize%pageSize==0) {
			return totalSize/pageSize;
		} else {
			return totalSize/pageSize+1;
		}
	
		
	}
	
	

}
