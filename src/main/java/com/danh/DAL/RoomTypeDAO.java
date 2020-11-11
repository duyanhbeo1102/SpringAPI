package com.danh.DAL;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.danh.model.RoomType;

public class RoomTypeDAO extends BaseDAO<RoomType>{

	@Override
	public ArrayList<RoomType> all() {
		ArrayList<RoomType> roomTypes = new ArrayList<>();
		String query = "{ call GET_ACT_RT }";
		ResultSet rs;
		try {
			CallableStatement stsm = connection.prepareCall(query);
			rs = stsm.executeQuery();
			while(rs.next()) {
				RoomType roomType = new RoomType();
				roomType.setRtID(rs.getInt("RT_ID"));
				roomType.setRtName(rs.getString("RT_NAME"));
				roomTypes.add(roomType);
				}
		}
		catch (SQLException e) {
			Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return roomTypes;
	}

	@Override
	public RoomType get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(RoomType model) {
		// TODO Auto-generated method stub
		
	}

}
