package com.hjr.admin.mud.room.model;


import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_map")
public class MapModel extends BaseModel {

	String mapXY;

	String countryName;



	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		updateMap.put("roomName", this.getMapXY());
		return updateMap;
	}

	public String getMapXY() {
		return mapXY;
	}

	public void setMapXY(String mapXY) {
		this.mapXY = mapXY;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}