package com.hjr.admin.mud.character.controller;

import com.hjr.admin.mud.character.model.CharacterModel;
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
@RequestMapping("mud/character")
public class CharacterController extends BaseController{

	Class clazz = CharacterModel.class;

    @RequestMapping(value="/show",method=RequestMethod.POST)
    public List<CharacterModel> show(HttpServletRequest request,@RequestBody String para){
    	return show(request,para,clazz);
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<CharacterModel> add(@RequestBody CharacterModel model, HttpServletRequest request){
    	add(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/del",method=RequestMethod.POST)
    public List<CharacterModel> del(@RequestBody CharacterModel model, HttpServletRequest request){
    	del(model);
		return ref(request,clazz);
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    public List<CharacterModel> edit(@RequestBody CharacterModel model, HttpServletRequest request){
		edit(model,clazz);
		return ref(request,clazz);
    }	
}
