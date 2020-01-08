package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	/**
	 * 查询内容列表 
	 * 
	 */
	public EasyUIDataGridResult getContent(int page,int rows,long categoryId);

	/**
	 * 内容添加
	 * @param content
	 * @return
	 */
	public TaotaoResult addContent(TbContent content);
	
	/**
	 * 根据商品分类id查询内容列表 
	 * 查出数据把 数据放入到定义的用来接收内容pojo中
	 * 
	 */
	public List<TbContent> getContentList(long cid);
	
	
}
