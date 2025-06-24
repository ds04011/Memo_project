package com.ds04011.memo.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Helloworld {
	
	@ResponseBody
	@GetMapping("/hello")
	public String helloWorld() {
			
		String hello = "hello world";
		
		return hello;
		
	}
	
	@GetMapping("/helloworld")
	public String hello() {
		return "hello/hello.html";
	}
	
	@GetMapping("/text1")
	@ResponseBody
	public String test() {
		return "test";
		
	}

}
