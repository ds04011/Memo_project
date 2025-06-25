package com.ds04011.memo.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
	
	public int insertUser(@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("email") String email
			, @Param("name") String name);
		

}
