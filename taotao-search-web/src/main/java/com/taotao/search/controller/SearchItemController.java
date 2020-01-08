package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchItemService;

@Controller
public class SearchItemController {

//	@Value("ITEM_ROWS")
//	private Integer ITEM_ROWS;
	@Autowired
	private SearchItemService service;
	/**
	 * 根据条件搜索商品数据
	 * @param page
	 * @param queryString
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("search")
	public String search(Model model,@RequestParam(defaultValue="1") Integer page,@RequestParam(value="q")String queryString) throws Exception{
		//引入
		//注入
		//调用
		//处理乱码
		//queryString=new String(queryString.getBytes("iso8859-1"),"utf-8");
		queryString=new String(queryString.getBytes("iso-8859-1"),"utf-8");
		//设置数据传递到jsp
		SearchResult search = service.search(queryString, page, 60);
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages", search.getPageCount());
		model.addAttribute("itemList", search.getItemList());
		model.addAttribute("page", page);
		System.out.println(queryString);
		System.out.println(search.getPageCount());
		System.out.println(search.getItemList());
		System.out.println("----------------------------------------");
		System.out.println(search.toString());
		System.out.println("----------------------------------------");
		System.out.println(page);
		return "search";
	}
	
}
