package com.hjr.admin.system.user.controller;

import com.hjr.admin.system.user.model.UserModel;
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
@RequestMapping("/system/user")
public class UserController extends BaseController{
	
	Class clazz = UserModel.class;
	
    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<UserModel> show(HttpServletRequest request,String para){
    	return show(request,para,clazz);
    }	

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<UserModel> add(@RequestBody UserModel user,HttpServletRequest request){
    	add(user);
		return ref(request,clazz);
    }	 	
	
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<UserModel> del(@RequestBody UserModel user,HttpServletRequest request){
    	del(user);
		return ref(request,clazz);
    }	
	
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<UserModel> edit(@RequestBody UserModel user,HttpServletRequest request){
		edit(user,clazz);
		return ref(request,clazz);
    }	
}
