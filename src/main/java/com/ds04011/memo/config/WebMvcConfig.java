package com.ds04011.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ds04011.memo.common.FileManager;

@Configuration // 설정을 위한 클래스
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") // url 경로, images 하위 전부 
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/"); 
		// pc 저장소 실제 경로 
	}

}
