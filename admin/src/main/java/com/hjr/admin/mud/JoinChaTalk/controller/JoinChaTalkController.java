package com.hjr.admin.mud.JoinChaTalk.controller;

import com.hjr.admin.mud.JoinChaTalk.model.JoinChaTalkModel;
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
@RequestMapping("mud/joinChaTalk")
public class JoinChaTalkController extends BaseController{

	Class clazz = JoinChaTalkModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<JoinChaTalkModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<JoinChaTalkModel> add(@RequestBody JoinChaTalkModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<JoinChaTalkModel> del(@RequestBody JoinChaTalkModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<JoinChaTalkModel> edit(@RequestBody JoinChaTalkModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
