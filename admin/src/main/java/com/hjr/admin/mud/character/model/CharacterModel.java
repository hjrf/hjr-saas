package com.hjr.admin.mud.character.model;


import com.hjr.admin.mud.JoinChaAttr.mapper.JoinChaAttrMapper;
import com.hjr.admin.mud.JoinChaAttr.model.JoinChaAttrModel;
import com.hjr.admin.mud.attr.model.AttrMapper;
import com.hjr.admin.mud.attr.model.AttrModel;
import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.List;
import java.util.Map;

@Document(collection = "mud_character")
public class CharacterModel extends BaseModel {

	String userId;

	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		return updateMap;
	}

	AttrModel attrModel;

	AttrModel attrModel_zb;

	AttrModel attrModel_ng;

	AttrModel attrModel_buff;


	public AttrModel getAttrModel() {
		AttrMapper attrMapper = new AttrMapper() ;
		AttrModel attrModel = (AttrModel) attrMapper.queryForOneByNameAndType(this.name,"人物");
		return attrModel;
	}

	public void setAttrModel(AttrModel attrModel) {
		this.attrModel = attrModel;
	}

	public AttrModel getAttrModel_zb() {
		JoinChaAttrMapper joinChaAttrMapper = new JoinChaAttrMapper() ;
		List<JoinChaAttrModel> joinCharacterAndAttrModelList = joinChaAttrMapper.queryForListByTypeAndName("装备",this.name);
		AttrModel attrModel = new AttrModel();
		AttrModel attrModelTemp;
		for(JoinChaAttrModel joinCharacterAndAttrModel:joinCharacterAndAttrModelList){
			attrModelTemp = joinCharacterAndAttrModel.getAttrModel();
			if(attrModelTemp != null) {
				attrModel.setSTR(attrModel.getSTR()+attrModelTemp.getSTR());
				attrModel.setAGI(attrModel.getAGI()+attrModelTemp.getAGI());
				attrModel.setINT(attrModel.getINT()+attrModelTemp.getINT());
				attrModel.setVIT(attrModel.getVIT()+attrModelTemp.getVIT());
				attrModel.setLuck(attrModel.getLuck()+attrModelTemp.getLuck());
				attrModel.setHpMax(attrModel.getHpMax()+attrModelTemp.getHpMax());
				attrModel.setMpMax(attrModel.getMpMax()+attrModelTemp.getMpMax());
				attrModel.setPpMax(attrModel.getPpMax()+attrModelTemp.getPpMax());
				attrModel.setPhysicalDamage(attrModel.getPhysicalDamage()+attrModelTemp.getPhysicalDamage());
				attrModel.setPhysicalDefense(attrModel.getPhysicalDefense()+attrModelTemp.getPhysicalDefense());
				attrModel.setPhysicalCrit(attrModel.getPhysicalCrit()+attrModelTemp.getPhysicalCrit());
				attrModel.setMagicDamage(attrModel.getMagicDamage()+attrModelTemp.getMagicDamage());
				attrModel.setMagicDefense(attrModel.getMagicDefense()+attrModelTemp.getMagicDefense());
				attrModel.setMagicCrit(attrModel.getMagicCrit()+attrModelTemp.getMagicCrit());
				attrModel.setDodge(attrModel.getDodge()+attrModelTemp.getDodge());
				attrModel.setResistance(attrModel.getResistance()+attrModelTemp.getResistance());
				attrModel.setFixedDamage(attrModel.getFixedDamage()+attrModelTemp.getFixedDamage());
				attrModel.setMovementSpeed(attrModel.getMovementSpeed()+attrModelTemp.getMovementSpeed());
				attrModel.setAttackSpeed(attrModel.getAttackSpeed()+attrModelTemp.getAttackSpeed());
			}
		}
		this.attrModel_zb = attrModel;
		return attrModel_zb;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAttrModel_zb(AttrModel attrModel_zb) {
		this.attrModel_zb = attrModel_zb;
	}


	public AttrModel getAttrModel_ng() {

		JoinChaAttrMapper joinChaAttrMapper = new JoinChaAttrMapper() ;
		List<JoinChaAttrModel> joinCharacterAndAttrModelList = joinChaAttrMapper.queryForListByTypeAndName("装备",this.name);
		AttrModel attrModel = new AttrModel();
		AttrModel attrModelTemp;
		for(JoinChaAttrModel joinCharacterAndAttrModel:joinCharacterAndAttrModelList){
			attrModelTemp = joinCharacterAndAttrModel.getAttrModel();
			if(attrModelTemp != null) {
				attrModel.setSTR(attrModel.getSTR()+attrModelTemp.getSTR());
				attrModel.setAGI(attrModel.getAGI()+attrModelTemp.getAGI());
				attrModel.setINT(attrModel.getINT()+attrModelTemp.getINT());
				attrModel.setVIT(attrModel.getVIT()+attrModelTemp.getVIT());
				attrModel.setLuck(attrModel.getLuck()+attrModelTemp.getLuck());
				attrModel.setHpMax(attrModel.getHpMax()+attrModelTemp.getHpMax());
				attrModel.setMpMax(attrModel.getMpMax()+attrModelTemp.getMpMax());
				attrModel.setPpMax(attrModel.getPpMax()+attrModelTemp.getPpMax());
				attrModel.setPhysicalDamage(attrModel.getPhysicalDamage()+attrModelTemp.getPhysicalDamage());
				attrModel.setPhysicalDefense(attrModel.getPhysicalDefense()+attrModelTemp.getPhysicalDefense());
				attrModel.setPhysicalCrit(attrModel.getPhysicalCrit()+attrModelTemp.getPhysicalCrit());
				attrModel.setMagicDamage(attrModel.getMagicDamage()+attrModelTemp.getMagicDamage());
				attrModel.setMagicDefense(attrModel.getMagicDefense()+attrModelTemp.getMagicDefense());
				attrModel.setMagicCrit(attrModel.getMagicCrit()+attrModelTemp.getMagicCrit());
				attrModel.setDodge(attrModel.getDodge()+attrModelTemp.getDodge());
				attrModel.setResistance(attrModel.getResistance()+attrModelTemp.getResistance());
				attrModel.setFixedDamage(attrModel.getFixedDamage()+attrModelTemp.getFixedDamage());
				attrModel.setMovementSpeed(attrModel.getMovementSpeed()+attrModelTemp.getMovementSpeed());
				attrModel.setAttackSpeed(attrModel.getAttackSpeed()+attrModelTemp.getAttackSpeed());
			}
		}
		this.attrModel_ng = attrModel;
		return this.attrModel_ng;
	}

	public void setAttrModel_ng(AttrModel attrModel_ng) {
		this.attrModel_ng = attrModel_ng;
	}

	public AttrModel getAttrModel_buff() {
		return attrModel_buff;
	}

	public void setAttrModel_buff(AttrModel attrModel_buff) {
		this.attrModel_buff = attrModel_buff;
	}

}