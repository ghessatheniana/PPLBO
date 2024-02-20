package com.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
	@RequestMapping("/Welcome!")
	@ResponseBody
	public String getWelComePage() {
		return "<h2>Welcome!</h2>";
	}
}