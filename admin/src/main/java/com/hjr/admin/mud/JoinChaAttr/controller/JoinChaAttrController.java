package com.hjr.admin.mud.JoinChaAttr.controller;


import com.hjr.admin.mud.JoinChaAttr.model.JoinChaAttrModel;
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
@RequestMapping("mud/joinChaAttr")
public class JoinChaAttrController extends BaseController{

	Class clazz = JoinChaAttrModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<JoinChaAttrModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<JoinChaAttrModel> add(@RequestBody JoinChaAttrModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<JoinChaAttrModel> del(@RequestBody JoinChaAttrModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<JoinChaAttrModel> edit(@RequestBody JoinChaAttrModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
