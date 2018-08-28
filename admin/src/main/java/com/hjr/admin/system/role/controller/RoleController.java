package com.hjr.admin.system.role.controller;

import com.hjr.admin.system.role.model.RoleModel;
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
@RequestMapping("/system/role")
public class RoleController extends BaseController {

	Class clazz = RoleModel.class;

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public List<RoleModel> show(HttpServletRequest request, String para) {
		return show(request, para, clazz);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public List<RoleModel> add(@RequestBody RoleModel user, HttpServletRequest request) {
		add(user);
		return ref(request, clazz);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public List<RoleModel> del(@RequestBody RoleModel user, HttpServletRequest request) {
		del(user);
		return ref(request, clazz);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public List<RoleModel> edit(@RequestBody RoleModel user, HttpServletRequest request) {
		edit(user, clazz);
		return ref(request, clazz);
	}
}
