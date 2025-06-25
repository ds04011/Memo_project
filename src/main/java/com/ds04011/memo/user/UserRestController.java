package com.ds04011.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.memo.user.service.UserService;

//API 구성을 위한 컨트롤러, 모든 메서드에 responsebody 가 들어가야함,  
@RestController  // = responsebody + controller
@RequestMapping("/user")
public class UserRestController {
	
//	@Autowired
//	private UserService userService;
	
	private UserService userService;
	UserRestController(UserService userService){
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public Map<String, String> join(@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("email") String email
			, @RequestParam("name") String name) {
		
		boolean result = userService.addUser(loginId, password, email, name);
		Map<String, String> resultMap = new HashMap<>();
		
		if(result) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}

}
