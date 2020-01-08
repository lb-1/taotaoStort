package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.JsonUtils;
import com.taotao.managerweb.util.FastDFSClient;

@Controller
public class PictureController {
	
	@Value("${TAOTAO_IMAGE_SERVICE_URL}")
	private String TAOTAO_IMAGE_SERVICE_URL;
	
	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String fileUpload(MultipartFile uploadFile){
		try{
			//取文件的扩展名
			String originalFilename = uploadFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			
			//创建一个FastDFS的客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/fast_dfs.conf");
			
			//执行上传处理
			String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			//拼接并返回url和ip地址  拼装完整的url
			String url = TAOTAO_IMAGE_SERVICE_URL+path;
			//返回map
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			return JsonUtils.objectToJson(result);

		}catch(Exception e){
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			return JsonUtils.objectToJson(result);

		}
		
	}
	
}
