package org.liufeng.course.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.liufeng.course.message.resp.Article;
import org.liufeng.course.message.resp.NewsMessage;
import org.liufeng.course.pojo.User;
import org.liufeng.course.pojo.UserLocation;

public class SerchUtil {

	public static NewsMessage serchPerson(User user,UserLocation location,String fromUserName, String toUserName){
		String bd09lng = location.getBd09Lng();
		String bd09lat = location.getBd09Lat();
		String date=location.getDate();
		String name=user.getName();
		//respContent = name+date+"所处的坐标是:\n经度：" + bd09lng + "\n纬度："+ bd09lat;
		
		String convertUrl = "http://api.map.baidu.com/staticimage/v2?ak=KvCjP5IB8fAg0ASojiGFsNpsqaeSusV2&width=500&height=250&center={x},{y}&labels={x},{y}&zoom=16&labelStyles={z},1,14,0xffffff,0x000fff,1";
		convertUrl = convertUrl.replace("{x}", bd09lng);
		convertUrl = convertUrl.replace("{y}", bd09lat);
		convertUrl = convertUrl.replace("{z}", name);
		List<Article> list = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle(name+date+"所处的位置是:");
		article.setUrl("");
		article.setPicUrl(convertUrl);
		list.add(article);
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticles(list);
		newsMessage.setArticleCount(list.size());
		return newsMessage;
	}
}
