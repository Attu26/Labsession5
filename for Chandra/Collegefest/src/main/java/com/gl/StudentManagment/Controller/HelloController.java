package com.gl.StudentManagment.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/")
public class HelloController {
	
	@RequestMapping("/hello")
	public String showMainPage() {
		return "demo";
		
				
	}

}
