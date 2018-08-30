package com.hjr.admin.mud.map.controller;



import com.hjr.admin.mud.room.model.MapModel;
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
@RequestMapping("mud/map")
public class MapController extends BaseController{

	Class clazz = MapModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<MapModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<MapModel> add(@RequestBody MapModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<MapModel> del(@RequestBody MapModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<MapModel> edit(@RequestBody MapModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
