package com.ds04011.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.memo.user.domain.User;
import com.ds04011.memo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	
	
	@PostMapping("/login")
	public Map<String, String> login(@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request){
		
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		if(user !=null) {
			resultMap.put("result",  "success");

			
			//세션 관리 객체 가져오기
			HttpSession session =  request.getSession();
			
			// 로그인이 되었다. 
			// 사용자 정보를 일부 저장해서 사용하자. 
			// 세션은 모든 요청에서 접근하고 사용할 수 있음. 
			session.setAttribute("userId" , user.getId()); // 세션의 값 저장 또한 맵처럼
			// 세션에 userId 라는 키에 값이 있으면, 로그인된 상태다. 
			session.setAttribute("userName", user.getName());
			// 필요한 정보 세션에 다 넣어버려.
			
			
			
			
		} else {
			resultMap.put("result",  "fail");
		}
		
		return resultMap;
	}
	
	
	

}
