package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.service.TestService;

@Controller
public class TestController {
	
	//注入service接口
	@Autowired
	private TestService testService;
	
	/**
	 * 测试dubbo是否正常
	 * 
	 */
	@RequestMapping("/test/query")
	@ResponseBody
	public String query(){
		
		return testService.queryNow();
	}
	
}
