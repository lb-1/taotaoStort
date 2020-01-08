package com.taotao.search.mapper;

import java.util.List;

import com.taotao.common.pojo.SearchItem;

/**
 * 定义mapper关联查询三张搜索时的商品数据
 * 
 * @author 李博
 *
 */
public interface SerachItemMapper {
	
	//查询所有商品的数据
	public List<SearchItem> getSearchItemList();
	
	//根据商品的id查询商品的数据
	public SearchItem getSearchItemByid(Long id);
	
}
