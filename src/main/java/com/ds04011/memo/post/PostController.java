package com.ds04011.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ds04011.memo.post.Service.PostService;
import com.ds04011.memo.post.domain.Post;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("post/view")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	
	@GetMapping("/list")
	public String postList(Model model
			, HttpSession session) {
		
		
		// 세션에서 정보를 뽑아 쓸때, 
		// html 에 서 사용하는 정보는 model 에 담아서 보내는게 일반적인데, 
		// 이게 워낙 많으니까, 타임리프에서 바로 사용 가능하게 함
		
		List<Post> postList = postService.getPostList((Long)session.getAttribute("userId"));
		model.addAttribute("memoList", postList);
		
		return"post/list";
	}
	
	@GetMapping("/create")
	public String post() {
		return"post/input";
	}
	
	
	@GetMapping("/detail")
	public String postDetail(@RequestParam("id") long id
			, Model model) {
			
		
		Post post = postService.getPost(id);
		model.addAttribute("result", post);
		
		
		return "post/detail";
	}
	

}
