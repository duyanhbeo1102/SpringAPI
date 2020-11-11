package com.danh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danh.DAL.RoomDAO;
import com.danh.model.Room;
@Service("roomService")
@Transactional
public class RoomService implements Services<Room>{

	@Override
	public Room findById(int id) {
		RoomDAO roomDB = new RoomDAO();
		Room room = roomDB.get(id);
		return room;
	}

	@Override
	public Room findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Room model) {
		RoomDAO roomDB = new RoomDAO();
		roomDB.insert(model);
	}

	@Override
	public void update(Room model) {
		RoomDAO roomDAO = new RoomDAO();
		roomDAO.update(model);
	}

	@Override
	public void deleteById(int id) {
		RoomDAO roomDAO = new RoomDAO();
		roomDAO.delete(Integer.toString(id));
	}

	@Override
	public List<Room> getAll() {
		RoomDAO roomDAO = new RoomDAO();
		ArrayList<Room> rooms = roomDAO.all();
		return rooms;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExist(Room model) {
		RoomDAO roomDB = new RoomDAO();
		Room room = roomDB.get(model.getRoomID());
		if(room.getRoomID() != 0) {
			return true;
		}
		else {
			return false;
		}
	}


}
