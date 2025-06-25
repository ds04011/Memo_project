package com.ds04011.memo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds04011.memo.common.MD5HashingEncoder;
import com.ds04011.memo.user.repository.UserRepository;

@Service
public class UserService {
	
	
	
//	@Autowired
//	private UserRepository userRepository;  이렇게 하는건, 리포지토리를 여기서만 쓸수 있게됨.
	// 근데 하다보면 다른 곳에서도 리포지토리를 사용할 일이 생김. 그래서 이 방법은 지양함.
	// 아래의 방법으로 리포지토리를 주입받는다. 
	
	// 또한 다른 생성자 없이, Autowired 를 위한 생성자만 있는 경우, 어노테이션 생략 가능.
	private final UserRepository userRepository;  // final:변수 저장후 수정 불가 ,고정값,상수 선언
//	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//사용자 추가
	public boolean addUser(String loginId, String password, String email, String name) {
		
		
		// 여기서 비밀번호 해싱 해줘야 함. 근데 반복되는건, 따로 빼서 호출하는게 맞다. 
		// MD5HashingEncoder encoder = new MD5HashingEncoder(); // static 으로 바꾸면서 객체 필요 없음   
		String encodedPassword = MD5HashingEncoder.encode(password);
		
		
		int count = userRepository.insertUser(loginId, encodedPassword, email, name);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}

}
