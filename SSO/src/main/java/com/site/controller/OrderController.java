package com.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.site.framework.ResponseEntity;
import com.site.model.OrderReturn;
import com.site.service.OrderService;


@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping(value = "/submitorder", method=RequestMethod.POST)
	 public  @ResponseBody ResponseEntity<?> submitorder(@RequestBody String param){
		OrderReturn orderReturn = new OrderReturn();
		orderReturn = (OrderReturn) orderService.SubmitOrder(param);
		if(orderReturn.getState().equals("true")){
			return ResponseEntity.success(orderReturn);
		}
		
		return ResponseEntity.fail("202", "fail", orderReturn);
				
	}

}
