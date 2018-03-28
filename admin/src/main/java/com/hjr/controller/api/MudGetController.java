package com.hjr.controller.api;

import com.hjr.controller.BaseController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/mud_get")
public class MudGetController extends BaseController {

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/player", method = RequestMethod.POST)
	public String getMapDataByPost() {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		String player = null;
		try {
			// JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(1));
			List<Map> players = mongoTemplate.find(query, Map.class, "player");

			for (Map m : players) {
				player = (String) m.get("player");
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return player;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/player_by_room_id", method = RequestMethod.POST)
	public List<Map<String, Object>> getPlayerByRoomId(int roomId) {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		String player = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("chaCurRoomId").is(roomId));
			List<Map> players = mongoTemplate.find(query, Map.class, "player");

			for (Map m : players) {
				returnMsg.add(m);
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return returnMsg;
	}


	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/getFightSortPlayer", method = RequestMethod.POST)
	public List<Map<String, Object>> getFightSortPlayer(int limit) {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		String player = null;
		try {
			Query query = new Query();
			query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"chaFightValue")));
			query.limit(limit);
			List<Map> players = mongoTemplate.find(query, Map.class, "player");
			for (Map m : players) {
				returnMsg.add(m);
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return returnMsg;
	}

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/getMoneySortPlayer", method = RequestMethod.POST)
	public List<Map<String, Object>> getMoneySortPlayer(int limit) {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		String player = null;
		try {
			Query query = new Query();
			query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"chaMoney")));
			query.limit(limit);
			List<Map> players = mongoTemplate.find(query, Map.class, "player");
			for (Map m : players) {
				returnMsg.add(m);
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return returnMsg;
	}

}
