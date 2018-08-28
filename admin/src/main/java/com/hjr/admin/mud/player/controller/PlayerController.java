package com.hjr.admin.mud.player.controller;

import com.hjr.admin.mud.player.model.PlayerModel;
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
@RequestMapping("mud/player")
public class PlayerController extends BaseController{
	
	Class clazz = PlayerModel.class;
	
    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<PlayerModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }	

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<PlayerModel> add(@RequestBody PlayerModel model,HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }	 	
	
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<PlayerModel> del(@RequestBody PlayerModel model,HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }	
	
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<PlayerModel> edit(@RequestBody PlayerModel model,HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
