package com.hjr.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection="hjr_tenant")
public class TenantModel extends Baseinfo {     
  
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