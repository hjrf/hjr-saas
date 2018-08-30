package com.hjr.admin.mud.JoinIteSki.controller;

import com.hjr.admin.mud.JoinIteSki.model.JoinIteSkiModel;
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
@RequestMapping("mud/joinIteSki")
public class JoinIteSkiController extends BaseController{

	Class clazz = JoinIteSkiModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<JoinIteSkiModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<JoinIteSkiModel> add(@RequestBody JoinIteSkiModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<JoinIteSkiModel> del(@RequestBody JoinIteSkiModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<JoinIteSkiModel> edit(@RequestBody JoinIteSkiModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
