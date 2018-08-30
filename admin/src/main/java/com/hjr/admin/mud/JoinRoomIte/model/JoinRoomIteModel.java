package com.hjr.admin.mud.JoinRoomIte.model;

import com.hjr.admin.mud.items.model.ItemsModel;
import com.hjr.admin.mud.room.model.RoomModel;
import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_join_room_ite")
public class JoinRoomIteModel extends BaseModel {

	String roomId;

	String iteId;

	RoomModel roomModel;
	ItemsModel itemsModel;


	public Map<String, Object> toMap() {
		updateMap = super.toMap();

		return updateMap;
	}


	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getIteId() {
		return iteId;
	}

	public void setIteId(String iteId) {
		this.iteId = iteId;
	}

	public RoomModel getRoomModel() {
		return roomModel;
	}

	public void setRoomModel(RoomModel roomModel) {
		this.roomModel = roomModel;
	}

	public ItemsModel getItemsModel() {
		return itemsModel;
	}

	public void setItemsModel(ItemsModel itemsModel) {
		this.itemsModel = itemsModel;
	}
}