package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	/**
	 * 查询商品列表
	 * 参数 int page int rows
	 * 业务逻辑 查询所有商品列表 进行分页处理
	 * 返回值  EasyUIDataGridResult
	 */
	public EasyUIDataGridResult getItemList(int page,int rows);
	
	/**
	 * 根据商品的基础数据 和商品的描述信息 插入商品（插入商品基础表  和商品描述表）
	 */
	public TaotaoResult saveItem(TbItem item,String desc);
	
}
