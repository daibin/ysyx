package org.liufeng.course.pojo;

public class PoliceSignin {
	private String openId;
	private String name;
	private String policeId;
	private String lng;
	private String lat;
	private String bd09Lng;
	private String bd09Lat;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getBd09Lng() {
		return bd09Lng;
	}

	public void setBd09Lng(String bd09Lng) {
		this.bd09Lng = bd09Lng;
	}

	public String getBd09Lat() {
		return bd09Lat;
	}

	public void setBd09Lat(String bd09Lat) {
		this.bd09Lat = bd09Lat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}
}
