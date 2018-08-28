package com.hjr.admin.system.login.controller;

import com.hjr.admin.system.user.model.UserModel;
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
@RequestMapping("/system/login")
public class LoginController extends BaseController{
    @RequestMapping(value="/check",method=RequestMethod.POST)
    public void check(@RequestBody UserModel user,HttpServletRequest request){
    	System.out.println("a");
    }
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<UserModel> add(@RequestBody UserModel user,HttpServletRequest request){
    	add(user);
		return ref(request,UserModel.class);
    }	 	
}
	
	
