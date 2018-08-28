package com.hjr.admin.mud.JoinChaAttr.model;


import com.hjr.admin.mud.attr.model.AttrModel;
import com.hjr.admin.mud.character.model.CharacterModel;
import com.hjr.admin.mud.items.model.ItemsModel;
import com.hjr.admin.mud.skill.model.SkillModel;
import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_join_cha_attr")
public class JoinChaAttrModel extends BaseModel {

	private SkillModel skillModel;

	private ItemsModel itemsModel;

	private AttrModel attrModel;

	private CharacterModel characterModel;

	String chaId;

	String iteId;

	String skiId;

	String attrId;

	int durationTime;

	int intervalTime;


	String fromType;//内功，技能，装备，食物

	public Map<String, Object> toMap() {
		updateMap = super.toMap();

		return updateMap;
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

	public String getSkiId() {
		return skiId;
	}

	public void setSkiId(String skiId) {
		this.skiId = skiId;
	}

	public String getAttrId() {
		return attrId;
	}

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}

	public SkillModel getSkillModel() {
		return skillModel;
	}

	public void setSkillModel(SkillModel skillModel) {
		this.skillModel = skillModel;
	}

	public ItemsModel getItemsModel() {
		return itemsModel;
	}

	public void setItemsModel(ItemsModel itemsModel) {
		this.itemsModel = itemsModel;
	}

	public AttrModel getAttrModel() {
		return attrModel;
	}

	public void setAttrModel(AttrModel attrModel) {
		this.attrModel = attrModel;
	}

	public CharacterModel getCharacterModel() {
		return characterModel;
	}

	public void setCharacterModel(CharacterModel characterModel) {
		this.characterModel = characterModel;
	}

	public int getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}

	public int getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}
}