package com.hjr.controller.api;

import com.hjr.controller.BaseController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/mud_submit")
public class MudSubmitController extends BaseController {

	@ResponseBody // 表示返回的是json对象
	@RequestMapping(value = "/player", method = RequestMethod.POST)
	public List<Map<String, Object>> getMapDataByPost(@RequestBody String jsonStr) {
		List<Map<String, Object>> returnMsg = new ArrayList<Map<String, Object>>();
		try {
			// JSONObject jsonObject = JSONObject.parseObject(jsonStr);

			mongoTemplate.save(jsonStr, "player");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return returnMsg;
	}
}
