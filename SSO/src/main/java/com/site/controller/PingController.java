package com.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.framework.ResponseEntity;
import com.site.json.JsonConvertUtils;
import com.site.model.Order;
import com.site.service.OrderService;
import com.site.service.TestService;

@Controller
public class PingController {

	@Autowired
	private TestService testService;	
	@RequestMapping(value = "/ping",method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> ping(HttpServletRequest req,HttpServletResponse res) {
		return ResponseEntity.success("pong");
	}
	
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> test(HttpServletRequest req,HttpServletResponse res) {
		return ResponseEntity.success(testService.test());
	}
		
}
