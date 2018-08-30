package com.hjr.admin.mud.JoinRoomCha.controller;


import com.alibaba.fastjson.JSONObject;
import com.hjr.admin.mud.JoinRoomCha.model.JoinRoomChaMapper;
import com.hjr.admin.mud.JoinRoomCha.model.JoinRoomChaModel;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("mud/joinRoomCha")
public class JoinRoomChaController extends BaseController{

	Class clazz = JoinRoomChaModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<JoinRoomChaModel> show(HttpServletRequest request,@RequestBody  String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/showRoomChaByRoomId",method=RequestMethod.POST)
    public List<JoinRoomChaModel> showRoomChaByRoomId(HttpServletRequest request,@RequestBody  String para){
        JSONObject jsonObject = JSONObject.parseObject(para);
        Map map = (Map)jsonObject;
        map = (Map) map.get("para");
        String roomId = (String) map.get("roomId");
        List<JoinRoomChaModel> joinRoomChaModelList = joinRoomChaMapper.queryForListByRoomId(roomId);
        return joinRoomChaModelList;
    }

    @RequestMapping(value="/addRoomChaByRoomAndChaId",method=RequestMethod.POST)
    public JSONObject addRoomChaByRoomAndChaId(HttpServletRequest request, @RequestBody  String para){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","1");
        jsonObject.put("msg","添加成功");
        return jsonObject;
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<JoinRoomChaModel> add(@RequestBody JoinRoomChaModel model, @RequestBody HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<JoinRoomChaModel> del(@RequestBody JoinRoomChaModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<JoinRoomChaModel> edit(@RequestBody JoinRoomChaModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
