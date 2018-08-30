package com.hjr.admin.mud.JoinIteSki.model;


import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_join_ite_ski")
public class JoinIteSkiModel extends BaseModel {

	String attrActionDesc;
	String attrName;
	String attrType;
	String attrDesc;


	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		updateMap.put("attrName", this.getAttrName());
		updateMap.put("attrType", this.getAttrType());
		updateMap.put("attrDesc", this.getAttrName());
		return updateMap;
	}

	public String getAttrActionDesc() {
		return attrActionDesc;
	}

	public void setAttrActionDesc(String attrActionDesc) {
		this.attrActionDesc = attrActionDesc;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getAttrDesc() {
		return attrDesc;
	}

	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}
}