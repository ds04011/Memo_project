package com.ds04011.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//view 용 컨트롤러
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
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		// 세션에 저장한 사용자 정보 삭제
		HttpSession session = request.getSession();
		
		session.removeAttribute("userName");
		session.removeAttribute("userId");
		
		return "redirect:/user/view/login";
		
	}
}
