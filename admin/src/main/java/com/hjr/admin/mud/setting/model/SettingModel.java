package com.hjr.admin.mud.setting.model;


import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.List;
import java.util.Map;

@Document(collection = "mud_setting")
public class SettingModel extends BaseModel {

	List<Map<String, Object>> function;

	String chaId;


	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		updateMap.put("function", this.getFunction());
		return updateMap;
	}

	public List<Map<String, Object>> getFunction() {
		return function;
	}

	public void setFunction(List<Map<String, Object>> function) {
		this.function = function;
	}

	public String getChaId() {
		return chaId;
	}

	public void setChaId(String chaId) {
		this.chaId = chaId;
	}
}