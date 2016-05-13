package org.liufeng.course.util;

import java.util.ArrayList;
import java.util.List;

import org.liufeng.course.message.resp.Article;

public class ArticleUtil {
	
	public static List<Article> makeArticleList() {
		// ��Ŀ�ĸ�·��
		String basePath = "http://whgawj.duapp.com/chapter-09/";
		List<Article> list = new ArrayList<Article>();
		
			Article article1 = new Article();
			Article article2 = new Article();
			Article article3 = new Article();
			Article article4 = new Article();
			Article article5 = new Article();
			Article article6 = new Article();
			
			article1.setTitle("��ӭ��ע�人�����������־�΢�����ں�");
			// P1��ʾ�û����͵�λ�ã�����ת���󣩣�p2��ʾ��ǰPOI����λ��
			article1.setUrl("http://www.whga.gov.cn/mobile/index.jsp");
			// ������ͼ�ĵ�ͼƬ����Ϊ��ͼ
			article1.setPicUrl(basePath + "images/321.jpg");
			list.add(article1);
			
			article2.setTitle("����Ⱥ�Ƭ�������Ĳ�");
			// P1��ʾ�û����͵�λ�ã�����ת���󣩣�p2��ʾ��ǰPOI����λ��
			article2.setUrl("http://www.whga.gov.cn/pawh/News.jsp?id=1201412151019401801");
			// ������ͼ�ĵ�ͼƬ����Ϊ��ͼ
			article2.setPicUrl(basePath + "images/2.jpg");
			list.add(article2);
			
			article3.setTitle("��С��̽��������");
			// P1��ʾ�û����͵�λ�ã�����ת���󣩣�p2��ʾ��ǰPOI����λ��
			article3.setUrl("http://www.whga.gov.cn/pawh/News.jsp?id=1201409161031063693");
			// ������ͼ�ĵ�ͼƬ����Ϊ��ͼ
			article3.setPicUrl(basePath + "images/3.jpg");
			list.add(article3);
			
			article4.setTitle("�����־�Ӣ�����");
			// P1��ʾ�û����͵�λ�ã�����ת���󣩣�p2��ʾ��ǰPOI����λ��
			article4.setUrl("http://www.whga.gov.cn/pawh/News.jsp?id=1201508060928560765");
			// ������ͼ�ĵ�ͼƬ����Ϊ��ͼ
			article4.setPicUrl(basePath + "images/4.jpg");
			list.add(article4);
			
			article5.setTitle("������ˮ�������");
			// P1��ʾ�û����͵�λ�ã�����ת���󣩣�p2��ʾ��ǰPOI����λ��
			article5.setUrl("http://www.whga.gov.cn/pawh/News.jsp?id=1201510211623451096");
			// ������ͼ�ĵ�ͼƬ����Ϊ��ͼ
			article5.setPicUrl(basePath + "images/5.jpg");
			list.add(article5);
			
			article6.setTitle("������ĸ�����ߡ�����");
			// P1��ʾ�û����͵�λ�ã�����ת���󣩣�p2��ʾ��ǰPOI����λ��
			article6.setUrl("http://www.whga.gov.cn/pawh/News.jsp?id=1201510211632181101");
			// ������ͼ�ĵ�ͼƬ����Ϊ��ͼ
			article6.setPicUrl(basePath + "images/6.jpg");
			list.add(article6);
		return list;
	}

}
