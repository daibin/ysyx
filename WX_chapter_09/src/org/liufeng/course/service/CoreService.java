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
 * ���ķ�����
 * 
 * @author liufeng
 * @date 2013-11-19
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXml = null;
		// Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = null;
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

				String content = requestMap.get("Content").trim();

				if (content.startsWith("����")) {
					String keyWord = content.replaceAll("����", "").trim();
					if (keyWord.equals("")) {
						respContent = getUsage();
					} else {
						// ��ȡ�û����һ�η��͵ĵ���λ��
						UserLocation location = MySQLUtil.getLastLocation(request, fromUserName);
						// δ��ȡ��
						if (null == location) {
							respContent = getUsage();
						} else {
							// ����ת���󣨾�ƫ�������������ܱ�POI
							List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord,location.getBd09Lng(),location.getBd09Lat());
							// δ������POI
							if (null == placeList || 0 == placeList.size()) {
								respContent = String.format("/�ѹ��������͵�λ�ø���δ��������%s����Ϣ��", keyWord);
							} else {
								List<Article> articleList = BaiduMapUtil.makeArticleList(placeList,location.getBd09Lng(),location.getBd09Lat());
								// �ظ�ͼ����Ϣ
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
				} else if (content.startsWith("��λ���֤")) {
					String keyWord = content.replaceAll("��λ���֤", "").trim();
					if (keyWord.equals("")) {
						respContent = "�����������·���";
					} else {
								User user = MySQLUtil.getOpenIdByIdCard(request, keyWord);
								UserLocation location = MySQLUtil.getLastLocation(request, user.getOpenId());
								respXml = MessageUtil.messageToXml(SerchUtil.serchPerson(user, location, fromUserName, toUserName));
							}
				} else if (content.startsWith("��λ��Ա��")) {
					String keyWord = content.replaceAll("��λ��Ա��", "").trim();
					if (keyWord.equals("")) {
						respContent = "�����������·���";
					} else {
								User user = MySQLUtil.getOpenIdByPoliceId(request, keyWord);
								UserLocation location = MySQLUtil.getLastLocation(request, user.getOpenId());
								respXml = MessageUtil.messageToXml(SerchUtil.serchPerson(user, location, fromUserName, toUserName));
							}
						}
				else{
					respContent="�����͵����ı���Ϣ";
				}
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				String picUrl = requestMap.get("PicUrl");
				respContent = "�����͵���ͼƬ��Ϣ��--" + picUrl + " --";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				String content = requestMap.get("Recognition").replace("��", "")
						.trim();
				// String mediaId=requestMap.get("MediaId");
				// String formatType=requestMap.get("Format");
				// respContent = "�����͵���������Ϣ��--"+mediaId+" --"+speechText+" --";
				if (content.equals("����")) {
					respContent = getUsage();
				}
				// �ܱ�����
				else if (content.startsWith("����")) {
					String keyWord = content.replaceAll("����", "").trim();
					// ��ȡ�û����һ�η��͵ĵ���λ��
					UserLocation location = MySQLUtil.getLastLocation(request,
							fromUserName);
					// δ��ȡ��
					if (null == location) {
						respContent = getUsage();
					} else {
						// ����ת���󣨾�ƫ�������������ܱ�POI
						List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord, location.getBd09Lng(),location.getBd09Lat());
						// δ������POI
						if (null == placeList || 0 == placeList.size()) {
							respContent = String.format("/�ѹ��������͵�λ�ø���δ��������%s����Ϣ��", keyWord);
						} else {
							List<Article> articleList = BaiduMapUtil.makeArticleList(placeList,location.getBd09Lng(),location.getBd09Lat());
							// �ظ�ͼ����Ϣ
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
					respContent = "�����͵���������Ϣ";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// �û����͵ľ�γ��
				String lng = requestMap.get("Location_Y");
				String lat = requestMap.get("Location_X");
				// ����ת����ľ�γ��
				String bd09Lng = null;
				String bd09Lat = null;
				// ���ýӿ�ת������
				UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
				if (null != userLocation) {
					bd09Lng = userLocation.getBd09Lng();
					bd09Lat = userLocation.getBd09Lat();
				}
				// �����û�����λ��
				MySQLUtil.saveUserLocation(request, fromUserName, lng, lat,
						bd09Lng, bd09Lat);

				StringBuffer buffer = new StringBuffer();
				// buffer.append("request--").append(request).append("\n");
				// buffer.append("fromUserName--").append(fromUserName).append("\n");
				// buffer.append("��Ѷ����").append(lng).append("\n").append("��Ѷγ��").append(lat).append("\n");
				// buffer.append("�ٶȾ���").append(bd09Lng).append("\n").append("�ٶ�γ��").append(bd09Lat);
				buffer.append("[���]").append("�ɹ���������λ�ã�").append("\n\n");
				buffer.append("���������������ؼ��ʻ��߷�����������ȡ�ܱ���Ϣ�ˣ����磺").append("\n");
				buffer.append("        ����������").append("\n");
				buffer.append("        ��������").append("\n");
				buffer.append("        ����������վ").append("\n");
				buffer.append("�����ԡ������������ֿ�ͷ��");
				respContent = buffer.toString();
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ��ע
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					// respContent = "лл���Ĺ�ע��";
					/**
					 * by:daibin 20160423
					 */
					List<Article> articleList = ArticleUtil.makeArticleList();
					// �ظ�ͼ����Ϣ
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
					// ���ýӿ�ת������
					UserLocation userLocation = BaiduMapUtil.convertCoord(
							longitude, latitude);
					if (null != userLocation) {
						bd09Lng = userLocation.getBd09Lng();
						bd09Lat = userLocation.getBd09Lat();
					}
					// �����û�����λ��
					MySQLUtil.saveUserLocation(request, fromUserName, longitude, latitude, bd09Lng, bd09Lat);
					/*respContent = "��Ŀǰ����λ��:\n����:" + bd09Lng + "\nγ��:" + bd09Lat + "\n��ȷ��:" + precision;*/
				}
			} else {
				respContent = getUsage();
			}
			if (null != respContent) {
				// �����ı���Ϣ������
				textMessage.setContent(respContent);
				// ���ı���Ϣ����ת����xml
				respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

	/**
	 * ��ע��ʾ��
	 * 
	 * @return
	 */
	private static String getSubscribeMsg() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("���Ƿ��й����������Ĵ���ATM������ľ�����").append("\n\n");
		buffer.append("���Ƿ��й�����������Ѱ��ʳ�����ֳ����ľ�����").append("\n\n");
		buffer.append("�ܱ�����Ϊ���ĳ��б��ݻ�����Ϊ���ṩרҵ���ܱ�����ָ�ϣ��ظ�����������ʼ����ɣ�");
		return buffer.toString();
	}

	/**
	 * ʹ��˵��
	 * 
	 * @return
	 */
	private static String getUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("�ܱ�����ʹ��˵��").append("\n\n");
		buffer.append("1�����͵���λ��").append("\n");
		buffer.append("������ڵײ��ġ�+����ť��ѡ��λ�á����㡰���͡�").append("\n\n");
		buffer.append("2��ָ���ؼ�������").append("\n");
		buffer.append("��ʽ������+�ؼ���\n���磺����ATM������KTV����������");
		return buffer.toString();
	}
}
