package com.hjr.admin.system.role.model;

import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "hjr_role")
public class RoleModel extends BaseModel {
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