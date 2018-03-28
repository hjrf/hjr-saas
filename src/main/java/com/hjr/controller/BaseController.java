package com.hjr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjr.model.Baseinfo;
import com.hjr.model.UserModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import util.SpringDataPageable;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseController<T extends Baseinfo> {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	protected MongoOperations mongoTemplate = (MongoOperations) ctx.getBean("mongoTemplate");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	@RequestMapping("/")
	protected List<T> ref(HttpServletRequest request, Class<?> clazz) {
		// 这里可以设置查询后并编辑后是否保留查询
		Map<String, Object> page = (Map<String, Object>) request.getSession().getAttribute("page");
		return ref(page, clazz);
	}

	protected List<T> ref(Map<String, Object> page, Class<?> clazz) {
		return ref(page, clazz, null);
	}

	protected List<T> ref(Map<String, Object> page, Class<?> clazz, Map<String, String> search) {
		int pagenumber = 0;
		int current = 0;
		if (null != page) {
			for (String key : page.keySet()) {
				pagenumber = Integer.valueOf(page.get("pagenumber").toString());
				current = Integer.valueOf(page.get("current").toString());
			}
		}
		SpringDataPageable pageable = new SpringDataPageable();
		Query query = new Query();
		// 开始页
		pageable.setPagenumber(current);
		// 每页条数
		pageable.setPagesize(pagenumber);
		// 搜索
		if (null != search) {
			String whereFiled = "";
			String likeFiled = "";
			String where = "";
			String like = "";
			for (String key : search.keySet()) {
				whereFiled = search.get("whereFiled");
				likeFiled = search.get("likeFiled");
				where = search.get("where");
				like = search.get("like");
			}
			Criteria criteria = null;
			if (null != where && !"".equals(where)) {
				criteria = Criteria.where(whereFiled).is(where);
				query.addCriteria(criteria);
			}
			if (null != like && !"".equals(like)) {
				criteria = Criteria.where(likeFiled).regex("^.*" + like + ".*$");
				query.addCriteria(criteria);
			}
		}
		// 排序
		query.with(new Sort(new Order(Direction.DESC, "sortNumber")).and(new Sort(Direction.DESC, "updatedTime")));
		// 查询
		List<T> pageUsers = (List<T>) mongoTemplate.find(query.with(pageable), clazz);
		// 查询出一共的条数
		Long count = mongoTemplate.count(query, clazz);
		try {
			// 当前页面获取的记录数为空，整除后余一，刷新标志开启
			if (null == pageUsers || count % pagenumber == 1) {
				((T) pageUsers.get(0)).setRefresh(true);
				;
			} else {
				((T) pageUsers.get(0)).setRefresh(false);
			}
			((T) pageUsers.get(0)).setRecordNum(count);
		} catch (Exception e) {

		}
		return pageUsers;
	}

	protected List<T> show(HttpServletRequest request, @RequestBody List<Map<String, Object>> para, Class<?> clazz) {
		Map<String, Object> page = null;
		Map<String, String> search = null;
		ObjectMapper mapper = new ObjectMapper();
		// 如果有page和search则获取
		for (int i = 0; i < para.size(); i++) {
			if ("[page]".equals(para.get(i).keySet().toString())) {
				List temp = (List) para.get(i).get("page");
				page = (Map<String, Object>) temp.get(0);
			}
			if ("[search]".equals(para.get(i).keySet().toString())) {
				List temp = (List) para.get(i).get("search");
				search = (Map<String, String>) temp.get(0);
			}
		}
		request.getSession().setAttribute("page", page);
		request.getSession().setAttribute("search", search);
		return ref(page, clazz, search);
	}

	protected void add(T t) {
		t.setCreatedTime(df.format(new Date()));
		mongoTemplate.insert(t);
	}

	protected void add(List<T> t) {
		for (T tt : t) {
			tt.setCreatedTime(df.format(new Date()));
		}
		mongoTemplate.insertAll(t);
	}

	protected void edit(T t, Class clazz) {
		t.setUpdatedTime(df.format(new Date()));
		Update update = new Update();
		Map<String, Object> valueMap = t.toMap();
		valueMap.keySet().forEach(key -> update.set(key, valueMap.get(key)));
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(t.getId()));
		mongoTemplate.updateFirst(query, update, clazz);
	}

	protected void del(T t) {
		mongoTemplate.remove(t);
	}

	protected void add(T t, HttpServletRequest request) {
		UserModel user = (UserModel) request.getSession().getAttribute("user");

		t.setCreatedTime(df.format(new Date()));
		t.setCreatedName(user.getUsername());
		mongoTemplate.insert(t);
	}

	protected void edit(T t, HttpServletRequest request) {
		UserModel user = (UserModel) request.getSession().getAttribute("user");
		t.setUpdatedTime(df.format(new Date()));
		t.setUpdatedName(user.getUsername());

		Update update = new Update();
		Map<String, Object> valueMap = t.toMap();
		valueMap.keySet().forEach(key -> update.set(key, valueMap.get(key)));
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(t.getId()));
	}

}
