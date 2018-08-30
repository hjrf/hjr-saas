package com.hjr.admin.mud.JoinChaSki.model;


import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_join_cha_ski")
public class JoinChaSkiModel extends BaseModel {

	String skiId;

	public Map<String, Object> toMap() {
		updateMap = super.toMap();

		return updateMap;
	}

	public String getSkiId() {
		return skiId;
	}

	public void setSkiId(String skiId) {
		this.skiId = skiId;
	}
}