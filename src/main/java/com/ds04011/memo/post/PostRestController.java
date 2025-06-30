package com.ds04011.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.memo.post.Service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	
	@PostMapping("/create")
	public Map<String, String> create(@RequestParam("title") String title
			, @RequestParam("contents") String contents
			, HttpSession session){
		
		long userId = (Long)(session.getAttribute("userId")); 
		// 오브젝트라서 형변환 해줘야함.
		
		Map<String, String> resultMap = new HashMap<>();
		if(postService.addPost(userId, title, contents)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
	}

}
