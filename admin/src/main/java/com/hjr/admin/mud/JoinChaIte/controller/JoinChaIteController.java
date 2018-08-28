package com.hjr.admin.mud.JoinRoomCha.controller;


import com.hjr.admin.mud.JoinChaIte.model.JoinChaIteModel;
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
@RequestMapping("mud/joinChaIte")
public class JoinChaIteController extends BaseController{

	Class clazz = JoinChaIteModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<JoinChaIteModel> show(HttpServletRequest request, String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<JoinChaIteModel> add(@RequestBody JoinChaIteModel model, @RequestBody HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<JoinChaIteModel> del(@RequestBody JoinChaIteModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<JoinChaIteModel> edit(@RequestBody JoinChaIteModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
