package com.hjr.admin.mud.JoinChaAttr.mapper;


import com.hjr.admin.mud.JoinChaAttr.model.JoinChaAttrModel;
import com.hjr.admin.mud.attr.model.AttrModel;
import com.hjr.admin.mud.character.model.CharacterModel;
import com.hjr.admin.mud.items.model.ItemsModel;
import com.hjr.admin.mud.skill.model.SkillModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import util.BaseMapper;
import util.BaseModel;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JoinChaAttrMapper extends BaseMapper{
	public JoinChaAttrMapper() {
		super(JoinChaAttrModel.class);
	}

	public List<JoinChaAttrModel>  queryForListByTypeAndName(String type, String name) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("type").is(type));
			query.addCriteria(Criteria.where("name").is(name));
			return mongoTemplate.find(query, JoinChaAttrModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	public List<JoinChaAttrModel>  queryForListByTypeAndchaId(String type, String chaId) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("type").is(type));
			query.addCriteria(Criteria.where("chaId").is(chaId));
			return mongoTemplate.find(query, JoinChaAttrModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}
}