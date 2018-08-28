package com.hjr.admin.mud.attr.controller;

import com.hjr.admin.mud.attr.model.AttrModel;
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
@RequestMapping("mud/attr")
public class AttrController extends BaseController{

	Class clazz = AttrModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<AttrModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<AttrModel> add(@RequestBody AttrModel model,HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<AttrModel> del(@RequestBody AttrModel model,HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<AttrModel> edit(@RequestBody AttrModel model,HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
