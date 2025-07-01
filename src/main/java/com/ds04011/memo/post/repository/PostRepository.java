package com.ds04011.memo.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.memo.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	
	// 추가로 필요한 쿼리 담당
	public List<Post> findByUserId(long userId);
	

}
