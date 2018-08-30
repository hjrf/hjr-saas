package com.hjr.admin.mud.JoinRoomIte.controller;


import com.hjr.admin.mud.JoinRoomIte.model.JoinRoomIteModel;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("mud/joinRoomIte")
public class JoinRoomIteController extends BaseController{

	Class clazz = JoinRoomIteModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<JoinRoomIteModel> show(HttpServletRequest request, String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<JoinRoomIteModel> add(@RequestBody JoinRoomIteModel model, @RequestBody HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<JoinRoomIteModel> del(@RequestBody JoinRoomIteModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<JoinRoomIteModel> edit(@RequestBody JoinRoomIteModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
