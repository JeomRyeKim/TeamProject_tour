package com.oracle.tour.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/*
 * Planner Configuration 클래스
 */
@Configuration
public class LJConfig {
	
	//Entity-DTO 간 변환
	// 반복적인 object 간 변환을 간단하게 줄이고 싶을 때 사용
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    
    //HTTP hidden Method : delete, put, patch .. 등
    @Bean
    public HiddenHttpMethodFilter httpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
    
    
    

}
