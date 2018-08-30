package com.hjr.admin.mud.JoinRoomCha.model;


import com.hjr.admin.mud.character.model.CharacterModel;
import com.hjr.admin.mud.room.model.RoomModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_join_room_cha")
public class JoinRoomChaModel extends BaseModel {

	RoomModel roomModel;

	CharacterModel characterModel;

	public Map<String, Object> toMap() {
		updateMap = super.toMap();

		return updateMap;
	}

	public RoomModel getRoomModel() {
		return roomModel;
	}

	public void setRoomModel(RoomModel roomModel) {
		this.roomModel = roomModel;
	}

	public CharacterModel getCharacterModel() {
		return characterModel;
	}

	public void setCharacterModel(CharacterModel characterModel) {
		this.characterModel = characterModel;
	}


}