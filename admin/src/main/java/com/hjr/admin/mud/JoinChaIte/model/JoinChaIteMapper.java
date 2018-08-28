package com.hjr.admin.mud.JoinChaIte.model;


import com.hjr.admin.mud.JoinChaAttr.model.JoinChaAttrModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import util.BaseMapper;

import java.util.Collections;
import java.util.List;

public class JoinChaIteMapper extends BaseMapper{
	public JoinChaIteMapper() {
		super(JoinChaAttrModel.class);
	}


	public List<JoinChaIteModel> queryForAlreadyEquipByChaId(String chaId)
	{
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("isEquip").is(true));
			query.addCriteria(Criteria.where("chaId").is(chaId));
			return mongoTemplate.find(query, JoinChaIteModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	public List<JoinChaIteModel> queryForListByChaId(String chaId)
	{
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("chaId").is(chaId));
			return mongoTemplate.find(query, JoinChaIteModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}


}