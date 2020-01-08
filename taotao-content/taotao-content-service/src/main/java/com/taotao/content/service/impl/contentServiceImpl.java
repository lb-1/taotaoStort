package com.taotao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.content.service.Jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;

@Service
public class contentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	/**
	 *根据 内容分类id  分页查询商品内容
	 */
	@Override
	public EasyUIDataGridResult getContent(int page, int rows, long categoryId) {
		
		//设置分页信息
		PageHelper.startPage(page, rows);

		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		//创建返回结果对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal((int)pageInfo.getTotal());
		result.setRows(list);

		return result;
	}
	
	/**
	 * 添加内容
	 */
	@Override
	public TaotaoResult addContent(TbContent content) {
		//补全属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入数据
		contentMapper.insert(content);
		try {
			//缓存同步
			jedisClient.hdel("CONTENT_KEY", content.getCategoryId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}

	@Override
	public List<TbContent> getContentList(long cid) {
		
		try {
			//缓存查询
			String json = jedisClient.hget("CONTENT_KEY", cid + "");
			//判断json是否为空
			if (StringUtils.isNotBlank(json)) {
				//不为空
				//把json转换成list
				//有缓存
				System.out.println("有缓存。。。。");
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		//根据cid查询内容列表
		TbContentExample example = new TbContentExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		
		try {
			//在返回数据之前添加缓存  如果没有缓存
			System.out.println("没有缓存");
			jedisClient.hset("CONTENT_KEY", cid + "", JsonUtils.objectToJson(list));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
