package com.hjr.admin.mud.attr.model;


import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

import java.util.Map;

@Document(collection = "mud_attr")
public class AttrModel extends BaseModel {
	String attrType = "";
	String attrDesc = "";
	String attrActionDesc = "";
	int HP;
	int MP;
	int PP;
	int CD;//单位 0.1s
	int HPbi;
	int MPbi;
	int PPbi;
	int hpMax;
	int mpMax;
	int ppMax;
	int STR;//力量-臂力
	int INT;//智力-内息
	int AGI;//敏捷-身法
	int VIT;//体质-体魄
	int physicalDamage;//物伤
	int magicDamage;//魔伤
	int fixedDamage;//固伤
	int physicalCrit;//物暴
	int magicCrit;//魔暴
	int physicalDefense;//物防
	int magicDefense;//魔防
	int luck;//幸运
	int attackSpeed;//攻速
	int movementSpeed;//移速
	int hit;//命中
	int dodge;//闪避
	int resistance;//抗性
	int durationTime;//持续时间
	int intervalTime;//间隔时间

	String chaId;


	public Map<String, Object> toMap() {
		updateMap = super.toMap();
		updateMap.put("attrType", this.getAttrType());
		return updateMap;
	}

	public String getChaId() {
		return chaId;
	}

	public void setChaId(String chaId) {
		this.chaId = chaId;
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

	public String getAttrActionDesc() {
		return attrActionDesc;
	}

	public void setAttrActionDesc(String attrActionDesc) {
		this.attrActionDesc = attrActionDesc;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int HP) {
		this.HP = HP;
	}

	public int getMP() {
		return MP;
	}

	public void setMP(int MP) {
		this.MP = MP;
	}

	public int getPP() {
		return PP;
	}

	public void setPP(int PP) {
		this.PP = PP;
	}

	public int getCD() {
		return CD;
	}

	public void setCD(int CD) {
		this.CD = CD;
	}

	public int getHPbi() {
		return HPbi;
	}

	public void setHPbi(int HPbi) {
		this.HPbi = HPbi;
	}

	public int getMPbi() {
		return MPbi;
	}

	public void setMPbi(int MPbi) {
		this.MPbi = MPbi;
	}

	public int getPPbi() {
		return PPbi;
	}

	public void setPPbi(int PPbi) {
		this.PPbi = PPbi;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getMpMax() {
		return mpMax;
	}

	public void setMpMax(int mpMax) {
		this.mpMax = mpMax;
	}

	public int getPpMax() {
		return ppMax;
	}

	public void setPpMax(int ppMax) {
		this.ppMax = ppMax;
	}

	public int getSTR() {
		return STR;
	}

	public void setSTR(int STR) {
		this.STR = STR;
	}

	public int getINT() {
		return INT;
	}

	public void setINT(int INT) {
		this.INT = INT;
	}

	public int getAGI() {
		return AGI;
	}

	public void setAGI(int AGI) {
		this.AGI = AGI;
	}

	public int getVIT() {
		return VIT;
	}

	public void setVIT(int VIT) {
		this.VIT = VIT;
	}

	public int getPhysicalDamage() {
		return physicalDamage;
	}

	public void setPhysicalDamage(int physicalDamage) {
		this.physicalDamage = physicalDamage;
	}

	public int getMagicDamage() {
		return magicDamage;
	}

	public void setMagicDamage(int magicDamage) {
		this.magicDamage = magicDamage;
	}

	public int getFixedDamage() {
		return fixedDamage;
	}

	public void setFixedDamage(int fixedDamage) {
		this.fixedDamage = fixedDamage;
	}

	public int getPhysicalCrit() {
		return physicalCrit;
	}

	public void setPhysicalCrit(int physicalCrit) {
		this.physicalCrit = physicalCrit;
	}

	public int getMagicCrit() {
		return magicCrit;
	}

	public void setMagicCrit(int magicCrit) {
		this.magicCrit = magicCrit;
	}

	public int getPhysicalDefense() {
		return physicalDefense;
	}

	public void setPhysicalDefense(int physicalDefense) {
		this.physicalDefense = physicalDefense;
	}

	public int getMagicDefense() {
		return magicDefense;
	}

	public void setMagicDefense(int magicDefense) {
		this.magicDefense = magicDefense;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
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
}