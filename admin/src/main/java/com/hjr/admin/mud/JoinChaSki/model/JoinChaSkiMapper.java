package com.hjr.admin.mud.JoinChaSki.model;


import com.hjr.admin.mud.JoinChaAttr.model.JoinChaAttrModel;
import com.hjr.admin.mud.JoinChaIte.model.JoinChaIteModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import util.BaseMapper;

import java.util.Collections;
import java.util.List;

public class JoinChaSkiMapper extends BaseMapper{
	public JoinChaSkiMapper() {
		super(JoinChaAttrModel.class);
	}


	public List<JoinChaSkiModel> queryForAlreadyEquipByChaId(String chaId)
	{
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("isEquip").is(true));
			query.addCriteria(Criteria.where("chaId").is(chaId));
			return mongoTemplate.find(query, JoinChaSkiModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	public List<JoinChaSkiModel> queryForListByChaId(String chaId)
	{
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("chaId").is(chaId));
			return mongoTemplate.find(query, JoinChaSkiModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}



}