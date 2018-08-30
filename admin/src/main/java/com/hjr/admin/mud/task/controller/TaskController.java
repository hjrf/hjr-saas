package com.hjr.admin.mud.task.controller;

import com.hjr.admin.mud.task.model.TaskModel;
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
@RequestMapping("mud/task")
public class TaskController extends BaseController{

	Class clazz = TaskModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<TaskModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<TaskModel> add(@RequestBody TaskModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<TaskModel> del(@RequestBody TaskModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<TaskModel> edit(@RequestBody TaskModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}