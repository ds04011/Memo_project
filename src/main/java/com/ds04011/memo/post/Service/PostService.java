package com.ds04011.memo.post.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ds04011.memo.common.FileManager;
import com.ds04011.memo.post.domain.Post;
import com.ds04011.memo.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	
	
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository=postRepository;
	}
	
	
	public boolean addPost(long userId, String title, String contents 
			, MultipartFile file) {
		
		
		String imagePath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder().userId(userId)
		.title(title)
		.contents(contents)
		.imagePath(imagePath)
		.build();
		
		
		
		try {
			postRepository.save(post);
			
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
	}
	
	
	public List<Post> getPostList(long userId){
		
		List<Post> postList = postRepository.findByUserId(userId);
		
		return postList;
	}
	
	public Post getPost(long id) {
		Optional<Post> opPost = postRepository.findById(id);
		
		if(opPost.isPresent()) {
			return opPost.get();
		} else {
			return null;
		}
		
	}
	
	
	public boolean updatePost(long id, String title, String contents) {
		
		Optional<Post> opPost = postRepository.findById(id);
		if(opPost.isPresent()) {
			Post post = opPost.get();
			
			post = post.toBuilder()    // 수정시킬꺼니까, 덮어씌우기 
			.title(title)
			.contents(contents)
			.build();
			
			try {
				postRepository.save(post);  // 프라이머리 키 기준 중복되는 대상을 save 에 넣으면 수정해줌.
				
			} catch (PersistenceException e) {
				return false;
			}
			
		} else {
			return false;
		}
		return true;
	}
	
	public boolean deletePost(long id) {
		Optional<Post> opPost= postRepository.findById(id);
		if(opPost.isPresent()) {
			Post post = opPost.get();
			
			FileManager.removeFile(post.getImagePath());
			postRepository.delete(post);
			return true;
			
		} else {
			return false;
		}
		
	}
	

}
