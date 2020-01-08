package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.service.ItemService;

@Controller
public class PageController {
	@Autowired
	private ItemService itemService;
	/**
	 * 展示首页
	 */
	@RequestMapping("/")
	public String showIndex(){
		
		return "index";
	}
	/**
	 * 展示菜单页面
	 */
	@RequestMapping("/{page}")
	public String showItemList(@PathVariable String page){
		
		return page;
	}
	/**
	 	1、初始化表格请求的url：/item/list
		2、Datagrid默认请求参数：
			1、page：当前的页码，从1开始。
			2、rows：每页显示的记录数。
		3、响应的数据：json数据。EasyUIDataGridResult
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		
		return result;
	}
	
	
}
