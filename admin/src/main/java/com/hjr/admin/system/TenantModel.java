package com.hjr.admin.system;

import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection="hjr_tenant")
public class TenantModel extends BaseModel {
  
    private String password;

	public Map<String, Object> toMap() {
		updateMap =  super.toMap();
		updateMap.put("password", this.getPassword());
		return updateMap;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}  