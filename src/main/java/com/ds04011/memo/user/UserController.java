package com.ds04011.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@GetMapping("/view/register")
	public String joinInput() {
		
		return "user/join.html";
	}

	@GetMapping("/view/login")
	public String loginInput() {
		
		return "user/login.html";
	}
}
