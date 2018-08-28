package com.hjr.admin.mud.items.model;


import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_items")
public class ItemsModel extends BaseModel {





	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		return updateMap;
	}

}