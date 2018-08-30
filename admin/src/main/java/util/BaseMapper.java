package util;

import com.hjr.admin.mud.JoinChaAttr.model.JoinChaAttrModel;
import com.hjr.admin.mud.JoinRoomCha.model.JoinRoomChaModel;
import com.hjr.admin.mud.character.model.CharacterModel;
import com.hjr.admin.mud.room.model.RoomModel;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class BaseMapper <T extends BaseModel>{
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	protected MongoOperations mongoTemplate = (MongoOperations) ctx.getBean("mongoTemplate");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	Class clazz;
	public BaseMapper(Class clazz,String prefix) {
		this.clazz = clazz;
	}
	public BaseMapper(Class clazz) {
		this(clazz,"");
	}
	public void create(T t) {
		try {
			mongoTemplate.insert(t);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Object queryForOneByName(String name) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("name").is(name));
			return mongoTemplate.findOne(query, clazz);

		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public List<Object> queryForListByName(String name) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("name").is(name));
			return mongoTemplate.find(query, clazz);

		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object queryForOneByChaId(String chaId) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("chaId").is(chaId));
			return mongoTemplate.findOne(query, clazz);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object queryForOneById(String id) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(id));
			return mongoTemplate.findOne(query, clazz);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Object> queryForListByChaId(String chaId) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("chaId").is(chaId));
			return mongoTemplate.find(query, clazz);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Object> queryForListByRoomId(String roomId) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("roomModel._id").is(new ObjectId(roomId)));
			return mongoTemplate.find(query, clazz);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<T> queryForListByFiled(String filed , String value)
	{
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(filed).is(value));
			return mongoTemplate.find(query, clazz);
		}catch (Exception e){
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	public Object queryForOneByNameAndType(String name,String type) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("name").is(name));
			query.addCriteria(Criteria.where("attrType").is(type));
			return mongoTemplate.findOne(query, clazz);

		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object queryForOneByFiledAndValue(String filed,String value) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(filed).is(value));
			return mongoTemplate.findOne(query, clazz);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public void updateModel(T t) {
		try {
			mongoTemplate.save(t);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


}