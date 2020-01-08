package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	/**
	 * 请求的url：/item/save
		参数：TbItem item,String desc
		返回值：TaotaoResult

	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult saveItem(TbItem item,String desc){
		
		
		return itemService.saveItem(item, desc);
	}
	
}
