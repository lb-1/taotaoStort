package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;

@Controller
public class ContentController {

	/**
	 * 请求的url：/content/query/list
		参数：categoryId 分类id
		响应的数据：json数据
		{total:查询结果总数量,rows[{id:1,title:aaa,subtitle:bb,...}]}
		EasyUIDataGridResult
		描述商品数据List<TbContent>
		查询的表：tb_content 
	 * 
	 * 查询内容列表 并分页
	 */
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(int page, int rows, long categoryId){
		EasyUIDataGridResult result = contentService.getContent(page, rows, categoryId);
		
		return result;
	}
	
	/**
	 * 添加内容
	 * @param content
	 * @return
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content) {
		TaotaoResult result = contentService.addContent(content);
		return result;
	}

}
