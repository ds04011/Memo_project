package com.ds04011.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ds04011.memo.post.Service.PostService;
import com.ds04011.memo.post.domain.Post;

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
			, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile 
			, HttpSession session){
		
		// 이렇게 해버리면, 이미지 파일이 필수가 됨.  해서 required=false 가 들어가야 한다. 
		// 이미지 파일이 없으면 null 로 전달이 됨. 그러니 null 을 다룰 수 있도로 코드를 짜야함.
		
		
		long userId = (Long)(session.getAttribute("userId")); 
		// 오브젝트라서 형변환 해줘야함.
		
		// 로그인 안되서, userId 를 세션에서 못가져오는 경우는 어떤 상황이 발생하지?
		
		Map<String, String> resultMap = new HashMap<>();
		if(postService.addPost(userId, title, contents, imageFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
	}
	
	@PutMapping("/update")
	public Map<String, String> updatePost(@RequestParam("id") long id, 
			@RequestParam("title") String title, 
			@RequestParam("contents") String contents) {
		
		Map<String, String> resultMap = new HashMap<>();
		if(postService.updatePost(id, title, contents)) {
			resultMap.put("result",  "success");
		} else {
			resultMap.put("result",  "fail");
		}
		return resultMap;
		
	}
	
	//삭제 할 때에는, 그 메모에 속해있는 모든 것을 제거해야함, 
	// 이미지 폴더 밑 댓글, 등등
	
	@DeleteMapping("/delete")
	public boolean deletePost(@RequestParam("id") long id) {
		boolean result = postService.deletePost(id);
		return result;
		
	}

}
