package com.oracle.tour.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Planner {
	private String m_id;		// 회원 번호
	private int    plan_no;			// 플랜 번호
	private int    c_no;			// 컨텐츠 번호
	private String p_title;			// 플랜 제목
	private String p_term;			// 플랜 기간
	private String p_content;		// 플랜 내용
	private String p_date;			// 플랜 작성일
	private int    p_day;			// 플랜 짜는 며칠 차 표시 day
	private String p_start_day;		// 플랜 여행 시작일
	private String p_end_day;		// 플랜 여행 종료일
}
