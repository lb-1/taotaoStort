package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 查询商品列表的返回的数据类
 * 包含列 根据相应的json数据
 * @author 李博
 *
 */
public class EasyUIDataGridResult implements Serializable {
	
	//总条数
	private Integer total;
	private List rows; //返回商品的集合
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
