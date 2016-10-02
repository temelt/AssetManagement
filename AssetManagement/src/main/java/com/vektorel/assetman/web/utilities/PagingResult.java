package com.vektorel.assetman.web.utilities;

import java.util.List;

public class PagingResult {
	
	public PagingResult() {
		// TODO Auto-generated constructor stub
	}
	
	
	public PagingResult(List list, int rowCount) {
		super();
		this.list = list;
		this.rowCount = rowCount;
	}


	List list;
	int rowCount;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

}
