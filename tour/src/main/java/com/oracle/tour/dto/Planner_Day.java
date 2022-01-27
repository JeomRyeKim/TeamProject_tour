package com.oracle.tour.dto;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Planner_Day {
	private int p_no;			// 플랜 번호
	private String m_id;		// 회원 번호
	private int p_no_detail;	// 플랜상세 번호
	private Date p_day;		    // 선택한 기간중의 날짜 하나
	
	// Contents테이블의 c_title
	private String c_title;		// 지도에서 찍은 곳 이름을 담을 c_title
	
	private String p_content;	// 선택한 장소의 설명
	private int p_map_x;		// 위도
	private int p_map_y;		// 경도
	
	
	
}
