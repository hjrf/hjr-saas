// package com.hjr.mq;
//
// import java.io.IOException;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Iterator;
// import java.util.List;
// import java.util.Map;
// import java.util.Set;
//
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.support.ClassPathXmlApplicationContext;
// import org.springframework.data.mongodb.core.MongoOperations;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Query;
// import org.springframework.data.mongodb.core.query.Update;
//
// import com.alibaba.fastjson.JSONObject;
//
// public class Test2 {
// ApplicationContext ctx = new
// ClassPathXmlApplicationContext("applicationContext.xml");
// MongoOperations mongoTemplate = (MongoOperations)
// ctx.getBean("mongoTemplate");
// String packeg = "com.xh.mq.";
//
// public Test2() throws Exception {
// String jsonStr =
// "{\"class_otm_lesson\":{\"id\":\"49e\",\"a_a\":\"49ee470124b446e7a3338b86a6d2cfd8\",\"a_b\":\"31265f5677ab4323bcffd773ee87721a\",\"a_c\":\"31265f5677ab4323bcffd773ee87721a\",\"a_d\":\"31265f5677ab4323bcffd773ee87721a\",\"is_delete\":\"2\"}}";
// JSONObject jsonObject = JSONObject.parseObject(jsonStr);
// String jsonKey = jsonObject.keySet().toString().trim().substring(1,
// jsonObject.keySet().toString().trim().length() - 1);
// String tableKey = jsonKey.replace('_', '.');
// jsonObject = JSONObject.parseObject(jsonObject.getString(jsonKey));
//
// // 根据id判断ixID是否存在,不存在则插入,存在则判断是否删除，不删除更新。
// Query query = new Query();
// Criteria criteria = Criteria.where("ixId").is(jsonObject.getString("id"));
// query.addCriteria(criteria);
//
// if (mongoTemplate.exists(query, tableKey))// 如果存在
// {
// if (jsonObject.getString("is_delete") != null &&
// !"".equals(jsonObject.getString("is_delete"))) {
// if ("1".equals(jsonObject.getString("is_delete"))) {
// // 删除
// System.out.println("删除");
// mongoTemplate.remove(query, tableKey);
// } else if ("2".equals(jsonObject.getString("is_delete"))) {
// // 不删除,根据id判断ixID是否存在
// System.out.println("不删除");
// // mongoTemplate.remove(query, tableKey);
// // mongoTemplate.insert(jsonObject, tableKey);
// Update update = new Update();
// Iterator iterator = jsonObject.keySet().iterator();
// while (iterator.hasNext()) {
// String key = (String) iterator.next();
// String value = jsonObject.getString(key);
// update.set(underlineToCamel(key), value);
// }
// mongoTemplate.upsert(query, update, tableKey);
// }
// }
// } else {
// Set<String> keySet = jsonObject.keySet();
// Iterator it = keySet.iterator();
// List<String> keyList = new ArrayList<String>();
// while (it.hasNext()) {
// String key = (String) it.next();
// keyList.add(key);
// }
// for (int i = 0; i < keyList.size(); i++) {
// if ("id".equals(keyList.get(i))) {
// jsonObject.put("ixId", jsonObject.remove(keyList.get(i)));
// } else {
// jsonObject.put(underlineToCamel(keyList.get(i)),
// jsonObject.remove(keyList.get(i)));
// }
// }
// mongoTemplate.insert(jsonObject, tableKey);
//
// }
//
// // clazz = getClazz(underlineToCamel(key),jsonObject.getString(key));
// // System.out.println(clazz);
//
// }
//
// public static String underlineToCamel(String param) {
// if (param == null || "".equals(param.trim())) {
// return "";
// }
// int len = param.length();
// StringBuilder sb = new StringBuilder(len);
// for (int i = 0; i < len; i++) {
// char c = param.charAt(i);
// if (c == '_') {
// if (++i < len) {
// sb.append(Character.toUpperCase(param.charAt(i)));
// }
// } else {
// sb.append(c);
// }
// }
// return sb.toString();
// }
//
// Map<String, Object> getMap(String tableName, String jsonStr) {
// JSONObject jsonBean = JSONObject.parseObject(jsonStr);
// Map<String, Object> map = new HashMap<>();
// map = jsonBean;
// return map;
// }
//
// Class<?> getClazz(String clazzName, String jsonStr) {
// JSONObject jsonBean = JSONObject.parseObject(jsonStr);
// Class<?> clazz = null;
// try {
// clazz = (Class<?>) JSONObject.toJavaObject(jsonBean, Class.forName(packeg +
// clazzName));
// } catch (ClassNotFoundException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// return clazz;
// }
//
// /**
// * @param args
// * @throws SQLException
// * @throws IOException
// */
// public static void main(String[] args) throws Exception {
// new Test2();
// }
// }