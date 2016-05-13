package org.liufeng.course.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.liufeng.course.pojo.User;
import org.liufeng.course.pojo.UserLocation;

/**
 * Mysql���ݿ������
 * 
 * @author liufeng
 * @date 2013-11-19
 */
public class MySQLUtil {
	/**
	 * ��ȡMysql���ݿ�����
	 * 
	 * @return Connection
	 */
	private Connection getConn(HttpServletRequest request) {
		Connection conn = null;
		// ��request����ͷ��ȡ��IP���˿ڡ��û���������
		//String host = request.getHeader("BAE_ENV_ADDR_SQL_IP");
		//String port = request.getHeader("BAE_ENV_ADDR_SQL_PORT");
		/*String username = request.getHeader("BAE_ENV_AK");
		String password = request.getHeader("BAE_ENV_SK");*/
		String username = "b0a3dbd09d864b95a8d74b255a6d6bbd";//request.getHeader("BAE_ENV_AK");
		String password = "3a454e091752405ea5ded78c7bd21e8e";//request.getHeader("BAE_ENV_SK");
		// ���ݿ�����
		//String dbName = "ZOenTMfugqedHyCpkaMD";
		// JDBC URL
		//String url = String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);
		String url="jdbc:mysql://sqld.duapp.com:4050/ZOenTMfugqedHyCpkaMD";
		try {
			// ����MySQL����
			Class.forName("com.mysql.jdbc.Driver");
			// ��ȡ���ݿ�����
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * �����û�����λ��
	 * 
	 * @param request �������
	 * @param openId �û���OpenID
	 * @param lng �û����͵ľ���
	 * @param lat �û����͵�γ��
	 * @param bd09_lng �����ٶ�����ת����ľ���
	 * @param bd09_lat �����ٶ�����ת�����γ��
	 */
	public static void saveUserLocation(HttpServletRequest request, String openId, String lng, String lat, String bd09_lng, String bd09_lat) {
		String sql = "insert into user_location(open_id, lng, lat, bd09_lng, bd09_lat,date) values (?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = new MySQLUtil().getConn(request);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ps.setString(2, lng);
			ps.setString(3, lat);
			ps.setString(4, bd09_lng);
			ps.setString(5, bd09_lat);
			ps.setString(6, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
			ps.executeUpdate();
			// �ͷ���Դ
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�û����һ�η��͵ĵ���λ��
	 * 
	 * @param request �������
	 * @param openId �û���OpenID
	 * @return UserLocation
	 */
	public static UserLocation getLastLocation(HttpServletRequest request, String openId) {
		UserLocation userLocation = null;
		String sql = "select open_id, lng, lat, bd09_lng, bd09_lat,date from user_location where open_id=? order by id desc limit 0,1";
		try {
			Connection conn = new MySQLUtil().getConn(request);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userLocation = new UserLocation();
				userLocation.setOpenId(rs.getString("open_id"));
				userLocation.setLng(rs.getString("lng"));
				userLocation.setLat(rs.getString("lat"));
				userLocation.setBd09Lng(rs.getString("bd09_lng"));
				userLocation.setBd09Lat(rs.getString("bd09_lat"));
				userLocation.setDate(rs.getString("date"));
			}
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userLocation;
	}
	
	
	/**
	 * by:daibin 20160425
	 * @param request:open_id
	 * @param police_id:��Ա��
	 * @return
	 */
	public static User getOpenIdByPoliceId(HttpServletRequest request, String policeId){
		User user = null;
		String sql = "select open_id,name,id_card,police_id,is_police from user where police_id=? order by id desc limit 0,1";
		try {
			Connection conn = new MySQLUtil().getConn(request);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, policeId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user=new User();
				user.setOpenId(rs.getString("open_id"));
				user.setName(rs.getString("name"));
				user.setIdCard(rs.getString("id_card"));
				user.setPoliceId(rs.getString("police_id"));
				user.setIsPolice(rs.getString("is_police"));
			}
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	/**
	 * by:daibin 20160425
	 * @param request:open_id
	 * @param idCard:���֤��
	 * @return
	 */
	public static User getOpenIdByIdCard(HttpServletRequest request, String idCard){
		User user = null;
		String sql = "select open_id,name,id_card,police_id,is_police from user where id_card=? order by id desc limit 0,1";
		try {
			Connection conn = new MySQLUtil().getConn(request);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, idCard);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user=new User();
				user.setOpenId(rs.getString("open_id"));
				user.setName(rs.getString("name"));
				user.setIdCard(rs.getString("id_card"));
				user.setPoliceId(rs.getString("police_id"));
				user.setIsPolice(rs.getString("is_police"));
			}
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
