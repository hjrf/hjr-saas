package com.hjr.admin.mud.room.controller;


import com.hjr.admin.mud.room.model.RoomModel;
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
@RequestMapping("mud/room")
public class RoomController extends BaseController{

	Class clazz = RoomModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<RoomModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<RoomModel> add(@RequestBody RoomModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<RoomModel> del(@RequestBody RoomModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<RoomModel> edit(@RequestBody RoomModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }
}
