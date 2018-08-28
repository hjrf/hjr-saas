package com.hjr.admin.mud.JoinChaIte.model;


import com.hjr.admin.mud.character.model.CharacterModel;
import com.hjr.admin.mud.items.model.ItemsModel;
import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_join_cha_ite")
public class JoinChaIteModel extends BaseModel {

	boolean isEquip;

	String chaId;

	String iteId;

	public Map<String, Object> toMap() {
		updateMap = super.toMap();

		return updateMap;
	}

	public boolean isEquip() {
		return isEquip;
	}

	public void setEquip(boolean equip) {
		isEquip = equip;
	}

	public String getChaId() {
		return chaId;
	}

	public void setChaId(String chaId) {
		this.chaId = chaId;
	}

	public String getIteId() {
		return iteId;
	}

	public void setIteId(String iteId) {
		this.iteId = iteId;
	}

}