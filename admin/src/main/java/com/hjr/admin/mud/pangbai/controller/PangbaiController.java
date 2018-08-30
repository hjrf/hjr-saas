package com.hjr.admin.mud.pangbai.controller;

import com.hjr.admin.mud.pangbai.model.PangbaiModel;
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
@RequestMapping("mud/pangbai")
public class PangbaiController extends BaseController{

	Class clazz = PangbaiModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<PangbaiModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<PangbaiModel> add(@RequestBody PangbaiModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<PangbaiModel> del(@RequestBody PangbaiModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<PangbaiModel> edit(@RequestBody PangbaiModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
