package com.hjr.admin.mud.character.mapper;


import com.hjr.admin.mud.JoinChaAttr.model.JoinChaAttrModel;
import com.hjr.admin.mud.character.model.CharacterModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import util.BaseMapper;

import java.util.Collections;
import java.util.List;


public class CharacterMapper extends BaseMapper {


	public CharacterMapper() {
		super(CharacterModel.class);
	}

	public CharacterModel queryForOneByName(String name) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("name").is(name));
			return mongoTemplate.findOne(query, CharacterModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public CharacterModel queryForOneById(String id) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(id));
			return mongoTemplate.findOne(query, CharacterModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public CharacterModel queryForOneByUserId(String userId) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("userId").is(userId));
			return mongoTemplate.findOne(query, CharacterModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}