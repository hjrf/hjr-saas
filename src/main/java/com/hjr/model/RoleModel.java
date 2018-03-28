package com.hjr.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "hjr_role")
public class RoleModel extends Baseinfo {
	private String permission;

	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		updateMap.put("permission", this.getPermission());
		return updateMap;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}