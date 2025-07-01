package com.ds04011.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	// 다른 곳에서 사용할 경로니까, 퍼블릭으로 해서 써먹자. 
	public static final String FILE_UPLOAD_PATH = "D:\\yhw\\SpringProject\\spring-tools-for-eclipse-4.31.0.RELEASE-e4.36.0-win32.win32.x86_64\\upload\\memo"; 
	
	// 파일 저장 기능
	// 저장된 파일을 클라가 접근할 수 있는 url 경로로 리턴
	public static String saveFile(long userId ,MultipartFile file) {
	
		if(file == null) {
			return null;
		}
		
		// 파일 이름 유지 
		// 폴더 (디렉토리) 만들어서 저장
		// 사용자 정보를 디렉토리 네이밍이 사용
		// 시간 정보 포함 디테일하게 아니라 대충 나눈 시간. 
		// UNIX TIME  :  1970 1 1 0 0 0  부터 흐른 시간을 milli sec 단위로 표현함.
		// 종합, 폴더이름 = 2_889939208            userId + UNIX TIME
		// 같은 유저가 1/1000 초 안에 똑같은 파일을 생성하지 않는이상, 겹치는 폴더는 없음
		
		String directoryName =  "/" + userId + "_" + System.currentTimeMillis();       
		
		// 폴더(디렉토리) 만들기
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		// 폴더 만들기 
		File directory = new File(directoryPath);
		
		// 경로가 잘못되면, 작동안함
		// 파일 저장 공간 모자라면, 작동안함  
		// 등등 예외처리 에러처리가 상당히 디테일함. 
		
		if(!directory.mkdir()) {
			//생성 실패
			return null;
		} 
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename(); 
		
		try {
			byte[] bytes = file.getBytes();
			
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
			
		} catch (IOException e) {
			//파일 저장 실패
			e.printStackTrace();
			return null;
		}
		
		//실제 파일 저장 위치와 url 경로 매칭 규칙 
		// D:\\yhw\\SpringProject\\spring-tools-for-eclipse-4.31.0.RELEASE-e4.36.0-win32.win32.x86_64\\upload\\memo
		// /images/2_983745223/test.png
		return "/images" + directoryName + "/" + file.getOriginalFilename();
		
	}

}
