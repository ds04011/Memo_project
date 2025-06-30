package com.ds04011.memo.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("post/view")
public class PostController {
	
	@GetMapping("/list")
	public String postList() {
		
		
		// 세션에서 정보를 뽑아 쓸때, 
		// html 에 서 사용하는 정보는 model 에 담아서 보내는게 일반적인데, 
		// 이게 워낙 많으니까, 타임리프에서 바로 사용 가능하게 함
		return"post/list";
	}
	
	@GetMapping("/create")
	public String post() {
		return"post/input";
	}

}
