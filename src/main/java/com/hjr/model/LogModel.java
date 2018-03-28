package com.hjr.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "hjr_log")
public class LogModel extends Baseinfo {

	private String operate;
	private String operateTime;

	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		updateMap.put("operate", this.getOperate());
		updateMap.put("operateTime", this.getOperateTime());
		return updateMap;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

}