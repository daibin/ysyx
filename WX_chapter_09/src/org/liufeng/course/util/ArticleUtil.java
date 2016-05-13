package org.liufeng.course.util;

import java.util.ArrayList;
import java.util.List;

import org.liufeng.course.message.resp.Article;

public class ArticleUtil {
	
	public static List<Article> makeArticleList() {
		// 项目的根路径
		String basePath = "http://whgawj.duapp.com/chapter-09/";
		List<Article> list = new ArrayList<Article>();
		
			Article article1 = new Article();
			Article article2 = new Article();
			Article article3 = new Article();
			Article article4 = new Article();
			Article article5 = new Article();
			Article article6 = new Article();
			
			article1.setTitle("欢迎关注武汉东西湖公安分局微警公众号");
			// P1表示用户发送的位置（坐标转换后），p2表示当前POI所在位置
			article1.setUrl("http://www.whga.gov.cn/mobile/index.jsp");
			// 将首条图文的图片设置为大图
			article1.setPicUrl(basePath + "images/321.jpg");
			list.add(article1);
			
			article2.setTitle("“光谷好片警”张文昌");
			// P1表示用户发送的位置（坐标转换后），p2表示当前POI所在位置
			article2.setUrl("http://www.whga.gov.cn/pawh/News.jsp?id=1201412151019401801");
			// 将首条图文的图片设置为大图
			article2.setPicUrl(basePath + "images/2.jpg");
			list.add(article2);
			
			article3.setTitle("“小案探长”付浩");
			// P1表示用户发送的位置（坐标转换后），p2表示当前POI所在位置
			article3.setUrl("http://www.whga.gov.cn/pawh/News.jsp?id=1201409161031063693");
			// 将首条图文的图片设置为大图
			article3.setPicUrl(basePath + "images/3.jpg");
			list.add(article3);
			
			article4.setTitle("“反恐精英”刘炜");
			// P1表示用户发送的位置（坐标转换后），p2表示当前POI所在位置
			article4.setUrl("http://www.whga.gov.cn/pawh/News.jsp?id=1201508060928560765");
			// 将首条图文的图片设置为大图
			article4.setPicUrl(basePath + "images/4.jpg");
			list.add(article4);
			
			article5.setTitle("“最美水警”李晨蕾");
			// P1表示用户发送的位置（坐标转换后），p2表示当前POI所在位置
			article5.setUrl("http://www.whga.gov.cn/pawh/News.jsp?id=1201510211623451096");
			// 将首条图文的图片设置为大图
			article5.setPicUrl(basePath + "images/5.jpg");
			list.add(article5);
			
			article6.setTitle("“警务改革践行者”吴鹏");
			// P1表示用户发送的位置（坐标转换后），p2表示当前POI所在位置
			article6.setUrl("http://www.whga.gov.cn/pawh/News.jsp?id=1201510211632181101");
			// 将首条图文的图片设置为大图
			article6.setPicUrl(basePath + "images/6.jpg");
			list.add(article6);
		return list;
	}

}
