package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.util.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AD1Node;

@Controller
public class IndexController {

	@Autowired
	private ContentService contentService;
	
	/**
	 * 展示首页
	 */
	@RequestMapping("/index")
	public String showIndex(Model model){
		//调用方法 查询tbcontent 的列表
		List<TbContent> list = contentService.getContentList(89l);
		//转换成ad1node列表
		List<AD1Node> list2 = new ArrayList<AD1Node>();
		for (TbContent tbContent : list) {
			AD1Node node = new AD1Node();
			node.setAlt(tbContent.getSubTitle());
			node.setHref(tbContent.getUrl());
			node.setSrc(tbContent.getPic());
			node.setSrcB(tbContent.getPic2());
			node.setHeight("240");
			node.setHeightB("240");
			node.setWidth("670");
			node.setWidthB("550");
			list2.add(node);
		}
		
		model.addAttribute("ad1", JsonUtils.objectToJson(list2));
		return "index";
	}
}
