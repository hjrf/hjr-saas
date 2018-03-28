package com.hjr.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "hjr_user")
public class UserModel extends Baseinfo {

	private String realname;
	private String username;
	private String password;
	private String role;
	private String course;
	private String state;
	private String question;
	private String work;

	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		updateMap.put("realname", this.getRealname());
		updateMap.put("username", this.getUsername());
		updateMap.put("role", this.getRole());
		updateMap.put("course", this.getCourse());
		updateMap.put("state", this.getState());
		return updateMap;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

}