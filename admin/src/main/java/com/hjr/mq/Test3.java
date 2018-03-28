// package com.hjr.mq;
//
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// import com.alibaba.fastjson.JSONObject;
//
// public class Test3 {
//
// public static void main(String[] args) {
// String message = "{\"student_class_otm\":{\"parent_id\":\"7d 0
// ae6c35a1aeb3242ea2ddc009abae0\",\"student_id\":\"9
// c08260654fa27eb34114ee53602396d\",\"goods_id\":\"76e514
// e7db84d89f318dd88c9c5401cf\",\"status\":\"2\",\"is_usable\":\"1\",\"is_delete\":\"2\",\"lesson_num\":null,\"merchant_id\":\"0647e5574f
// ca4a4dbb15603ba190d9a7\"}}";
// System.out.println(message);
// JSONObject jsonObject = JSONObject.parseObject(message);
// String jsonKey = jsonObject.keySet().toString().trim().substring(1,
// jsonObject.keySet().toString().trim().length() - 1);
// jsonObject = JSONObject.parseObject(jsonObject.getString(jsonKey));
// System.out.println(jsonObject);
// List<Map> orders = new ArrayList<Map>();
// orders.add(jsonObject);
// for (Map<String, Object> order : orders) {
// // 原学生班级订单查询
// Map<String, Object> cond = new HashMap<String, Object>();
// System.out.println(order);
// }
// }
// }
