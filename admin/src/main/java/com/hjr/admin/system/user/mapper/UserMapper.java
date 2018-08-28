package com.hjr.admin.system.user.mapper;


import com.hjr.admin.system.user.model.UserModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import util.BaseMapper;


public class UserMapper extends BaseMapper {


	public UserMapper() {
		super(UserModel.class);
	}

	public UserModel queryForOneByUsername(String username) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("username").is(username));
			return mongoTemplate.findOne(query, UserModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public UserModel queryForOneByUsernameAndPassword(String username,String password) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("username").is(username));
			query.addCriteria(Criteria.where("password").is(password));
			return mongoTemplate.findOne(query, UserModel.class);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}



}