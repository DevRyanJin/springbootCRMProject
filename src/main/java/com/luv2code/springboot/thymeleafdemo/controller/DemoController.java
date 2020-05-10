package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DemoController {

	//create a mapping for "/hello"
	@RequestMapping("/hello")
	public String hello(Model theModel) {
		
		theModel.addAttribute("theDate", new java.util.Date()); 
		return "helloworld";
	}
}
