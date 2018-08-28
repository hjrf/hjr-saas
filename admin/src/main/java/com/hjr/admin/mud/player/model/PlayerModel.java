package com.hjr.admin.mud.player.model;

import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_player")
public class PlayerModel extends BaseModel {

	String zhaoshi;
	String chaCurHP;
	String chaMaxMP;
	String chaMaxPP;
	String chaHeight;
	String chaAppearance;
	String chaType;
	String chaRole;
	String chaCurPP;
	String chaCurMP;
	String chaRace;
	String chaDesc;
	String chaTitleName;
	String chaMoney;
	String chaWeight;
	String chaName;
	String chaCurRoomId;
	String chaSex;
	String chaFightValue;
	String chaMaxHP;

	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		updateMap.put("zhaoshi", this.getZhaoshi());
		updateMap.put("chaCurHP", this.getChaCurHP());
		updateMap.put("chaName", this.getChaName());
		return updateMap;
	}


	public String getZhaoshi() {
		return zhaoshi;
	}

	public void setZhaoshi(String zhaoshi) {
		this.zhaoshi = zhaoshi;
	}

	public String getChaCurHP() {
		return chaCurHP;
	}

	public void setChaCurHP(String chaCurHP) {
		this.chaCurHP = chaCurHP;
	}

	public String getChaMaxMP() {
		return chaMaxMP;
	}

	public void setChaMaxMP(String chaMaxMP) {
		this.chaMaxMP = chaMaxMP;
	}

	public String getChaMaxPP() {
		return chaMaxPP;
	}

	public void setChaMaxPP(String chaMaxPP) {
		this.chaMaxPP = chaMaxPP;
	}

	public String getChaHeight() {
		return chaHeight;
	}

	public void setChaHeight(String chaHeight) {
		this.chaHeight = chaHeight;
	}

	public String getChaAppearance() {
		return chaAppearance;
	}

	public void setChaAppearance(String chaAppearance) {
		this.chaAppearance = chaAppearance;
	}

	public String getChaType() {
		return chaType;
	}

	public void setChaType(String chaType) {
		this.chaType = chaType;
	}

	public String getChaRole() {
		return chaRole;
	}

	public void setChaRole(String chaRole) {
		this.chaRole = chaRole;
	}

	public String getChaCurPP() {
		return chaCurPP;
	}

	public void setChaCurPP(String chaCurPP) {
		this.chaCurPP = chaCurPP;
	}

	public String getChaCurMP() {
		return chaCurMP;
	}

	public void setChaCurMP(String chaCurMP) {
		this.chaCurMP = chaCurMP;
	}

	public String getChaRace() {
		return chaRace;
	}

	public void setChaRace(String chaRace) {
		this.chaRace = chaRace;
	}

	public String getChaDesc() {
		return chaDesc;
	}

	public void setChaDesc(String chaDesc) {
		this.chaDesc = chaDesc;
	}

	public String getChaTitleName() {
		return chaTitleName;
	}

	public void setChaTitleName(String chaTitleName) {
		this.chaTitleName = chaTitleName;
	}

	public String getChaMoney() {
		return chaMoney;
	}

	public void setChaMoney(String chaMoney) {
		this.chaMoney = chaMoney;
	}

	public String getChaWeight() {
		return chaWeight;
	}

	public void setChaWeight(String chaWeight) {
		this.chaWeight = chaWeight;
	}

	public String getChaName() {
		return chaName;
	}

	public void setChaName(String chaName) {
		this.chaName = chaName;
	}

	public String getChaCurRoomId() {
		return chaCurRoomId;
	}

	public void setChaCurRoomId(String chaCurRoomId) {
		this.chaCurRoomId = chaCurRoomId;
	}

	public String getChaSex() {
		return chaSex;
	}

	public void setChaSex(String chaSex) {
		this.chaSex = chaSex;
	}

	public String getChaFightValue() {
		return chaFightValue;
	}

	public void setChaFightValue(String chaFightValue) {
		this.chaFightValue = chaFightValue;
	}

	public String getChaMaxHP() {
		return chaMaxHP;
	}

	public void setChaMaxHP(String chaMaxHP) {
		this.chaMaxHP = chaMaxHP;
	}
}