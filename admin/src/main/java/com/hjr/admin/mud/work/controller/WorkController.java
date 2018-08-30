package com.hjr.admin.mud.work.controller;

import com.hjr.admin.mud.work.model.WorkModel;
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
@RequestMapping("mud/work")
public class WorkController extends BaseController{

	Class clazz = WorkModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<WorkModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<WorkModel> add(@RequestBody WorkModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<WorkModel> del(@RequestBody WorkModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<WorkModel> edit(@RequestBody WorkModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
