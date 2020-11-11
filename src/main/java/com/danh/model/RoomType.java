package com.danh.model;

public class RoomType extends BaseModel {
	private int rtID;
	private String rtName;
	private int rtStatus;
	public RoomType() {
		super();
	}
	public RoomType(int rtID, String rtName, int rtStatus) {
		super();
		this.rtID = rtID;
		this.rtName = rtName;
		this.rtStatus = rtStatus;
	}
	public int getRtID() {
		return rtID;
	}
	public void setRtID(int rtID) {
		this.rtID = rtID;
	}
	public String getRtName() {
		return rtName;
	}
	public void setRtName(String rtName) {
		this.rtName = rtName;
	}
	public int getRtStatus() {
		return rtStatus;
	}
	public void setRtStatus(int rtStatus) {
		this.rtStatus = rtStatus;
	}
	@Override
	public String toString() {
		return "RoomType [rtID=" + rtID + ", rtName=" + rtName + ", rtStatus=" + rtStatus + "]";
	}
	
}
