package org.liufeng.course.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.liufeng.course.message.resp.Article;
import org.liufeng.course.message.resp.NewsMessage;
import org.liufeng.course.message.resp.TextMessage;
import org.liufeng.course.pojo.BaiduPlace;
import org.liufeng.course.pojo.User;
import org.liufeng.course.pojo.UserLocation;
import org.liufeng.course.util.ArticleUtil;
import org.liufeng.course.util.BaiduMapUtil;
import org.liufeng.course.util.MessageUtil;
import org.liufeng.course.util.MySQLUtil;
import org.liufeng.course.util.SerchUtil;

/**
 * 核心服务类
 * 
 * @author liufeng
 * @date 2013-11-19
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

				String content = requestMap.get("Content").trim();

				if (content.startsWith("附近")) {
					String keyWord = content.replaceAll("附近", "").trim();
					if (keyWord.equals("")) {
						respContent = getUsage();
					} else {
						// 获取用户最后一次发送的地理位置
						UserLocation location = MySQLUtil.getLastLocation(request, fromUserName);
						// 未获取到
						if (null == location) {
							respContent = getUsage();
						} else {
							// 根据转换后（纠偏）的坐标搜索周边POI
							List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord,location.getBd09Lng(),location.getBd09Lat());
							// 未搜索到POI
							if (null == placeList || 0 == placeList.size()) {
								respContent = String.format("/难过，您发送的位置附近未搜索到“%s”信息！", keyWord);
							} else {
								List<Article> articleList = BaiduMapUtil.makeArticleList(placeList,location.getBd09Lng(),location.getBd09Lat());
								// 回复图文消息
								NewsMessage newsMessage = new NewsMessage();
								newsMessage.setToUserName(fromUserName);
								newsMessage.setFromUserName(toUserName);
								newsMessage.setCreateTime(new Date().getTime());
								newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
								newsMessage.setArticles(articleList);
								newsMessage.setArticleCount(articleList.size());
								respXml = MessageUtil.messageToXml(newsMessage);
							}
						}
					}
				} else if (content.startsWith("定位身份证")) {
					String keyWord = content.replaceAll("定位身份证", "").trim();
					if (keyWord.equals("")) {
						respContent = "请检查信令重新发送";
					} else {
								User user = MySQLUtil.getOpenIdByIdCard(request, keyWord);
								UserLocation location = MySQLUtil.getLastLocation(request, user.getOpenId());
								respXml = MessageUtil.messageToXml(SerchUtil.serchPerson(user, location, fromUserName, toUserName));
							}
				} else if (content.startsWith("定位警员号")) {
					String keyWord = content.replaceAll("定位警员号", "").trim();
					if (keyWord.equals("")) {
						respContent = "请检查信令重新发送";
					} else {
								User user = MySQLUtil.getOpenIdByPoliceId(request, keyWord);
								UserLocation location = MySQLUtil.getLastLocation(request, user.getOpenId());
								respXml = MessageUtil.messageToXml(SerchUtil.serchPerson(user, location, fromUserName, toUserName));
							}
						}
				else{
					respContent="您发送的是文本信息";
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				String picUrl = requestMap.get("PicUrl");
				respContent = "您发送的是图片消息！--" + picUrl + " --";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				String content = requestMap.get("Recognition").replace("！", "")
						.trim();
				// String mediaId=requestMap.get("MediaId");
				// String formatType=requestMap.get("Format");
				// respContent = "您发送的是语音消息！--"+mediaId+" --"+speechText+" --";
				if (content.equals("附近")) {
					respContent = getUsage();
				}
				// 周边搜索
				else if (content.startsWith("附近")) {
					String keyWord = content.replaceAll("附近", "").trim();
					// 获取用户最后一次发送的地理位置
					UserLocation location = MySQLUtil.getLastLocation(request,
							fromUserName);
					// 未获取到
					if (null == location) {
						respContent = getUsage();
					} else {
						// 根据转换后（纠偏）的坐标搜索周边POI
						List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord, location.getBd09Lng(),location.getBd09Lat());
						// 未搜索到POI
						if (null == placeList || 0 == placeList.size()) {
							respContent = String.format("/难过，您发送的位置附近未搜索到“%s”信息！", keyWord);
						} else {
							List<Article> articleList = BaiduMapUtil.makeArticleList(placeList,location.getBd09Lng(),location.getBd09Lat());
							// 回复图文消息
							NewsMessage newsMessage = new NewsMessage();
							newsMessage.setToUserName(fromUserName);
							newsMessage.setFromUserName(toUserName);
							newsMessage.setCreateTime(new Date().getTime());
							newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
							newsMessage.setArticles(articleList);
							newsMessage.setArticleCount(articleList.size());
							respXml = MessageUtil.messageToXml(newsMessage);
						}
					}
				} else
					respContent = "您发送的是语音消息";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// 用户发送的经纬度
				String lng = requestMap.get("Location_Y");
				String lat = requestMap.get("Location_X");
				// 坐标转换后的经纬度
				String bd09Lng = null;
				String bd09Lat = null;
				// 调用接口转换坐标
				UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
				if (null != userLocation) {
					bd09Lng = userLocation.getBd09Lng();
					bd09Lat = userLocation.getBd09Lat();
				}
				// 保存用户地理位置
				MySQLUtil.saveUserLocation(request, fromUserName, lng, lat,
						bd09Lng, bd09Lat);

				StringBuffer buffer = new StringBuffer();
				// buffer.append("request--").append(request).append("\n");
				// buffer.append("fromUserName--").append(fromUserName).append("\n");
				// buffer.append("腾讯经度").append(lng).append("\n").append("腾讯纬度").append(lat).append("\n");
				// buffer.append("百度经度").append(bd09Lng).append("\n").append("百度纬度").append(bd09Lat);
				buffer.append("[愉快]").append("成功接收您的位置！").append("\n\n");
				buffer.append("您可以输入搜索关键词或者发送语音来获取周边信息了，例如：").append("\n");
				buffer.append("        附近公安局").append("\n");
				buffer.append("        附近超市").append("\n");
				buffer.append("        附近公交车站").append("\n");
				buffer.append("必须以“附近”两个字开头！");
				respContent = buffer.toString();
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					// respContent = "谢谢您的关注！";
					/**
					 * by:daibin 20160423
					 */
					List<Article> articleList = ArticleUtil.makeArticleList();
					// 回复图文消息
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setArticles(articleList);
					newsMessage.setArticleCount(articleList.size());
					respXml = MessageUtil.messageToXml(newsMessage);
					// respContent = getSubscribeMsg();
				}
				/**
				 * by:daibin 20160424
				 */
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// tx-Longitude
					String longitude = requestMap.get("Longitude");
					// tx-Latitude
					String latitude = requestMap.get("Latitude");
					String precision = requestMap.get("Precision");
					// bd-Longitude
					String bd09Lng = null;
					// bd-Latitude
					String bd09Lat = null;
					// 调用接口转换坐标
					UserLocation userLocation = BaiduMapUtil.convertCoord(
							longitude, latitude);
					if (null != userLocation) {
						bd09Lng = userLocation.getBd09Lng();
						bd09Lat = userLocation.getBd09Lat();
					}
					// 保存用户地理位置
					MySQLUtil.saveUserLocation(request, fromUserName, longitude, latitude, bd09Lng, bd09Lat);
					/*respContent = "您目前所在位置:\n经度:" + bd09Lng + "\n纬度:" + bd09Lat + "\n精确度:" + precision;*/
				}
			} else {
				respContent = getUsage();
			}
			if (null != respContent) {
				// 设置文本消息的内容
				textMessage.setContent(respContent);
				// 将文本消息对象转换成xml
				respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

	/**
	 * 关注提示语
	 * 
	 * @return
	 */
	private static String getSubscribeMsg() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("您是否有过出门在外四处找ATM或厕所的经历？").append("\n\n");
		buffer.append("您是否有过出差在外搜寻美食或娱乐场所的经历？").append("\n\n");
		buffer.append("周边搜索为您的出行保驾护航，为您提供专业的周边生活指南，回复“附近”开始体验吧！");
		return buffer.toString();
	}

	/**
	 * 使用说明
	 * 
	 * @return
	 */
	private static String getUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("周边搜索使用说明").append("\n\n");
		buffer.append("1）发送地理位置").append("\n");
		buffer.append("点击窗口底部的“+”按钮，选择“位置”，点“发送”").append("\n\n");
		buffer.append("2）指定关键词搜索").append("\n");
		buffer.append("格式：附近+关键词\n例如：附近ATM、附近KTV、附近厕所");
		return buffer.toString();
	}
}
