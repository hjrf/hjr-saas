package com.hjr.admin.mud.setting.controller;


import com.hjr.admin.mud.setting.model.SettingModel;
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
@RequestMapping("mud/setting")
public class SettingController extends BaseController{

	Class clazz = SettingModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<SettingModel> show(HttpServletRequest request, @RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<SettingModel> add(@RequestBody SettingModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<SettingModel> del(@RequestBody SettingModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<SettingModel> edit(@RequestBody SettingModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
