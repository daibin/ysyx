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
 * Mysql数据库操作类
 * 
 * @author liufeng
 * @date 2013-11-19
 */
public class MySQLUtil {
	/**
	 * 获取Mysql数据库连接
	 * 
	 * @return Connection
	 */
	private Connection getConn(HttpServletRequest request) {
		Connection conn = null;
		// 从request请求头中取出IP、端口、用户名和密码
		//String host = request.getHeader("BAE_ENV_ADDR_SQL_IP");
		//String port = request.getHeader("BAE_ENV_ADDR_SQL_PORT");
		/*String username = request.getHeader("BAE_ENV_AK");
		String password = request.getHeader("BAE_ENV_SK");*/
		String username = "b0a3dbd09d864b95a8d74b255a6d6bbd";//request.getHeader("BAE_ENV_AK");
		String password = "3a454e091752405ea5ded78c7bd21e8e";//request.getHeader("BAE_ENV_SK");
		// 数据库名称
		//String dbName = "ZOenTMfugqedHyCpkaMD";
		// JDBC URL
		//String url = String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);
		String url="jdbc:mysql://sqld.duapp.com:4050/ZOenTMfugqedHyCpkaMD";
		try {
			// 加载MySQL驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取数据库连接
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 保存用户地理位置
	 * 
	 * @param request 请求对象
	 * @param openId 用户的OpenID
	 * @param lng 用户发送的经度
	 * @param lat 用户发送的纬度
	 * @param bd09_lng 经过百度坐标转换后的经度
	 * @param bd09_lat 经过百度坐标转换后的纬度
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
			// 释放资源
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户最后一次发送的地理位置
	 * 
	 * @param request 请求对象
	 * @param openId 用户的OpenID
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
			// 释放资源
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
	 * @param police_id:警员号
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
			// 释放资源
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
	 * @param idCard:身份证号
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
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
