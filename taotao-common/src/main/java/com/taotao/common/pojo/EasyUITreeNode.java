package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * 
 * 商品类目树
 * @author 李博
 *
 */
public class EasyUITreeNode implements Serializable{

	private long id; //父节点的id值
	private String text; //节点名字
	private String state;//状态 如果节点下有子节点 “close” 如果没有子节点“open”
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
