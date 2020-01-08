package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.TaotaoResult;
import com.taotao.search.service.SearchItemService;

@Controller
public class ImportAllItem {

	@Autowired
	private SearchItemService service;
	
	/**
	 * 导入所有的数据到索引库中
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index/importAll")
	@ResponseBody
	public TaotaoResult importAllItems() throws Exception{
		
		//引入服务
		//注入服务
		//调用
		return service.importAllSearchItems();
	}
}
