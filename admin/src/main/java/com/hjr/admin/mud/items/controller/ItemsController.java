package com.hjr.admin.mud.items.controller;

import com.hjr.admin.mud.items.model.ItemsModel;
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
@RequestMapping("mud/items")
public class ItemsController extends BaseController{

	Class clazz = ItemsModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<ItemsModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<ItemsModel> add(@RequestBody ItemsModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<ItemsModel> del(@RequestBody ItemsModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<ItemsModel> edit(@RequestBody ItemsModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
