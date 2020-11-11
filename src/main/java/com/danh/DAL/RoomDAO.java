package com.danh.DAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.danh.model.Room;
import com.danh.model.RoomType;

public class RoomDAO extends BaseDAO<Room>{

	@Override
	public ArrayList<Room> all() {
		ArrayList<Room> rooms = new ArrayList<>();
		String query = "{ call GET_ACTIVE_ROOM }";
		ResultSet rs;
		try {
			CallableStatement stsm = connection.prepareCall(query);
			rs = stsm.executeQuery();
			while(rs.next()) {
				Room room = new Room();
				room.setRoomID(rs.getInt("ROOM_ID"));
				room.setRoomName(rs.getString("ROOM_NAME"));
				room.setRoomPrice(rs.getDouble("ROOM_PRICE"));
				RoomType rt = new RoomType();
				rt.setRtID(rs.getInt("RT_ID"));
				room.setRt(rt);
				room.setRoomImage64(rs.getString("ROOM_IMAGE_64"));
				rooms.add(room);
				}
		}
		catch (SQLException e) {
			Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		finally {
			closeConnection();
		}
		return rooms;
	}

	@Override
	public Room get(int id) {
		Room room = new Room();
		String query = "{ call GET_ROOM_BY_ID(?) }";
		ResultSet rs;
		try {
			CallableStatement stsm = connection.prepareCall(query);
			stsm.setInt(1, id);
			rs = stsm.executeQuery();
			if(rs.next()) {
				room.setRoomID(rs.getInt("ROOM_ID"));
				room.setRoomName(rs.getString("ROOM_NAME"));
				room.setRoomPrice(rs.getDouble("ROOM_PRICE"));
				room.setRoomNote(rs.getString("ROOM_NOTE"));
				room.setRoomStatus(rs.getInt("ROOM_STATUS"));
				RoomType rt = new RoomType();
				rt.setRtID(rs.getInt("RT_ID"));
				rt.setRtName(rs.getString("RT_NAME"));
				room.setRoomImage64(rs.getString("ROOM_IMAGE_64"));
				room.setRt(rt);
				}
		}
		catch (SQLException e) {
			Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		finally {
			closeConnection();
		}
		return room;
	}

	@Override
	public void insert(Room model) {
		String query = "{ call INSERT_ROOM(?,?,?,?,?,?,?) }";
		try {
			CallableStatement stsm = connection.prepareCall(query);
			stsm.setInt(1, model.getRoomID());
			stsm.setString(2, model.getRoomName());
			stsm.setInt(3, model.getRt().getRtID());
			stsm.setDouble(4, model.getRoomPrice());
			stsm.setInt(5, 1);
			stsm.setString(6, model.getRoomNote());
			stsm.setString(7, model.getRoomImage64());
			stsm.execute();
			
		}
		catch (SQLException e) {
			Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		finally {
			closeConnection();
		}
	}
	
	
	public void update(Room model) {
		String query = "{ call UPDATE_ROOM(?,?,?,?,?,?,?) }";
		try {
			CallableStatement stsm = connection.prepareCall(query);
			stsm.setInt(1, model.getRoomID());
			stsm.setString(2, model.getRoomName());
			stsm.setInt(3, model.getRt().getRtID());
			stsm.setDouble(4, model.getRoomPrice());
			stsm.setInt(5, model.getRoomStatus());
			stsm.setString(6, model.getRoomNote());
			stsm.setString(7, model.getRoomImage64());
			stsm.execute();
		}
		catch (SQLException e) {
			Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		finally {
//			closeConnection();
		}
	}
	
	public void delete(String id) {
		String query = "{ call DELETE_ROOM_LIST(?) }";
		try {
				CallableStatement stsm = connection.prepareCall(query);
				stsm.setString(1, id);
				stsm.execute();
				
		}
		catch (SQLException e) {
			Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		finally {
			closeConnection();
		}
	}
	
	public void closeConnection() {
		if(connection != null) {
			try {
//				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public int getRoomTotalRows() {
		int rows = 0;
		String query = "{ call GET_TOTAL_ROW_OF_ROOM }";
		ResultSet rs;
		try {
			CallableStatement stsm = connection.prepareCall(query);
			rs = stsm.executeQuery();
			if(rs.next()) {
				rows= rs.getInt("TOTAL_ROOM");
			}
		
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			
		}
		return rows;
	}
	public ArrayList<Room> getRoomFromTo(int page, int pageSize){
		int from = page * pageSize - (pageSize - 1);
        int to = page * pageSize;
        ArrayList<Room> rooms = new ArrayList<>();
		String query = "{ call GET_ROOM_FROM_TO(?,?) }";
		ResultSet rs;
		try {
			CallableStatement stsm = connection.prepareCall(query);
			stsm.setInt(1, from);
			stsm.setInt(2, to);
			rs = stsm.executeQuery();
			while(rs.next()) {
				Room room = new Room();
				room.setRoomID(rs.getInt("ROOM_ID"));
				room.setRoomName(rs.getString("ROOM_NAME"));
				room.setRoomPrice(rs.getDouble("ROOM_PRICE"));
				RoomType rt = new RoomType();
				rt.setRtID(rs.getInt("RT_ID"));
				room.setRt(rt);
				room.setRoomImage64(rs.getString("ROOM_IMAGE_64"));
				rooms.add(room);
				}
		}
		catch (SQLException e) {
			Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		finally {
			closeConnection();
		}
		return rooms;
	}
	
	public ArrayList<Room> searchRoom(String keyword){
        ArrayList<Room> rooms = new ArrayList<>();
		String query = "{ call SEARCH_ROOM(?) }";
		ResultSet rs;
		try {
			CallableStatement stsm = connection.prepareCall(query);
			stsm.setString(1, keyword);
			rs = stsm.executeQuery();
			while(rs.next()) {
				Room room = new Room();
				room.setRoomID(rs.getInt("ROOM_ID"));
				room.setRoomName(rs.getString("ROOM_NAME"));
				room.setRoomPrice(rs.getDouble("ROOM_PRICE"));
				RoomType rt = new RoomType();
				rt.setRtID(rs.getInt("RT_ID"));
				room.setRt(rt);
				room.setRoomImage64(rs.getString("ROOM_IMAGE_64"));
				rooms.add(room);
				}
		}
		catch (SQLException e) {
			Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		finally {
			closeConnection();
		}
		return rooms;
	}
	
//	public XSSFWorkbook exportToExcel() {
//		ArrayList<Room> rooms = all();
//			
//		XSSFWorkbook  workbook = new XSSFWorkbook();
//		XSSFSheet  sheet = workbook.createSheet("Room sheet"); 
//		// Create a CellStyle with the font  
//		CellStyle headerCellStyle = createStyleForHeader(workbook);
//		CellStyle titleCellStyle = createStyleForTitle(workbook);
//
//		        int rownum = 1;
//		        Cell cell;
//		        Row row;
//		        Row titleRow;
//		        Row signRow;
//		        titleRow = sheet.createRow(0);
//		        cell = titleRow.createCell(0);
//		        cell.setCellValue("Hotel Room Management");
//		        cell.setCellStyle(titleCellStyle);
//		        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
//		        
//		        row = sheet.createRow(rownum);
//		        
//		        // EmpNo
//		        cell = row.createCell(0);
//		        cell.setCellValue("Room ID");
//		        cell.setCellStyle(headerCellStyle);
//		        
//		        // EmpName
//		        cell = row.createCell(1);
//		        cell.setCellValue("Room Name");
//		        cell.setCellStyle(headerCellStyle);
//		        // Salary
//		        cell = row.createCell(2);
//		        cell.setCellValue("Room Type");
//		        cell.setCellStyle(headerCellStyle);
//		        // Grade
//		        cell = row.createCell(3);
//		        cell.setCellValue("Room Price");
//		        cell.setCellStyle(headerCellStyle);
//		        // Grade
//		        
//		        cell = row.createCell(4);
//		        cell.setCellValue("Room Status");
//		        cell.setCellStyle(headerCellStyle);
//		        // Bonus
//		        cell = row.createCell(5);
//		        cell.setCellValue("Room Note");
//		        cell.setCellStyle(headerCellStyle);
//		        for (Room r : rooms) {
//		            rownum++;
//		            row = sheet.createRow(rownum);
//		 
//		            // EmpNo (A)
//		            cell = row.createCell(0);
//		            cell.setCellValue(r.getRoomID());
//		            // EmpName (B)
//		            cell = row.createCell(1);
//		            cell.setCellValue(r.getRoomName());
//		            // Salary (C)
//		            cell = row.createCell(2);
//		            cell.setCellValue(r.getRt().getRtName());
//		            // Grade (D)
//		            cell = row.createCell(3);
//		            cell.setCellValue(r.getRoomPrice());
//		            // Bonus (E)
//		            cell = row.createCell(4);
//		            cell.setCellValue(r.getRoomStatus());
//		            // Bonus (E)
//		            cell = row.createCell(5);
//		            cell.setCellValue(r.getRoomNote());
//		            // Bonus (E)
//		           
//		        }
//		        
//		        titleRow = sheet.createRow(rownum+2);
//		        cell = titleRow.createCell(5);
//		        cell.setCellValue("Signature");
//		        cell.setCellStyle(titleCellStyle);
//		        for(int i=0;i<=5;i++) {
//		        	sheet.autoSizeColumn(i);
//		        }
//		        
//		        System.out.println("number of row"+rownum);
//
//		        
//		     return workbook;  
//		 
//	}
//	
//	private  CellStyle createStyleForHeader(XSSFWorkbook workbook) {
//		Font font = workbook.createFont();
//		font.setBold(true);  
//		font.setFontHeightInPoints((short) 13);  
//		font.setColor(IndexedColors.BLUE.getIndex());
//        CellStyle style = workbook.createCellStyle();
//        style.setFont(font);
//        return style;
//    }
//	
//	private  CellStyle createStyleForTitle(XSSFWorkbook workbook) {
//		Font font = workbook.createFont();
//		font.setBold(true);  
//		font.setFontHeightInPoints((short) 15);  
//		font.setColor(IndexedColors.AQUA.getIndex());
//		
//        CellStyle style = workbook.createCellStyle();
//        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setFont(font);
//        return style;
//    }
}
