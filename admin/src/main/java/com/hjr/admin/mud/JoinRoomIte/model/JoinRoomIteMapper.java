package com.hjr.admin.mud.JoinRoomIte.model;


import com.hjr.admin.mud.JoinChaAttr.model.JoinChaAttrModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import util.BaseMapper;

import java.util.Collections;
import java.util.List;

public class JoinRoomIteMapper extends BaseMapper{
	public JoinRoomIteMapper() {
		super(JoinRoomIteModel.class);
	}


	public List<JoinRoomIteModel> queryForListByRoomId(String roomId)
	{
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("roomId").is(roomId));
			return mongoTemplate.find(query, JoinRoomIteModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

}