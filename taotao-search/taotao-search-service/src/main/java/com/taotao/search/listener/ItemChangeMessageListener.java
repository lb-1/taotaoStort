package com.taotao.search.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.search.service.SearchItemService;

/**
 * 接收消息的监听器
 * @author 李博
 *
 */
public class ItemChangeMessageListener implements MessageListener  {
	
	//注入service  直接调用方法更新
	@Autowired
	private SearchItemService searchItemService;

	@Override
	public void onMessage(Message message) {
		System.out.println("我收到消息了");
		try {
			TextMessage textMessage = null;
			Long itemId = null; 
			//取商品id
			if (message instanceof TextMessage) {
				textMessage = (TextMessage) message;
				itemId = Long.parseLong(textMessage.getText());
			}
			//向索引库添加文档
			System.out.println("获取的商品id为"+itemId);
			System.out.println("索引库在更新");
			searchItemService.updateItemById(itemId);						
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
