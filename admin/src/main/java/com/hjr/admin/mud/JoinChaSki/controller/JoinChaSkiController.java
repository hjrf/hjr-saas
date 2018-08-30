package com.hjr.admin.mud.JoinChaSki.controller;

import com.hjr.admin.mud.JoinChaSki.model.JoinChaSkiModel;
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
@RequestMapping("mud/joinChaSki")
public class JoinChaSkiController extends BaseController{

	Class clazz = JoinChaSkiModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<JoinChaSkiModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<JoinChaSkiModel> add(@RequestBody JoinChaSkiModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<JoinChaSkiModel> del(@RequestBody JoinChaSkiModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<JoinChaSkiModel> edit(@RequestBody JoinChaSkiModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
