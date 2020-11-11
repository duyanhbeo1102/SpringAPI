package com.danh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.danh.model.Room;
import com.danh.service.RoomService;

@RestController
public class RoomRestController {
	@Autowired
    RoomService roomService;
	
	@RequestMapping(value = "/room", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> getAllRoom() {
        List<Room> rooms = roomService.getAll();
        if (rooms.isEmpty()) {
            return new ResponseEntity<List<Room>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/room/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> getRoom(@PathVariable("id") int id) {
        Room room = roomService.findById(id);
        if (room == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Room>(room, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/room", method = RequestMethod.POST)
    public ResponseEntity<Void> insertRoom(@RequestBody Room room, UriComponentsBuilder ucBuilder) {

        if (roomService.isExist(room)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        else {
        	roomService.insert(room);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/room/{id}").buildAndExpand(room.getRoomID()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/room", method = RequestMethod.PUT)
    public ResponseEntity<Room> updateRoom( @RequestBody Room room) {

        Room currentRoom = roomService.findById(room.getRoomID());

        if (currentRoom == null) {
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }

        roomService.update(room);
        return new ResponseEntity<Room>(currentRoom, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/room/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Room> deleteRoom(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);

        Room room = roomService.findById(id);
        if (room == null) {
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }

        roomService.deleteById(id);
        return new ResponseEntity<Room>(HttpStatus.NO_CONTENT);
    }
}
