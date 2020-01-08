package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	
	/**
	 * 根据父节点id，查询该节点的子类目列表
	 */
	public List<EasyUITreeNode> getItemCatList(Long parentId);
}
