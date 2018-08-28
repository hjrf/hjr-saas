package util;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseModel implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	private String tenantId;
	protected String title;
	protected String type;
	protected String content;
	protected String name;
	protected String createdTime;
	protected String updatedTime;
	protected String createdName;
	protected String updatedName;
	protected String introduct;
	protected int sortNumber;
	protected int isDisable;
	@Id
	private String id;
	private Long recordNum;
	private boolean isRefresh;

	protected Map<String, Object> updateMap = new HashMap<String, Object>();

	public Map<String, Object> toMap() {
		updateMap.put("tenantId", this.getTenantId());
		updateMap.put("createdTime", this.getCreatedTime());
		updateMap.put("createdName", this.getCreatedName());
		updateMap.put("updatedTime", this.getUpdatedTime());
		updateMap.put("updatedName", this.getUpdatedName());
		updateMap.put("name", this.getName());
		updateMap.put("isDisable", this.getIsDisable());
		updateMap.put("sortNumber", this.getSortNumber());
		updateMap.put("introduct", this.getIntroduct());
		updateMap.put("title", this.getUpdatedName());
		updateMap.put("content", this.getUpdatedName());
		return updateMap;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getIntroduct() {
		return introduct;
	}

	public void setIntroduct(String introduct) {
		this.introduct = introduct;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

	public String getUpdatedName() {
		return updatedName;
	}

	public void setUpdatedName(String updatedName) {
		this.updatedName = updatedName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(Long recordNum) {
		this.recordNum = recordNum;
	}

	public boolean isRefresh() {
		return isRefresh;
	}

	public void setRefresh(boolean isRefresh) {
		this.isRefresh = isRefresh;
	}

	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}

	public int getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(int isDisable) {
		this.isDisable = isDisable;
	}

}