package com.hjr.admin.mud.room.model;


import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_room")
public class RoomModel extends BaseModel {

	String roomXY;

	String roomType;

	String mapName;

	String roomDesc = "";

	String roomFunction;

	String roomWordImg;

	String roomCharacterIdGroup;

	String roomItemsNameGroup;

	String roomCharacterNameGroup;

	String roomItemsIdGroup;

	String roomThroughDesc;

	String roomExportMapName;

	int roomExportRoomId;

	int haveChaId;

	int isThrough;

	String throughNeedName;

	boolean isBuild;

	String workType;

	int roomPrice;

	int workPeopleNum;


	boolean isEvent;

	boolean isTemplet;

	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		return updateMap;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getRoomXY() {
		return roomXY;
	}

	public void setRoomXY(String roomXY) {
		this.roomXY = roomXY;
	}



	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public String getRoomFunction() {
		return roomFunction;
	}

	public void setRoomFunction(String roomFunction) {
		this.roomFunction = roomFunction;
	}

	public String getRoomWordImg() {
		return roomWordImg;
	}

	public void setRoomWordImg(String roomWordImg) {
		this.roomWordImg = roomWordImg;
	}

	public String getRoomCharacterIdGroup() {
		return roomCharacterIdGroup;
	}

	public void setRoomCharacterIdGroup(String roomCharacterIdGroup) {
		this.roomCharacterIdGroup = roomCharacterIdGroup;
	}

	public String getRoomItemsNameGroup() {
		return roomItemsNameGroup;
	}

	public void setRoomItemsNameGroup(String roomItemsNameGroup) {
		this.roomItemsNameGroup = roomItemsNameGroup;
	}

	public String getRoomCharacterNameGroup() {
		return roomCharacterNameGroup;
	}

	public void setRoomCharacterNameGroup(String roomCharacterNameGroup) {
		this.roomCharacterNameGroup = roomCharacterNameGroup;
	}

	public String getRoomItemsIdGroup() {
		return roomItemsIdGroup;
	}

	public void setRoomItemsIdGroup(String roomItemsIdGroup) {
		this.roomItemsIdGroup = roomItemsIdGroup;
	}

	public String getRoomThroughDesc() {
		return roomThroughDesc;
	}

	public void setRoomThroughDesc(String roomThroughDesc) {
		this.roomThroughDesc = roomThroughDesc;
	}

	public String getRoomExportMapName() {
		return roomExportMapName;
	}

	public void setRoomExportMapName(String roomExportMapName) {
		this.roomExportMapName = roomExportMapName;
	}

	public int getRoomExportRoomId() {
		return roomExportRoomId;
	}

	public void setRoomExportRoomId(int roomExportRoomId) {
		this.roomExportRoomId = roomExportRoomId;
	}

	public int getHaveChaId() {
		return haveChaId;
	}

	public void setHaveChaId(int haveChaId) {
		this.haveChaId = haveChaId;
	}

	public int getIsThrough() {
		return isThrough;
	}

	public void setIsThrough(int isThrough) {
		this.isThrough = isThrough;
	}

	public String getThroughNeedName() {
		return throughNeedName;
	}

	public void setThroughNeedName(String throughNeedName) {
		this.throughNeedName = throughNeedName;
	}

	public boolean isBuild() {
		return isBuild;
	}

	public void setBuild(boolean build) {
		isBuild = build;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	public int getWorkPeopleNum() {
		return workPeopleNum;
	}

	public void setWorkPeopleNum(int workPeopleNum) {
		this.workPeopleNum = workPeopleNum;
	}

	public boolean isEvent() {
		return isEvent;
	}

	public void setEvent(boolean event) {
		isEvent = event;
	}

	public boolean isTemplet() {
		return isTemplet;
	}

	public void setTemplet(boolean templet) {
		isTemplet = templet;
	}
}