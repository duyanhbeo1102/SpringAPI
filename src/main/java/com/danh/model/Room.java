package com.danh.model;

public class Room extends BaseModel{
	private int roomID;
	private String roomName;
	private RoomType rt;
	private double roomPrice;
	private int roomStatus;
	private String roomNote;
	private String roomImage64;
	public Room() {
		super();
	}
	public Room(int roomID, String roomName, RoomType rt, double roomPrice, int roomStatus, String roomNote,
			String roomImage64) {
		super();
		this.roomID = roomID;
		this.roomName = roomName;
		this.rt = rt;
		this.roomPrice = roomPrice;
		this.roomStatus = roomStatus;
		this.roomNote = roomNote;
		this.roomImage64 = roomImage64;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public RoomType getRt() {
		return rt;
	}
	public void setRt(RoomType rt) {
		this.rt = rt;
	}
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public int getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(int roomStatus) {
		this.roomStatus = roomStatus;
	}
	public String getRoomNote() {
		return roomNote;
	}
	public void setRoomNote(String roomNote) {
		this.roomNote = roomNote;
	}
	public String getRoomImage64() {
		return roomImage64;
	}
	public void setRoomImage64(String roomImage64) {
		this.roomImage64 = roomImage64;
	}
	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", roomName=" + roomName + ", rt=" + rt + ", roomPrice=" + roomPrice
				+ ", roomStatus=" + roomStatus + ", roomNote=" + roomNote + ", roomImage64=" + roomImage64 + "]";
	}
	
}
