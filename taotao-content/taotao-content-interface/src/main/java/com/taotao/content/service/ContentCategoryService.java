package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.util.TaotaoResult;

public interface ContentCategoryService {

	/**
	 * 内容分类表的接口
	 */
	public List<EasyUITreeNode> getContentCategoryList(Long parentId);
	
	/**
	 * 新增节点的接口
	 */
	public TaotaoResult addContentCategory(long parentId,String name);
	/**
	 * 重命名节点
	 */
	public TaotaoResult updadteContentCategoryName(long id,String name);

	/**
	 * 删除节点
	 */
	public TaotaoResult deleteContentCategory(long id);
}
