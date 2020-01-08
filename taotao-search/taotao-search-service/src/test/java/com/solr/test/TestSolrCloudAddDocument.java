package com.solr.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * solr集群测试
 * @author 李博
 *
 */
public class TestSolrCloudAddDocument {

	
	@Test
	public void testCloud() throws Exception{
	
		//第一步 引入solrj相关的jar包  添加到工程中
		//第二步  创建SolrServer 对象  需要使用 CloudSolrServer子类 构造方法的参数是zookeeper的地址列表
		CloudSolrServer server = new 
				CloudSolrServer("192.168.25.128:2182,192.168.25.128:2183,192.168.25.128:2184");
		//第三步 需要设置 DefalutCollection属性
		server.setDefaultCollection("collection2");
		//第四步 创建 SolrInputDocument对象
		SolrInputDocument inputDocument = new SolrInputDocument();
		//第五步 把文档对象中添加域
		inputDocument.addField("id", "test001");
		inputDocument.addField("item_title", "测试商品");
		inputDocument.addField("item_price", "100");
		//第六步 把文档对象写入索引库
		server.add(inputDocument);
		//第七步 提交
		server.commit();
	}
}
