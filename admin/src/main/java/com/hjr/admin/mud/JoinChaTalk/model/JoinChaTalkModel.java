package com.hjr.admin.mud.JoinChaTalk.model;


import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_join_cha_talk")
public class JoinChaTalkModel extends BaseModel {

	public Map<String, Object> toMap() {
		updateMap = super.toMap();

		return updateMap;
	}
}